package com.example.first_app;

import java.util.Comparator;

public class PlayerComparePoints implements Comparator<Player>{

	@Override
	public int compare(Player o1, Player o2) {
		// TODO Auto-generated method stub
		return new Integer(o2.getPlayerPoints()).compareTo(new Integer(o1.getPlayerPoints()));
	}


}
