package sdu.wirattapong.rattanakosinisland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class Food extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);}
    
    public void onClickBack (View view) {finish();}
    public void onClickHome (View view){Intent intent = new Intent(Food.this, MainActivity.class);
        startActivity(intent);}
}
