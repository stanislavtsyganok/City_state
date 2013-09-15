package com.example.first_app;

import android.os.Parcel;
import android.os.Parcelable;

public class Building implements Parcelable{
	private int buildingId;
	private String buildingName;
	private int buildingCost;
	private int buildingColor;
	private String buildingImg;
	//private Building_special buildingSpecial;
	
	Building(int id, String name, int cost, int color, String img){
		buildingId = id;
		buildingName = name;
		buildingCost = cost;
		buildingColor = color;
		buildingImg = img;
		//buildingSpecial = new Building_special();
	}
	
	public String getName(){
		return buildingName;
	}
	
	public String getId(){
		return String.valueOf(buildingId);
	}
	
	public int getCost(){
		return buildingCost;
	}
	
	public String getColor(){
		String color ="";
		switch (buildingColor) {
		case 1:
			color = "�������";
			break;
		case 2:
			color = "�������";
			break;
		case 3:
			color = "�����";
			break;
		case 4:
			color = "������";
			break;
		case 5:
			color = "����������";
			break;
		}
		return color;
	}
	
	public int describeContents() {
	    return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeInt(buildingId);
		parcel.writeString(buildingName);
		parcel.writeInt(buildingCost);
		parcel.writeInt(buildingColor);
		parcel.writeString(buildingImg);
		//TODO add data for BuildingSpecial
	}
	
	public static final Parcelable.Creator<Building> CREATOR = new Parcelable.Creator<Building>() {
	    public Building createFromParcel(Parcel parcel) {
	    	return new Building(parcel);
	    }
	
	    public Building[] newArray(int size) {
	    	return new Building[size];
	    }
	};
	
	Building(Parcel parcel)
	{
		buildingId = parcel.readInt();
		buildingName = parcel.readString();
		buildingCost = parcel.readInt();
		buildingColor = parcel.readInt();
		buildingImg = parcel.readString();
	//	BuildingSpecial = new Building_special();
	}
}
