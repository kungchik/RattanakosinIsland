package sdu.wirattapong.rattanakosinisland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class Food extends AppCompatActivity {

    ListView listView;
    private int[] ints = new int[]{R.drawable.img_food, R.drawable.img_food1, R.drawable.img_food2,
            R.drawable.img_food3, R.drawable.img_food4, R.drawable.img_food5, R.drawable.img_food6,
            R.drawable.img_food7};

    private String[] titleStrings, detailStrings, shortString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        listView = (ListView) findViewById(R.id.allFood);

        //Get Value
        titleStrings = getResources().getStringArray(R.array.title_food);
        detailStrings = getResources().getStringArray(R.array.detail_food);

        //SubString detailString ตัดคำใน detail string เพื่อไม่ให้เกิน 30 Character
        shortString = new String[detailStrings.length]; //จองพื้นที่ในหน่วยความจำตัวแปร shortString
        for (int i=0; i < detailStrings.length; i++) {
            shortString[i] = detailStrings[i].substring(0, 50) + "...";

        } //end for

        //Create ListView
        MyAdapter1 myAdapter = new MyAdapter1(Food.this,ints ,titleStrings,shortString);
        listView.setAdapter(myAdapter);

        //Active when click List View
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Food.this, Detailfood.class);
                intent.putExtra("Title_food", titleStrings[position]);
                intent.putExtra("Detail_food", detailStrings[position]);
                intent.putExtra("Image", ints[position]);
                startActivity(intent);
            }
        });

    }
    public void onClickBack (View view) {finish();}
    public void onClickHome (View view){Intent intent = new Intent(Food.this, MainActivity.class);
        startActivity(intent);}
}
