//
//  ChooseFoodViewController.swift
//  Chef_hire
//
//  Created by Reelover reelover on 10/08/18.
//  Copyright © 2018 Reelover. All rights reserved.
//

import UIKit
import Alamofire
import SVProgressHUD
class ChooseFoodViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
  
    
    var data: [[String: Any]] = [[String: Any]]()

    
    var food_name = [String]()
    var food_image = [String]()
    var food_price = [String]()
    var food_items = [String]()
    var meal_id = [String]()
    var check_meal_id = [String]()
    
    var f_name:String!
    var f_image:String!
    var f_price:String!
    var f_items:String!
    var f_id:String!
    
   
    var i = 0
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return data.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableview.dequeueReusableCell(withIdentifier: "cell") as! ChooseFoodTableViewCell
        
        
        cell.food_name.text = food_name[indexPath.row]
        cell.food_price.text = food_price[indexPath.row]
        cell.food_items.text = food_items[indexPath.row]
        
        
         cell.food_select.setOn(false, animated: true)
        
      
        if(check_meal_id.count > 0){
            if(meal_id[indexPath.row] == check_meal_id[i] ){
                cell.food_select.setOn(true, animated: true)

                if(i < check_meal_id.count - 1){
                    i = i+1
                }
                print(i)
            }
            else{
                cell.food_select.setOn(false, animated: true)

            }

        }
        
        
        cell.onSwitchTapped = {
            
            print("on clicked")
         
            
            if( cell.food_select.isOn){
                
                
                self.unselect_foods(meal_id: self.meal_id[indexPath.row])
                
                cell.food_select.setOn(false, animated: true)
            }else{
                
                 self.choose_foods(meal_id: self.meal_id[indexPath.row])
              
                cell.food_select.setOn(true, animated: true)
            }
            
        }
        
        
        
        
        
        Alamofire.request(food_image[indexPath.row])
            .response { response in
                guard let imageData = response.data else {
                    print("Could not get image from image URL returned in search results")
                    
                    return
                }
                cell.food_image.image = UIImage(data: imageData)
        }
        
        return cell
    }
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        
        return 100;
        
    }
    
 
 
    
    
    func unselect_foods(meal_id: String){
        
        
        SVProgressHUD.show()
        
        
        let userdefault = UserDefaults.standard
        let chef_id =  userdefault.string(forKey: "chef_id")
        
        
        print("get check details api calls : ");
        
        let parameters : Parameters = [
            "chef_id":chef_id ?? " ",
            "meal_id": meal_id
        ]
        
        
        let urlString = "http://www.zeenarch.com/chef_hire/unselect_foods.php"
        
        Alamofire.request(urlString, method: .post, parameters: parameters, encoding:  URLEncoding.httpBody).responseJSON{ response in
            
            switch response.result {
            case .success:
                debugPrint(response)
                
                   SVProgressHUD.dismiss()
            case .failure(let error):
                print(error)
            }
            
         
            
            
        }
        
    }
    
    
    
    
    func choose_foods(meal_id: String){
        
        
        SVProgressHUD.show()
        
        
        let userdefault = UserDefaults.standard
        let chef_id =  userdefault.string(forKey: "chef_id")
        
        
        print("get check details api calls : ");
        
        let parameters : Parameters = [
            "chef_id":chef_id ?? " ",
            "meal_id": meal_id
        ]
        
        
        let urlString = "http://www.zeenarch.com/chef_hire/choose_foods.php"
        
        Alamofire.request(urlString, method: .post, parameters: parameters, encoding:  URLEncoding.httpBody).responseJSON{ response in
            
            switch response.result {
            case .success:
                debugPrint(response)
                
                 SVProgressHUD.dismiss()
            case .failure(let error):
                print(error)
            }
            
           
      
            
        }
        
    }
    
    

    @IBOutlet weak var tableview: UITableView!
    override func viewDidLoad() {
        super.viewDidLoad()

        tableview.dataSource = self
        tableview.delegate = self
        
        get_food_details();
        // Do any additional setup after loading the view.
    }

    
    func check_food_status(){
        
        
        
        SVProgressHUD.show()
        
        
        let userdefault = UserDefaults.standard
        let chef_id =  userdefault.string(forKey: "chef_id")
        
        
        print("get check details api calls : ");
        
        let parameters : Parameters = [
            "chef_id":chef_id ?? " "
        ]
        
        
        let urlString = "http://www.zeenarch.com/chef_hire/check_foods_status.php"
        
        
        Alamofire.request(urlString, method: .post, parameters: parameters, encoding:  URLEncoding.httpBody).responseJSON{ response in
            
            switch response.result {
            case .success:
                debugPrint(response)
                
                
                if let JSON = response.result.value{
                    
                    var jsonobject = JSON as! [String: AnyObject]
                    if var jsonarray = jsonobject["data"] as! [[String:Any]]?{
                  
                        
                        for data in 0..<jsonarray.count{
                            
                            var these = jsonarray[data]
                            let meal_id = (these["meal_id"] as? String) ?? " "
                            
                            
                            self.check_meal_id.append(meal_id)
                            
                        }
                        
                        print(self.check_meal_id)
                        
                    }
                    
                }
                
                
            case .failure(let error):
                print(error)
            }
            
            SVProgressHUD.dismiss()
          self.tableview.reloadData()
            
        }
        
        
    }
    
 
    
    func get_food_details()  {
        
        SVProgressHUD.show()
        
        print("get food details api calls : ");
        
        let parameters : Parameters = [
            :
        ]
        
        
        let urlString = "http://www.zeenarch.com/chef_hire/show_all_foods.php"
        
        
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
                        
                        
                        self.check_food_status()
                        
                    }
                    
                }
                
                SVProgressHUD.dismiss()
                
            case .failure(let error):
                print(error)
            }
            
            
        }
        
        
    }
    
}
