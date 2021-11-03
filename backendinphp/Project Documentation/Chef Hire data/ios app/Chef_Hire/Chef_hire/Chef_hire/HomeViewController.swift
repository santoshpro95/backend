//
//  HomeViewController.swift
//  Chef_hire
//
//  Created by Reelover reelover on 04/08/18.
//  Copyright © 2018 Reelover. All rights reserved.
//

import UIKit
import Alamofire
import SVProgressHUD

class HomeViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
        var data: [[String: Any]] = [[String: Any]]()
    
    var food_name = [String]()
    var food_image = [String]()
    var food_price = [String]()
      var food_items = [String]()
     var meal_id = [String]()
    
    
    var f_name:String!
    var f_image:String!
    var f_price:String!
      var f_items:String!
       var f_id:String!
    
     var pass_f_name:String!
     var pass_f_price:String!
      var pass_f_image:String!
         var pass_f_id:String!
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return data.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        
        let cell = tableview.dequeueReusableCell(withIdentifier: "cell") as! HomeTableViewCell
        
        
        cell.food_name.text = food_name[indexPath.row]
        cell.food_price.text = food_price[indexPath.row]
        cell.food_items.text = food_items[indexPath.row]
        
        Alamofire.request(food_image[indexPath.row])
            .response { response in
                guard let imageData = response.data else {
                    print("Could not get image from image URL returned in search results")
                    
                    return
                }
                cell.image_url.image = UIImage(data: imageData)
        }
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        
            return 150;
      
    }
    
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
        tableview.deselectRow(at: indexPath, animated: true)
        
        
        pass_f_name = food_name[indexPath.row]
        pass_f_price = food_price[indexPath.row]
        pass_f_image = food_image[indexPath.row]
        pass_f_id = meal_id[indexPath.row]
        
        performSegue(withIdentifier: "chef_list", sender: self)
        
       
    }
    
    
        override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
            
            if (segue.identifier == "chef_list") {
                
               let seconfd_vc : ChefListViewController = segue.destination as! ChefListViewController
                
                seconfd_vc.food_name = pass_f_name
                seconfd_vc.food_price = pass_f_price
                seconfd_vc.food_image = pass_f_image
                seconfd_vc.meal_id = pass_f_id
                
            }
 
            
       }
    
    
    
    @IBOutlet weak var tableview: UITableView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        tableview.delegate = self
        tableview.dataSource = self
        
        get_food_details();

        // Do any additional setup after loading the view.
    }
    
    
    func get_food_details()  {
        
        SVProgressHUD.show()
        
        print("get food details api calls : ");
        
        let parameters : Parameters = [
            :
        ]
        
        
        let urlString = "http://www.zeenarch.com/chef_hire/food_details.php"
        
        
        Alamofire.request(urlString, method: .post, parameters: parameters, encoding:  URLEncoding.httpBody).responseJSON{ response in
            
            switch response.result {
            case .success:
                debugPrint(response)
                
                
                if let JSON = response.result.value{
                    
                    var jsonobject = JSON as! [String: AnyObject]
                    if var jsonarray = jsonobject["data"] as! [[String:Any]]?{
                        
                        for i in 0..<jsonarray.count {
                            self.data.append(jsonarray[i])
                        }
                        
                        for data in 0..<jsonarray.count{
                            
                            var these = jsonarray[data]
                            let name = (these["name"] as? String) ?? " "
                            let price = (these["price"] as? String) ?? " "
                            let image = (these["image"] as? String) ?? " "
                               let items = (these["items"] as? String) ?? " "
                            let meal_id = (these["meal_id"] as? String) ?? " "
                            
                            
                            self.food_name.append(name)
                            self.food_price.append(price)
                            self.food_image.append(image)
                             self.food_items.append(items)
                               self.meal_id.append(meal_id)
                        }
                        
                    }
                    
                }
                
                   self.tableview.reloadData()
      
                SVProgressHUD.dismiss()

            case .failure(let error):
                print(error)
            }
            
            
        }
        
        
    }
    


}
