//
//  ChefRegViewController.swift
//  Chef_hire
//
//  Created by Reelover reelover on 05/08/18.
//  Copyright © 2018 Reelover. All rights reserved.
//

import UIKit
import Alamofire
import SVProgressHUD

class ChefRegViewController: UIViewController {
    @IBOutlet weak var email: UITextField!
    
    @IBOutlet weak var address: UITextField!
    @IBOutlet weak var name: UITextField!
    @IBOutlet weak var nextbtn: UIButton!
    
    
    var phone: String = ""
    var p_chef_id :String = ""
    var p_verify :String = ""
    var p_name :String = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        makeroundbtn()
        // Do any additional setup after loading the view.
    }
    override func touchesBegan(_ touches: Set<UITouch>,
                               with event: UIEvent?) {
        self.view.endEditing(true)
    }
    
    @IBAction func close(_ sender: Any) {
         self.dismiss(animated: true, completion: nil)
    }
    
    func makeroundbtn(){
        
        nextbtn.layer.cornerRadius = 5
        nextbtn.clipsToBounds = true
    }
    
    @IBAction func submit(_ sender: Any) {
        
        

        save_data_api()
       
        
    }

    
    
    func save_data_api(){
        
        SVProgressHUD.show()
        
        print("save data api calls : ");
        
        let parameters : Parameters = [
            "name" :name.text ?? "",
            "phone": phone,
            "address":address.text ?? "",
            "email" : email.text ?? ""
        ]
        
        
        let urlString = "http://www.zeenarch.com/chef_hire/chef_reg.php"
        
        
        Alamofire.request(urlString, method: .post, parameters: parameters, encoding:  URLEncoding.httpBody).responseJSON{ response in
            
            switch response.result {
            case .success:
                debugPrint(response)
            
                
                if let JSON = response.result.value{
                    
                    var jsonobject = JSON as! [String: AnyObject]
                   
                        
                    print(jsonobject)
                        
                   // let verify = (jsonobject["verify"] as? String) ?? ""
                    let name = (jsonobject["name"] as? String) ?? " "
                    let phone = (jsonobject["phone"] as? String) ?? " "
                    let email = (jsonobject["email"] as? String) ?? " "
                    let address = (jsonobject["address"] as? String) ?? " "
                    let chef_id = (jsonobject["chef_id"] as? String) ?? " "
                    
                 
                    
                    let userdefault = UserDefaults.standard
                    
                    userdefault.set(true, forKey: "onboardingcomplete")
                    userdefault.set(true, forKey: "chef_screen")
                    
                    userdefault.set(name, forKey: "chef_name")
                    userdefault.set(phone, forKey: "chef_phone")
                    userdefault.set(email, forKey: "chef_email")
                    userdefault.set(address, forKey: "chef_address")
                    userdefault.set(chef_id, forKey: "chef_id")
                    
                    
                    userdefault.synchronize()
                    
                    
                    self.go_to_chef_home()
                }
                
                
                SVProgressHUD.dismiss(withDelay: 1)

                
            case .failure(let error):
                print(error)
            }
            
            
        }
 
    }
    

    func go_to_chef_home(){
        performSegue(withIdentifier: "chef_home", sender: self)
    }
 
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "chef_home" {
               let controller = segue.destination as! ChefTabViewController
            controller.phone_number = phone
        }
    }
    
  
    
}
