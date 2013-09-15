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

public class Act_player_invite extends Activity {
	
	Logger log = Logger.getLogger(Act_game_screen.class.getName());
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		  super.onCreate(savedInstanceState);
		
		  LinearLayout linL = new LinearLayout(this);
		  linL.setOrientation(LinearLayout.VERTICAL);
		  LayoutParams linLParams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		  setContentView(linL,linLParams);
		  
		  Intent intent = getIntent();
		  
		  ArrayList<Role> gameRoleDeck = intent.getParcelableArrayListExtra("gameRoleDeck");
		  ArrayList<Role> gameRoleDeckTurn = intent.getParcelableArrayListExtra("gameRoleDeckTurn");
		  ArrayList<Role> openedRoleTurn = intent.getParcelableArrayListExtra("openedRoleTurn");
		  ArrayList<Building> gameBuildingDeck = intent.getParcelableArrayListExtra("gameBuildingDeck");
		  
		  ArrayList<Player> players = intent.getParcelableArrayListExtra("players");
		  		  
		  int iterator_turn = intent.getIntExtra("iterator_turn", 0);
		  
		  LinearLayout.LayoutParams lpMatchContent = new LinearLayout.LayoutParams (LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		  lpMatchContent.gravity = Gravity.LEFT;
		  
		  TextView tv = new TextView(this);
		  tv.setText("Сейчас ход - "+players.get(iterator_turn).getPlayerName()+". Он "+players.get(iterator_turn).getPlayerRole());
		  tv.setLayoutParams(lpMatchContent);
		  linL.addView(tv);
		  
		  final Intent next_intent = new Intent(this, Act_action_choice.class);
		  next_intent.putExtra("iterator_turn", iterator_turn);
		  next_intent.putExtra("gameRoleDeck", gameRoleDeck);
		  next_intent.putExtra("openedRoleTurn", openedRoleTurn);
		  next_intent.putExtra("gameRoleDeckTurn", gameRoleDeckTurn);
		  next_intent.putExtra("gameBuildingDeck", gameBuildingDeck);
		  next_intent.putExtra("players", players);
		  
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
