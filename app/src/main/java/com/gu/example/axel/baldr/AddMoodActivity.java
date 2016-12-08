package com.gu.example.axel.baldr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Button;

/* Created by Axel on 02-Oct-16.
 */

public class AddMoodActivity extends AppCompatActivity {
    private Button save;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_mood);
        setTitle("New Mood");

        Button save = (Button) findViewById(R.id.button2);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveMood(view);
                /* editor.putString("jsondata", jobj.toString());
                String strJson = sharedPref.getString("jsondata","0");//second parameter is necessary ie.,Value to return if this preference does not exist.
if(strJson != null) JSONObject jsonData = new JSONObject(strJson);
                 */

            }
        });

    }

    public void exitAdd(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void saveMood(View view){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Lightarray","Test");
        editor.commit();
    }
}
