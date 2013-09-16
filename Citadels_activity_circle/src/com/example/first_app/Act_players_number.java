package com.example.first_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Act_players_number extends Activity{
		
	  EditText players_number;
	  Button btn_pn_next;	
	
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
			
		  LinearLayout.LayoutParams lpMatchContent = new LinearLayout.LayoutParams (LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		  lpMatchContent.gravity = Gravity.LEFT;
		
		  LinearLayout linLayout = new LinearLayout(this);
		  linLayout.setOrientation(LinearLayout.VERTICAL);
		  LayoutParams linLayoutParam = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT); 
		  setContentView(linLayout, linLayoutParam);
		  
		  TextView tv = new TextView(this);
	      tv.setText("Выберите количество игроков");
	      tv.setLayoutParams(lpMatchContent);
	      linLayout.addView(tv);
	      
	      final Intent intent = new Intent(this, Act_game_roles.class);
	     
	      for(int i=4; i<8; i++){
			  Button btn = new Button(this);
	    	  btn.setText(String.valueOf(i));
	    	  linLayout.addView(btn,lpMatchContent);
	    	  final int y = i;
	    	  
	    	  btn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					intent.putExtra("playersNumber", y);
					startActivity(intent);
				}
			});
	    	 
		  }

	  }
	  
	  
}
