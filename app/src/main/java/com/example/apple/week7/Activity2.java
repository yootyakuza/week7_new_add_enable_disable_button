package com.example.apple.week7;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Activity2 extends AppCompatActivity {

    Button bt1S, bt2R;
    EditText Mult1;
    TextView tw;
    private String Data = "yutData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        final Context context = getApplicationContext();
        bt1S = (Button) findViewById(R.id.butS);
        bt2R = (Button) findViewById(R.id.butR);
        bt1S.setEnabled(false);
        bt2R.setEnabled(false);

        Mult1 = (EditText) findViewById(R.id.editText1);
        tw = (TextView) findViewById(R.id.textView1);

        Mult1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Button สามารถใช้ได้หรือ enable ได้ต่อเมื่อพิมพ์ตัวอักษร >= 2 ตัว
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Button สามารถใช้ได้เมื่อพิมพ์ตัวอักษร > 0

                if (charSequence.toString().isEmpty()) {
                    bt1S.setEnabled(false);
                    bt2R.setEnabled(false);
                } else {
                    bt1S.setEnabled(true);
                    bt2R.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Button สามารถใช้ได้เมื่อพิมพ์ตัวอักษร > 0

//                if(editable.toString().isEmpty()){
//                    bt2R.setEnabled(false);
//                }
//                else {
//                    bt2R.setEnabled(true);
//                }
            }
        });

        bt1S.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String string = Mult1.getText().toString();
                File file = new File(context.getFilesDir(), Data);

                try {
                    FileOutputStream oS = openFileOutput(Data, context.MODE_PRIVATE);
                    oS.write(string.getBytes());
                    Toast t = Toast.makeText(context, "Save file successfully: " + string, Toast.LENGTH_SHORT);
                    t.show();
                    oS.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        bt2R.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    InputStream iS = context.openFileInput(Data);
                    if (iS != null) {
                        InputStreamReader iR = new InputStreamReader(iS);
                        BufferedReader bfR = new BufferedReader(iR);

                        String recieveString = "";
                        StringBuilder sb = new StringBuilder();

                        while ((recieveString = bfR.readLine()) != null) {
                            sb.append(recieveString);
                        }
                        iS.close();
                        String ret = sb.toString();
                        tw.setText(ret);
                    }

                } catch (IOException e) {
                    Log.e("test activity", "Can not read file: " + e.toString());
                }
            }
        });

    }
}
