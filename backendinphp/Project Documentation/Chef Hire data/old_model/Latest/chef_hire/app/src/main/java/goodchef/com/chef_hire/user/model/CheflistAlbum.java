package goodchef.com.chef_hire.user.model;

/**
 * Created by santo on 8/12/2018.
 */

public class CheflistAlbum {

    private String name;
    private String phone;
    private String address;
    private String image;
    private String rating;
    private String email;
    private String id;

    private String food_name;
    private String food_price;
    private String food_image;
    private String distance_between;
    private String items;
    private String delivery;
    private String c_fcm;
    private String type;

    public CheflistAlbum(String name, String phone, String address,String image,String rating,
                         String email,String id,String food_name, String food_price,
                         String food_image,String distance_between, String items, String delivery, String c_fcm, String type) {

        this.name = name;
        this.phone = phone;
        this.address = address;
        this.image = image;
        this.rating = rating;
        this.email = email;
        this.id = id;
        this.delivery = delivery;

        this.food_name = food_name;
        this.food_price = food_price;
        this.food_image = food_image;
        this.c_fcm = c_fcm;
        this.items = items;
        this.distance_between = distance_between;
        this.type = type;

    }

    public String getType() {
        return type;
    }

    public String getC_fcm() {
        return c_fcm;
    }

    public String getDelivery() {
        return delivery;
    }

    public String getItems() {
        return items;
    }

    public String getDistance_between() {
        return distance_between;
    }


    public String getFood_image() {
        return food_image;
    }

    public String getFood_name() {
        return food_name;
    }

    public String getFood_price() {
        return food_price;
    }

    public String getImage() {
        return image;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getRating() {
        return rating;
    }

}
