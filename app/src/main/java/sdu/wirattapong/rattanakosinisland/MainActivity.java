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
        while(intTimes <= 4){

            //Variable and Constant
            InputStream objInputStream = null;
            String strJSON = null;
            String strTravelTabel = "http://5711020660016.sci.dusit.ac.th/traveltable.php";
            String strOrderURL = "http://5711020660016.sci.dusit.ac.th/ordertable.php";
            HttpPost objHttpPost = null;

            //1.Create InputStream
            try{
                HttpClient objHttpClient = new DefaultHttpClient();
                switch (intTimes){
                    case 3:
                        objHttpPost = new HttpPost(strTravelTabel);
                        break;
                    default:
                        objHttpPost = new HttpPost(strOrderURL);
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
                            String order_ID = jsonObject.getString("or_id");
                            String order_MENU = jsonObject.getString("order_MENU");
                            String or_Detel = jsonObject.getString("or_Detel");
                            String or_Long = jsonObject.getString("or_Long");
                            String or_Lat = jsonObject.getString("or_Lat");
                            String or_Image = jsonObject.getString("or_Image");
                            String or_price = jsonObject.getString("or_price");
                            objOrberTable.AddNewOrderTable(order_ID,order_MENU,or_Detel,or_Long,or_Lat,or_Image,or_price);
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
                            objTravelTabel.AddNewOrderTable(tvl_ID,tvl_Travel,tvl_Source,tvl_opentime,tvl_Image,tvl_lat,tvl_long);
                            break;
//                        case 2:
//                            //update OrderTable
//                            String id_member = jsonObject.getString("id_member");
//                            String id_menu = jsonObject.getString("id_menu");
//                            String date_order = jsonObject.getString("date_order");
//                            String price_order = jsonObject.getString("price_order");
//                            String status = jsonObject.getString("status");
//                            objOrderTable.AddNewOrderTable(id_member,id_menu,date_order,price_order,status);
//                            break;
//
//                        default:
//                            //update OrderListTable
//                            String id_order = jsonObject.getString("id_order");
//                            String id_menulist = jsonObject.getString("id_menu");
//                            String amount = jsonObject.getString("amount");
//                            String total = jsonObject.getString("Price");
//                            objOrderListTable.AddNewOrderListTable(id_order,id_menulist,amount,total);
//                            break;
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
