package sdu.wirattapong.rattanakosinisland;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import sdu.wirattapong.rattanakosinisland.Database.OrberTable;
import sdu.wirattapong.rattanakosinisland.Database.TravelTabel;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private OrberTable objOrberTable;
    private TravelTabel objTravelTabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Cdatabase();
        //addtest();
        deleteAllData();

        synJSONtoSQLite();

    }
    private void deleteAllData() {
        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase("travelguide.db",MODE_APPEND,null);
        objSqLiteDatabase.delete("traveltable",null,null);
        objSqLiteDatabase.delete("ordertable",null,null);

    }


    private void synJSONtoSQLite() {
        StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(myPolicy);

        //Loop 2 Time
        int intTimes = 0;
        while(intTimes <= 1){

            //Variable and Constant
            InputStream objInputStream = null;
            String strJSON = null;
            String strOrderURL = "http://5711020660016.sci.dusit.ac.th/ordertable.php";
            String strTravelTabel = "http://5711020660016.sci.dusit.ac.th/traveltable.php";
            HttpPost objHttpPost = null;

            //1.Create InputStream
            try{
                HttpClient objHttpClient = new DefaultHttpClient();
                switch (intTimes){
                    case 0:
                        objHttpPost = new HttpPost(strOrderURL);
                        break;
                    default:
                        objHttpPost = new HttpPost(strTravelTabel);
                        break;
                }
                HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
                HttpEntity objHttpEntity = objHttpResponse.getEntity();
                objInputStream = objHttpEntity.getContent();
            }catch (Exception e){
                Log.d("bakery","InputStream ==> "+e.toString());
            }
            //2.Create strJSON
            try{
                InputStreamReader objInputStreamReader = new InputStreamReader(objInputStream,"UTF-8");
                BufferedReader objBufferedReader = new BufferedReader(objInputStreamReader);
                StringBuilder objStringBuilder = new StringBuilder();
                String strLine = null;
                while((strLine = objBufferedReader.readLine()) != null){
                    objStringBuilder.append(strLine);
                }
                objInputStream.close();
                strJSON = objStringBuilder.toString();
            }catch (Exception e){
                Log.d("masterUNG","strJSON"+e.toString());
            }
            //3.Update to SQLite
            try{
                JSONArray objJsonArray = new JSONArray(strJSON);
                for(int i =0;i<objJsonArray.length();i++){
                    JSONObject jsonObject = objJsonArray.getJSONObject(i);
                    switch (intTimes){
                        case 0:
                            //update OrberTable
                            String or_name = jsonObject.getString("or_name");
                            String or_detel = jsonObject.getString("or_detel");
                            objOrberTable.AddNewOrderTable(or_name,or_detel);
                            break;
                        default:
                            //update MenuTable
                            String tvl_ID = jsonObject.getString("tvl_ID");
                            String tvl_Travel = jsonObject.getString("tvl_Travel");
                            String tvl_Source = jsonObject.getString("tvl_Source");
                            String tvl_opentime = jsonObject.getString("tvl_opentime");
                            String tvl_Image = jsonObject.getString("tvl_Image");
                            String tvl_lat = jsonObject.getString("tvl_lat");
                            String tvl_long = jsonObject.getString("tvl_long");
                            objTravelTabel.AddNewOrderTable(tvl_Travel,tvl_Source,tvl_opentime,tvl_Image,tvl_lat,tvl_long);
                            break;
                    }
                }
            }catch (Exception e){
                Log.d("mbakery","strJSON"+e.toString());
            }
            //Increase intTimes
            intTimes += 1;
        }
    } // ซิ้งข้อมูลจาก mysql to sqlite

    private void Cdatabase() {//open database
        objOrberTable = new OrberTable(this);
        objTravelTabel = new TravelTabel(this);
    }



    public void onClickTour (View view) {
        Intent intent = new Intent(MainActivity.this, Tour.class);
        startActivity(intent);
    }

    public void onClickFood (View view){
        Intent intent = new Intent(MainActivity.this, Foodtry.class);
        startActivity(intent);
    }
    public void onClickHome (View view){
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
