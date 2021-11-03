package goodchef.com.chef_hire.tiffin.model;

/**
 * Created by santo on 8/20/2018.
 */

public class ServicesAlbum {

    private String name;
    private String breakfast;
    private String lunch;
    private String dinner;

    private String image;
    private String info;
    private String s_id;
    private String phone;
    private String address;

    public ServicesAlbum(String name, String breakfast,String lunch, String dinner, String image, String info,
                         String s_id, String phone, String address){
        this.name = name;
        this.breakfast = breakfast;
        this.image = image;
        this.info = info;
        this.s_id = s_id;
        this.phone = phone;
        this.lunch = lunch;
        this.dinner = dinner;
        this.address = address;
    }


    public String getAddress() {
        return address;
    }


    public String getBreakfast() {
        return breakfast;
    }

    public String getDinner() {
        return dinner;
    }

    public String getLunch() {
        return lunch;
    }

    public String getPhone() {
        return phone;
    }

    public String getS_id() {
        return s_id;
    }


    public String getInfo() {
        return info;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

}
