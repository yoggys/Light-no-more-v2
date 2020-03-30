package Player;

import Entity.*;
import System.*;
import java.util.ArrayList;
import java.awt.*;

//class by Mateusz Karbownik
public class Player {

	public static ArrayList<Champion> champions = new ArrayList<Champion>();
	public static ArrayList<Champion> reserve = new ArrayList<Champion>();
	public static ArrayList<Champion> tavernChampions = new ArrayList<Champion>();
	public static ArrayList<Someone> enemys = new ArrayList<Someone>();
	
	public static int currentDungeon = 1;
	public static String langCards[];


	public static void changeSquad(int posCurrent, int posReserve){
		Champion tmp = new Champion(0,0,"");
		tmp = champions.get(posCurrent);
		champions.set(posCurrent, reserve.get(posReserve));
		reserve.set(posReserve, tmp);
	}
	
	public static void buyChampion(Champion buyChamp){
		reserve.add(buyChamp);
	}

	public static void sellChampion(int posReserve){
		reserve.remove(posReserve);
	}
	
	public static void championCurCard(int choice, Graphics2D g, Images image, int x, int y){
		g.setColor(Color.BLACK);
		image.draw(g, x, y, "Resources/HUD/card.png");
		image.draw(g, x, y-44, "Resources/HUD/cardframe.png");
		image.draw(g, x, y-44, Player.champions.get(choice).getAvatar());
		g.drawString(langCards[0] + Player.champions.get(choice).getName(), x+50, y+30*6);
		g.drawString("HP/MAX_HP: " + Player.champions.get(choice).getHp() +"/"+ Player.champions.get(choice).getMaxHp(), x+50, y+30*7);	
		g.drawString("SA/MAX_SA: " + Player.champions.get(choice).getStamina() +"/"+ Player.champions.get(choice).getMaxStamina(), x+50, y+30*8);
		g.drawString(langCards[1]+"("+langCards[2]+"/"+langCards[3]+"): ", x+50, y+30*9);
		if(Player.champions.get(choice).skills.size() != 0){
			if(Player.champions.get(choice).skills.get(0).getEfect() == null){
				g.drawString("*"+ Player.champions.get(choice).skills.get(0).getName() + "(" +
				-1*(Player.champions.get(choice).skills.get(0).getDamage()) +")", x+50, y+30*10);
			}
			else{
				g.drawString("*"+ Player.champions.get(choice).skills.get(0).getName() + "(" +
				-1*((Player.champions.get(choice).skills.get(0).getDamage() + 
				Player.champions.get(choice).skills.get(0).getEfect().getTotal())) + ")", x+50, y+30*10);
			}
			if(Player.champions.get(choice).skills.get(0).getEfect() == null){
				g.drawString("*"+ Player.champions.get(choice).skills.get(1).getName() + "(" +
				-1*(Player.champions.get(choice).skills.get(1).getDamage()) +")", x+50, y+30*11);
			}
			else{
				g.drawString("*"+ Player.champions.get(choice).skills.get(1).getName() + "(" +
				-1*((Player.champions.get(choice).skills.get(1).getDamage() + 
				Player.champions.get(choice).skills.get(1).getEfect().getTotal())) + ")", x+50, y+30*11);
			}
		}
		else{
			g.drawString(langCards[4], x+50, y+30*10);
		}
	}

	public static void championResCard(int choice, Graphics2D g, Images image, int x, int y){
		g.setColor(Color.BLACK);
		image.draw(g, x, y, "Resources/HUD/card.png");
		image.draw(g, x, y-44, "Resources/HUD/cardframe.png");
		image.draw(g, x, y-44, Player.reserve.get(choice).getAvatar());
		g.drawString(langCards[0] + Player.reserve.get(choice).getName(), x+50, y+30*6);
		g.drawString("HP/MAX_HP: " + Player.reserve.get(choice).getHp() +"/"+ Player.reserve.get(choice).getMaxHp(), x+50, y+30*7);	
		g.drawString("SA/MAX_SA: " + Player.reserve.get(choice).getStamina() +"/"+ Player.reserve.get(choice).getMaxStamina(), x+50, y+30*8);
		g.drawString(langCards[1]+"("+langCards[2]+"/"+langCards[3]+"): ", x+50, y+30*9);
		if(Player.reserve.get(choice).skills.size() != 0){
			if(Player.reserve.get(choice).skills.get(0).getEfect() == null){
				g.drawString("*"+ Player.reserve.get(choice).skills.get(0).getName() + "(" +
				-1*(Player.reserve.get(choice).skills.get(0).getDamage()) +")", x+50, y+30*10);
			}
			else{
				g.drawString("*"+ Player.reserve.get(choice).skills.get(0).getName() + "(" +
				-1*((Player.reserve.get(choice).skills.get(0).getDamage() + 
				Player.reserve.get(choice).skills.get(0).getEfect().getTotal())) + ")", x+50, y+30*10);
			}
			if(Player.reserve.get(choice).skills.get(0).getEfect() == null){
				g.drawString("*"+ Player.reserve.get(choice).skills.get(1).getName() + "(" +
				-1*(Player.reserve.get(choice).skills.get(1).getDamage()) +")", x+50, y+30*11);
			}
			else{
				g.drawString("*"+ Player.reserve.get(choice).skills.get(1).getName() + "(" +
				-1*((Player.reserve.get(choice).skills.get(1).getDamage() + 
				Player.reserve.get(choice).skills.get(1).getEfect().getTotal())) + ")", x+50, y+30*11);
			}
		}
		else{
			g.drawString(langCards[4], x+50, y+30*10);
		}
	}

	public static void championTavCard(int choice, Graphics2D g, Images image, int x, int y){
		g.setColor(Color.BLACK);
		image.draw(g, x, y, "Resources/HUD/card.png");
		image.draw(g, x, y-44, "Resources/HUD/cardframe.png");
		image.draw(g, x, y-44, Player.tavernChampions.get(choice).getAvatar());
		g.drawString(langCards[0] + Player.tavernChampions.get(choice).getName(), x+50, y+30*6);
		g.drawString("HP/MAX_HP: " + Player.tavernChampions.get(choice).getHp() +"/"+ Player.tavernChampions.get(choice).getMaxHp(), x+50, y+30*7);	
		g.drawString("SA/MAX_SA: " + Player.tavernChampions.get(choice).getStamina() +"/"+ Player.tavernChampions.get(choice).getMaxStamina(), x+50, y+30*8);
		g.drawString(langCards[1]+"("+langCards[2]+"/"+langCards[3]+"): ", x+50, y+30*9);
		if(Player.tavernChampions.get(choice).skills.size() != 0){
			if(Player.tavernChampions.get(choice).skills.get(0).getEfect() == null){
				g.drawString("*"+ Player.tavernChampions.get(choice).skills.get(0).getName() + "(" +
				-1*(Player.tavernChampions.get(choice).skills.get(0).getDamage()) +")", x+50, y+30*10);
			}
			else{
				g.drawString("*"+ Player.tavernChampions.get(choice).skills.get(0).getName() + "(" +
				-1*((Player.tavernChampions.get(choice).skills.get(0).getDamage() + 
				Player.tavernChampions.get(choice).skills.get(0).getEfect().getTotal())) + ")", x+50, y+30*10);
			}
			if(Player.tavernChampions.get(choice).skills.get(0).getEfect() == null){
				g.drawString("*"+ Player.tavernChampions.get(choice).skills.get(1).getName() + "(" +
				-1*(Player.tavernChampions.get(choice).skills.get(1).getDamage()) +")", x+50, y+30*11);
			}
			else{
				g.drawString("*"+ Player.tavernChampions.get(choice).skills.get(1).getName() + "(" +
				-1*((Player.tavernChampions.get(choice).skills.get(1).getDamage() + 
				Player.tavernChampions.get(choice).skills.get(1).getEfect().getTotal())) + ")", x+50, y+30*11);
			}
		}
		else{
			g.drawString(langCards[4], x+50, y+30*10);
		}
	}
}

















