package System;

import java.sql.*;
import java.util.ArrayList;

import Entity.Champion;
import Entity.Efect;
import Entity.Skill;
import Player.Inventory;
import Player.Player;

//class by Mateusz Karbownik
public class LoadGame {
	
	public static void loadGame() throws SQLException {
		
		Connection conn = null;
		try {
		    conn = DriverManager.getConnection("jdbc:h2:./Saves/LNM_save", "LNM", "LNM");
			
			Statement statement = conn.createStatement();
			
			if(true){
				statement.execute("SELECT * FROM player");
				ResultSet rs = statement.getResultSet();			
				ResultSetMetaData md = rs.getMetaData();

				ArrayList <String> blank = new ArrayList<>();

				while (rs.next()) {
					for (int i = 1; i <= md.getColumnCount(); i++){	
						blank.add(String.valueOf(rs.getObject(i)));	
					}
				}
				Inventory.setGold(Integer.parseInt(blank.get(1)));
				Player.currentDungeon = Integer.parseInt(blank.get(2));		
			}

			if(true){
				statement.execute("SELECT * FROM inventory");
				ResultSet rs = statement.getResultSet();			
				ResultSetMetaData md = rs.getMetaData();

				ArrayList <String> blank = new ArrayList<>();

				Inventory.clearItems();
				while (rs.next()) {
					for (int i = 1; i <= md.getColumnCount(); i++){	
						blank.add(String.valueOf(rs.getObject(i)));	
					}
				}

				for(int i = 1; i < blank.size()+1;i+=2){
					Inventory.additem(Integer.parseInt(blank.get(i)));
				}
			}

			champAdd(conn, statement, "champions");
			champAdd(conn, statement, "reserve");
			champAdd(conn, statement, "tavern_champions");
		}

		finally {
			if (conn!= null){
				conn.close();
			}
		}
	}
	private static void champAdd(Connection conn, Statement statement, String from) throws SQLException {
		if(true){
			statement.execute("SELECT * FROM " + from);
			ResultSet rs = statement.getResultSet();			
			ResultSetMetaData md  = rs.getMetaData();
			int tmp = 0;

			ArrayList <String> blank = new ArrayList<>();
			ArrayList <String> name = new ArrayList<>();
			ArrayList <Integer> hp = new ArrayList<>();
			ArrayList <Integer> maxhp = new ArrayList<>();
			ArrayList <Integer> sa = new ArrayList<>();
			ArrayList <Integer> maxsa = new ArrayList<>();
			ArrayList <String> skill1_name = new ArrayList<>();
			ArrayList <Integer> skill1_dmg = new ArrayList<>();
			ArrayList <Integer> skill1_sa = new ArrayList<>();
			ArrayList <Integer> skill1_effect_dmg = new ArrayList<>();
			ArrayList <Integer> skill1_effect_time = new ArrayList<>();
			ArrayList <String> skill2_name = new ArrayList<>();
			ArrayList <Integer> skill2_sa = new ArrayList<>();
			ArrayList <Integer> skill2_dmg = new ArrayList<>();
			ArrayList <Integer> skill2_effect_dmg = new ArrayList<>();
			ArrayList <Integer> skill2_effect_time = new ArrayList<>();
			ArrayList <String> avatar = new ArrayList<>();

			while (rs.next()) {
				for (int i = 1; i <= md.getColumnCount(); i++) {
					blank.add(String.valueOf(rs.getObject(i)));	
				}
				tmp++;
			}
			for(int i = 0; i < tmp*17; i+=17){
				name.add(blank.get(i+1));
				hp.add(Integer.parseInt(blank.get(i+2)));
				maxhp.add(Integer.parseInt(blank.get(i+3)));
				sa.add(Integer.parseInt(blank.get(i+4)));
				maxsa.add(Integer.parseInt(blank.get(i+5)));
				skill1_name.add(blank.get(i+6));
				skill1_dmg.add(Integer.parseInt(blank.get(i+7)));
				skill1_sa.add(Integer.parseInt(blank.get(i+8)));	
				skill1_effect_dmg.add((Integer.parseInt(blank.get(i+9))));	
				skill1_effect_time.add((Integer.parseInt(blank.get(i+10))));	
				skill2_name.add(blank.get(i+11));
				skill2_dmg.add((Integer.parseInt(blank.get(i+12))));
				skill2_sa.add((Integer.parseInt(blank.get(i+13))));	
				skill2_effect_dmg.add((Integer.parseInt(blank.get(i+14))));	
				skill2_effect_time.add(Integer.parseInt(blank.get(i+15)));	
				avatar.add(blank.get(i+16));
			}

			if(from == "champions")
				Player.champions.clear();
			if(from == "reserve")
				Player.reserve.clear();
			if(from == "tavern_champions")
				Player.tavernChampions.clear();
			
			for(int i = 0; i < name.size(); i++){
				Efect effect1, effect2;
				Skill skill1, skill2;
				if(skill1_effect_dmg.get(i) != 0){
					effect1 = new Efect(skill1_effect_dmg.get(i) , skill1_effect_time.get(i));
					skill1 = new Skill(skill1_name.get(i),skill1_dmg.get(i), skill1_sa.get(i), effect1);
				}
				else{
					skill1 = new Skill(skill1_name.get(i),skill1_dmg.get(i), skill1_sa.get(i));
				}
				if(skill1_effect_dmg.get(i) != 0){
					effect2 = new Efect(skill2_effect_dmg.get(i) , skill2_effect_time.get(i));
					skill2 = new Skill(skill2_name.get(i),skill2_dmg.get(i), skill2_sa.get(i), effect2);
				}
				else{
					skill2 = new Skill(skill2_name.get(i),skill2_dmg.get(i), skill2_sa.get(i));
				}
				Champion champ = new Champion(hp.get(i),maxhp.get(i),sa.get(i),maxsa.get(i),name.get(i),avatar.get(i));
				champ.addSkill(skill1);
				champ.addSkill(skill2);

				if(from == "champions")
					Player.champions.add(champ);
				if(from == "reserve")
					Player.reserve.add(champ);
				if(from == "tavern_champions")
					Player.tavernChampions.add(champ);
			}
		}
	}
}







