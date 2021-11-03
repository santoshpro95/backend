package goodchef.com.chef_hire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import goodchef.com.chef_hire.MainActivity;
import goodchef.com.chef_hire.R;
import android.widget.Toast;

import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughActivity;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughCard;

import java.util.ArrayList;
import java.util.List;

public class IntroScreen extends FancyWalkthroughActivity {

    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);



        FancyWalkthroughCard fancywalkthroughCard1 = new FancyWalkthroughCard("Find Foods", "Find your foods in your neighborhood.",R.drawable.food);
        FancyWalkthroughCard fancywalkthroughCard2 = new FancyWalkthroughCard("Pick the chef", "Pick the chef using trusted ratings and reviews.",R.drawable.tea);
        FancyWalkthroughCard fancywalkthroughCard3 = new FancyWalkthroughCard("Checkout your meal", "Easily find the type of food you're craving.",R.drawable.cutlery);
        //FancyWalkthroughCard fancywalkthroughCard4 = new FancyWalkthroughCard("Meal is on the way", "Get ready and comfortable while our biker bring your meal at your door.",R.drawable.mealisonway);

        fancywalkthroughCard1.setBackgroundColor(R.color.white);
        fancywalkthroughCard1.setIconLayoutParams(300,300,0,0,0,0);
        fancywalkthroughCard2.setBackgroundColor(R.color.white);
        fancywalkthroughCard2.setIconLayoutParams(300,300,0,0,0,0);
        fancywalkthroughCard3.setBackgroundColor(R.color.white);
        fancywalkthroughCard3.setIconLayoutParams(300,300,0,0,0,0);
//        fancywalkthroughCard4.setBackgroundColor(R.color.white);
//        fancywalkthroughCard4.setIconLayoutParams(300,300,0,0,0,0);
        List<FancyWalkthroughCard> pages = new ArrayList<>();

        pages.add(fancywalkthroughCard1);
        pages.add(fancywalkthroughCard2);
        pages.add(fancywalkthroughCard3);
       // pages.add(fancywalkthroughCard4);

        for (FancyWalkthroughCard page : pages) {
            page.setTitleColor(R.color.black);
            page.setDescriptionColor(R.color.black);
        }
        setFinishButtonTitle("Get Started");
        showNavigationControls(true);
        setColorBackground(R.color.colorPrimary);
        setImageBackground(R.drawable.food_back2);
        setInactiveIndicatorColor(R.color.grey_600);
        setActiveIndicatorColor(R.color.colorPrimary);
        setOnboardPages(pages);
    }

    @Override
    public void onFinishButtonPressed() {

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
}
