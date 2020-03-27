package Entity;

import java.util.ArrayList;
import java.util.Arrays;

public class Inventory {
	private static ArrayList<Integer> items /*= new ArrayList<Integer>();*/ = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
	//private static int[] items = {1,2,3,4,5,6,7,8,9,0};
	
	//jak testujesz wpisz 0 zawsze na koncu i podaj dobry amount bo wywali gre
	//w sensie tego merchanta bo tam polega wszystko na tym statycznym liczniku i id
	//bo mechanike zrobilem ze 0 to puste eq i zawsze jest po przedmiotach btw

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

	private static int gold = 1200;

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
		gold += Store.sell(items.get(pos));
		items.remove(items.get(pos));
	}

	public static int getid(int pos){
		return items.get(pos);
	}

	public static void pay(int price){
		gold -= price;
	}

	public static int invsize() {
		return items.size();
	}

	public static int getprice(int pos){
		return Store.price(items.get(pos));
	}

	public static int getgold(){
		return gold;
	}
	public static String getname(int pos){
		return names[pos];
	}
}

















