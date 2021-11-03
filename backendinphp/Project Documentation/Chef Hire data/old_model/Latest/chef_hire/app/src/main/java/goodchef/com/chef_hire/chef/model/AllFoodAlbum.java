package goodchef.com.chef_hire.chef.model;

import java.util.ArrayList;

/**
 * Created by santo on 8/13/2018.
 */

public class AllFoodAlbum {


    private String f_name;
    private String f_price;
    private String image;
    private String items;
    private String meal_id;
    private ArrayList data;

    public AllFoodAlbum(String f_name, String f_price, String image, String items, String meal_id,ArrayList data){
        this.f_name = f_name;
        this.f_price = f_price;
        this.image = image;
        this.items = items;
        this.meal_id = meal_id;
        this.data = data;

    }


    public ArrayList getData() {
        return data;
    }

    public String getF_name() {
        return f_name;
    }

    public String getImage() {
        return image;
    }

    public String getMeal_id() {
        return meal_id;
    }

    public String getF_price() {
        return f_price;
    }

    public String getItems() {
        return items;
    }

}
