package com.example.sofraapp.database.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sofraapp.database.model.Order;

import java.util.List;

@Dao
public interface OrderDAOs {
    @Insert
    void insertOrder(Order order);

    @Update
    void updateOrder(Order order);

    @Delete
    void deleteOrder(Order order);

    @Query( "DELETE FROM `Order`" )
    void  deleteAllnOrder();


    @Query("select * from `Order`")
    public List<Order> getOrderList();
}
