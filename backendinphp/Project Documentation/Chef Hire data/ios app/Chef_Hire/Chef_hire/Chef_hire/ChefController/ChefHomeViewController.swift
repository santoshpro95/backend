//
//  ChefHomeViewController.swift
//  Chef_hire
//
//  Created by Reelover reelover on 07/08/18.
//  Copyright © 2018 Reelover. All rights reserved.
//

import UIKit
import Alamofire
import SVProgressHUD

class ChefHomeViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    @IBOutlet weak var tableview: UITableView!
    
    var data: [[String: Any]] = [[String: Any]]()
    
    var f_name = [String]()
    var f_address = [String]()
    var f_info = [String]()
    var order_id = [String]()
    
    var pass_order_id: String = ""
   

    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return data.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        let cell = tableview.dequeueReusableCell(withIdentifier: "cell") as! ChefHomeTableViewCell
        
        
        cell.food_name.text = f_name[indexPath.row]
      
        cell.food_info.text = f_info[indexPath.row]
        
        cell.food_address.text = f_address[indexPath.row]
    
        
        if(f_address[indexPath.row] == "delivered"){
            
             cell.food_address.textColor = UIColor.black
        }
        else{
        
            cell.food_address.textColor = UIColor.green
        }
     
        
        return cell
        
    }
    
    
    override func viewDidAppear(_ animated: Bool) {
        data.removeAll()
        f_name.removeAll()
        f_info.removeAll()
        f_address.removeAll()
        order_id.removeAll()
        
        let userdefault = UserDefaults.standard
        let chef_id =  userdefault.string(forKey: "chef_id")
        
        check_verification(chef_id: chef_id!)
        
    }

  
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
        tableview.dataSource = self
        tableview.delegate = self
        
     
        
    
      
  
        // Do any additional setup after loading the view.
    }
    
    func check_verification(chef_id: String){
        
        SVProgressHUD.show()
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
                        
                        //   let name = (these["name"] as? String) ?? " "
                        //  let phone = (these["phone"] as? String) ?? " "
                          let chef_id = (these["chef_id"] as? String) ?? " "
                        
                        //  let rating = (these["rating"] as? String) ?? " "
                        let verify = (these["verify"] as? String) ?? " "
                        //  let email = (these["address"] as? String) ?? " "
                        
                        if(verify == "0"){
        
                            print("Verification under process")
                            self.show_msg()
                            
                        }
                        else{
                            self.check_orders(chef_id: chef_id)
                        }
                        
                        
                        
                    }
                    
                   
                }

            case .failure(let error):
                print(error)
            }
            
             SVProgressHUD.dismiss()
        }
        
    }
    
    func show_msg(){
        //1. Create the alert controller.
        let alert = UIAlertController(title: "Verification", message: "Verification is under Process", preferredStyle: .alert)
        // 3. Grab the value from the text field, and print it when the user clicks OK.
        alert.addAction(UIAlertAction(title: "OK", style: .default, handler: { (_) in
            
            
            
        }))
        // 4. Present the alert.
        self.present(alert, animated: true, completion: nil)
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        
        return 100;
        
    }
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
        tableview.deselectRow(at: indexPath, animated: true)
        
        
        pass_order_id = order_id[indexPath.row]
        
        
        
        performSegue(withIdentifier: "order_details", sender: self)
        
        
    }
    
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        
        if (segue.identifier == "order_details") {
            
            let seconfd_vc : ChefOrderTrackViewController = segue.destination as! ChefOrderTrackViewController
            
            seconfd_vc.get_order_no = pass_order_id
            
            
            
        }
        
        
    }
    
    func check_orders(chef_id: String)  {
        
        
       SVProgressHUD.show()
        print("chef details api calls : ");
        
        let parameters : Parameters = [
            
            "chef_id": chef_id
        ]
        
        
        let urlString = "http://www.zeenarch.com/chef_hire/get_chef_orders.php"
        
        
        Alamofire.request(urlString, method: .post, parameters: parameters, encoding:  URLEncoding.httpBody).responseJSON{ response in
            
            switch response.result {
            case .success:
                debugPrint(response)
                
                
                if let JSON = response.result.value{
                    
                    var jsonobject = JSON as! [String: AnyObject]
                    if var jsonarray = jsonobject["data"] as! [[String:Any]]?{
                        for i in 0..<jsonarray.count {
                            self.data.append(jsonarray[i])
                        }
                        
                        
                        for data in 0..<jsonarray.count{
                            
                            var these = jsonarray[data]
                            
                            
                            
                            let order_id_no = (these["order_id"] as? String) ?? " "
                            
                            // let user_name = (these["user_name"] as? String) ?? " "
                            // let user_phone = (these["user_phone"] as? String) ?? " "
                               // let user_address = (these["user_address"] as? String) ?? " "
                            
                            //   let chef_name = (these["chef_name"] as? String) ?? " "
                            //   let chef_phone = (these["chef_phone"] as? String) ?? " "
                            //   let chef_id = (these["chef_id"] as? String) ?? " "
                            
                            let food_name = (these["food_name"] as? String) ?? " "
                            let food_price = (these["food_price"] as? String) ?? " "
                            let meals_no = (these["meals_no"] as? String) ?? " "
                            
                            
                           
                            
                            let food_status = (these["status"] as? String) ?? " "
                          
                            
                            
                            self.f_name.append(food_name)
                            self.f_info.append("\(meals_no) meals, Price: \(food_price)")
                            self.f_address.append(food_status)
                            self.order_id.append(order_id_no)
                            
                            
                        }
                        
                        
                        
                        
                    }
                    
                    self.tableview.reloadData()
                    
                    
                }
                else{
                    
                    // show Message No Orders You have
                    print("No orders Yet")
                    
                }
                
                
                
                
            case .failure(let error):
                print(error)
            }
            SVProgressHUD.dismiss()
            
        }
        
        
        
    }

}
