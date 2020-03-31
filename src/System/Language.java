package System;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import GameState.*;
import Player.*;

//class by Mateusz Karbownik
public class Language {
	
	private static  Locale en = new Locale("en", "US");
	private static Locale pl = new Locale("pl", "PL"); 	

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

		names.add(bndl.getString("Price"));
		names.add(bndl.getString("Back"));
		names.add(bndl.getString("None"));
		names.add(bndl.getString("No inv"));
		HqInfoState.options = names.toArray(new String[names.size()]);
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

		names.add(bndl.getString("Back"));
		names.add(bndl.getString("Buy items"));
		names.add(bndl.getString("Sell items"));
		names.add(bndl.getString("No items"));
		names.add(bndl.getString("Missing inv"));
		names.add(bndl.getString("Missing money"));
		MerchantState.options = names.toArray(new String[names.size()]);
		names.clear();

		names.add(bndl.getString("Upgrade squad champions"));
		names.add(bndl.getString("Change current squad"));
		names.add(bndl.getString("Inspect inventory"));
		names.add(bndl.getString("Back"));
		HeadquartersState.options = names.toArray(new String[names.size()]);
		names.clear();

		names.add(bndl.getString("Reserve champs"));
		names.add(bndl.getString("Current squad"));
		names.add(bndl.getString("Change current squad"));
		names.add(bndl.getString("This"));
		names.add(bndl.getString("For"));
		names.add(bndl.getString("No res"));
		names.add(bndl.getString("Back"));
		HqSquadState.text = names.toArray(new String[names.size()]);
		names.clear();

		names.add(bndl.getString("Back"));
		names.add(bndl.getString("Heal"));
		names.add(bndl.getString("Missing money"));
		CharlatanState.options = names.toArray(new String[names.size()]);
		names.clear();

		names.add(bndl.getString("Back"));
		names.add(bndl.getString("Upgrade"));
		names.add(bndl.getString("Missing money"));
		HqUpgradeState.options = names.toArray(new String[names.size()]);
		names.clear();

		
		names.add(bndl.getString("Name"));
		names.add(bndl.getString("Skills"));
		names.add(bndl.getString("Dmg"));
		names.add(bndl.getString("Heal2"));
		names.add(bndl.getString("None"));
		Player.langCards = names.toArray(new String[names.size()]);
		names.clear();

		names.add(bndl.getString("Continue"));
		names.add(bndl.getString("Save game"));
		names.add(bndl.getString("Mute music"));
		names.add(bndl.getString("Quit"));
		names.add(bndl.getString("Unmute music"));
		EscState.options = names.toArray(new String[names.size()]);
		names.clear();
 	}
}
