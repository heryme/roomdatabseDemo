package com.example.pavneet_singh.roomdemo.new_db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.example.pavneet_singh.roomdemo.model.Response;
import com.example.pavneet_singh.roomdemo.model.VenuesItem;
import com.example.pavneet_singh.roomdemo.new_db.dao.VenueDao;
import com.example.pavneet_singh.roomdemo.notedb.dao.NoteDao;
import com.example.pavneet_singh.roomdemo.notedb.model.Note;
import com.example.pavneet_singh.roomdemo.util.Constants;
import com.example.pavneet_singh.roomdemo.util.DateRoomConverter;

/**
 * Created by Pavneet_Singh on 12/31/17.
 */

@Database(entities = {Response.class }, version = 1)
@TypeConverters({Convertor.class})
public abstract class VenueDatabase extends RoomDatabase {

    public abstract VenueDao getVenueDao();


    private static VenueDatabase noteDB;

    // synchronized is use to avoid concurrent access in multithred environment
    public static /*synchronized*/ VenueDatabase getInstance(Context context) {
        if (null == noteDB) {
            noteDB = buildDatabaseInstance(context);
        }
        return noteDB;
    }

    private static VenueDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                VenueDatabase.class,
                Constants.DB_NAME1).allowMainThreadQueries().build();
    }

    public  void cleanUp(){
        noteDB = null;
    }
}