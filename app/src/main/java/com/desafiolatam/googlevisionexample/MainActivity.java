package com.desafiolatam.googlevisionexample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.MagicalPermissions;

public class MainActivity extends AppCompatActivity implements LabelsCallback{
    private MagicalPermissions magicalPermissions;
    private int RESIZE_PHOTO_PIXELS_PERCENTAGE = 50;
    private MagicalCamera magicalCamera;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Toast.makeText(this, "Take a picture!" , Toast.LENGTH_SHORT).show();

        String[] permissions = new String[] {
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        magicalPermissions = new MagicalPermissions(this, permissions);
        magicalCamera = new MagicalCamera(this, RESIZE_PHOTO_PIXELS_PERCENTAGE, magicalPermissions);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                magicalCamera.takePhoto();
            }
        });


        //String link = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4d/Cat_November_2010-1a.jpg/220px-Cat_November_2010-1a.jpg";

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        magicalPermissions.permissionResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        magicalCamera.resultPhoto(requestCode, resultCode, data);
        if (RESULT_OK == resultCode)
        {
            //image = magicalCamera.getPhoto();
            fab.setTag(magicalCamera.getPhoto());

        }

    }

    @Override
    public Bitmap getBitmap() {
        /*Bitmap auxImage = image;
        image = null;
        return auxImage;*/
        return null;
    }
}
