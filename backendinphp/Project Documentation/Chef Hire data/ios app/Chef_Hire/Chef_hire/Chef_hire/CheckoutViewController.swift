//
//  CheckoutViewController.swift
//  Chef_hire
//
//  Created by Reelover reelover on 04/08/18.
//  Copyright © 2018 Reelover. All rights reserved.
//

import UIKit
import Alamofire
import SVProgressHUD
import MessageUI

class CheckoutViewController: UIViewController {

    var get_chef_name: String = ""
    var get_chef_image : String = ""
    var get_chef_phone : String = ""
    var get_chef_email: String = ""
    var get_chef_id: String = ""
    var get_chef_address: String = ""
    var get_choose: String = ""
      var get_chef_rating: String = ""
    
        var get_food_name : String = ""
        var get_food_image : String = ""
        var get_food_price : String = ""
    
    var no_of_meals: String = ""
      var final_meal_price: String = ""
    
    
    @IBOutlet weak var food_img: UIImageView!
    @IBOutlet weak var incre_meals: UIStepper!
    
    @IBOutlet weak var food_price: UILabel!
    @IBOutlet weak var food_name: UILabel!
    @IBOutlet weak var chef_info: UILabel!
    
    @IBOutlet weak var address: UILabel!
    
    @IBOutlet weak var food_type: UILabel!
    @IBOutlet weak var paybtn: UIButton!
    

    @IBAction func Meals_no(_ sender: UIStepper) {
        
        let no =  Int(sender.value).description
        let price = Int(get_food_price)
        let final_price = price! * Int(no)!
        
        food_price.text = "\(no) meals Price: \(String(describing: final_price))/-"
        
        no_of_meals = no
        final_meal_price = String(describing: final_price)
        
//        let price : Int = Int(food_price.text!)!
//        let final_price : Int = price + 30
//
//        food_price.text = String(final_price)
//
        
        
        
    }

    
    
    func send_mail()  {
        
        
    }
    
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
     
        
        incre_meals.wraps = true
        incre_meals.autorepeat = true
        incre_meals.maximumValue = 10
        incre_meals.minimumValue = 1
        
        makeroundbtn()
        
        food_price.text = "\(1) meal Price: \(get_food_price)/-"
        
   
        no_of_meals = "1"
        final_meal_price = get_food_price
        
        
        Alamofire.request(get_food_image)
            .response { response in
                guard let imageData = response.data else {
                    print("Could not get image from image URL returned in search results")
                    
                    return
                }
                self.food_img.image = UIImage(data: imageData)
        }
        print(get_choose)
        
        if(get_choose == "0"){
             food_type.text = "Food Order cook at resturant"
        }
        else if(get_choose == "1"){
            food_type.text = "Hire Chef cook at your Home"
        }
        
 
        
        chef_info.text = "\(get_chef_name) will arrive your location if any you can call on \(get_chef_phone) this number."
        
           let userdefault = UserDefaults.standard
           let addr = userdefault.string(forKey: "address")
        
        address.text = addr
        food_name.text = get_food_name
        
        
        
        // Do any additional setup after loading the view.
    }
    
    func makeroundbtn(){
        
        paybtn.layer.cornerRadius = 5
        paybtn.clipsToBounds = true
    }

    @IBAction func Change_addr(_ sender: Any) {
    
        //1. Create the alert controller.
        let alert = UIAlertController(title: "Address", message: "Enter your new address", preferredStyle: .alert)
        
        //2. Add the text field. You can configure it however you need.
        alert.addTextField { (textField) in
            textField.text = self.address.text
            
        }
        
        // 3. Grab the value from the text field, and print it when the user clicks OK.
        alert.addAction(UIAlertAction(title: "OK", style: .default, handler: { [weak alert] (_) in
            let textField = alert?.textFields![0] // Force unwrapping because we know it exists.
           
            
            self.address.text = textField?.text
            
            let userdefault = UserDefaults.standard
            userdefault.set(textField?.text, forKey: "address")
            
            let u_name =  userdefault.string(forKey: "name")
            let phone =  userdefault.string(forKey: "phone")
            userdefault.synchronize()
            
            
            self.update_acount(name: u_name!, address:(textField?.text!)!, phone: phone!)
            
            
        }))
        
        // 4. Present the alert.
        self.present(alert, animated: true, completion: nil)
        
    
    }
    func update_acount(name:String, address: String, phone: String){
        
        SVProgressHUD.show()
        
        print("account update api calls : ");
        
        let parameters : Parameters = [
            "name" :name,
            "phone": phone,
            "address" : address
        ]
        
        
        let urlString = "http://www.zeenarch.com/chef_hire/user_account_update.php"
        
        
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

    @IBAction func submit_order(_ sender: Any) {

         let userdefault = UserDefaults.standard
        
        
        order_placed(user_name: userdefault.string(forKey: "name")!, user_phone: userdefault.string(forKey: "phone")!, user_address: userdefault.string(forKey: "address")!, chef_name: get_chef_name, chef_phone: get_chef_phone, chef_address: get_chef_address, chef_id: get_chef_id, food_name: get_food_name, food_price:   final_meal_price, no_meals:   no_of_meals, choose: get_choose)

    }
    func show_msg(){
        //1. Create the alert controller.
        let alert = UIAlertController(title: "Order Placed ", message: "You can track your Order in My account Section", preferredStyle: .alert)
        // 3. Grab the value from the text field, and print it when the user clicks OK.
        alert.addAction(UIAlertAction(title: "OK", style: .default, handler: { (_) in

            
            
        }))
        // 4. Present the alert.
        self.present(alert, animated: true, completion: nil)
    }
    
    func order_placed( user_name:String, user_phone:String, user_address: String, chef_name : String, chef_phone: String, chef_address:String,chef_id: String, food_name: String, food_price:String, no_meals:String , choose: String)  {
        
        SVProgressHUD.show()
        
        print("order placed api calls : ");
        
        let parameters : Parameters = [
            
            "user_name": user_name,
            "user_phone":user_phone,
            "user_address":user_address,
            
            "chef_name":chef_name,
            "chef_phone":chef_phone,
            "chef_address":chef_address,
            
            "chef_id":chef_id,
            "food_name":food_name,
            "food_price":food_price,
            "no_meals":no_meals,
            "choose": choose
        ]
        
        
        let urlString = "http://www.zeenarch.com/chef_hire/chef_order.php"
        
        
        Alamofire.request(urlString, method: .post, parameters: parameters, encoding:  URLEncoding.httpBody).responseJSON{ response in
            
            switch response.result {
            case .success:
                debugPrint(response)
                
                
                if let JSON = response.result.value{
                    
                    var jsonobject = JSON as! [String: AnyObject]
                     let order_id = (jsonobject["order_id"] as? String) ?? " "
                    
                    print(order_id)
                    self.show_msg()
                }
                

                
                self.paybtn.setTitle("Order Placed", for: .normal)
                self.paybtn.backgroundColor = UIColor.gray
                self.paybtn.isEnabled = false
                
                SVProgressHUD.dismiss(withDelay: 1)
                
            case .failure(let error):
                print(error)
            }
            
            
        }
        
        
    }
    



}
