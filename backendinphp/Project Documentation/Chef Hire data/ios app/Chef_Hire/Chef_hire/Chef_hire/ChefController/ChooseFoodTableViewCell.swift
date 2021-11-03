//
//  ChooseFoodTableViewCell.swift
//  Chef_hire
//
//  Created by Reelover reelover on 10/08/18.
//  Copyright © 2018 Reelover. All rights reserved.
//

import UIKit

class ChooseFoodTableViewCell: UITableViewCell {
    @IBOutlet weak var food_name: UILabel!
    
    @IBOutlet weak var food_select: UISwitch!
    @IBOutlet weak var food_price: UILabel!
    @IBOutlet weak var food_items: UILabel!
    @IBOutlet weak var food_image: UIImageView!
    
  var onSwitchTapped : (() -> Void)? = nil
    
    
    @IBAction func Choose(_ sender: UISwitch) {
        
        if let onSwitchTapped = self.onSwitchTapped {
            onSwitchTapped()
        }
       
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
