package goodchef.com.chef_hire.chef.model;

/**
 * Created by santo on 8/20/2018.
 */

public class BuyAlbum {

    private String name;
    private String price;
    private String image;


    public BuyAlbum(String name, String price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;

    }


    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

}
