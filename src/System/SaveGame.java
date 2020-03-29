package System;

import java.sql.*;

import Player.Inventory;
import Player.Player;

//class by Mateusz Karbownik
public class SaveGame {
	
	public static void saveGame() throws SQLException {
		
		Connection conn = null;
		try {
		    conn = DriverManager.getConnection("jdbc:h2:./LNM_save", "LNM", "LNM");
			
		    Statement statement = conn.createStatement();

		    statement.executeUpdate("DROP TABLE IF EXISTS `inventory`;");
		    statement.executeUpdate("CREATE TABLE `inventory` ("+
						  "`ID` int(10) NOT NULL auto_increment,"+
						  "`ITEM_ID` int(10) default NULL,"+
						  "`GOLD` int(10) default NULL,"+
						  "PRIMARY KEY  (`ID`)"+
						") ;");				
			for(int i = 0; i < Inventory.invsize(); i++){
				PreparedStatement put = conn.prepareStatement 
					("INSERT INTO `inventory` (`ITEM_ID`,`GOLD`) VALUES (?,?);");
					put.setInt(1, Inventory.getid(i));
					put.setInt(2, Inventory.getgold());
					put.executeUpdate();
			}

			statement.executeUpdate("DROP TABLE IF EXISTS `champions`;");
		    statement.executeUpdate("CREATE TABLE `champions` ("+
						  "`ID` int(10) NOT NULL auto_increment,"+
						  "`NAME` char(20) default NULL,"+
						  "`HP` int(10) default NULL,"+
						  "`MAXHP` int(10) default NULL,"+
						  "`SA` int(10) default NULL,"+
						  "`MAXSA` int(10) default NULL,"+
						  "`SKILL1_NAME` char(20) default NULL,"+
						  "`SKILL1_DMG` int(10) default NULL,"+
						  "`SKILL1_TIME` int(10) default NULL,"+
						  "`SKILL2_NAME` char(20) default NULL,"+
						  "`SKILL2_DMG` int(10) default NULL,"+
						  "`SKILL2_TIME` int(10) default NULL,"+
						  "PRIMARY KEY  (`ID`)"+
						") ;");				
			for(int i = 0; i < Player.champions.size(); i++){
				PreparedStatement put = conn.prepareStatement 
					("INSERT INTO `champions` (`NAME`,`HP`,`MAXHP`,`SA`,`MAXSA`,`SKILL1_NAME`,`SKILL1_DMG`,`SKILL1_TIME`,`SKILL2_NAME`,`SKILL2_DMG`,`SKILL2_TIME`) VALUES (?,?,?,?,?,?,?,?,?,?,?);");
					put.setString(1, Player.champions.get(i).getName());
					put.setInt(2, Player.champions.get(i).getHp());
					put.setInt(3, Player.champions.get(i).getMaxHp());
					put.setInt(4, Player.champions.get(i).getStamina());
					put.setInt(5, Player.champions.get(i).getMaxStamina());
					put.setString(6, Player.champions.get(i).skills.get(0).getName());
					put.setInt(7, Player.champions.get(i).skills.get(0).getDamage());
					put.setInt(8, Player.champions.get(i).skills.get(0).getEfect().getTime());
					put.setString(9, Player.champions.get(i).skills.get(1).getName());
					put.setInt(10, Player.champions.get(i).skills.get(1).getDamage());
					put.setInt(11, Player.champions.get(i).skills.get(1).getEfect().getTime());
					put.executeUpdate();
			}

			statement.executeUpdate("DROP TABLE IF EXISTS `reserve`;");
		    statement.executeUpdate("CREATE TABLE `reserve` ("+
						  "`ID` int(10) NOT NULL auto_increment,"+
						  "`NAME` char(20) default NULL,"+
						  "`HP` int(10) default NULL,"+
						  "`MAXHP` int(10) default NULL,"+
						  "`SA` int(10) default NULL,"+
						  "`MAXSA` int(10) default NULL,"+
						  "`SKILL1_NAME` char(20) default NULL,"+
						  "`SKILL1_DMG` int(10) default NULL,"+
						  "`SKILL1_TIME` int(10) default NULL,"+
						  "`SKILL2_NAME` char(20) default NULL,"+
						  "`SKILL2_DMG` int(10) default NULL,"+
						  "`SKILL2_TIME` int(10) default NULL,"+
						  "PRIMARY KEY  (`ID`)"+
						") ;");				
			for(int i = 0; i < Player.reserve.size(); i++){
				PreparedStatement put = conn.prepareStatement 
					("INSERT INTO `reserve` (`NAME`,`HP`,`MAXHP`,`SA`,`MAXSA`,`SKILL1_NAME`,`SKILL1_DMG`,`SKILL1_TIME`,`SKILL2_NAME`,`SKILL2_DMG`,`SKILL2_TIME`) VALUES (?,?,?,?,?,?,?,?,?,?,?);");
					put.setString(1, Player.reserve.get(i).getName());
					put.setInt(2, Player.reserve.get(i).getHp());
					put.setInt(3, Player.reserve.get(i).getMaxHp());
					put.setInt(4, Player.reserve.get(i).getStamina());
					put.setInt(5, Player.reserve.get(i).getMaxStamina());
					put.setString(6, Player.reserve.get(i).skills.get(0).getName());
					put.setInt(7, Player.reserve.get(i).skills.get(0).getDamage());
					put.setInt(8, Player.reserve.get(i).skills.get(0).getEfect().getTime());
					put.setString(9, Player.reserve.get(i).skills.get(1).getName());
					put.setInt(10, Player.reserve.get(i).skills.get(1).getDamage());
					put.setInt(11, Player.reserve.get(i).skills.get(1).getEfect().getTime());
					put.executeUpdate();
			}
			
			statement.executeUpdate("DROP TABLE IF EXISTS `tavernchamps`;");
		    statement.executeUpdate("CREATE TABLE `tavernchamps` ("+
						  "`ID` int(10) NOT NULL auto_increment,"+
						  "`NAME` char(20) default NULL,"+
						  "`HP` int(10) default NULL,"+
						  "`MAXHP` int(10) default NULL,"+
						  "`SA` int(10) default NULL,"+
						  "`MAXSA` int(10) default NULL,"+
						  "`SKILL1_NAME` char(20) default NULL,"+
						  "`SKILL1_DMG` int(10) default NULL,"+
						  "`SKILL1_TIME` int(10) default NULL,"+
						  "`SKILL2_NAME` char(20) default NULL,"+
						  "`SKILL2_DMG` int(10) default NULL,"+
						  "`SKILL2_TIME` int(10) default NULL,"+
						  "PRIMARY KEY  (`ID`)"+
						") ;");				
			for(int i = 0; i < Player.tavernChampions.size(); i++){
				PreparedStatement put = conn.prepareStatement 
					("INSERT INTO `tavernchamps` (`NAME`,`HP`,`MAXHP`,`SA`,`MAXSA`,`SKILL1_NAME`,`SKILL1_DMG`,`SKILL1_TIME`,`SKILL2_NAME`,`SKILL2_DMG`,`SKILL2_TIME`) VALUES (?,?,?,?,?,?,?,?,?,?,?);");
					put.setString(1, Player.tavernChampions.get(i).getName());
					put.setInt(2, Player.tavernChampions.get(i).getHp());
					put.setInt(3, Player.tavernChampions.get(i).getMaxHp());
					put.setInt(4, Player.tavernChampions.get(i).getStamina());
					put.setInt(5, Player.tavernChampions.get(i).getMaxStamina());
					put.setString(6, Player.tavernChampions.get(i).skills.get(0).getName());
					put.setInt(7, Player.tavernChampions.get(i).skills.get(0).getDamage());
					put.setInt(8, Player.tavernChampions.get(i).skills.get(0).getEfect().getTime());
					put.setString(9, Player.tavernChampions.get(i).skills.get(1).getName());
					put.setInt(10, Player.tavernChampions.get(i).skills.get(1).getDamage());
					put.setInt(11, Player.tavernChampions.get(i).skills.get(1).getEfect().getTime());
					put.executeUpdate();
			}
		} 
		finally {
			if (conn!= null){
				conn.close();
			}
		}
	}
}







