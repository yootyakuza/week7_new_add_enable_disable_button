package com.example.apple.week7;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Activity1_Main extends AppCompatActivity {

    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity1__main);


        final Context context = getApplicationContext();
        String filename = "sarayutData";
        String string = "Hello world!";
        b1 = (Button)findViewById(R.id.but1);

        File file = new File(context.getFilesDir(),filename);

        try
        {
            FileOutputStream oS = openFileOutput(filename,context.MODE_PRIVATE);
            oS.write(string.getBytes());
            oS.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try
        {
            InputStream iS = context.openFileInput(filename);
            if(iS != null){
                InputStreamReader iR = new InputStreamReader(iS);
                BufferedReader bfR = new BufferedReader(iR);

                String recieveString = "";
                StringBuilder sb = new StringBuilder();

                while((recieveString = bfR.readLine()) != null)
                {
                    sb.append(recieveString);
                }
                iS.close();
                String ret = sb.toString();
                Toast t = Toast.makeText(context,"Read file successfully : " + ret,Toast.LENGTH_SHORT);
                t.show();

            }

        } catch (IOException e) {
            Log.e("login activity","Can not read file: " + e.toString());
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context.getApplicationContext(),Activity2.class);
                startActivity(i);
            }
        });
    }
}
