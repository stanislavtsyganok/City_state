package com.example.first_app;

import java.util.ArrayList;
import java.util.logging.Logger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Act_game_roles extends Activity {
	Logger log = Logger.getLogger(Act_game_screen.class.getName());
	  
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
		
		intent = new Intent(this, Act_game_screen.class);
		  
		intent.putExtra("gameRoleDeck", gameRoleDeck);
		intent.putExtra("gameBuildingDeck", gameBuildingDeck);
		intent.putExtra("players", players);
		  
		startActivity(intent);
		finish();


	}
}
