package com.example.first_app;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Act_other_players extends Activity {
	ArrayList<Player> players;
	TableLayout tableL;
	Button btnBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		players = ((CityApp)getApplication()).getPlayers();
		setContentView(R.layout.results);
		
		tableL = (TableLayout) findViewById(R.id.result_table);
		
		for(int i=0;i<players.size();i++){
			TableRow tRow = new TableRow(this);
			String[] tmpStr = players.get(i).getDataForTable();
			for(int n=0; n<tmpStr.length;n++){
				TextView tv = new TextView(this);
				tv.setText(tmpStr[n]);
				tRow.addView(tv);
			}
			tableL.addView(tRow);
		}
		
		btnBack = (Button) findViewById(R.id.btnBackRes);
		btnBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
	}
}
