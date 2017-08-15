package sdu.wirattapong.rattanakosinisland;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by SuPerJoWTF on 25/3/2560.
 */

public class MyAdapter extends BaseAdapter{

    private Context objcontextcake;
    private String[] name,detel,image;
    //Explicit
    public MyAdapter(Context context,String[] name, String[] detel, String[] image) {
        this.objcontextcake = context;
        this.name = name;
        this.detel = detel;
        this.image = image;


    }

    @Override
    public int getCount() {return name.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater objLayoutInflater = (LayoutInflater) objcontextcake.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = objLayoutInflater.inflate(R.layout.my_listview,parent,false);

        TextView TravelTextView = (TextView) view.findViewById(R.id.txtTitleTour);
        TextView Traveldetel = (TextView) view.findViewById(R.id.txtDetailTour);
        ImageView TravelImage = (ImageView) view.findViewById(R.id.imvIconTravel);

        Picasso.with(objcontextcake).load(image[position]).into(TravelImage);
        TravelTextView.setText(name[position]);
        Traveldetel.setText(detel[position]);
        return view;
    }
}
