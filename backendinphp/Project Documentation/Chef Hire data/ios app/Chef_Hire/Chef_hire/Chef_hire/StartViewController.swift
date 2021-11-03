//
//  StartViewController.swift
//  Chef_hire
//
//  Created by Reelover reelover on 05/08/18.
//  Copyright © 2018 Reelover. All rights reserved.
//

import UIKit

class StartViewController: UIViewController {
    
    var logout:String = ""
    
    @IBOutlet weak var chewfController: UIButton!
    
    @IBOutlet weak var userController: UIButton!
    override func viewDidLoad() {
        super.viewDidLoad()
        makeroundbtn()
        // Do any additional setup after loading the view.
    }

    
    func makeroundbtn(){
        
        chewfController.layer.cornerRadius = 5
        chewfController.clipsToBounds = true
        
        userController.layer.cornerRadius = 5
        userController.clipsToBounds = true
    }

}
