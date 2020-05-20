package com.projects.android.cache.database;

import androidx.room.TypeConverter;

import java.util.Date;

public class Converter {

    @TypeConverter
    public static Date fromTimeStamp(Long time){
        return time == null ? null : new Date(time);
    }

    @TypeConverter
    public static long fromTimeStamp(Date date){
        return date == null ? null : date.getTime();
    }
}
