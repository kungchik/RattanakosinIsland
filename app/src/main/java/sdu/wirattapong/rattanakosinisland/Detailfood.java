package sdu.wirattapong.rattanakosinisland;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Detailfood extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailfood);
        //Initial View
        TextView titleTextView = (TextView) findViewById(R.id.txtTitleDetail);
        TextView DetailTextView = (TextView) findViewById(R.id.txtDetailScroll);
        ImageView imageView = (ImageView) findViewById(R.id.imvDetail);


        //รับข้อมูลจาก Main Activity มาแสดงผลบน Detail
        titleTextView.setText(getIntent().getStringExtra("Title"));
        DetailTextView.setText(getIntent().getStringExtra("Detail"));
        imageView.setImageResource(getIntent().getIntExtra("Image",R.drawable.img_food));



    } //Main Method

    public void onClickBack(View view) {
        finish();
    }
    public void onClickHome (View view){
        finish();}
    public void onClickFood (View view){
        Intent intent = new Intent(Detailfood.this, Food.class);
        startActivity(intent);
    }
}

