package sdu.wirattapong.rattanakosinisland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
    public void onClickTour (View view) {
        Intent intent = new Intent(MainActivity.this, Tour.class);
        startActivity(intent);
    }

    public void onClickFood (View view){
        Intent intent = new Intent(MainActivity.this, Food.class);
        startActivity(intent);
    }

}
