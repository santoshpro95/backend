//
//  VerifyViewController.swift
//  Chef_hire
//
//  Created by Reelover reelover on 07/08/18.
//  Copyright © 2018 Reelover. All rights reserved.
//

import UIKit

class VerifyViewController: UIViewController {
 
    @IBOutlet weak var message: UILabel!
    @IBOutlet weak var submitbtn: UIButton!
    
     var phone_number: String = ""
    override func viewDidLoad() {
        super.viewDidLoad()
    
        message.text = "Please type the verification code sent to +91 \(phone_number)"
        
        makeroundbtn()
        // Do any additional setup after loading the view.
    }
    
    func makeroundbtn(){
        
        submitbtn.layer.cornerRadius = 5
        submitbtn.clipsToBounds = true
    }
    override func touchesBegan(_ touches: Set<UITouch>,
                               with event: UIEvent?) {
        self.view.endEditing(true)
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func Submit(_ sender: Any) {
        
        performSegue(withIdentifier: "chef_otp", sender: self)
        
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "chef_otp" {
            let controller = segue.destination as! ChefRegViewController
            controller.phone = phone_number
        }
    
    }
    

    
    
    @IBAction func Close(_ sender: Any) {
           self.dismiss(animated: true, completion: nil)
    }
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
