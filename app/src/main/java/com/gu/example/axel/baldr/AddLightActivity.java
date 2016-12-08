package com.gu.example.axel.baldr;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Axel on 02-Oct-16.
 */

public class AddLightActivity extends AppCompatActivity {


    private TextView textView;
    private EditText addcode;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_light);

        Button btn = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.text);
        addcode   = (EditText)findViewById(R.id.discoveryCode);
        add = (Button) findViewById(R.id.button3);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            addLight(view);

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        setTitle("New Light");
    }

    public void exitAdd(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void addLight(View view) {
        JSONObject jin =  new JSONObject();
        JSONObject newLight = new JSONObject();
        String code = addcode.getText().toString();

        try {
            // TODO: Get home id, this is app specific and read from SharedPreferences, if none exists on first run it needs to be generated.
            // App will probably have this as a variable we can just use here.
            String homeid="something";
            jin.put("discoveryCode",code);
            jin.put("home",homeid);
            newLight.put("version", 1);
            newLight.put("protocolName", "baldr");
            newLight.put("discovery",jin);
        }catch(JSONException e){
            System.out.println(e);
        }
        // TODO send message using MQTTConnection
    }

}
