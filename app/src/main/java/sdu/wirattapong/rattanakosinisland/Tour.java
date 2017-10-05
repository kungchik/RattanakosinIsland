package sdu.wirattapong.rattanakosinisland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import sdu.wirattapong.rattanakosinisland.Database.TravelTable;

public class Tour extends AppCompatActivity {
    private TravelTable objTravelTable;
    private ListView listView;
    private String[]name,detel,image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);
            BindWidget();
            Cdatabase();
            createListView();

        }

        private void createListView() {
            //MenuTable objCasephone = new MenuTable(this);
            final String[] strname = objTravelTable.readALLTravelTable(1);
            final String[] strdetel = objTravelTable.readALLTravelTable(2);
            final String[] strbath = objTravelTable.readALLTravelTable(4);

            MyAdapter objMyAdapter = new MyAdapter(Tour.this ,strname,strdetel,strbath);
            listView.setAdapter(objMyAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    name = strname;
                    detel = strdetel;
                    image = strbath;

                    Intent intent = new Intent(Tour.this, Detail.class);
                    intent.putExtra("name", strname[position]);
                    intent.putExtra("detel",strdetel[position]);
                    intent.putExtra("image",strbath[position]);
                    startActivity(intent);

                }
            });
        }

        private void Cdatabase() {
            objTravelTable = new TravelTable(this);
        }

        private void BindWidget() {
            listView = (ListView) findViewById(R.id.allTour);
        }


    public void onClickBack (View view) {
        finish();
    }
    public void onClickHome (View view){
        Intent intent = new Intent(Tour.this, MainActivity.class);
        startActivity(intent);
    }
}
