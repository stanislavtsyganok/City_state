package com.example.first_app;

import java.util.ArrayList;
import java.util.Collections;
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

public class Act_4_1_circle_begining extends Activity{
	  Logger log = Logger.getLogger(Act_4_1_circle_begining.class.getName());
	  
	  Game_func gameFunction;
	  
	  ArrayList<Role> gameRoleDeckTurn;
	  ArrayList<Role> openedRoleTurn;
	  
	  
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
		   
		  super.onCreate(savedInstanceState);
		  
		  LinearLayout linL = new LinearLayout(this);
		  linL.setOrientation(LinearLayout.VERTICAL);
		  LayoutParams linLParams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		  setContentView(linL,linLParams);
		  
		  Intent intent = getIntent();
		  
		  ArrayList<Role> gameRoleDeck = intent.getParcelableArrayListExtra("gameRoleDeck");
		  ArrayList<Building> gameBuildingDeck = intent.getParcelableArrayListExtra("gameBuildingDeck");
		  
		  ArrayList<Player> players = intent.getParcelableArrayListExtra("players");
		  
		  gameFunction = new Game_func(this);
		  
		  Collections.sort(players, new PlayerCompareId());
		 
		  gameRoleDeckTurn = gameFunction.CreateRoleDeckTurn(gameRoleDeck, players.size());
		  
		  openedRoleTurn = new ArrayList <Role>();
		  
		  for (int i=0; i<8-players.size()-2;i++){
			  openedRoleTurn.add(gameRoleDeckTurn.get(0));
			  gameRoleDeckTurn.remove(0);
		  }
		  gameRoleDeckTurn.trimToSize();
		  
		  final Intent intent_next = new Intent(Act_4_1_circle_begining.this, Act_4_2_role_choice.class);
		  
		  intent_next.putExtra("gameRoleDeck", gameRoleDeck);
		  intent_next.putExtra("openedRoleTurn", openedRoleTurn);
		  intent_next.putExtra("gameRoleDeckTurn", gameRoleDeckTurn);
		  intent_next.putExtra("gameBuildingDeck", gameBuildingDeck);
		  intent_next.putExtra("players", players);
		  
		  LinearLayout.LayoutParams lpMatchContent = new LinearLayout.LayoutParams (LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		  lpMatchContent.gravity = Gravity.LEFT;
		  
		  TextView tv = new TextView(this);
		  tv.setText(players.get(0).getPlayerName()+" выбери себе персонажа");
		  tv.setLayoutParams(lpMatchContent);
		  linL.addView(tv);
		  
		  linL.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if (event.getAction()==MotionEvent.ACTION_DOWN)
						  startActivity(intent_next);
					  finish();
					  return true;
				}
		  });
	  }
}

