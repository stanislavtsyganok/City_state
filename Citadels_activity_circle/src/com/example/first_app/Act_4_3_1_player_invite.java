package com.example.first_app;

import java.util.ArrayList;
import java.util.logging.Logger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Act_4_3_1_player_invite extends Activity {
	
	Logger log = Logger.getLogger(Act_4_1_circle_begining.class.getName());
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		  super.onCreate(savedInstanceState);
		
		  LinearLayout linL = new LinearLayout(this);
		  linL.setOrientation(LinearLayout.VERTICAL);
		  LayoutParams linLParams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		  setContentView(linL,linLParams);
		  
		  Intent intent = getIntent();
		  
		  ArrayList<Player> players = ((CityApp)getApplication()).getPlayers();
		  int iterator_turn = intent.getIntExtra("iterator_turn", 0);
		  
		  LinearLayout.LayoutParams lpMatchContent = new LinearLayout.LayoutParams (LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		  lpMatchContent.gravity = Gravity.LEFT;
		  
		  TextView tv = new TextView(this);
		  tv.setText("Сейчас ход - "+players.get(iterator_turn).getPlayerName()+". Он "+players.get(iterator_turn).getPlayerRole());
		  tv.setLayoutParams(lpMatchContent);
		  linL.addView(tv);
		  
		  final Intent next_intent = new Intent(this, Act_4_3_2_action_choice.class);
		  next_intent.putExtra("iterator_turn", iterator_turn);

		  linL.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if (event.getAction()==MotionEvent.ACTION_DOWN)
						startActivity(next_intent);
					    finish();
			    	return true;
				}
		  });
	}
}
