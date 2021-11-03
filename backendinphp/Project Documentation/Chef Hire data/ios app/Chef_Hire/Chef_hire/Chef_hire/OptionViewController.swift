//
//  OptionViewController.swift
//  Chef_hire
//
//  Created by Reelover reelover on 06/08/18.
//  Copyright © 2018 Reelover. All rights reserved.
//

import UIKit

class OptionViewController: UIViewController {
    
    var food_name: String = ""
    var food_price: String = ""
    var food_image: String = ""
    
    
    @IBOutlet weak var continuebtn: UIButton!
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    
    @IBAction func submit(_ sender: Any) {
        goto_Chef_list()
        
    }
    
    
    
    
    
    func goto_Chef_list(){
        let vc = ChefListViewController()
        vc.food_name = food_name
        vc.food_price = food_price
        vc.food_image = food_image
        navigationController?.pushViewController(vc, animated: true)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "chef_list" {
            let controller = segue.destination as! ChefListViewController
            controller.food_name = food_name
               controller.food_price = food_price
               controller.food_name = food_image
        }
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
