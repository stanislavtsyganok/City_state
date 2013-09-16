package com.example.first_app;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Act_game_results extends Activity {
	ArrayList<Player> players;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		
		players = intent.getParcelableArrayListExtra("players");
		setContentView(R.layout.results);
		
	}
}
