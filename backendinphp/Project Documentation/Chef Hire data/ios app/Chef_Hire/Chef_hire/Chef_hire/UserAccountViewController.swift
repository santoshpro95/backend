//
//  UserAccountViewController.swift
//  Chef_hire
//
//  Created by Reelover reelover on 09/08/18.
//  Copyright © 2018 Reelover. All rights reserved.
//

import UIKit
import Alamofire
import SVProgressHUD

class UserAccountViewController: UIViewController {
    var phone_number: String = ""
    
    
    
    @IBOutlet weak var sh_name: UILabel!
    @IBOutlet weak var sh_address: UILabel!
    
    
    @IBOutlet weak var logout_btn: UIButton!
    override func viewDidLoad() {
        super.viewDidLoad()
        let userdefault = UserDefaults.standard
        let u_name =  userdefault.string(forKey: "name")
        let u_address =  userdefault.string(forKey: "address")
        sh_name.text = u_name
        sh_address.text = u_address
        
        
        makeroundbtn()
        // Do any additional setup after loading the view.
    }
    
    func makeroundbtn(){
        
        logout_btn.layer.cornerRadius = 5
        logout_btn.clipsToBounds = true
    }
    
    
    @IBAction func edit_name(_ sender: Any) {
        //1. Create the alert controller.
        let alert = UIAlertController(title: "Name", message: "Enter your new name", preferredStyle: .alert)
        
        //2. Add the text field. You can configure it however you need.
        alert.addTextField { (textField) in
            textField.text = self.sh_name.text
            
        }
        
        // 3. Grab the value from the text field, and print it when the user clicks OK.
        alert.addAction(UIAlertAction(title: "OK", style: .default, handler: { [weak alert] (_) in
            let textField = alert?.textFields![0] // Force unwrapping because we know it exists.
            
            
            self.sh_name.text = textField?.text
            
            let userdefault = UserDefaults.standard
            userdefault.set(textField?.text, forKey: "name")
            self.update_acount(name: (textField?.text!)!, address:self.sh_address.text!, phone: self.phone_number )
            
        }))
        
        // 4. Present the alert.
        self.present(alert, animated: true, completion: nil)
        
        
    }
    
    @IBAction func edit_address(_ sender: Any) {
        
        //1. Create the alert controller.
        let alert = UIAlertController(title: "Address", message: "Enter your new address", preferredStyle: .alert)
        
        //2. Add the text field. You can configure it however you need.
        alert.addTextField { (textField) in
            textField.text = self.sh_address.text
            
        }
        
        // 3. Grab the value from the text field, and print it when the user clicks OK.
        alert.addAction(UIAlertAction(title: "OK", style: .default, handler: { [weak alert] (_) in
            let textField = alert?.textFields![0] // Force unwrapping because we know it exists.
            
            
            self.sh_address.text = textField?.text
            
            let userdefault = UserDefaults.standard
            userdefault.set(textField?.text, forKey: "address")
            self.update_acount(name: self.sh_name.text!, address:(textField?.text!)!, phone: self.phone_number )
            
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
    
    
    
    
    
    
    @IBAction func Logout(_ sender: Any) {
        
        let userdefault = UserDefaults.standard
        userdefault.set(false, forKey: "onboardingcomplete")
        
        performSegue(withIdentifier: "user_logout", sender: self)
        
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "user_logout" {
            let controller = segue.destination as! StartViewController
            controller.logout = "user_logout"
        }
   
    }
    
}
