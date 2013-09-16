package com.example.first_app;

import java.util.ArrayList;
//import java.util.logging.Logger;

import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable{
	//Logger log = Logger.getLogger(Act_game_screen.class.getName());
	private int playerId;
	 	
	private String playerName;
	private int coinsNumber;
	private int points;
	private Role turnRole;
	private ArrayList<Building> inHandBuildings;
	private ArrayList<Building> builded;

	private int isCrowned ;
	
	Game_func gameFunction;
	private int maxBuilding = 2;
		
	Player(int id, String pName, ArrayList<Building> buildingDeck, int isC){
		playerId = id;
		inHandBuildings = new ArrayList<Building>();
		builded = new ArrayList<Building>();
		gameFunction = new Game_func();
		
		playerName = pName;
		coinsNumber = 2;
		points = 0;
		for(int i=0;i<4;i++)
			inHandBuildings.add(gameFunction.PickACard(buildingDeck));
		turnRole = new Role(1,"Ассасин","Path");
		isCrowned = isC;
	}
	
	public void setRole(Role ChoicedRole){
		turnRole = ChoicedRole;
	}
	
	public void pickTwoCards(ArrayList<Building> buildingDeck){
		for(int i=0;i<2;i++)
			inHandBuildings.add(gameFunction.PickACard(buildingDeck));
	}
	
	public void pickTwoCoins(){
		coinsNumber += 2;
	}
	
	public String getPlayerRole(){
		return turnRole.getName();
	}
	
	public int getPlayerRoleId(){
		return turnRole.getId();
	}
	
	public String getPlayerName(){
		return playerName;
	}
	
	public int getGoldAmount(){
		return coinsNumber;
	}
	
	public ArrayList<Building> getBuilded()	{
		return builded;
	}
	
	public ArrayList<Building> getBuildingCards()	{
		return inHandBuildings;
	}
	
	public int getPlayerId(){
		return playerId;
	}
	
	public int getPlayerPoints(){
		return points;
	}
	
	public String[] getDataForTable(){
		String [] tmpStr = {playerName, String.valueOf(builded.size()), String.valueOf(inHandBuildings.size()), String.valueOf(coinsNumber), String.valueOf(points)};
		return tmpStr;
	}
	
	public boolean build(Building building){
		points += building.getCost();
		coinsNumber -= building.getCost();
		builded.add(building);
		inHandBuildings.remove(building);
		return builded.size()>=maxBuilding;
	}
	
	
	
	public int describeContents() {
	    return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeInt(playerId);
		
		parcel.writeString(playerName);
		parcel.writeInt(coinsNumber);
		parcel.writeInt(points);
		
		parcel.writeInt(isCrowned);
		turnRole.writeToParcel(parcel, flags);
		
		parcel.writeTypedList(inHandBuildings);
		parcel.writeTypedList(builded);
	}
	
	public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
	    public Player createFromParcel(Parcel parcel) {
	    	return new Player(parcel);
	    }
	
	    public Player[] newArray(int size) {
	    	return new Player[size];
	    }
	};
	
	Player(Parcel parcel){
		inHandBuildings = new ArrayList<Building>();
		builded = new ArrayList<Building>();
		gameFunction = new Game_func();
		
		playerId = parcel.readInt();
				
		playerName = parcel.readString();
		coinsNumber = parcel.readInt();
		points = parcel.readInt();
		isCrowned = parcel.readInt();
		turnRole = new Role(parcel);
		
		parcel.readTypedList(inHandBuildings, Building.CREATOR);
		parcel.readTypedList(builded, Building.CREATOR);
		
	}

	
}
