//
//  ChefHomeTableViewCell.swift
//  Chef_hire
//
//  Created by Reelover reelover on 07/08/18.
//  Copyright © 2018 Reelover. All rights reserved.
//

import UIKit

class ChefHomeTableViewCell: UITableViewCell {

    @IBOutlet weak var food_info: UILabel!
    @IBOutlet weak var food_address: UILabel!
    @IBOutlet weak var food_name: UILabel!
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
