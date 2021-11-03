//
//  PrivacyPolicyViewController.swift
//  Chef_hire
//
//  Created by Reelover reelover on 05/08/18.
//  Copyright © 2018 Reelover. All rights reserved.
//

import UIKit
import WebKit
class PrivacyPolicyViewController: UIViewController {

    @IBOutlet weak var webpage: WKWebView!
    var get_url :String = "http://www.zeenarch.com/chef_hire/policy.html"
    override func viewDidLoad() {
        super.viewDidLoad()
        let url = URL(string: get_url)
        let request = URLRequest(url: url!)
        webpage.load(request)
        
        // Do any additional setup after loading the view.
    }
  
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
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
