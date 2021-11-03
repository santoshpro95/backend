package goodchef.com.chef_hire.tiffin.model;

/**
 * Created by santo on 8/24/2018.
 */

public class MenuAlbum {

    private String day;
    private String menu;

    public MenuAlbum(String day, String menu){
        this.day = day;
        this.menu = menu;
    }


    public String getDay() {
        return day;
    }

    public String getMenu() {
        return menu;
    }

}
