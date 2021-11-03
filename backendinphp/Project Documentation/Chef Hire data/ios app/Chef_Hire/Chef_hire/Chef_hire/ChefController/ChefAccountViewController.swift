//
//  ChefAccountViewController.swift
//  Chef_hire
//
//  Created by Reelover reelover on 08/08/18.
//  Copyright © 2018 Reelover. All rights reserved.
//

import UIKit
import Alamofire
import SVProgressHUD

class ChefAccountViewController: UIViewController {

    @IBOutlet weak var name: UILabel!
    @IBOutlet weak var chef_available: UISwitch!
    
    @IBOutlet weak var verified: UILabel!
    @IBOutlet weak var phone_email: UILabel!
    
    @IBOutlet weak var resturant: UISwitch!
    @IBOutlet weak var hire_chef: UISwitch!
    
   
    var chef_id_no: String = ""
     var chef_address_det: String = ""
    
    @IBAction func Account(_ sender: Any) {
        
        performSegue(withIdentifier: "chef_account", sender: self)
    }
    
    
    override func viewDidAppear(_ animated: Bool) {

        check_verification(chef_id: chef_id_no)
        
    }
    
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "chef_account" {
            let controller = segue.destination as! MoreAccountViewController
            controller.chef_id = chef_id_no
         
        }
        
    }
    
    
    
    
    @IBAction func Resturant_sw(_ sender: UISwitch) {
        
         if(resturant.isOn){
            resturant.setOn(false, animated: true)
            hire_chef.setOn(true, animated: true)
            
            set_choose_api(choose:"1",chef_id: chef_id_no)
         }else{
            resturant.setOn(true, animated: true)
            hire_chef.setOn(false, animated: true)
            
            set_choose_api(choose:"0",chef_id: chef_id_no)
            
        }
        
        
    }
    
    @IBAction func Hire_chef_sw(_ sender: UISwitch) {
        
        if(hire_chef.isOn){
            resturant.setOn(true, animated: true)
            hire_chef.setOn(false, animated: true)
            
             set_choose_api(choose:"0",chef_id: chef_id_no)
            
        }else{
            resturant.setOn(false, animated: true)
            hire_chef.setOn(true, animated: true)
            
            set_choose_api(choose:"1",chef_id: chef_id_no)
        }
        
        
        
    }
    
    
    
    
    
    
    @IBAction func Available_sw(_ sender: UISwitch) {
        
        
        if(chef_available.isOn){
            
            
            chef_available.setOn(false, animated: true)
            set_available_api(available:"0",chef_id: chef_id_no)
            
            
        }
        else{
            
            chef_available.setOn(true, animated: true)
            set_available_api(available:"1",chef_id: chef_id_no)
        }
        
        
    }
    
    
    func set_choose_api(choose:String, chef_id: String){
        
        
        SVProgressHUD.show()
        
        print("save data api calls : ");
        
        let parameters : Parameters = [
            "choose" :choose,
            "chef_id" :chef_id
            
        ]
        
        
        let urlString = "http://www.zeenarch.com/chef_hire/change_chef_choose.php"
        
        
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
    
    
    func set_available_api(available:String, chef_id: String){
        
        
        SVProgressHUD.show()
        
        print("save data api calls : ");
        
        let parameters : Parameters = [
            "available" :available,
            "chef_id" :chef_id
            
        ]
        
        
        let urlString = "http://www.zeenarch.com/chef_hire/change_chef_status.php"
        
        
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
    

    
    override func viewDidLoad() {
        super.viewDidLoad()

        let userdefault = UserDefaults.standard
        let chef_id =  userdefault.string(forKey: "chef_id")
        let chef_address =  userdefault.string(forKey: "chef_address")
        
        chef_address_det = chef_address!
        chef_id_no = chef_id!
        
        // Do any additional setup after loading the view.
    }
    func check_verification(chef_id: String){
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
                        
                          let chef_name = (these["name"] as? String) ?? " "
                         let phone = (these["phone"] as? String) ?? " "
                       // let chef_id = (these["chef_id"] as? String) ?? " "
                        
                        //  let rating = (these["rating"] as? String) ?? " "
                        let verify = (these["verify"] as? String) ?? " "
                        let email = (these["email"] as? String) ?? " "
                        let available = (these["status"] as? String) ?? " "
                        let choose = (these["choose"] as? String) ?? " "
                        
                        
                        if(available == "1"){
                              self.chef_available.setOn(true, animated:true)
                            
                        }
                        else{
                              self.chef_available.setOn(false, animated:true)
                        }
                        
                        
                        
                        if(choose == "1"){
                            self.resturant.setOn(false, animated: true)
                            self.hire_chef.setOn(true, animated: true)
                            
                         
                        }
                        else{
                            
                            self.resturant.setOn(true, animated: true)
                            self.hire_chef.setOn(false, animated: true)
                        }
                        
                        
                        
                        self.name.text = chef_name
                        self.phone_email.text = "\(phone) (\(email))"
                        
                        
                        
                        
                        
                        if(verify == "0"){
                            
                            print("Verification under process")
                            self.verified.text = "Your Account is not Verified !"
                            self.verified.textColor = UIColor.red
                            
                        }
                        else{
                            self.verified.text = "Your Account has been Verified"
                            self.verified.textColor = UIColor.green
                        }
                        
                        
                        
                    }
                    
                    SVProgressHUD.dismiss()
                }
                
            case .failure(let error):
                print(error)
            }
            
            
        }
        
    }
    
    
    @IBAction func Logout(_ sender: Any) {
        
        let userdefault = UserDefaults.standard
        
        userdefault.set(false, forKey: "onboardingcomplete")
        userdefault.set(false, forKey: "chef_screen")
    }
    
    
   

}
