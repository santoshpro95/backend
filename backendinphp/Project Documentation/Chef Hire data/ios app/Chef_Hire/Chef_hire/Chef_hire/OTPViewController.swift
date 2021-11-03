//
//  OTPViewController.swift
//  Chef_hire
//
//  Created by Reelover reelover on 03/08/18.
//  Copyright © 2018 Reelover. All rights reserved.
//

import UIKit

class OTPViewController: UIViewController {

    @IBOutlet weak var OTP_verify: UITextField!
    @IBOutlet weak var submitbtn: UIButton!
    @IBOutlet weak var phoneno: UILabel!
    
//    declare variables------
    
   var phone_number: String = ""
    
    
//    declare variables------
    override func viewDidLoad() {
        super.viewDidLoad()

        phoneno.text = "Please type the verification code sent to +91 \(phone_number)"
        
        makeroundbtn()
        // Do any additional setup after loading the view.
    }
    
    
    override func touchesBegan(_ touches: Set<UITouch>,
                               with event: UIEvent?) {
        self.view.endEditing(true)
    }
    
    
    @IBAction func editPhoneNo(_ sender: Any) {
        self.dismiss(animated: true, completion: nil)
    }
    
    @IBAction func SubmitOTP(_ sender: Any) {
        
        
        goto_Signup()
        
    }
    
    
    func goto_Signup(){
        let vc = SignupViewController()
        vc.get_phoneNo = phone_number
        navigationController?.pushViewController(vc, animated: true)
    }
    
    func makeroundbtn(){
        
        submitbtn.layer.cornerRadius = 5
        submitbtn.clipsToBounds = true
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "signup" {
            let controller = segue.destination as! SignupViewController
            controller.get_phoneNo = phone_number
        }
    }

}
