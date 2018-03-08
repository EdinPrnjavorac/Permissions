package com.example.edinprnjavorac.permissions;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnPermissions;

    private static final int UNIQUE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPermissions = (Button) findViewById(R.id.btnPermissions);

        btnPermissions.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    UNIQUE_REQUEST_CODE);

                }

                    else {
                    Toast.makeText(MainActivity.this, "Permission granted", Toast.LENGTH_SHORT).show();

                    }
                }



        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==UNIQUE_REQUEST_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Thank you, permission granted", Toast.LENGTH_SHORT).show();
            }
            else if (grantResults[0] == PackageManager.PERMISSION_DENIED)
            {

                if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {



                AlertDialog.Builder dialog = new AlertDialog.Builder(this);

                dialog.setMessage("This permission is important to save a file to the phone! Please permit it!")
                        .setTitle("Important permission required!");

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                UNIQUE_REQUEST_CODE);
                    }
                });

                dialog.setNegativeButton("NO THANKS", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(MainActivity.this, "Cannot be done!", Toast.LENGTH_SHORT).show();

                    }
                });

                dialog.show();
                }
                else
                {
                    Toast.makeText(this, "We will never show this to you again!", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
}
