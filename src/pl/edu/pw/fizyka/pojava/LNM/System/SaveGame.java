package pl.edu.pw.fizyka.pojava.LNM.System;
import java.sql.*;

import pl.edu.pw.fizyka.pojava.LNM.Player.Inventory;
import pl.edu.pw.fizyka.pojava.LNM.Player.Player;

//class by Mateusz Karbownik
public class SaveGame {

	public static void saveGame() throws SQLException {

		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:h2:./Saves/LNM_save", "LNM", "LNM");

			Statement statement = conn.createStatement();

			statement.executeUpdate("DROP TABLE IF EXISTS `inventory`;");
			statement.executeUpdate("CREATE TABLE `inventory` (" + "`ID` int(10) NOT NULL auto_increment,"
					+ "`ITEM_ID` int(10) default NULL," + "PRIMARY KEY  (`ID`)" + ") ;");
			for (int i = 0; i < Inventory.invSize(); i++) {
				PreparedStatement put = conn.prepareStatement("INSERT INTO `inventory` (`ITEM_ID`) VALUES (?);");
				put.setInt(1, Inventory.getId(i));
				put.executeUpdate();
			}
			statement.executeUpdate("DROP TABLE IF EXISTS `player`;");
			statement.executeUpdate(
					"CREATE TABLE `player` (" + "`ID` int(10) NOT NULL auto_increment," + "`GOLD` int(10) default NULL,"
							+ "`DUNGEON` int(10) default NULL," + "PRIMARY KEY  (`ID`)" + ") ;");
			if (true) {
				PreparedStatement put = conn.prepareStatement("INSERT INTO `player` (`GOLD`,`DUNGEON`) VALUES (?,?);");
				put.setInt(1, Inventory.getGold());
				put.setInt(2, Player.currentDungeon);
				put.executeUpdate();
			}

			statement.executeUpdate("DROP TABLE IF EXISTS `champions`;");
			statement.executeUpdate("CREATE TABLE `champions` (" + "`ID` int(10) NOT NULL auto_increment,"
					+ "`NAME` char(20) default NULL," + "`HP` int(10) default NULL," + "`MAXHP` int(10) default NULL,"
					+ "`SA` int(10) default NULL," + "`MAXSA` int(10) default NULL,"
					+ "`SKILL1_NAME` char(20) default NULL," + "`SKILL1_DMG` int(10) default NULL,"
					+ "`SKILL1_SA` int(10) default NULL," + "`SKILL1_EFFECT_DMG` int(10) default NULL,"
					+ "`SKILL1_EFFECT_TIME` int(10) default NULL," + "`SKILL2_NAME` char(20) default NULL,"
					+ "`SKILL2_DMG` int(10) default NULL," + "`SKILL2_SA` int(10) default NULL,"
					+ "`SKILL2_EFFECT_DMG` int(10) default NULL," + "`SKILL2_EFFECT_TIME` int(10) default NULL,"
					+ "`AVATAR` char(50) default NULL," + "PRIMARY KEY  (`ID`)" + ") ;");
			for (int i = 0; i < Player.champions.size(); i++) {
				PreparedStatement put = conn.prepareStatement(
						"INSERT INTO `champions` (`NAME`,`HP`,`MAXHP`,`SA`,`MAXSA`,`SKILL1_NAME`,`SKILL1_DMG`,`SKILL1_SA`,`SKILL1_EFFECT_DMG`,`SKILL1_EFFECT_TIME`,`SKILL2_NAME`,`SKILL2_DMG`,`SKILL2_SA`,`SKILL2_EFFECT_DMG`,`SKILL2_EFFECT_TIME`,`AVATAR`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
				put.setString(1, Player.champions.get(i).getName());
				put.setInt(2, Player.champions.get(i).getHp());
				put.setInt(3, Player.champions.get(i).getMaxHp());
				put.setInt(4, Player.champions.get(i).getStamina());
				put.setInt(5, Player.champions.get(i).getMaxStamina());
				put.setString(6, Player.champions.get(i).skills.get(0).getName());
				put.setInt(7, Player.champions.get(i).skills.get(0).getDamage());
				put.setInt(8, Player.champions.get(i).skills.get(0).getStaminaUse());
				if (Player.champions.get(i).skills.get(0).getEffect() != null) {
					put.setInt(9, Player.champions.get(i).skills.get(0).getEffect().getDamage());
					put.setInt(10, Player.champions.get(i).skills.get(0).getEffect().getTime());
				} else {
					put.setInt(9, 0);
					put.setInt(10, 0);
				}
				put.setString(11, Player.champions.get(i).skills.get(1).getName());
				put.setInt(12, Player.champions.get(i).skills.get(1).getDamage());
				put.setInt(13, Player.champions.get(i).skills.get(1).getStaminaUse());
				if (Player.champions.get(i).skills.get(0).getEffect() != null) {
					put.setInt(14, Player.champions.get(i).skills.get(0).getEffect().getDamage());
					put.setInt(15, Player.champions.get(i).skills.get(0).getEffect().getTime());
				} else {
					put.setInt(14, 0);
					put.setInt(15, 0);
				}
				put.setString(16, Player.champions.get(i).getAvatar());
				put.executeUpdate();
			}

			statement.executeUpdate("DROP TABLE IF EXISTS `reserve`;");
			statement.executeUpdate("CREATE TABLE `reserve` (" + "`ID` int(10) NOT NULL auto_increment,"
					+ "`NAME` char(20) default NULL," + "`HP` int(10) default NULL," + "`MAXHP` int(10) default NULL,"
					+ "`SA` int(10) default NULL," + "`MAXSA` int(10) default NULL,"
					+ "`SKILL1_NAME` char(20) default NULL," + "`SKILL1_DMG` int(10) default NULL,"
					+ "`SKILL1_SA` int(10) default NULL," + "`SKILL1_EFFECT_DMG` int(10) default NULL,"
					+ "`SKILL1_EFFECT_TIME` int(10) default NULL," + "`SKILL2_NAME` char(20) default NULL,"
					+ "`SKILL2_DMG` int(10) default NULL," + "`SKILL2_SA` int(10) default NULL,"
					+ "`SKILL2_EFFECT_DMG` int(10) default NULL," + "`SKILL2_EFFECT_TIME` int(10) default NULL,"
					+ "`AVATAR` char(50) default NULL," + "PRIMARY KEY  (`ID`)" + ") ;");
			for (int i = 0; i < Player.reserve.size(); i++) {
				PreparedStatement put = conn.prepareStatement(
						"INSERT INTO `reserve` (`NAME`,`HP`,`MAXHP`,`SA`,`MAXSA`,`SKILL1_NAME`,`SKILL1_DMG`,`SKILL1_SA`,`SKILL1_EFFECT_DMG`,`SKILL1_EFFECT_TIME`,`SKILL2_NAME`,`SKILL2_DMG`,`SKILL2_SA`,`SKILL2_EFFECT_DMG`,`SKILL2_EFFECT_TIME`,`AVATAR`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
				put.setString(1, Player.reserve.get(i).getName());
				put.setInt(2, Player.reserve.get(i).getHp());
				put.setInt(3, Player.reserve.get(i).getMaxHp());
				put.setInt(4, Player.reserve.get(i).getStamina());
				put.setInt(5, Player.reserve.get(i).getMaxStamina());
				put.setString(6, Player.reserve.get(i).skills.get(0).getName());
				put.setInt(7, Player.reserve.get(i).skills.get(0).getDamage());
				put.setInt(8, Player.reserve.get(i).skills.get(0).getStaminaUse());
				if (Player.reserve.get(i).skills.get(0).getEffect() != null) {
					put.setInt(9, Player.reserve.get(i).skills.get(0).getEffect().getDamage());
					put.setInt(10, Player.reserve.get(i).skills.get(0).getEffect().getTime());
				} else {
					put.setInt(9, 0);
					put.setInt(10, 0);
				}
				put.setString(11, Player.reserve.get(i).skills.get(1).getName());
				put.setInt(12, Player.reserve.get(i).skills.get(1).getDamage());
				put.setInt(13, Player.reserve.get(i).skills.get(1).getStaminaUse());
				if (Player.reserve.get(i).skills.get(0).getEffect() != null) {
					put.setInt(14, Player.reserve.get(i).skills.get(0).getEffect().getDamage());
					put.setInt(15, Player.reserve.get(i).skills.get(0).getEffect().getTime());
				} else {
					put.setInt(14, 0);
					put.setInt(15, 0);
				}
				put.setString(16, Player.reserve.get(i).getAvatar());
				put.executeUpdate();
			}

			statement.executeUpdate("DROP TABLE IF EXISTS `tavern_champions`;");
			statement.executeUpdate("CREATE TABLE `tavern_champions` (" + "`ID` int(10) NOT NULL auto_increment,"
					+ "`NAME` char(20) default NULL," + "`HP` int(10) default NULL," + "`MAXHP` int(10) default NULL,"
					+ "`SA` int(10) default NULL," + "`MAXSA` int(10) default NULL,"
					+ "`SKILL1_NAME` char(20) default NULL," + "`SKILL1_DMG` int(10) default NULL,"
					+ "`SKILL1_SA` int(10) default NULL," + "`SKILL1_EFFECT_DMG` int(10) default NULL,"
					+ "`SKILL1_EFFECT_TIME` int(10) default NULL," + "`SKILL2_NAME` char(20) default NULL,"
					+ "`SKILL2_DMG` int(10) default NULL," + "`SKILL2_SA` int(10) default NULL,"
					+ "`SKILL2_EFFECT_DMG` int(10) default NULL," + "`SKILL2_EFFECT_TIME` int(10) default NULL,"
					+ "`AVATAR` char(50) default NULL," + "PRIMARY KEY  (`ID`)" + ") ;");
			for (int i = 0; i < Player.tavernChampions.size(); i++) {
				PreparedStatement put = conn.prepareStatement(
						"INSERT INTO `tavern_champions` (`NAME`,`HP`,`MAXHP`,`SA`,`MAXSA`,`SKILL1_NAME`,`SKILL1_DMG`,`SKILL1_SA`,`SKILL1_EFFECT_DMG`,`SKILL1_EFFECT_TIME`,`SKILL2_NAME`,`SKILL2_DMG`,`SKILL2_SA`,`SKILL2_EFFECT_DMG`,`SKILL2_EFFECT_TIME`,`AVATAR`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
				put.setString(1, Player.tavernChampions.get(i).getName());
				put.setInt(2, Player.tavernChampions.get(i).getHp());
				put.setInt(3, Player.tavernChampions.get(i).getMaxHp());
				put.setInt(4, Player.tavernChampions.get(i).getStamina());
				put.setInt(5, Player.tavernChampions.get(i).getMaxStamina());
				put.setString(6, Player.tavernChampions.get(i).skills.get(0).getName());
				put.setInt(7, Player.tavernChampions.get(i).skills.get(0).getDamage());
				put.setInt(8, Player.tavernChampions.get(i).skills.get(0).getStaminaUse());
				if (Player.tavernChampions.get(i).skills.get(0).getEffect() != null) {
					put.setInt(9, Player.tavernChampions.get(i).skills.get(0).getEffect().getDamage());
					put.setInt(10, Player.tavernChampions.get(i).skills.get(0).getEffect().getTime());
				} else {
					put.setInt(9, 0);
					put.setInt(10, 0);
				}
				put.setString(11, Player.tavernChampions.get(i).skills.get(1).getName());
				put.setInt(12, Player.tavernChampions.get(i).skills.get(1).getDamage());
				put.setInt(13, Player.tavernChampions.get(i).skills.get(1).getStaminaUse());
				if (Player.tavernChampions.get(i).skills.get(0).getEffect() != null) {
					put.setInt(14, Player.tavernChampions.get(i).skills.get(0).getEffect().getDamage());
					put.setInt(15, Player.tavernChampions.get(i).skills.get(0).getEffect().getTime());
				} else {
					put.setInt(14, 0);
					put.setInt(15, 0);
				}
				put.setString(16, Player.tavernChampions.get(i).getAvatar());
				put.executeUpdate();
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}
