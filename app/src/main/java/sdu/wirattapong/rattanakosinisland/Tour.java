package sdu.wirattapong.rattanakosinisland;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import sdu.wirattapong.rattanakosinisland.Database.TravelTabel;

public class Tour extends AppCompatActivity {
    private TravelTabel objTravelTable;
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
            objTravelTable = new TravelTabel(this);
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
