//
//  ChefListViewController.swift
//  Chef_hire
//
//  Created by Reelover reelover on 04/08/18.
//  Copyright © 2018 Reelover. All rights reserved.
//

import UIKit
import Alamofire
import SVProgressHUD

class ChefListViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    
    
    
    var data: [[String: Any]] = [[String: Any]]()
    
    var chef_name = [String]()
    var chef_phone = [String]()
    var chef_address = [String]()
    var chef_rating = [String]()
    var chef_image = [String]()
    var chef_email = [String]()
    var chef_id = [String]()
    
    var c_name:String!
    var c_image:String!
    var c_address:String!
    var c_rating:String!
    var c_phone:String!
    var c_email:String!
    var c_id:String!
    var choose_no : String!
    
    var pass_c_name:String!
    var pass_c_phone:String!
    var pass_c_image:String!
    var pass_c_email:String!
    var pass_c_id:String!
    var pass_c_address:String!
    var pass_c_rating:String!
    
    var pass_f_name:String!
    var pass_f_price:String!
    var pass_f_image:String!
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return data.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "cell") as! ChefListTableViewCell
        
        cell.name.text = chef_name[indexPath.row]
        cell.distance.text = chef_address[indexPath.row]
        cell.rating.text = chef_rating[indexPath.row]
        
        
        Alamofire.request(chef_image[indexPath.row])
            .response { response in
                guard let imageData = response.data else {
                    print("Could not get image from image URL returned in search results")
                    
                    return
                }
                cell.chef_pic.image = UIImage(data: imageData)
        }
        
        
        return cell
    }
    
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        
        return 100;
        
    }
    
    
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
        tableview.deselectRow(at: indexPath, animated: true)
        
        
        pass_c_name = chef_name[indexPath.row]
        pass_c_image = chef_image[indexPath.row]
         pass_c_phone = chef_phone[indexPath.row]
        pass_c_email = chef_email[indexPath.row]
        pass_c_id = chef_id[indexPath.row]
        pass_c_address = chef_address[indexPath.row]
        pass_c_rating = chef_rating[indexPath.row]
        
        pass_f_price = food_price
        pass_f_image = food_image
        pass_f_name = food_name
        
        performSegue(withIdentifier: "checkout", sender: self)
        
        
    }
    
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        
        if (segue.identifier == "checkout") {
            
            let seconfd_vc : CheckoutViewController = segue.destination as! CheckoutViewController
            
            seconfd_vc.get_chef_name = pass_c_name
            seconfd_vc.get_chef_image = pass_c_image
            seconfd_vc.get_chef_phone = pass_c_phone
            seconfd_vc.get_chef_email = pass_c_email
            seconfd_vc.get_chef_id = pass_c_id
            seconfd_vc.get_chef_address = pass_c_address
             seconfd_vc.get_chef_rating = pass_c_rating
            
             seconfd_vc.get_food_name = pass_f_name
             seconfd_vc.get_food_image = pass_f_image
             seconfd_vc.get_food_price = pass_f_price
             seconfd_vc.get_choose = choose_no
        }
        
        
    }
    
    

    @IBOutlet weak var tableview: UITableView!
    
    var food_name: String = ""
    var food_price: String = ""
    var food_image: String = ""
    var meal_id: String = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        tableview.dataSource = self
        tableview.delegate = self
        open_popup_Screen()

        // Do any additional setup after loading the view.
    }
    
    func open_popup_Screen() {
        
        
        let alert = UIAlertController(title: "Choose",
                                      message: "Hire Chef or Food Order",
                                      preferredStyle: .alert)
        let action1 = UIAlertAction(title: "Hire Chef", style: .default, handler: { (action) -> Void in
            self.choose_no = "1"
            self.get_chefList(choose: "1", meal_id: self.meal_id)
            
        })
        
        let action2 = UIAlertAction(title: "Food Order", style: .default, handler: { (action) -> Void in
            self.choose_no = "0"
            self.get_chefList(choose: "0", meal_id: self.meal_id)
        })
        
    
        // Restyle the view of the Alert
//        alert.view.tintColor = UIColor.blue  // change text color of the buttons
//        alert.view.backgroundColor = UIColor.white  // change background color
//        alert.view.layer.cornerRadius = 25   // change corner radius
        
        // Add action buttons and present the Alert
        alert.addAction(action1)
        alert.addAction(action2)

        present(alert, animated: true, completion: nil)
        

     
    }
    
    
    func show_msg(){
        
        let alert = UIAlertController(title: "Opps!",
                                      message: "Chef is not availabel on this Option",
                                      preferredStyle: .alert)
        let action1 = UIAlertAction(title: "Change Option", style: .default, handler: { (action) -> Void in
          self.open_popup_Screen()
            
        })
        
        let action2 = UIAlertAction(title: "Cancel", style: .default, handler: { (action) -> Void in
          
        })
        
        
        // Restyle the view of the Alert
        //        alert.view.tintColor = UIColor.blue  // change text color of the buttons
        //        alert.view.backgroundColor = UIColor.white  // change background color
        //        alert.view.layer.cornerRadius = 25   // change corner radius
        
        // Add action buttons and present the Alert
        alert.addAction(action1)
        alert.addAction(action2)
        
        present(alert, animated: true, completion: nil)
        
    }

    func get_chefList(choose: String, meal_id: String) {

        SVProgressHUD.show()
        
        print("get chef details api calls : ");
        
        let parameters : Parameters = [
            "choose": choose,
            "meal_id":meal_id
            
        ]
        
        let urlString = "http://www.zeenarch.com/chef_hire/chef_list.php"
        
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
                            let phone = (these["phone"] as? String) ?? " "
                            let address = (these["address"] as? String) ?? " "
                            let rating = (these["rating"] as? String) ?? " "
                            let image = (these["image"] as? String) ?? " "
                             let email = (these["email"] as? String) ?? " "
                             let id = (these["chef_id"] as? String) ?? " "
                            // let status = (these["status"] as? String) ?? " "
                            
                            self.chef_name.append(name)
                            self.chef_phone.append(phone)
                            self.chef_address.append(address)
                            self.chef_image.append(image)
                            self.chef_rating.append(rating)
                            self.chef_email.append(email)
                            self.chef_id.append(id)
                            
                        }
                        
                    }
                    
                }
                
                self.tableview.reloadData()

            case .failure(let error):
                print(error)
                
                
                self.show_msg()
                
            }
            SVProgressHUD.dismiss()
        }
   
    }

}
