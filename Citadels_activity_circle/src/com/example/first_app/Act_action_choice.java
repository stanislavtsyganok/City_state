package com.example.first_app;

import java.util.ArrayList;
import java.util.logging.Logger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
//TODO кол-во золота и карт

public class Act_action_choice extends Activity {
	Logger log = Logger.getLogger(Act_game_screen.class.getName());
	
	Button btnChooseGold;
	Button btnChooseCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.action_choose);
	    
	    btnChooseGold = (Button) findViewById(R.id.btn_gold);
	    btnChooseCards = (Button) findViewById(R.id.btn_cards);
	    
		Intent intent = getIntent();
		  
		 
		ArrayList<Role> gameRoleDeck = intent.getParcelableArrayListExtra("gameRoleDeck");
		ArrayList<Role> gameRoleDeckTurn = intent.getParcelableArrayListExtra("gameRoleDeckTurn");
		ArrayList<Role> openedRoleTurn = intent.getParcelableArrayListExtra("openedRoleTurn");
		final ArrayList<Building> gameBuildingDeck = intent.getParcelableArrayListExtra("gameBuildingDeck");
		  
		final ArrayList<Player> players = intent.getParcelableArrayListExtra("players");
		final int iterator_turn = intent.getIntExtra("iterator_turn", 0);
		  
		final Intent next_intent = new Intent(this, Act_building.class);
		next_intent.putExtra("iterator_turn", iterator_turn);
		next_intent.putExtra("gameRoleDeck", gameRoleDeck);
		next_intent.putExtra("openedRoleTurn", openedRoleTurn);
		next_intent.putExtra("gameRoleDeckTurn", gameRoleDeckTurn);
		next_intent.putExtra("gameBuildingDeck", gameBuildingDeck);
		next_intent.putExtra("players", players);
	    
	    OnClickListener oncl_choice_sc = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.btn_cards:
					players.get(iterator_turn).pickTwoCards(gameBuildingDeck);
					startActivity(next_intent);
					finish();
					
					break;

				case R.id.btn_gold:
					players.get(iterator_turn).pickTwoCoins();
					startActivity(next_intent);
					finish();
			    		
					break;

				default:
					break;
				}
			}
			
		};
		btnChooseCards.setOnClickListener(oncl_choice_sc);
		btnChooseGold.setOnClickListener(oncl_choice_sc);
	    
    }
}
