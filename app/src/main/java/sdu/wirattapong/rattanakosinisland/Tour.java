package sdu.wirattapong.rattanakosinisland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class Tour extends AppCompatActivity {

    ListView listView;
    private int[] ints = new int[]{R.drawable.img1, R.drawable.img2, R.drawable.img3,
            R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7,
            R.drawable.img8, R.drawable.img9};

    private String[] titleStrings, detailStrings, shortString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);

        listView = (ListView) findViewById(R.id.allTour);

        //Get Value
        titleStrings = getResources().getStringArray(R.array.title);
        detailStrings = getResources().getStringArray(R.array.detail);

        //SubString detailString ตัดคำใน detail string เพื่อไม่ให้เกิน 30 Character
        shortString = new String[detailStrings.length]; //จองพื้นที่ในหน่วยความจำตัวแปร shortString
        for (int i=0; i < detailStrings.length; i++) {
            shortString[i] = detailStrings[i].substring(0, 50) + "...";

        } //end for

        //Create ListView
        MyAdapter myAdapter = new MyAdapter(Tour.this,ints ,titleStrings,shortString);
        listView.setAdapter(myAdapter);

        //Active when click List View
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Tour.this, Detail.class);
                intent.putExtra("Title", titleStrings[position]);
                intent.putExtra("Detail", detailStrings[position]);
                intent.putExtra("Image", ints[position]);
                startActivity(intent);
            }
        });
    }
    public void onClickBack (View view) {
        finish();
    }
    public void onClickHome (View view){
        Intent intent = new Intent(Tour.this, MainActivity.class);
        startActivity(intent);
    }
}
