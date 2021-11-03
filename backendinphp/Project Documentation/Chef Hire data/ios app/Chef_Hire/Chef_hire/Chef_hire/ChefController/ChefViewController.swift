//
//  ChefViewController.swift
//  Chef_hire
//
//  Created by Reelover reelover on 09/08/18.
//  Copyright © 2018 Reelover. All rights reserved.
//

import UIKit
import Alamofire
import SVProgressHUD

class ChefViewController: UIViewController {

    @IBOutlet weak var phone_number: UITextField!
    @IBOutlet weak var nextbtn: UIButton!
    override func viewDidLoad() {
        super.viewDidLoad()
        makeroundbtn()
        // Do any additional setup after loading the view.
    }

    func check_chef_api(phone: String){
        SVProgressHUD.show()
        
        print("check user api calls : ");
        
        let parameters : Parameters = [
            
            "phone": phone
        ]
        
        
        let urlString = "http://www.zeenarch.com/chef_hire/check_chef.php"
        
        
        Alamofire.request(urlString, method: .post, parameters: parameters, encoding:  URLEncoding.httpBody).responseJSON{ response in
            
            switch response.result {
            case .success:
                debugPrint(response)
                
                SVProgressHUD.dismiss()
                
                
                if let JSON = response.result.value{
                    
                    
                    var jsonobject = JSON as! [String: AnyObject]
                    
                    
                    let verify = (jsonobject["verify"] as? String) ?? ""
                    let name = (jsonobject["name"] as? String) ?? " "
                    let phone = (jsonobject["phone"] as? String) ?? " "
                    let email = (jsonobject["email"] as? String) ?? " "
                    
                    let address = (jsonobject["address"] as? String) ?? " "
                    let rating = (jsonobject["rating"] as? String) ?? " "
                    let image = (jsonobject["image"] as? String) ?? " "
                    
                    let adhar = (jsonobject["adhar"] as? String) ?? " "
                    let choose = (jsonobject["choose"] as? String) ?? " "
                    let status = (jsonobject["status"] as? String) ?? " "
                    let chef_id = (jsonobject["chef_id"] as? String) ?? " "
                    
                    let user_type = (jsonobject["user_type"] as? String) ?? " "
                    
                 
                    
                    
                    print(user_type)
                    if(user_type == "old"){
                        self.call_home()
                        
                    }
                    else{
                        self.call_otp()
                    }
                    
                    
                    self.save_local(name: name ,phone: phone, email: email, address: address,rating: rating, image: image, adhar:adhar, choose:choose, status:status, chef_id:chef_id, verify: verify)
                    
                    
                }
                
            case .failure(let error):
                print(error)
            }
            
            
        }
        
        
    }
    func save_local(name: String, phone: String, email: String,address:String, rating:String, image:String, adhar:String, choose: String, status:String, chef_id: String, verify:String){
        
        
        let userdefault = UserDefaults.standard
        
        userdefault.set(true, forKey: "onboardingcomplete")
        userdefault.set(true, forKey: "chef_screen")
        
        userdefault.set(address, forKey: "chef_address")
        userdefault.set(name, forKey: "chef_name")
        userdefault.set(phone, forKey: "chef_phone")
        userdefault.set(email, forKey: "chef_email")
        userdefault.set(chef_id, forKey: "chef_id")
        
        
        userdefault.synchronize()
        
        
        
        print("locally saved")
        
    }
    
    
    @IBAction func close(_ sender: Any) {
        self.dismiss(animated: true, completion: nil)
    }
    
    @IBAction func Next(_ sender: Any) {
        
        check_chef_api(phone: phone_number.text!)
        
    }
    
    override func touchesBegan(_ touches: Set<UITouch>,
                               with event: UIEvent?) {
        self.view.endEditing(true)
    }
    
    func makeroundbtn(){
        
        nextbtn.layer.cornerRadius = 5
        nextbtn.clipsToBounds = true
    }
    
    func call_otp(){
        performSegue(withIdentifier: "chef_otpverf", sender: self)
    }
    
    func call_home(){
        performSegue(withIdentifier: "chef", sender: self)
    }
    
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "chef_otpverf" {
            let controller = segue.destination as! VerifyViewController
            controller.phone_number = phone_number.text!
        }
        else if (segue.identifier == "chef"){
            let controller =  segue.destination as! ChefTabViewController
              controller.phone_number = phone_number.text!
            
        }
    }



}
