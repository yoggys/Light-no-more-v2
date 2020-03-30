package System;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import GameState.*;
import Player.Inventory;

public class Language {
	
	private static  Locale en = new Locale("en", "US");
	private static Locale pl = new Locale("pl", "PL"); 	
	
	private static ResourceBundle bundle;
	
	public Language() {
		bundle = ResourceBundle.getBundle("lang");
	}
	
 	public static void setLanguage(String language) {
 		if(language == "pl") {
 			loc(pl);
 		}
 		else if(language == "en") {
 			loc(en);
 		}
	} 
 	
 	private static void loc(Locale loc) {
 		ResourceBundle bndl = ResourceBundle.getBundle("lang", loc);	
		ArrayList<String> names = new ArrayList<String>();
		for(int i = 0; i < 10; i++) {
			names.add(bndl.getString("Item" + i));
		}
		Inventory.info = names.toArray(new String[names.size()]);
		names.clear();

		names.add(bndl.getString("New game"));
		names.add(bndl.getString("Load game"));
		names.add(bndl.getString("Help"));
		names.add(bndl.getString("Quit"));
		MenuState.options = names.toArray(new String[names.size()]);
		names.clear();
		
		HelpState.options = bndl.getString("Back");

		names.add(bndl.getString("Enter dark"));
		names.add(bndl.getString("Tavern"));
		names.add(bndl.getString("Charlatan"));
		names.add(bndl.getString("Merchant"));
		names.add(bndl.getString("Headquarters"));
		TownState.objects = names.toArray(new String[names.size()]);
		names.clear();

		names.add(bndl.getString("Reserve champs"));
		names.add(bndl.getString("Tavern champs"));
		names.add(bndl.getString("Sell"));
		names.add(bndl.getString("Buy"));
		names.add(bndl.getString("No tav"));
		names.add(bndl.getString("No res"));
		names.add(bndl.getString("Missing money"));
		names.add(bndl.getString("Missing res"));
		names.add(bndl.getString("Back"));
		TavernState.text = names.toArray(new String[names.size()]);
		names.clear();
 	}
}
