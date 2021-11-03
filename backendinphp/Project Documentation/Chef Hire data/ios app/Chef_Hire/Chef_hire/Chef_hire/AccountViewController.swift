//
//  AccountViewController.swift
//  Chef_hire
//
//  Created by Reelover reelover on 04/08/18.
//  Copyright © 2018 Reelover. All rights reserved.
//

import UIKit


class AccountViewController: UIViewController {
    
    @IBOutlet weak var address: UILabel!
    @IBOutlet weak var phone_no: UILabel!
    @IBOutlet weak var name: UILabel!

    
    
    override func viewDidAppear(_ animated: Bool) {
        let userdefault = UserDefaults.standard
        let phone =  userdefault.string(forKey: "phone")
        let u_name =  userdefault.string(forKey: "name")
        let u_address =  userdefault.string(forKey: "address")
        
        
        
        name.text = u_name
        phone_no.text = phone
        address.text = u_address
        
    }
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

    
        
        // Do any additional setup after loading the view.
    }
    
    @IBAction func Account(_ sender: Any) {
        
        performSegue(withIdentifier: "user_account", sender: self)
    }
    
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "user_account" {
            let controller = segue.destination as! UserAccountViewController
            controller.phone_number = phone_no.text!
          
        }
     
    }
    

}
