package com.example.mathtest;

import androidx.appcompat.app.AppCompatActivity;



import android.content.SharedPreferences;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class GoldCoins extends AppCompatActivity {
    //懒汉式的单例模式
    private static GoldCoins instance =null;

    public static GoldCoins getInstance(){
        if (instance == null) {
            synchronized (GoldCoins.class) {
                if (instance == null) {
                    instance = new GoldCoins();
                }
            }
        }
        return instance;
    }
    private GoldCoins(){}

    private int coins;

    public boolean AddCoins(int increaseNum){
        if((coins+increaseNum)<0){
            return false;
        }
        coins += increaseNum;
        DataSave();
        return true;
    }

    public String GetCoins_String(){
        DataLoad();
        return String.valueOf(coins);
    }
    public int GetCoins_Int(){
        DataLoad();
        return coins;
    }

    public int SetCoins(int nums){
        coins = nums;
        DataSave();
        return coins;
    }

    //从文件中读取金币数量
    public void DataLoad (){
        SharedPreferences sp=MainActivity.getContextObject().getSharedPreferences("Coins",MODE_PRIVATE);
        coins=sp.getInt("coins",0);
    }

    //将金币数量保存在本地
    public void DataSave(){
        SharedPreferences sp=MainActivity.getContextObject().getSharedPreferences("Coins",MODE_PRIVATE);
        SharedPreferences.Editor ed=sp.edit();
        ed.putInt("coins",coins);
        ed.commit();
    }

}
