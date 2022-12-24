package com.example.skies;
import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB_Connection {
    private static final String user = "root";
    private static final String pass = "";
    private Connection connect = null;

    @SuppressLint("NewApi")
    public Connection Connect_DB(){
        StrictMode.ThreadPolicy Policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(Policy);
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String URL = "jdbc:mysql://192.168.1.9:3306/";
            connect = DriverManager.getConnection(URL,user,pass);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connect;
    }
}
