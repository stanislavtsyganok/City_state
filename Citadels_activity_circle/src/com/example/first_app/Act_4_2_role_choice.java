package com.example.first_app;

import java.util.ArrayList;
import java.util.logging.Logger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class Act_4_2_role_choice extends Activity {
	Logger log = Logger.getLogger(Act_4_1_circle_begining.class.getName());
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		  super.onCreate(savedInstanceState);
		
		  LinearLayout linL = new LinearLayout(this);
		  linL.setOrientation(LinearLayout.VERTICAL);
		  LayoutParams linLParams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		  setContentView(linL,linLParams);
		  
		  Intent intent = getIntent();
		  
		  Game_func gameF = new Game_func();
		  
		  ArrayList<Building> gameBuildingDeck;
		  ArrayList<Role> gameRoleDeck;
		  ArrayList<Role> gameRoleDeckTurn;
		  ArrayList<Role> openedRoleTurn;
		  
		  ArrayList<Player> players;
		  
		  gameRoleDeck = intent.getParcelableArrayListExtra("gameRoleDeck");
		  gameRoleDeckTurn = intent.getParcelableArrayListExtra("gameRoleDeckTurn");
		  openedRoleTurn = intent.getParcelableArrayListExtra("openedRoleTurn");
		  gameBuildingDeck = intent.getParcelableArrayListExtra("gameBuildingDeck");
		  
		  players = intent.getParcelableArrayListExtra("players");
		  		  
		  final int iterator_role_choose = intent.getIntExtra("iterator_role_choose", 0)+1;
		  
		  gameF.setViewRoleChoise(linL, players, openedRoleTurn, gameRoleDeck, gameRoleDeckTurn, gameBuildingDeck, iterator_role_choose, this);
		
	}
}
