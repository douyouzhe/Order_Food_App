package com.example.youzhedou.orderfood.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.youzhedou.orderfood.Model.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Youzhe Dou on 3/26/2018.
 */

public class Database extends SQLiteAssetHelper{

    private static final String DB_NAME ="order_DB.db";
    private static final int DB_VER =1;

    public Database(Context context){
        super(context,DB_NAME,null,DB_VER);
    }

    public List<Order> getCarts(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"productId","productName","quantity","price","discount"};
        String sqlTable = "OrderDetails";

        qb.setTables(sqlTable);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);


        final List<Order> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                    result.add(new Order(
                            cursor.getString(cursor.getColumnIndex("productId")),
                            cursor.getString(cursor.getColumnIndex("productName")),
                            cursor.getString(cursor.getColumnIndex("quantity")),
                            cursor.getString(cursor.getColumnIndex("price")),
                            cursor.getString(cursor.getColumnIndex("discount"))
                    ));

            }while(cursor.moveToNext());
        }
        return result;
    }


    public void addToCart(Order order){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO OrderDetails(productId,productName,quantity,price,discount) VALUES('%s','%s','%s','%s','%s');",
                order.getProductId(),
                order.getProductName(),
                order.getQuantity(),
                order.getPrice(),
                order.getDiscount());
        db.execSQL(query);
    }

    public void clearCart(){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM OrderDetails");
        db.execSQL(query);
    }

}
