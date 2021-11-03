//
//  ChefListTableViewCell.swift
//  Chef_hire
//
//  Created by Reelover reelover on 04/08/18.
//  Copyright © 2018 Reelover. All rights reserved.
//

import UIKit

class ChefListTableViewCell: UITableViewCell {

    @IBOutlet weak var rating: UILabel!
    @IBOutlet weak var distance: UILabel!
    @IBOutlet weak var name: UILabel!
    @IBOutlet weak var chef_pic: UIImageView!
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }
    
    

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
