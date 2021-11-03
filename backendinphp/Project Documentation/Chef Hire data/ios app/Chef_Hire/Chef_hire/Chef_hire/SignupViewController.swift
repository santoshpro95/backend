//
//  SignupViewController.swift
//  Chef_hire
//
//  Created by Reelover reelover on 03/08/18.
//  Copyright © 2018 Reelover. All rights reserved.
//

import UIKit
import Alamofire
import SVProgressHUD
class SignupViewController: UIViewController {

    
    @IBOutlet weak var phone_no: UILabel!
    @IBOutlet weak var fullName: UITextField!
    @IBOutlet weak var address: UITextField!
    @IBOutlet weak var submit: UIButton!
    
    var get_phoneNo : String = ""
    
//  getting api value
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

        phone_no.text = "+91 \(get_phoneNo)"
        makeroundbtn()
        // Do any additional setup after loading the view.
    }

    @IBAction func SubmitSignup(_ sender: Any) {
        
      
        
    
       let fullname : String = fullName.text!
       let full_address : String = address.text!
       let phone_no = "+91 \(get_phoneNo)"
        
        save_data(Phone: phone_no, name: fullname, address: full_address)
        
    }
    
    
    
    
    func save_data(Phone: String, name: String, address: String)  {
        
        SVProgressHUD.show()

        print("save data api calls : ");
        
        let parameters : Parameters = [
           "name" :name,
           "phone": Phone,
           "address" : address
        ]
        
        
        let urlString = "http://www.zeenarch.com/chef_hire/new_user.php"
        

        Alamofire.request(urlString, method: .post, parameters: parameters, encoding:  URLEncoding.httpBody).responseJSON{ response in

            switch response.result {
                case .success:
                    debugPrint(response)

                    SVProgressHUD.dismiss()
                    
                    if let JSON = response.result.value{
                        
                        
                        var jsonobject = JSON as! [String: AnyObject]
                        
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
                    }
                    
                
                
                
                case .failure(let error):
                    print(error)
            }
        
         
        }
      
        
    }

    
    @IBAction func close(_ sender: Any) {
         self.dismiss(animated: true, completion: nil)
        
    }
    
    override func touchesBegan(_ touches: Set<UITouch>,
                               with event: UIEvent?) {
        self.view.endEditing(true)
    }
    
    func makeroundbtn(){
        
        submit.layer.cornerRadius = 5
        submit.clipsToBounds = true
    }
 
}
