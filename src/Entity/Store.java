package Entity;

public class Store {

	private static int gold[] = {
		0, // 0 - none - 0 to puste eq
		200, // 1 - ksiega*
		300, // 2 -	bizuteria*
		400, // 3 -	srebro*
		500, // 4 -	zloto*
		600, // 5 -	klejnonty*
		100, // 6 -	stamina potion
		150, // 7 -	health potion
		200, // 8 -	poison potion
		200  // 9 - cure potion
	};

	//wymyslimy wiecej czegos przy skrzyniach

	private static int store[] = {
		200, // 0 -	stamina potion
		350, // 1 -	health potion
		500, // 2 -	poison potion
		500	 // 3 - cure potion
	};

	public static int sell(int id){
		return gold[id];
	}

	public static int price(int id){
		return gold[id];
	}

	public static int buy(int id){
		return store[id];
	}

}

















