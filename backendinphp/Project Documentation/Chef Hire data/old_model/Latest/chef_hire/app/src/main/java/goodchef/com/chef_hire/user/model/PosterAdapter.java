package goodchef.com.chef_hire.user.model;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import goodchef.com.chef_hire.R;

/**
 * Created by santo on 8/30/2018.
 */

public class PosterAdapter extends PagerAdapter{

    private Context mContext;
    private List<String> mDataList;
    private LayoutInflater layoutInflater;

    @Override
    public int getCount() {
        return mDataList.size();
    }


    public void setData( List<String> data, Context mcontext) {
        if (mDataList != data) {
            mDataList = data;
            // this.arr_likes = arr_likes;
            this.mContext = mcontext;
            // notifyDataSetChanged();
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        Context context = container.getContext();
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.poster, container, false);
        container.addView(view);


        ImageView image = (ImageView)view.findViewById(R.id.image);


        Glide.with(mContext).load(mDataList.get(position).toString())
                .into(image);



        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
