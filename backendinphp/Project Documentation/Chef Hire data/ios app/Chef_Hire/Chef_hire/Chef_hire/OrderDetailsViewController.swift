//
//  OrderDetailsViewController.swift
//  Chef_hire
//
//  Created by Reelover reelover on 05/08/18.
//  Copyright © 2018 Reelover. All rights reserved.
//

import UIKit
import Alamofire
import SVProgressHUD

class OrderDetailsViewController: UIViewController,UITableViewDelegate, UITableViewDataSource {
    var data: [[String: Any]] = [[String: Any]]()
    
    var f_name = [String]()
    var f_status = [String]()
    var f_info = [String]()
    var order_id = [String]()
     var chef_id = [String]()
    
    
    var pass_order_id: String = ""
     var pass_chef_id: String = ""
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        return data.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableview.dequeueReusableCell(withIdentifier: "cell") as! OrderedListTableViewCell
        
        
        cell.food_name.text = f_name[indexPath.row]
        cell.status.text = f_status[indexPath.row]
        cell.food_info.text = f_info[indexPath.row]
        
        if(f_status[indexPath.row] == "delivered"){
            
            cell.status.textColor = UIColor.black
        }
        else{
            
            cell.status.textColor = UIColor.green
        }
        
        return cell
    }
    

    @IBOutlet weak var tableview: UITableView!
    override func viewDidLoad() {
        super.viewDidLoad()
        tableview.delegate = self
        tableview.dataSource = self
        
        let userdefault = UserDefaults.standard
        let phone =  userdefault.string(forKey: "phone")
        getfood_details(phone_no: phone!)
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        
        return 100;
        
    }
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
        tableview.deselectRow(at: indexPath, animated: true)
        
        
        pass_order_id = order_id[indexPath.row]
         pass_chef_id = chef_id[indexPath.row]
        
        
        performSegue(withIdentifier: "food_details", sender: self)
        
        
    }
    
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        
        if (segue.identifier == "food_details") {
            
            let seconfd_vc : TrackViewController = segue.destination as! TrackViewController
            
            seconfd_vc.get_order_no = pass_order_id
            seconfd_vc.get_chef_id = pass_chef_id
            
            
        }
        
        
    }
    
    func getfood_details(phone_no: String)  {
        SVProgressHUD.show()
        print("chef details api calls : ");
        
        let parameters : Parameters = [
            
            "user_phone": phone_no
        ]
        
        
        let urlString = "http://www.zeenarch.com/chef_hire/my_oders.php"
        
        
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
                            //     let user_address = (these["user_address"] as? String) ?? " "
                            
                            //   let chef_name = (these["chef_name"] as? String) ?? " "
                            //   let chef_phone = (these["chef_phone"] as? String) ?? " "
                               let chef_id = (these["chef_id"] as? String) ?? " "
                            
                            let food_name = (these["food_name"] as? String) ?? " "
                            let food_price = (these["food_price"] as? String) ?? " "
                            let meals_no = (these["meals_no"] as? String) ?? " "
                            let food_status = (these["status"] as? String) ?? " "
                            
                            
                            self.f_name.append(food_name)
                            self.f_info.append("\(meals_no) meals, \(food_price)")
                            self.f_status.append(food_status)
                            self.order_id.append(order_id_no)
                            self.chef_id.append(chef_id)
                            
                        }
                        
                        
                        
                        
                    }
                    
                    self.tableview.reloadData()
                    
                    
                }
                
                
                
                
            case .failure(let error):
                print(error)
            }
            SVProgressHUD.dismiss()
            
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
