//
//  MoreAccountViewController.swift
//  Chef_hire
//
//  Created by Reelover reelover on 09/08/18.
//  Copyright © 2018 Reelover. All rights reserved.
//

import UIKit

import Alamofire
import SVProgressHUD





class MoreAccountViewController: UIViewController {

    var chef_id : String = ""
  
    
    @IBOutlet weak var logout_btn: UIButton!
    @IBOutlet weak var sh_address: UILabel!
    @IBOutlet weak var sh_name: UILabel!
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
        let userdefault = UserDefaults.standard
        let chef_name =  userdefault.string(forKey: "chef_name")
        let chef_address =  userdefault.string(forKey: "chef_address")
        
        sh_name.text = chef_name
        sh_address.text = chef_address
        
        
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
            userdefault.set(textField?.text, forKey: "chef_name")
             self.update_acount(name: (textField?.text!)!, address:self.sh_address.text!, chef_id: self.chef_id )
            
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
            userdefault.set(textField?.text, forKey: "chef_address")
            self.update_acount(name: self.sh_name.text!, address:(textField?.text!)!, chef_id: self.chef_id )
            
        }))
        
        // 4. Present the alert.
        self.present(alert, animated: true, completion: nil)
        
    }
    
    
    
    func update_acount(name:String, address: String, chef_id: String){
        
        SVProgressHUD.show()
        
        print("account update api calls : ");
        
        let parameters : Parameters = [
            "name" :name,
            "chef_id": chef_id,
            "address" : address
        ]
        
        
        let urlString = "http://www.zeenarch.com/chef_hire/chef_account_update.php"
        
        
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
         userdefault.set(false, forKey: "chef_screen")
        
        performSegue(withIdentifier: "chef_logout", sender: self)
        
    }
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "chef_logout" {
            let controller = segue.destination as! StartViewController
            controller.logout = "chef_logout"
        }
        
    }

}
