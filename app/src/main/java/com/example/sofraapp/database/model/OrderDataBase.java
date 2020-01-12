package com.example.sofraapp.database.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sofraapp.database.DAOs.OrderDAOs;

@Database(entities = Order.class, version = 1, exportSchema = false)
public abstract class OrderDataBase extends RoomDatabase {

    private final static String SOFRA_APP_DATABASE = "SOFRA_APP_DATABASE";
    private static OrderDataBase orderDataBase;

    public abstract OrderDAOs orderDAOs();

    public static OrderDataBase getInstance(Context context) {

        if (orderDataBase == null) {
            //initialize
            orderDataBase = Room.databaseBuilder(context, OrderDataBase.class, SOFRA_APP_DATABASE)
                    .fallbackToDestructiveMigrationFrom()
                    .allowMainThreadQueries()
                    .build();
        }
        return orderDataBase;
    }

}
