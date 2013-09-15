package com.example.first_app;

import java.util.logging.Logger;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class Citadels_dao {
	Citadels_db_helper Citadels_dbh;
	SQLiteDatabase Citadels_db;
	Logger log = Logger.getLogger(Citadels_dao.class.getName());
	
	Citadels_dao(Context context){
		Citadels_dbh = new Citadels_db_helper(context);
		Citadels_db = Citadels_dbh.getWritableDatabase();

	}
	
	public Cursor getAllRoles(){
		Cursor cursor;
		cursor = Citadels_db.query(Citadels_db_helper.RoleTable,
				null, null, null, null, null, null);
		/*		if(cursor.moveToFirst())
			log.info(cursor.getString(0));
		do{
		log.info(cursor.getString(1));
		}while(cursor.moveToNext());*/
		return cursor;
	}
	
	public void onDestroy(){
		Citadels_dbh.close();
		Citadels_db.close();
	}
	
	public Cursor getAllBuildings(){
		Cursor cursor;
		cursor = Citadels_db.query(Citadels_db_helper.BuildingTable,
				null, null, null, null, null, null);
		return cursor;
	}

}
