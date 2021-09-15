package com.example.phone_call_activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText num;
    Button call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num=findViewById(R.id.number_id);
        call=findViewById(R.id.Call_id);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int permission= ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CALL_PHONE);
                if(permission== PackageManager.PERMISSION_GRANTED){
                    CallMethod();
                }else
                {
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},0);
                }
            }


        });

    }
    private void CallMethod() {
        String NUMBER=num.getText().toString().trim();

        if (!NUMBER.isEmpty()){
            Intent intent=new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:"+NUMBER));
            startActivity(intent);
        }
        else
        {
            Toast.makeText(MainActivity.this, "Please enter valid Number", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 0:
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)//permission result nver null
                {
                    CallMethod();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "You Don't have permission", Toast.LENGTH_SHORT).show();
                }
        }
    }
}