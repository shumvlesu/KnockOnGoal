package com.example.knockongoal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;
import static android.Manifest.permission.CALL_PHONE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final GifImageView gif = findViewById(R.id.mushroom);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                gif.setRotation(gif.getRotation() + 180);

                Intent i = new Intent(Intent.ACTION_CALL);
                //Калитка на рахманинова
                //i.setData(Uri.parse("tel:+79385162364"));
                //Калитка на Есенина
                //i.setData(Uri.parse("tel:+79385162365"));
                //Ворота на рахманинова
                i.setData(Uri.parse("tel:+79385159516"));
                if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(i);
                } else {
                    requestPermissions(new String[]{CALL_PHONE}, 1);
                }


            }
        };
        gif.setOnClickListener(onClickListener);







    }
}
