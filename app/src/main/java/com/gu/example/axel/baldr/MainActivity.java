package com.gu.example.axel.baldr;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private int fabState = 1;
    BottomBar bottomBar;
    private Toolbar toolbar;
    FloatingActionButton fab;

    ListView listView;
    Json res;
    CustomAdapter ca;
    Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.lightList);

        String  resString = res.getLightInfo().getRoom();
        gson = new Gson();
        res = gson.fromJson(resString,Json.class);
        ca = new CustomAdapter(MainActivity.this, (List<Json>) res.getLightInfo());

        listView.setAdapter(ca);




        System.out.println("heeey " + getApplicationContext());
        /* MqttConnection connection = new MqttConnection(getApplicationContext());
        connection.connect();*/


        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.bringToFront();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fabState == 1) {
                    //Add light
                    Intent intent = new Intent(MainActivity.this, AddLightActivity.class);
                    startActivity(intent);
                }
                else if (fabState == 2){
                    //Add room
                    Intent intent = new Intent(MainActivity.this, AddRoomActivity.class);
                    startActivity(intent);
                }
                else if (fabState == 3){
                    //Add mood
                    Intent intent = new Intent(MainActivity.this, AddMoodActivity.class);
                    startActivity(intent);
                }
            }
        });

        //Setting listners for the bottombar, switching fragments and altering the FAB.
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.lightTab){
                    LightFragment f = new LightFragment();
                    fabState = 1;
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame,f)
                            .commit();
                    fab.show();

                    setTitle("Lights");

                }
                else if (tabId == R.id.roomTab){
                    RoomFragment f = new RoomFragment();
                    fabState = 2;
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame,f)
                            .commit();
                    fab.show();
                    setTitle("Rooms");
                }
                else if (tabId == R.id.moodTab){
                    MoodFragment f = new MoodFragment();
                    fabState = 3;
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame,f)
                            .commit();
                    fab.show();
                    setTitle("Moods");
                }
            }

        });
    }

    public void editLight(LightObject data){
            LightObject light = data;
            EditLight f = new EditLight();
            this.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame, f)
                    .commit();
            fab.hide();
            setTitle("Edit " + light.getName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
