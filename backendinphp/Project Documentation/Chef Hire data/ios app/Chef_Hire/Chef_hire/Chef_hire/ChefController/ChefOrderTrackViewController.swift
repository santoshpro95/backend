//
//  ChefOrderTrackViewController.swift
//  Chef_hire
//
//  Created by Reelover reelover on 07/08/18.
//  Copyright © 2018 Reelover. All rights reserved.
//

import UIKit
import Alamofire
import SVProgressHUD

class ChefOrderTrackViewController: UIViewController {
    var get_order_no: String = ""
    
    @IBOutlet weak var order_id: UILabel!
    @IBOutlet weak var food_name: UILabel!
    @IBOutlet weak var food_info: UILabel!
    @IBOutlet weak var address: UILabel!
    
    @IBOutlet weak var current_status: UILabel!
    @IBOutlet weak var accept_btn: UIButton!
    @IBOutlet weak var cook_btn: UIButton!
    @IBOutlet weak var travel_btn: UIButton!
    @IBOutlet weak var delevered_btn: UIButton!
    @IBOutlet weak var reject_btn: UIButton!
  
    
    @IBOutlet weak var user_details: UILabel!
    
 
    
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

        
        
        
        
        call_oder_details_api(order_id: get_order_no)
        makeroundbtn()
        make_disable_status()
        // Do any additional setup after loading the view.
    }
    
    func make_disable_status(){
        
        cook_btn.backgroundColor = UIColor.gray
        cook_btn.isEnabled = false
        
        travel_btn.backgroundColor = UIColor.gray
        travel_btn.isEnabled = false
        
        delevered_btn.backgroundColor = UIColor.gray
        delevered_btn.isEnabled = false
    }
    
    func accept_order(){
        
        cook_btn.backgroundColor = UIColor.blue
        cook_btn.isEnabled = true
        
        travel_btn.backgroundColor = UIColor.blue
        travel_btn.isEnabled = true
        
        delevered_btn.backgroundColor = UIColor.blue
        delevered_btn.isEnabled = true
    }
    func makeroundbtn(){
        
        accept_btn.layer.cornerRadius = 5
        accept_btn.clipsToBounds = true
        
        cook_btn.layer.cornerRadius = 5
        cook_btn.clipsToBounds = true
        cook_btn.imageView?.contentMode = .scaleAspectFit
        
        travel_btn.layer.cornerRadius = 5
        travel_btn.clipsToBounds = true
         travel_btn.imageView?.contentMode = .scaleAspectFit
        
        delevered_btn.layer.cornerRadius = 5
        delevered_btn.clipsToBounds = true
         delevered_btn.imageView?.contentMode = .scaleAspectFit
        
        reject_btn.layer.cornerRadius = 5
        reject_btn.clipsToBounds = true
    }
    
    func show_alert_msg(){
        
        //1. Create the alert controller.
        let alert = UIAlertController(title: "Alert", message: "Are you sure to can Order", preferredStyle: .alert)
        // 3. Grab the value from the text field, and print it when the user clicks OK.
        alert.addAction(UIAlertAction(title: "OK", style: .default, handler: { (_) in
            
            self.call_status_api (status: "cancel", order_id: self.get_order_no)
             self.make_disable_status()
            
            self.accept_btn.backgroundColor = UIColor.gray
            self.accept_btn.isEnabled = false
            
            self.reject_btn.backgroundColor = UIColor.gray
            self.reject_btn.isEnabled = false
            
        }))
        alert.addAction(UIAlertAction(title: "CANCEL", style: .default, handler: { (_) in
            
            
            
        }))
        // 4. Present the alert.
        self.present(alert, animated: true, completion: nil)
    }
    
    
    func call_status_api(status: String, order_id: String){
        
        SVProgressHUD.show()
        
        print("save data api calls : ");
        
        let parameters : Parameters = [
            "status" :status,
            "order_id": order_id
            
        ]
        
        
        let urlString = "http://www.zeenarch.com/chef_hire/status_update.php"
        
        
        Alamofire.request(urlString, method: .post, parameters: parameters, encoding:  URLEncoding.httpBody).responseJSON{ response in
            
            switch response.result {
            case .success:
                debugPrint(response)
                
                
                self.current_status.text = "Your Current Status : \(status)"
                
                
                
                
                
                SVProgressHUD.dismiss()
                
           
            case .failure(let error):
                print(error)
            }
            
            
        }
        

        
    }
    
    
    
    
    func call_oder_details_api(order_id : String){
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
                        
                         let user_name = (these["user_name"] as? String) ?? " "
                        let user_phone = (these["user_phone"] as? String) ?? " "
                        let user_address = (these["user_address"] as? String) ?? " "
                        
                       // let chef_name = (these["chef_name"] as? String) ?? " "
                      //  let chef_phone = (these["chef_phone"] as? String) ?? " "
                       // let chef_id = (these["chef_id"] as? String) ?? " "
                        
                        let f_name = (these["food_name"] as? String) ?? " "
                        let f_price = (these["food_price"] as? String) ?? " "
                        let meals_no = (these["meals_no"] as? String) ?? " "
                       let f_status = (these["status"] as? String) ?? " "
                        //  let choose = (these["choose"] as? String) ?? " "
                   
                        self.order_id.text = " Order Id : \(order_id_no)"
                        self.food_name.text = "\(f_name)"
                        self.food_info.text = "\(meals_no) meals Price: \(f_price)/- "
                        self.address.text = user_address
                        
                        self.user_details.text = "\(user_name) ordered, if any you can call them on this phone number : \(user_phone)"
                     
                        self.current_status.text = "Your Current Status : \(f_status)"
                        
                            SVProgressHUD.dismiss()
                        
                        if (f_status == "accept"){
                            
                            
                            
                                self.accept_btn.backgroundColor = UIColor.gray
                                self.accept_btn.isEnabled = false
                            
                            self.reject_btn.backgroundColor = UIColor.gray
                            self.reject_btn.isEnabled = false
                            
                            self.accept_order()
                            
                            }
                            else if(f_status == "cooking"){
                            
                            self.accept_btn.backgroundColor = UIColor.gray
                            self.accept_btn.isEnabled = false
                            self.reject_btn.backgroundColor = UIColor.gray
                            self.reject_btn.isEnabled = false
                                self.cook_btn.backgroundColor = UIColor.gray
                                self.cook_btn.isEnabled = false
                                
                                self.travel_btn.backgroundColor = UIColor.blue
                                self.travel_btn.isEnabled = true
                                
                                self.delevered_btn.backgroundColor = UIColor.blue
                                self.delevered_btn.isEnabled = true
                            }
                            else if(f_status == "travelling"){
                            
                            self.accept_btn.backgroundColor = UIColor.gray
                            self.accept_btn.isEnabled = false
                            self.reject_btn.backgroundColor = UIColor.gray
                            self.reject_btn.isEnabled = false
                                self.cook_btn.backgroundColor = UIColor.gray
                                self.cook_btn.isEnabled = false
                                
                                self.travel_btn.backgroundColor = UIColor.gray
                                self.travel_btn.isEnabled = false
                                
                                self.delevered_btn.backgroundColor = UIColor.blue
                                self.delevered_btn.isEnabled = true
                            }
                            else if (f_status == "delivered"){
                            
                            self.accept_btn.backgroundColor = UIColor.gray
                            self.accept_btn.isEnabled = false
                            self.reject_btn.backgroundColor = UIColor.gray
                            self.reject_btn.isEnabled = false
                                self.cook_btn.backgroundColor = UIColor.gray
                                self.cook_btn.isEnabled = false
                                
                                self.travel_btn.backgroundColor = UIColor.gray
                                self.travel_btn.isEnabled = false
                                
                                self.delevered_btn.backgroundColor = UIColor.gray
                                self.delevered_btn.isEnabled = false
                            }
                            else if(f_status == "cancel"){
                            
                            self.accept_btn.backgroundColor = UIColor.gray
                            self.accept_btn.isEnabled = false
                            self.reject_btn.backgroundColor = UIColor.gray
                            self.reject_btn.isEnabled = false
                            
                                self.cook_btn.backgroundColor = UIColor.gray
                                self.cook_btn.isEnabled = false
                                
                                self.travel_btn.backgroundColor = UIColor.gray
                                self.travel_btn.isEnabled = false
                                
                                self.delevered_btn.backgroundColor = UIColor.gray
                                self.delevered_btn.isEnabled = false
                                
                                
                                
                                
                            }
                        else{
                            
                        }
                            
                            
                       
                        
                        
                        
                      
                        
                    }
                }
                
                
            case .failure(let error):
                print(error)
            }
            
         
        }
    }
    
    
    @IBAction func Accept(_ sender: Any) {
        self.accept_btn.backgroundColor = UIColor.gray
        self.accept_btn.isEnabled = false
        
        self.reject_btn.backgroundColor = UIColor.gray
        self.reject_btn.isEnabled = false
        
    accept_order()
    call_status_api (status: "accept", order_id: get_order_no)
        
        
    }
    
    @IBAction func Reject(_ sender: Any) {
        
        show_alert_msg()
        
    }
    
    @IBAction func Cook(_ sender: Any) {
        cook_btn.backgroundColor = UIColor.gray
        cook_btn.isEnabled = false
        
        self.call_status_api (status: "cooking", order_id: get_order_no)
    }
    
    @IBAction func Travel(_ sender: Any) {
        travel_btn.backgroundColor = UIColor.gray
        travel_btn.isEnabled = false
        
               self.call_status_api (status: "travelling", order_id: get_order_no)
    }
    
    @IBAction func Delivered(_ sender: Any) {
        delevered_btn.backgroundColor = UIColor.gray
        delevered_btn.isEnabled = false
        
               self.call_status_api (status: "delivered", order_id: get_order_no)
    }
}
