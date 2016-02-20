package com.superman.coolweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.superman.coolweather.model.City;
import com.superman.coolweather.model.County;
import com.superman.coolweather.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/20.
 */
public class CoolWeatherDB {
    public static final String DB_NAME="cool_weather";
    public static final int VERSION=1;
    private static CoolWeatherDB coolWeatherDB;
    private SQLiteDatabase db;

    private CoolWeatherDB(Context context){
        CoolWeatherOpenHelper coolWeatherOpenHelper=new CoolWeatherOpenHelper(context
        ,DB_NAME,null,VERSION);
          db=coolWeatherOpenHelper.getWritableDatabase();
    }

    public synchronized static CoolWeatherDB getInstance(Context context){
        if(coolWeatherDB==null){
           coolWeatherDB=new CoolWeatherDB(context);
        }
        return coolWeatherDB;
    }

//    保存省份数据到数据库
    public void saveProvince(Province province){
        if(province!=null){
            ContentValues values=new ContentValues();
            values.put("province_name",province.getProvinceName());
            values.put("province_code",province.getProvinceCode());
            db.insert("Province",null,values);

        }
    }

//    从数据库中读取省份数据

    public List<Province> loadProvinces(){
        List<Province> list=new ArrayList<>();
        Cursor cursor=db.query("Province",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
            Province province=new Province();
            province.setProvinceName(cursor.getString(cursor.getColumnIndex("provnce_name")));
            province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
            province.setId(cursor.getInt(cursor.getColumnIndex("id")));
            list.add(province);}
            while (cursor.moveToNext());}
        if(cursor!=null){
            cursor.close();
        }

        return list;


    }
//    城市数据的存取
    public void saveCity(City city){
        if(city!=null){
            ContentValues values=new ContentValues();
            values.put("city_name",city.getCityName());
            values.put("city_code",city.getCityCode());
            values.put("province_id",city.getProvinceId());
            db.insert("City", null, values);

        }
    }

    public List<City> loadCities(int provinceId){
        List<City> list=new ArrayList<>();
        Cursor cursor=db.query("City",null,"provinceId=?",new String[]{String.valueOf(provinceId)},
                null,null,null);
        if(cursor.moveToFirst()){
            do {
                City city=new City();
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setProvinceId(provinceId);
                list.add(city);
            }while (cursor.moveToNext());
        }
        if(cursor!=null){
            cursor.close();
        }
        return list;
    }

//    县数据的存取

    public void saveCounty(County county){
        if(county!=null){
            ContentValues values=new ContentValues();
            values.put("county_name",county.getCountyName());
            values.put("county_code",county.getCountCode());
            values.put("city_id", county.getId());
            db.insert("Country",null,values);
        }
    }

    public List<County> loadCounties(int cityId){
        List<County> list=new ArrayList<County>();
        Cursor cursor=db.query("County",null,"city_id=?",new String[]{String.valueOf(cityId)},
                null,null,null);
        if(cursor.moveToFirst()){
            do{
            County county=new County();
            county.setId(cursor.getInt(cursor.getColumnIndex("id")));
            county.setCityId(cityId);
            county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
            county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
            list.add(county);}
            while (cursor.moveToNext());
        }
        if(cursor!=null){
            cursor.close();
        }
        return list;
    }

}
