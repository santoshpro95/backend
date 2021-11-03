package goodchef.com.chef_hire.delivery.model;

/**
 * Created by santo on 8/13/2018.
 */

public class FoodOrderAlbum {


    private String f_name;
    private String f_status;
    private String order_id;
    private String date;
    private String price;
    private String address;
    private String meals;


    public FoodOrderAlbum(String f_name, String f_status, String order_id, String date, String price, String address, String meals){

        this.f_name = f_name;
        this.f_status = f_status;
        this.order_id = order_id;
        this.date = date;
        this.price = price;
        this.address = address;
        this.meals = meals;
    }

    public String getAddress() {
        return address;
    }

    public String getMeals() {
        return meals;
    }

    public String getPrice() {
        return price;
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getF_status() {
        return f_status;
    }

    public String getF_name() {
        return f_name;
    }

    public String getDate() {
        return date;
    }


}
