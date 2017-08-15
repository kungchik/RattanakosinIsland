package sdu.wirattapong.rattanakosinisland;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by SuPerJoWTF on 25/3/2560.
 */

public class MyAdapterfood extends BaseAdapter{

    //Explicit
    private Context context;
    private int[] ints;
    private String[] titleStrings, detailStrings;

    public MyAdapterfood(Context context, int[] ints, String[] titleStrings, String[] detailStrings) {
        this.context = context;
        this.ints = ints;
        this.titleStrings = titleStrings;
        this.detailStrings = detailStrings;
    }

    @Override
    public int getCount() {
        return ints.length;
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

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.my_listviewfood,parent,false);

        //Initial View
        ImageView imageView = (ImageView) view.findViewById(R.id.imvIconTravel);
        TextView titleTextView = (TextView) view.findViewById(R.id.txtTitlefood);
        TextView DetailTextView = (TextView) view.findViewById(R.id.txtDetailfood);

        //Show View นำข้อมูลไปแสดงผลบนหน้า
        imageView.setImageResource(ints[position]);
        titleTextView.setText(titleStrings[position]);
        DetailTextView.setText(detailStrings[position]);



        return view;
    }
}
