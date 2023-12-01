package com.withorcc.enderpay.requests;

import com.withorcc.enderpay.Enderpay;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.UUID;

public class database {
    //private static Connection connection;

    public static Connection getConnection() throws SQLException {

//        if(connection != null){
//            return connection;
//        }
//
//        //Try to connect to my MySQL database running locally
//        String url = "jdbc:mysql://db.worldhosts.fun/s4980_BankDB";
//        String user = "u4980_7K91YKi39n";
//        String password = "=dgsI.+5R1!Aal=pozVfwcSD";
//
//        Connection connectionn = DriverManager.getConnection(url, user, password);
//
//        connection = connectionn;
//
//        return connection;
        return null;
    }

    public static String getvalue(Player player) throws SQLException {
        Connection connection;
        String url = "jdbc:mysql://db.worldhosts.fun/s4980_BankDB";
        String user = "u4980_7K91YKi39n";
        String password = "=dgsI.+5R1!Aal=pozVfwcSD";

        Connection connectionn = DriverManager.getConnection(url, user, password);

        connection = connectionn;





        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = '" + player.getName() + "' AND carddefault = 'True' ");

        ResultSet resultSet = statement.executeQuery();

        String c = null;
        if(resultSet.next()) {
            c = String.valueOf(resultSet.getInt(5));
            statement.close();
            connection.close();
        }
        return c;

    }
    public static void updatev(Player player, int a) throws SQLException {
        Connection connection;
        String url = Enderpay.enderpay.config.getString("db_host");
        String user = Enderpay.enderpay.config.getString("db_user");
        String password = Enderpay.enderpay.config.getString("db_password");

        Connection connectionn = DriverManager.getConnection(url, user, password);

        connection = connectionn;

        PreparedStatement statement = connection.prepareStatement("UPDATE users SET balance = " + a + " WHERE username = '" + player.getName() + "' AND carddefault = 'True'");

        statement.executeUpdate();

        statement.close();
        connection.close();

    }

}
