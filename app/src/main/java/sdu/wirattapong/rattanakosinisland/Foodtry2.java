package sdu.wirattapong.rattanakosinisland;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by SaNoMan on 24/7/2560.
 */

public  class Foodtry2 extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public Foodtry2() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Foodtry2 newInstance(int sectionNumber) {
        Foodtry2 fragment = new Foodtry2();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_foodtry2, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.candy, getArguments().getInt(ARG_SECTION_NUMBER)));
        return rootView;

    }

}
