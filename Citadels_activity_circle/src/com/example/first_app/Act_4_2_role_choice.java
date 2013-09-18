package com.example.first_app;

import java.util.ArrayList;
import java.util.logging.Logger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Act_4_2_role_choice extends Activity {
	Logger log = Logger.getLogger(Act_4_1_circle_begining.class.getName());
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		  super.onCreate(savedInstanceState);
		  
		  Intent intent = getIntent();
		  
		  ArrayList<Player> players = ((CityApp)getApplication()).getPlayers();
		  ArrayList<Role> openedRoleTurn = ((CityApp)getApplication()).getOpenedRoles();
		  
		  ArrayList<Role> gameRoleDeckTurn = intent.getParcelableArrayListExtra("gameRoleDeckTurn");
		  final int iterator_role_choose = intent.getIntExtra("iterator_role_choose", 0)+1;
		
		  LinearLayout linL = new LinearLayout(this);
		  linL.setOrientation(LinearLayout.VERTICAL);
		  LayoutParams linLParams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		  setContentView(linL,linLParams);
		  
		  LinearLayout.LayoutParams lpMatchContent = new LinearLayout.LayoutParams (LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		  lpMatchContent.gravity = Gravity.LEFT;
		  
		  String openedRoles = "Открытые роли: ";
		  for(int i=0; i<openedRoleTurn.size();i++)
			  openedRoles += openedRoleTurn.get(i).getName()+", ";
		  		  
		  openedRoles = openedRoles.substring(0, openedRoles.length()-2)+".";
	      
		  TextView tv = new TextView(this);
	      tv.setText(players.get(iterator_role_choose-1).getPlayerName()+" выбери роль.");
	      tv.setLayoutParams(lpMatchContent);
	      linL.addView(tv);
	      
	      tv = new TextView(this);
	      tv.setText(openedRoles);
	      tv.setLayoutParams(lpMatchContent);
	      linL.addView(tv);
		  
		  
		  Game_func gameF = new Game_func();
		  
		  //ArrayList<Building> gameBuildingDeck;
		  //ArrayList<Role> gameRoleDeck;
		  //ArrayList<Role> gameRoleDeckTurn;
		  //ArrayList<Role> openedRoleTurn;
		  
		  //ArrayList<Player> players;
		  
		  //gameRoleDeck = intent.getParcelableArrayListExtra("gameRoleDeck");
		  //openedRoleTurn = intent.getParcelableArrayListExtra("openedRoleTurn");
		  //gameBuildingDeck = intent.getParcelableArrayListExtra("gameBuildingDeck");
		  
		  //players = intent.getParcelableArrayListExtra("players");
		  		  

		  
		  gameF.setViewRoleChoise(linL, players, gameRoleDeckTurn, iterator_role_choose, this);
		
	}
}
