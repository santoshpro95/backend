package goodchef.com.chef_hire.user.model;

/**
 * Created by Lincoln on 18/05/16.
 */
public class HomeAlbum {
    private String name;
    private String items;
    private String price;
    private String image;
    private String meal_id;
   // int chat_no;


    public HomeAlbum(String name, String items, String price,String image,String meal_id ) {
        this.name = name;
        this.items = items;
        this.price = price;
        this.image = image;
        this.meal_id = meal_id;
    }


    public String getMeal_id() {
        return meal_id;
    }

    public String getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getItems() {
        return items;
    }

    public String getImage() {
        return image;
    }


}
