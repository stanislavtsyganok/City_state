package com.example.first_app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.database.Cursor;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Game_func {
	 Logger log = Logger.getLogger(Act_game_screen.class.getName());
	 	
	 Citadels_dao cDao;
	 static final int DeckCap = 8;
	 static final int NeededCol = 2;
	 
	 Common tmpCommon = new Common();
	 
	 
	 Game_func(Context context){
		 cDao = new Citadels_dao(context);

	 }
	 
	 Game_func(){
		 
	 }
	  
	 public ArrayList<Role> CreateRoleDeck(){ 
		 ArrayList<Role> tmpRoleDeck = new ArrayList<Role>(8);
		 Cursor tmpCursor = cDao.getAllRoles();
		 if(tmpCursor.moveToFirst())
			 for(int i=0;i<8;i++){//ѕозиции в курсоре с 0, а айди ролей с 1
				 tmpCursor.moveToPosition(i*2);
				 tmpRoleDeck.add(new Role(tmpCursor.getInt(0),tmpCursor.getString(1),tmpCursor.getString(2)));
			 }
		 tmpCursor.close();
		 return tmpRoleDeck;
	 }
	 
	 public ArrayList<Role> CreateRoleDeckTurn(ArrayList<Role> roles, int playersNum){
		 ArrayList<Role> tmpRolesDeck = new ArrayList<Role>(8);
		 tmpRolesDeck.addAll(roles);
		 ArrayList<Role> tmpRoleDeckTurn = new ArrayList<Role>(7);
		 int rand = tmpCommon.randNumber(roles.size());
		 tmpRolesDeck.remove(rand);
		 for(int i=0;i<8-playersNum-2;i++){
			 rand = tmpCommon.randNumber(roles.size()-i-1);
			 //log.info("нарандомило:"+tmpRolesDeck.get(rand).getName());
			 tmpRoleDeckTurn.add(tmpRolesDeck.get(rand));
			 tmpRolesDeck.remove(rand);
		 }
		 tmpRoleDeckTurn.addAll(tmpRolesDeck);
		 return tmpRoleDeckTurn;
	 }
	 
	 public ArrayList<Building> CreateBuildingDeck(){
		 ArrayList<Building> tmpBuildingDeck = new ArrayList<Building>();
		 Cursor tmpCursor = cDao.getAllBuildings();
		 if(tmpCursor.moveToFirst())
			 for(int i=0;i<tmpCursor.getCount();i++){
				 for(int n=0;n<tmpCursor.getInt(3);n++){
					 tmpBuildingDeck.add(new Building(tmpCursor.getInt(0),tmpCursor.getString(1),tmpCursor.getInt(2),tmpCursor.getInt(4),tmpCursor.getString(2)));
				 }
				 tmpCursor.moveToNext();
			 }
		 tmpCursor.close();
		 return tmpBuildingDeck;
		 
	 }
	 
	 public Building PickACard(ArrayList<Building> deck){
		 Building randomCard;
		 int rand = tmpCommon.randNumber(deck.size());
		 randomCard = deck.get(rand);
		 deck.remove(rand);
		 deck.trimToSize();
		 return randomCard;
	 }
	 
	 public void setViewRoleChoise( final LinearLayout linealL, final ArrayList<Player> players, final ArrayList<Role> openedRoleTurn,  final ArrayList<Role> gameRoleDeck, final ArrayList<Role> gameRoleDeckTurn, final ArrayList<Building> gameBuildingDeck, final int iterator_role_choose,  final Context context){
		 
		  LinearLayout.LayoutParams lpMatchContent = new LinearLayout.LayoutParams (LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		  lpMatchContent.gravity = Gravity.LEFT;
		  
		  String openedRoles = "ќткрытые роли: ";
		  for(int i=0; i<openedRoleTurn.size();i++)
			  openedRoles += openedRoleTurn.get(i).getName()+", ";
		  		  
		  openedRoles = openedRoles.substring(0, openedRoles.length()-2)+".";
	      
		  TextView tv = new TextView(context);
	      tv.setText(players.get(iterator_role_choose-1).getPlayerName()+" выбери роль.");
	      tv.setLayoutParams(lpMatchContent);
	      linealL.addView(tv);
	      
	      tv = new TextView(context);
	      tv.setText(openedRoles);
	      tv.setLayoutParams(lpMatchContent);
	      linealL.addView(tv);
	      
	      final Intent intent_r = new Intent(context, Act_role_choice.class);
	      final Intent intent_n = new Intent(context, Act_player_invite.class);
	      
      
	      for(int i=0; i<gameRoleDeckTurn.size();i++){//TODO отладить дл€ 7 игроков
	    	  final Role fnlRole = gameRoleDeckTurn.get(i);
	    	  Button btn = new Button(context);
	    	  btn.setText(gameRoleDeckTurn.get(i).getName());
	    	  linealL.addView(btn,lpMatchContent);
	    	  final int n = i;
	    	  btn.setOnClickListener(new View.OnClickListener() {
	    		  @Override
	    		  public void onClick(View v) {
	    			  Intent intent_tmp;
	    			  
	    		      players.get(iterator_role_choose-1).setRole(fnlRole);
	    			  gameRoleDeckTurn.remove(n);
	    			  if(iterator_role_choose<players.size()){
	    				  intent_tmp = intent_r;
	    			  }
	    			  else{
	    				  intent_tmp = intent_n;
		    			  Collections.sort(players, new PlayerCompereRole());
	    			  }
	    			  intent_tmp.putExtra("iterator_role_choose", iterator_role_choose);
	    			  intent_tmp.putExtra("gameRoleDeck", gameRoleDeck);
	    			  intent_tmp.putExtra("openedRoleTurn", openedRoleTurn);
		    		  intent_tmp.putExtra("gameRoleDeckTurn", gameRoleDeckTurn);
		    		  intent_tmp.putExtra("gameBuildingDeck", gameBuildingDeck);
		    		  intent_tmp.putExtra("players", players);
		    		  context.startActivity(intent_tmp);
		    		  ((Activity) context).finish();
	
	    		  
	    		  }
	    	  });
	      }
	 }
	 
	 /*public void refreshInHandLL(LinearLayout buildedL, final LinearLayout inHandL, final Player player, Context context){
		 
	 }*/
	 
	 
	 public void setBuldingScreen( final LinearLayout buildedL, final LinearLayout inHandL, final ArrayList<Player> players, final ArrayList<Role> openedRoleTurn,  final ArrayList<Role> gameRoleDeck, final ArrayList<Building> gameBuildingDeck,final int builded , final int iterator_turn, final Context context){
		 LinearLayout.LayoutParams lpMatchContent = new LinearLayout.LayoutParams (LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		 lpMatchContent.gravity = Gravity.LEFT;
		  
		 ArrayList<Building> tmpBuildings;
		 tmpBuildings = players.get(iterator_turn).getBuildingCards();
		 for(int i=0; i<tmpBuildings.size();i++){
			 final Button btn = new Button(context);
			 btn.setText(tmpBuildings.get(i).getName()+", "+tmpBuildings.get(i).getColor()+", "+tmpBuildings.get(i).getCost());
			 inHandL.addView(btn, lpMatchContent);
			 final Intent intent_refresh = new Intent(context, Act_building.class);
			 if(tmpBuildings.get(i).getCost()>players.get(iterator_turn).getGoldAmount() || builded!=0)
				 btn.setEnabled(false);
			 else{
				 final Building tmpBuilding = tmpBuildings.get(i);
				 btn.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						boolean lastTurn = players.get(iterator_turn).build(tmpBuilding);
						intent_refresh.putExtra("iterator_turn", iterator_turn);
						intent_refresh.putExtra("gameRoleDeck", gameRoleDeck);
						intent_refresh.putExtra("gameBuildingDeck", gameBuildingDeck);
						intent_refresh.putExtra("players", players);
						intent_refresh.putExtra("builded", builded+1);
						intent_refresh.putExtra("isLastTurn", lastTurn);
						context.startActivity(intent_refresh);
						((Activity) context).finish();

					}
				});
			 }
		 }
		 
		 tmpBuildings = players.get(iterator_turn).getBuilded();
		 for(int i=0; i<tmpBuildings.size();i++){
			 TextView tv = new TextView(context);
			 tv.setText(tmpBuildings.get(i).getName()+", "+tmpBuildings.get(i).getColor()+", "+tmpBuildings.get(i).getCost());
		     tv.setLayoutParams(lpMatchContent);
		     buildedL.addView(tv);
		      
		 }		
	 }
}

