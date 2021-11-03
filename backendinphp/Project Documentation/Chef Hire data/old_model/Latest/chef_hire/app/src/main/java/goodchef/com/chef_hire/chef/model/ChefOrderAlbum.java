package goodchef.com.chef_hire.chef.model;

/**
 * Created by santo on 8/13/2018.
 */

public class ChefOrderAlbum {


    private String f_name;
    private String f_status;
    private String order_id;
    private String date;
    private String price;
    private String quantity;
    private String user_addr;

    public ChefOrderAlbum(String f_name, String f_status, String order_id, String date, String price,  String quantity, String user_addr ){

        this.f_name = f_name;
        this.f_status = f_status;
        this.order_id = order_id;
        this.date = date;
        this.price = price;
        this.quantity = quantity;
        this.user_addr = user_addr;
    }

    public String getUser_addr() {
        return user_addr;
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

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getDate() {
        return date;
    }


}
