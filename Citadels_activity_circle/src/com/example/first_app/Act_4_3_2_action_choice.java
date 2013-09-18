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

public class Act_4_3_2_action_choice extends Activity {
	Logger log = Logger.getLogger(Act_4_1_circle_begining.class.getName());
	
	Button btnChooseGold;
	Button btnChooseCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.action_choose);
	    
	    btnChooseGold = (Button) findViewById(R.id.btn_gold);
	    btnChooseCards = (Button) findViewById(R.id.btn_cards);
	    
		Intent intent = getIntent();
		 
		final int iterator_turn = intent.getIntExtra("iterator_turn", 0);
		final ArrayList<Player> players = ((CityApp)getApplication()).getPlayers();
		final ArrayList<Building> gameBuildingDeck = ((CityApp)getApplication()).getBuildingDeck();
		
		((CityApp)getApplication()).setPlayers(players);
		((CityApp)getApplication()).setBuildingDeck(gameBuildingDeck);
		  
		final Intent next_intent = new Intent(this, Act_4_3_3_building.class);
		next_intent.putExtra("iterator_turn", iterator_turn);
		
	    OnClickListener oncl_choice_sc = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.btn_cards:
					players.get(iterator_turn).pickTwoCards(gameBuildingDeck);
					((CityApp)getApplication()).setPlayers(players);
					((CityApp)getApplication()).setBuildingDeck(gameBuildingDeck);
					startActivity(next_intent);
					finish();
					
					break;

				case R.id.btn_gold:
					players.get(iterator_turn).pickTwoCoins();
					((CityApp)getApplication()).setPlayers(players);
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
