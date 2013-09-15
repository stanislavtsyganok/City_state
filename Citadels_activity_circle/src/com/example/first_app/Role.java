package com.example.first_app;

import android.os.Parcel;
import android.os.Parcelable;

public class Role implements Parcelable{
	private int roleId;
	private String roleName;
	private String roleImg;
	//private Role_special roleSpecial;
	
	Role(int id, String name, String img){
		roleId = id;
		roleName = name;
		roleImg = img;
	//	roleSpecial = new Role_special();
	}
	
	public String getName(){
		return roleName;
	}
	
	public int getId(){
		return roleId;
	}
	
	public int describeContents() {
		    return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeInt(roleId);
		parcel.writeString(roleName);
		parcel.writeString(roleImg);
		//TODO add data for roleSpecial
	}
	
	public static final Parcelable.Creator<Role> CREATOR = new Parcelable.Creator<Role>() {
	    public Role createFromParcel(Parcel parcel) {
	    	return new Role(parcel);
	    }

	    public Role[] newArray(int size) {
	    	return new Role[size];
	    }
	};
	
	Role(Parcel parcel)
	{
		roleId = parcel.readInt();
		roleName = parcel.readString();
		roleImg = parcel.readString();
	//	roleSpecial = new Role_special();
	}
	
		
}
