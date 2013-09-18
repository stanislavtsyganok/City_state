package com.example.first_app;

import java.util.ArrayList;
import java.util.logging.Logger;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

public class Act_3_game_roles extends Activity {
	Logger log = Logger.getLogger(Act_4_1_circle_begining.class.getName());
	  
	Game_func gameFunction;
	ArrayList<Role> gameRoleDeck;
	ArrayList<Building> gameBuildingDeck;
	  
	ArrayList<Player> players;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		int playersNumber = intent.getIntExtra("playersNumber", 4);
		
		gameFunction = new Game_func(this);
		gameRoleDeck = gameFunction.CreateRoleDeck();
		  
		gameBuildingDeck = gameFunction.CreateBuildingDeck();
		players = new ArrayList<Player>(playersNumber);

		int isC = 1;
		for(int i=1;i<=playersNumber;i++){
			if(i!=1)
				isC = 0;
			players.add(new Player(i, "Player "+i, gameBuildingDeck, isC));
		}
		
		intent = new Intent(this, Act_4_1_circle_begining.class);
		
		((CityApp)getApplication()).setGlobalVar(gameBuildingDeck, gameRoleDeck, players);
		  
		startActivity(intent);
		finish();


	}
}
