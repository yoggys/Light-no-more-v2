package Player;

import System.*;
import java.util.ArrayList;
import java.util.Arrays;

//class by Mateusz Karbownik
public class Inventory {
	private static ArrayList<Integer> items = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,9));
	public static int itemsSize = items.size();

	private static String names[] = {
		"working",
		"book",
		"jewellery",
		"silverbar",
		"goldbar",
		"gems",
		"sapot",
		"hppot",
		"poisonpot",
		"curepot"
	};

	private static String info[] = {
		"working",
		"You can sell it to get some money",
		"You can sell it to get some money",
		"You can sell it to get some money",
		"You can sell it to get some money",
		"You can sell it to get some money",
		"SA Potion: Restore 30% of target stamina",
		"Health Potion: Restore 30% of target health",
		"Poison: Poisons target",
		"Cure Potion: Clear poison on target"
	};

	private static int gold = 120000;

	public static void additem(int id){
			items.add(id);
	}

	public static void buyitem(int id){
		int tmp = 0;
		if(id == 0) tmp = 6;
		if(id == 1) tmp = 7;
		if(id == 2) tmp = 8;
		if(id == 3) tmp = 9;

		if(gold - Store.buy(id) >= 0){
			items.add(tmp);
			gold -= Store.buy(id);
		}
		else{
			System.out.println("NOT ENOUGHT MONEY!");
		}
	}

	public static void sellitem(int pos){
		itemsSize--;
		gold += Store.sell(items.get(pos));
		items.remove(items.get(pos));
	}

	public static int getid(int pos){
		return items.get(pos);
	}

	public static void pay(int price){
		gold -= price;
	}

	public static void sell(int price){
		gold += price;
	}

	public static int invsize() {
		return items.size();
	}

	public static int getprice(int pos){
		if(pos < items.size()){
			return Store.price(items.get(pos));
		}
			return 0;
	}

	public static int getgold(){
		return gold;
	}
	public static String getname(int pos){
		return names[pos];
	}
	public static String getInfo(int pos){
		return info[items.get(pos)];
	}
}

















