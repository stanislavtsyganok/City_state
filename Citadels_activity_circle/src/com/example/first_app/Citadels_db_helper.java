package com.example.first_app;
import java.util.ArrayList;
import java.util.logging.Logger;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Citadels_db_helper extends SQLiteOpenHelper  {
	Logger log = Logger.getLogger(Act_game_screen.class.getName());
		
	static final String dbName = "Citadels_db";
	static final int dbVersion = 44;
	static final String RoleTable = "Role";
	
	static final String RoleId = "RoleId";
	static final String RoleName = "Name";
	static final String RoleSpecial = "Special";
	static final String RoleImg = "Image";
	
	static final String[] Role={"Ассасин","Ведьма",
								"Вор","Сбощик податей",
								"Чародей","Волшебник",
								"Король","Император",
								"Епископ","Аббат",
								"Купец","Алхимик",
								"Зодчий","Навигатор",
								"Кондотьер","Дипломат"};
	
	
	static final String BuildingTable = "Building";
	static final String BuildingColorTable = "BuildingColor";
	
	static final String BuildingColorId = "ColorId";
	static final String BuildingColorName ="Name";
	
	static final String[] Color={"Red","Green","Blue","Yellow","Purple"};
	
	static final String BuildingId = "BuildingId";
	static final String BuildingName = "Name";
	static final String BuildingCost = "Cost";
	static final String BuildingNumber = "Number";
	static final String BuildingColorKey = "ColorId";

	static final String BuildingImg = "Image";
	
	ArrayList<String[][]> BuildingsList;
	
	static final int ColorsNumber = 4;
	
	static String[][] RedBuildings = {{"Дозорная башня","1","3"},
									  {"Тюрьма","2","3"},
									  {"Марсово поле","3","3"},
									  {"Крепость","5","2"}};
	static String[][] GreenBuildings = {{"Таверна","1","4"},
									    {"Лавка","2","3"},
									    {"Рынок","2","4"},
									    {"Порт","3","3"},
									    {"Гавань","4","3"},
									    {"Ратуша","5","2"}};
	static String[][] BlueBuildings = {{"Храм","1","3"},
									   {"Церковь","2","3"},
									   {"Монастырь","3","3"},
									   {"Собор","5","2"}};
	static String[][] YellowBuildings = {{"Поместье","3","5"},
										 {"Замок","4","4"},
										 {"Палаццо","5","3"}};
	static String[][] PurpleBuildings;//TODO Фиолетовые кварталы
	
	public Citadels_db_helper(Context context) {
		  super(context, dbName, null ,dbVersion);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("CREATE TABLE IF NOT EXISTS "+RoleTable+" (" +
				RoleId+" INT PRIMARY KEY NOT NULL, " +
				RoleName+" TEXT NOT NULL, " +
				RoleSpecial+" TEXT, " +		// TODO add not null
				RoleImg+" TEXT NOT NULL);");
		
		String tempDbString = "INSERT INTO "+RoleTable+" VALUES ";
			
		for (int i=0;i<Role.length;i++){
			int n=i+1;
			tempDbString += "("+n+",\""+Role[i]+"\",1,1),";
		}
		tempDbString = tempDbString.substring(0, tempDbString.length()-1)+";";
		db.execSQL(tempDbString);
		
		db.execSQL("CREATE TABLE IF NOT EXISTS "+BuildingColorTable +"("+
					BuildingColorId+" INT PRIMARY KEY NOT NULL, " +
					BuildingColorName+" TEXT);");
				
		db.execSQL("CREATE TABLE IF NOT EXISTS "+BuildingTable+"(" +
					BuildingId+" INT PRIMARY KEY NOT NULL, " +
					BuildingName+ " TEXT NOT NULL, "+
					BuildingCost+" INT NOT NULL, " +			
					BuildingNumber+" INT NOT NULL, " +
					BuildingColorId+" INT NOT NULL, " +
					BuildingImg+" TEXT NOT NULL);");
		tempDbString = "INSERT INTO "+BuildingColorTable+" VALUES ";
		
		for (int i=0;i<5;i++){
			int n=i+1;
			tempDbString += "("+n+",\""+Color[i]+"\"),";
		}
		tempDbString = tempDbString.substring(0, tempDbString.length()-1)+";";
		db.execSQL(tempDbString);
		
		BuildingsList = new ArrayList<String[][]>(5);
		
		BuildingsList.add(RedBuildings);
		BuildingsList.add(GreenBuildings);
		BuildingsList.add(BlueBuildings);
		BuildingsList.add(YellowBuildings);
		
		tempDbString = "INSERT INTO "+BuildingTable+" VALUES ";
		String[][] tmpString;
		int id = 1;
		for(int i=1;i<ColorsNumber+1;i++){
			tmpString = BuildingsList.get(i-1);
			for (int k=0;k<tmpString.length;k++){
				tempDbString += "("+id+", \""+tmpString[k][0]+"\", "+tmpString[k][1]+", "+tmpString[k][2]+", "+ i+", \"Path\"),";
				id++;
				//log.info(tmpString[k][0]);
			}
		}
		tempDbString = tempDbString.substring(0, tempDbString.length()-1)+";";
		db.execSQL(tempDbString);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldV, int NewV) {
		db.execSQL("DROP TABLE IF EXISTS "+RoleTable);
		db.execSQL("DROP TABLE IF EXISTS "+BuildingTable);
		db.execSQL("DROP TABLE IF EXISTS "+BuildingColorTable);
		onCreate(db);
		// TODO Auto-generated method stub
		
	}
	
	public Cursor getRoleImg(int id){
		log.info("getRolecall");
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(RoleTable, null, null, null, null, null, null);
		if(cursor.moveToFirst())
			log.info(cursor.getString(0));
		return cursor;
	};
	
	
	
}
