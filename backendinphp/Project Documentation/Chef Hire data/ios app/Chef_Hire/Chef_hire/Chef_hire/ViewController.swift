//
//  ViewController.swift
//  Chef_hire
//
//  Created by Reelover reelover on 03/08/18.
//  Copyright © 2018 Reelover. All rights reserved.
//

import UIKit
import Alamofire
import SVProgressHUD

class ViewController: UIViewController {

    @IBOutlet weak var phone_number: UITextField!
    
    @IBOutlet weak var req_otp: UIButton!
    
    
    
    @IBAction func close(_ sender: Any) {
           self.dismiss(animated: true, completion: nil)
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
      makeroundbtn() // make round button
        
    }
    override func touchesBegan(_ touches: Set<UITouch>,
                               with event: UIEvent?) {
        self.view.endEditing(true)
    }
    
    func makeroundbtn(){
        
        req_otp.layer.cornerRadius = 5
        req_otp.clipsToBounds = true
    }


    @IBAction func requestingOtp(_ sender: Any) {

        
        
        check_user_api(phone: "+91 \(phone_number.text!)")
        
        

    }
    
    
    func check_user_api(phone: String){
        SVProgressHUD.show()
        
        print("check user api calls : ");
        
        let parameters : Parameters = [
         
            "phone": phone
        ]
        
        
        let urlString = "http://www.zeenarch.com/chef_hire/check_user.php"
        
        
        Alamofire.request(urlString, method: .post, parameters: parameters, encoding:  URLEncoding.httpBody).responseJSON{ response in
            
            switch response.result {
            case .success:
                debugPrint(response)
                
                SVProgressHUD.dismiss()
                
                
                   if let JSON = response.result.value{
                    
                    
                  var jsonobject = JSON as! [String: AnyObject]
                
                  let user_type = (jsonobject["user_type"] as? String) ?? " "
                    let name = (jsonobject["name"] as? String) ?? " "
                    let phone = (jsonobject["phone"] as? String) ?? " "
                    let address = (jsonobject["address"] as? String) ?? " "
                    
                    
                    
                    let userdefault = UserDefaults.standard
                    userdefault.set(true, forKey: "onboardingcomplete")
                    userdefault.set(false, forKey: "chef_screen")
                  
                    
                    userdefault.set(name, forKey: "name")
                    userdefault.set(phone, forKey: "phone")
                    userdefault.set(address, forKey: "address")
                    userdefault.synchronize()
                    
                    
                    print(user_type)
                if(user_type == "old"){
                    self.call_home()
                    
                }
                else{
                     self.call_otp()
                    }
                
                
           }
                
            case .failure(let error):
                print(error)
            }
            
            
        }
        
        
    }
    
    func call_otp(){
        performSegue(withIdentifier: "otpverf", sender: self)
    }
    
    func call_home(){
        performSegue(withIdentifier: "user", sender: self)
    }
    
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
         if segue.identifier == "otpverf" {
            let controller = segue.destination as! OTPViewController
            controller.phone_number = phone_number.text!
        }
         else if (segue.identifier == "user"){
          let controller =  segue.destination as! UserTabViewController
           controller.phone_number = phone_number.text!
           
        }
    }
    
  
    

}

