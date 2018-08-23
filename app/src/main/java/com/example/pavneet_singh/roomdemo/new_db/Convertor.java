package com.example.pavneet_singh.roomdemo.new_db;

import android.arch.persistence.room.TypeConverter;

import com.example.pavneet_singh.roomdemo.model.BeenHere;
import com.example.pavneet_singh.roomdemo.model.Meta;
import com.example.pavneet_singh.roomdemo.model.Response;
import com.example.pavneet_singh.roomdemo.model.Specials;
import com.example.pavneet_singh.roomdemo.model.VenuesItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Convertor {
    static Gson gson = new Gson();
    @TypeConverter
    public static Specials fromSpecials(String value) {
        Type Specials = new TypeToken<ArrayList<String>>() {}.getType();
        return new Gson().fromJson(value, Specials);

    }

    @TypeConverter
    public static String toSpecials(Specials specials) {
        Gson gson = new Gson();
        String json = gson.toJson(specials);
        return json;
    }

    @TypeConverter
    public static List<Object> fromString(String value) {
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayLisr(List<Object> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @TypeConverter
    public static String fromMetaString(Meta meta) {
        //Type metaType = new TypeToken<Meta>() {}.getType();
        Gson gson = new Gson();
        return gson.toJson(meta);
    }


    @TypeConverter
    public static Meta fromMeta(String value) {
        Type metaType = new TypeToken<Meta>() {}.getType();
        Gson gson = new Gson();
        return gson.fromJson(value,metaType);
    }

    @TypeConverter
    public static String fromResponseString(Response response) {
        //Type metaType = new TypeToken<Meta>() {}.getType();
        Gson gson = new Gson();
        return gson.toJson(response);
    }


    @TypeConverter
    public static Response fromResponse(String value) {
        Type responseType = new TypeToken<Response>() {}.getType();
        Gson gson = new Gson();
        return gson.fromJson(value,responseType);
    }

    /*@TypeConverter
    public static String fromVanueItemString(ArrayList<VenuesItem> venuesItemArrayList) {
        //Type metaType = new TypeToken<Meta>() {}.getType();
        Gson gson = new Gson();
        return gson.toJson(venuesItemArrayList);
    }


    @TypeConverter
    public static ArrayList<VenuesItem> fromVenuesItemList(String value) {
        Type venuesItemTypeList = new TypeToken<ArrayList<VenuesItem>>() {}.getType();
        Gson gson = new Gson();
        return gson.fromJson(value,venuesItemTypeList);
    }*/

    @TypeConverter
    public static List<VenuesItem> fromString1(String value) {
        Type listType = new TypeToken<List<VenuesItem>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }
    @TypeConverter
    public static String fromArrayList1(List<VenuesItem> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
