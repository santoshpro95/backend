package goodchef.com.chef_hire.delivery.model;

/**
 * Created by santo on 8/12/2018.
 */

public class TiffinAlbum {


    private String f_name;
    private String f_status;
    private String price;

    private String order_id;
    private String chef_id;
    private String time;
    private String days;
    private String address;



    public TiffinAlbum(String f_name, String f_status, String price, String order_id,
                         String chef_id, String time, String days, String address){


        this.f_name = f_name;
        this.f_status = f_status;
        this.price = price;
        this.order_id = order_id;
        this.chef_id = chef_id;
        this.time = time;
        this.days = days;
        this.address = address;
    }


    public String getAddress() {
        return address;
    }

    public String getDays() {
        return days;
    }

    public String getTime() {
        return time;
    }

    public String getChef_id() {
        return chef_id;
    }

    public String getPrice() {
        return price;
    }

    public String getF_name() {
        return f_name;
    }

    public String getF_status() {
        return f_status;
    }

    public String getOrder_id() {
        return order_id;
    }

}
