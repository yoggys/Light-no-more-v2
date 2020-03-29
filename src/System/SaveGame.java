package System;

import java.sql.*;

import Player.Inventory;
import Player.Player;

//class by Mateusz Karbownik
public class SaveGame {
	
	public SaveGame() throws SQLException {
		
		Connection conn = null;
		try {
		    conn = DriverManager.getConnection("jdbc:h2:LNM_save", "LNM", "LNM");
			
		    Statement statement = conn.createStatement();

		    statement.executeUpdate("DROP TABLE IF EXISTS `inventory`;");
		    statement.executeUpdate("CREATE TABLE `inventory` ("+
						  "`ID` int(10) unsigned NOT NULL auto_increment,"+
						  "`ITEM_ID` unsigned default NULL,"+
						  "PRIMARY KEY  (`ID`)"+
						") ;");				
			for(int i = 0; i < Inventory.invsize(); i++){
				PreparedStatement put = conn.prepareStatement 
					("INSERT INTO `inventory` (`ITEM_ID`) VALUES (?);");
					put.setInt(i, Inventory.getid(i));
					put.executeUpdate();
			}

			statement.executeUpdate("DROP TABLE IF EXISTS `champions`;");
		    statement.executeUpdate("CREATE TABLE `champions` ("+
						  "`ID` int(10) unsigned NOT NULL auto_increment,"+
						  "`NAME` String default NULL,"+
						  "`HP` unsigned default NULL,"+
						  "`MAXHP` unsigned default NULL,"+
						  "`SA` unsigned default NULL,"+
						  "`MAXSA` unsigned default NULL,"+
						  "PRIMARY KEY  (`ID`)"+
						") ;");				
			for(int i = 0; i < Player.champions.size(); i++){
				PreparedStatement put = conn.prepareStatement 
					("INSERT INTO `champions` (`NAME`,`HP`,`MAXHP`,`SA`,`MAXSA`) VALUES (?,?,?,?,?);");
					put.setString(i, Player.champions.get(i).getName());
					put.setInt(i, Player.champions.get(i).getHp());
					put.setInt(i, Player.champions.get(i).getMaxHp());
					put.setInt(i, Player.champions.get(i).getStamina());
					put.setInt(i, Player.champions.get(i).getMaxStamina());
					put.executeUpdate();
			}

			statement.executeUpdate("DROP TABLE IF EXISTS `reserve`;");
		    statement.executeUpdate("CREATE TABLE `reserve` ("+
						  "`ID` int(10) unsigned NOT NULL auto_increment,"+
						  "`NAME` String default NULL,"+
						  "`HP` unsigned default NULL,"+
						  "`MAXHP` unsigned default NULL,"+
						  "`SA` unsigned default NULL,"+
						  "`MAXSA` unsigned default NULL,"+
						  "PRIMARY KEY  (`ID`)"+
						") ;");				
			for(int i = 0; i < Player.reserve.size(); i++){
				PreparedStatement put = conn.prepareStatement 
					("INSERT INTO `reserve` (`NAME`,`HP`,`MAXHP`,`SA`,`MAXSA`) VALUES (?,?,?,?,?);");
					put.setString(i, Player.reserve.get(i).getName());
					put.setInt(i, Player.reserve.get(i).getHp());
					put.setInt(i, Player.reserve.get(i).getMaxHp());
					put.setInt(i, Player.reserve.get(i).getStamina());
					put.setInt(i, Player.reserve.get(i).getMaxStamina());
					put.executeUpdate();
			}
			
			statement.executeUpdate("DROP TABLE IF EXISTS `tavernchamps`;");
		    statement.executeUpdate("CREATE TABLE `tavernchamps` ("+
						  "`ID` int(10) unsigned NOT NULL auto_increment,"+
						  "`NAME` String default NULL,"+
						  "`HP` unsigned default NULL,"+
						  "`MAXHP` unsigned default NULL,"+
						  "`SA` unsigned default NULL,"+
						  "`MAXSA` unsigned default NULL,"+
						  "PRIMARY KEY  (`ID`)"+
						") ;");				
			for(int i = 0; i < Player.tavernChampions.size(); i++){
				PreparedStatement put = conn.prepareStatement 
					("INSERT INTO `tavernchamps` (`NAME`,`HP`,`MAXHP`,`SA`,`MAXSA`) VALUES (?,?,?,?,?);");
					put.setString(i, Player.tavernChampions.get(i).getName());
					put.setInt(i, Player.tavernChampions.get(i).getHp());
					put.setInt(i, Player.tavernChampions.get(i).getMaxHp());
					put.setInt(i, Player.tavernChampions.get(i).getStamina());
					put.setInt(i, Player.tavernChampions.get(i).getMaxStamina());
					put.executeUpdate();
			}
			
		} finally {
			if (conn!= null){
				conn.close();
			}
		}

	}
}







