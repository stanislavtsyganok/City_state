package com.example.first_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Act_first_screen extends Activity {

	Button btn_new_game;
	Button btn_setting;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_fa);
        
        btn_new_game = (Button) findViewById(R.id.new_game);
        btn_setting = (Button) findViewById(R.id.Settings);
        
        OnClickListener oncb_new_game = new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Act_first_screen.this, Act_players_number.class);
				startActivity(intent);
			}
        	
        };
        btn_new_game.setOnClickListener(oncb_new_game);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_fa, menu);
        return true;
    }
    
}
