package de.sinqular.discord.mysql;

import de.sinqular.discord.Start;
import de.sinqular.discord.utils.LogLevel;

import java.sql.*;


public class MySQL {

    private Connection con = null;

    private String host = "localhost";
    private int port = 3306;
    private String datenbank = "discord";
    private String user = "admin";
    private String password = "ficken!!11";

    public MySQL() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+datenbank+"?autoReconnect=true&user="+user+"&password="+password);
            Start.getLogger().log(LogLevel.INFORMATION, "MySQL Verbindung aufgebaut");
        } catch (SQLException ex) {
            Start.getLogger().log(LogLevel.ERROR, "MySQL Verbindung wurde abgelehnt oder konnte nicht hergestellt werden");
            Start.getLogger().log(LogLevel.ERROR, "Ist der Server online bzw. sind die Daten richtig ?");



        }
    }
    private Connection getConnection() {
        if(con == null) {
            new MySQL();
        }
        return con;
    }
    public void update(String statemant) {
        con = getConnection();

        if(con != null) {
            String sql = statemant;
            PreparedStatement ps = null;

            try {
                ps = con.prepareStatement(sql);
                ps.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    public ResultSet query(String statement) {
        try {
            return query(con.prepareStatement(statement));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public ResultSet query(PreparedStatement statement) {
        try {
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public void close() {
        try {
            con.close();
            Start.getLogger().log(LogLevel.WARNING, "MySQL Verbindung wurde unterbrochen");
        } catch (SQLException ex) {
            Start.getLogger().log(LogLevel.ERROR, "Fehler beim disconnecten");
        }
    }

    public Connection getCon() {
        return con;
    }
}
