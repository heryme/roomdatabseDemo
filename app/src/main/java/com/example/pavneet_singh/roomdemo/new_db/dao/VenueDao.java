package com.example.pavneet_singh.roomdemo.new_db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.pavneet_singh.roomdemo.model.Response;
import com.example.pavneet_singh.roomdemo.model.VenuesItem;
import com.example.pavneet_singh.roomdemo.notedb.model.Note;
import com.example.pavneet_singh.roomdemo.util.Constants;

import java.util.List;

/**
 * Created by Pavneet_Singh on 12/31/17.
 */

@Dao
public interface VenueDao {

    @Query("SELECT * FROM "+ Constants.TABLE_NAME_VENUE)
    List<Response> getVenue();

    /*
    * Insert the object in database
    * @param note, object to be inserted
    */
    @Insert
    long insertNote(Response note);

    /*
    * update the object in database
    * @param note, object to be updated
    */
    @Update
    void updateNote(Response repos);

    /*
    * delete the object from database
    * @param note, object to be deleted
    */
    @Delete
    void deleteNote(Response note);

    // Note... is varargs, here note is an array
    /*
    * delete list of objects from database
    * @param note, array of oject to be deleted
    */
    @Delete
    void deleteNotes(Response... note);

}
