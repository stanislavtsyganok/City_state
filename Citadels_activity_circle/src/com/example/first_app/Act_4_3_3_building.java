package com.example.first_app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Act_4_3_3_building extends Activity {
	Logger log = Logger.getLogger(Act_4_1_circle_begining.class.getName());
	
	
	TextView cointsTV;
	Button nextTurnBtn;
	Button observeBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
			
		Game_func gameF = new Game_func();	
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.building);
	    
	 	Intent intent = getIntent();
		 
		//final ArrayList<Role> gameRoleDeck = intent.getParcelableArrayListExtra("gameRoleDeck");
		//final ArrayList<Role> openedRoleTurn = intent.getParcelableArrayListExtra("openedRoleTurn");
		//final ArrayList<Building> gameBuildingDeck = intent.getParcelableArrayListExtra("gameBuildingDeck");
		  
		//final ArrayList<Player> players = intent.getParcelableArrayListExtra("players");
	 	final ArrayList<Player> players = ((CityApp)getApplication()).getPlayers();
	 	final boolean isLastTurn = ((CityApp)getApplication()).getIsLast();
		final int iterator_turn = intent.getIntExtra("iterator_turn", 0);
		int builded = intent.getIntExtra("builded", 0);
		//final boolean isLastTurn = intent.getBooleanExtra("isLastTurn", false);
		
		TextView cointsTV = (TextView) findViewById(R.id.moneyAmount);
		cointsTV.setText(String.valueOf(players.get(iterator_turn).getGoldAmount()));
		
		observeBtn = (Button) findViewById(R.id.players_obs_btn);
		nextTurnBtn = (Button) findViewById(R.id.end_turn_btn);
		
		LinearLayout buildedL = (LinearLayout) findViewById(R.id.buildedLL);
		LinearLayout inHandL = (LinearLayout) findViewById(R.id.inHandLL);
		
		
		final Intent next_intent_cycle = new Intent(this, Act_4_3_1_player_invite.class);
		final Intent next_intent_new_turn = new Intent(this, Act_4_1_circle_begining.class);
		final Intent next_intent_obs = new Intent(this, Act_other_players.class);
		final Intent next_intent_game_results = new Intent(this, Act_other_players.class);
				
		gameF.setBuldingScreen(buildedL, inHandL, players, builded, iterator_turn, this);
		
		OnClickListener ocl = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent next_intent = new Intent();
				
				switch (v.getId()) {
				/*case R.id.special_btn:
					
					break;*/
				case R.id.players_obs_btn:
					next_intent = next_intent_obs;
					next_intent.putExtra("players", players);
					startActivity(next_intent);
					
					break;
				case R.id.end_turn_btn:
					
					if(iterator_turn<players.size()-1){
						
						next_intent = next_intent_cycle;
						//next_intent.putExtra("openedRoleTurn", openedRoleTurn);
						next_intent.putExtra("isLastTurn", isLastTurn);
						
					}
					else{
						next_intent = next_intent_new_turn;
						if(isLastTurn){
							Collections.sort(players, new PlayerComparePoints());
							next_intent = next_intent_game_results;
							//next_intent.putExtra("players", players);
							startActivity(next_intent);
							finish();
							
						}
							
					}
					
					next_intent.putExtra("iterator_turn", iterator_turn+1);
					//next_intent.putExtra("gameRoleDeck", gameRoleDeck);
					//next_intent.putExtra("gameBuildingDeck", gameBuildingDeck);
					//next_intent.putExtra("players", players);
					
					startActivity(next_intent);
					finish();
					
					break;
				}
				
			}
		};

		nextTurnBtn.setOnClickListener(ocl);
		observeBtn.setOnClickListener(ocl);
		
	}
	
	public void refresh(){}
}
