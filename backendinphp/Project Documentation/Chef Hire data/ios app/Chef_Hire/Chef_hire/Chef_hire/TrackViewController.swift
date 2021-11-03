//
//  TrackViewController.swift
//  Chef_hire
//
//  Created by Reelover reelover on 05/08/18.
//  Copyright © 2018 Reelover. All rights reserved.
//

import UIKit
import Alamofire
import SVProgressHUD

class TrackViewController: UIViewController {
    @IBOutlet weak var order_id: UILabel!
    @IBOutlet weak var chef_info: UILabel!
    @IBOutlet weak var chef_image: UIImageView!
    
    @IBOutlet weak var status: UILabel!
    @IBOutlet weak var cancel_btn: UIButton!
    @IBOutlet weak var food_name: UILabel!
    @IBOutlet weak var food_price: UILabel!
    @IBOutlet weak var address: UILabel!
  
    
    var get_order_no: String = ""
     var get_chef_id: String = ""
    
    
    
     var current_rating: String = "0"
    
    override func viewDidLoad() {
        super.viewDidLoad()
       makeroundbtn()
        
        cancel_btn.backgroundColor = UIColor.gray
        cancel_btn.isEnabled = false
        
       print(get_order_no)
       get_order_details(order_id: get_order_no)
        
       
        // Do any additional setup after loading the view.
    }
    
    func makeroundbtn(){
        
        cancel_btn.layer.cornerRadius = 5
        cancel_btn.clipsToBounds = true
    }
    
    
    func get_order_details(order_id : String)  {
        
        
        SVProgressHUD.show()
        
        print("order details api calls : ");
        
        let parameters : Parameters = [
            
            "order_id": order_id
        ]
        
        
        let urlString = "http://www.zeenarch.com/chef_hire/order_details.php"
        
        
        Alamofire.request(urlString, method: .post, parameters: parameters, encoding:  URLEncoding.httpBody).responseJSON{ response in
            
            switch response.result {
            case .success:
                debugPrint(response)
                
                
                if let JSON = response.result.value{
                    
                    var jsonobject = JSON as! [String: AnyObject]
                    if var jsonarray = jsonobject["data"] as! [[String:Any]]?{
                        
                        var these = jsonarray[0]
                  
                        
                        let order_id_no = (these["order_id"] as? String) ?? " "
                        
                       // let user_name = (these["user_name"] as? String) ?? " "
                       // let user_phone = (these["user_phone"] as? String) ?? " "
                        let user_address = (these["user_address"] as? String) ?? " "
                        
                        let chef_name = (these["chef_name"] as? String) ?? " "
                        let chef_phone = (these["chef_phone"] as? String) ?? " "
                        let chef_id = (these["chef_id"] as? String) ?? " "
                        
                        let f_name = (these["food_name"] as? String) ?? " "
                        let f_price = (these["food_price"] as? String) ?? " "
                        let meals_no = (these["meals_no"] as? String) ?? " "
                        let f_status = (these["status"] as? String) ?? " "
                      //  let choose = (these["choose"] as? String) ?? " "
                        self.chef_info.text =  "\(chef_name) will arrive your location if any you can call on \(chef_phone) this number."
                        
                        self.order_id.text = " Order Id : \(order_id_no)"
                        self.food_name.text = "\(f_name)"
                        self.food_price.text = "\(meals_no) meals Price: \(f_price)/- "
                        
                       
                        
                        if(f_status == "delivered" ){
                            
                            self.give_rating()
                        }
                       
                             self.status.text = "Order Placed, Current Status: \(f_status)"
                        
           
                        
                        self.address.text = user_address
                        self.get_chef_details(chef_id: chef_id)
                        
                        if(f_status == "waiting"){
                            self.cancel_btn.backgroundColor = UIColor.red
                            self.cancel_btn.isEnabled = true
                        }
                      
                        
                       
                    }
                }
                
                
            case .failure(let error):
                print(error)
            }
            
            
        }
        
        
        
    }
    
    
    
    
    
    func give_rating(){
        
        
        
        let alert = UIAlertController(title: "Rating",
                                      message: "Give rating to the chef",
                                      preferredStyle: .alert)
        
        let action1 = UIAlertAction(title: "Bad", style: .default, handler: { (action) -> Void in
          
            let rate: Double = (Double(self.current_rating)! + 1 ) / 2
            
            let final_rate = String(format: "%.1f", rate)
            
            self.call_rating_api(rating: "\(final_rate)", chef_id: self.get_chef_id)
          
        })
        
        let action2 = UIAlertAction(title: "Good", style: .default, handler: { (action) -> Void in
           
            let rate: Double = (Double(self.current_rating)! + 3 ) / 2
            
            let final_rate = String(format: "%.1f", rate)
            
            self.call_rating_api(rating: "\(final_rate)", chef_id: self.get_chef_id)
         
        })
        
        let action3 = UIAlertAction(title: "Excellent", style: .default, handler: { (action) -> Void in
            let rate: Double = (Double(self.current_rating)! + 5 ) / 2
            let final_rate = String(format: "%.1f", rate)

            
            self.call_rating_api(rating: "\(final_rate)", chef_id: self.get_chef_id)
          
        
            
        })
        let action4 = UIAlertAction(title: "Cancel", style: .destructive, handler: { (action) -> Void in
            
            
        })

        alert.addAction(action1)
        alert.addAction(action2)
        alert.addAction(action3)
         alert.addAction(action4)
        present(alert, animated: true, completion: nil)
    }
    
    
    
    
    func call_rating_api(rating: String, chef_id: String){
        
        
        SVProgressHUD.show()
        
        print("chef rating api calls : ");
        
        let parameters : Parameters = [
            "rating" :rating,
            "chef_id": chef_id
        ]
        
        
        let urlString = "http://www.zeenarch.com/chef_hire/chef_rating.php"
        
        
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
    
    
    
    
    @IBAction func Cancel_order(_ sender: Any) {
        
        show_alert_msg()
        
    }
    
    func show_alert_msg(){
        
        //1. Create the alert controller.
        let alert = UIAlertController(title: "Alert", message: "Are you sure to cancel your Order", preferredStyle: .alert)
        // 3. Grab the value from the text field, and print it when the user clicks OK.
        alert.addAction(UIAlertAction(title: "OK", style: .default, handler: { (_) in
            
            self.cancel_order_api(order_id:self.get_order_no)
            
        }))
        alert.addAction(UIAlertAction(title: "CANCEL", style: .default, handler: { (_) in
            
            
            
        }))
        // 4. Present the alert.
        self.present(alert, animated: true, completion: nil)
    }
    
    func cancel_order_api(order_id: String){
        SVProgressHUD.show()
        
        print("save data api calls : ");
        
        let parameters : Parameters = [
            "order_id" :order_id
            
        ]
        
        
        let urlString = "http://www.zeenarch.com/chef_hire/order_cancel.php"
        
        
        Alamofire.request(urlString, method: .post, parameters: parameters, encoding:  URLEncoding.httpBody).responseJSON{ response in
            
            switch response.result {
            case .success:
                debugPrint(response)
                
                self.cancel_btn.backgroundColor = UIColor.gray
                self.cancel_btn.isEnabled = false
                self.cancel_btn.setTitle("Order Canceled", for: .normal)
                
                SVProgressHUD.dismiss()
                
                
            case .failure(let error):
                print(error)
            }
            
            
        }
        
    }
    
    
    func get_chef_details(chef_id : String){
        
        print("chef details api calls : ");
        
        let parameters : Parameters = [
            
            "chef_id": chef_id
        ]
        
        
        let urlString = "http://www.zeenarch.com/chef_hire/chef_details.php"
        
        
        Alamofire.request(urlString, method: .post, parameters: parameters, encoding:  URLEncoding.httpBody).responseJSON{ response in
            
            switch response.result {
            case .success:
                debugPrint(response)
                
                
                if let JSON = response.result.value{
                    
                    var jsonobject = JSON as! [String: AnyObject]
                    if var jsonarray = jsonobject["data"] as! [[String:Any]]?{
                        
                        var these = jsonarray[0]
                        
                     //   let name = (these["name"] as? String) ?? " "
                      //  let phone = (these["phone"] as? String) ?? " "
                      //  let address = (these["address"] as? String) ?? " "
                        
                       let rating = (these["rating"] as? String) ?? " "
                        let image = (these["image"] as? String) ?? " "
                      //  let email = (these["address"] as? String) ?? " "
                        
                        self.current_rating = rating
                        
                        Alamofire.request(image)
                            .response { response in
                                guard let imageData = response.data else {
                                    print("Could not get image from image URL returned in search results")
                                    
                                    return
                                }
                                self.chef_image.image = UIImage(data: imageData)
                        }
                        
                        
                    }
                    
                         SVProgressHUD.dismiss()
                }
                
                
        
                
            case .failure(let error):
                print(error)
            }
            
            
        }
        
        
        
    }



    


}
