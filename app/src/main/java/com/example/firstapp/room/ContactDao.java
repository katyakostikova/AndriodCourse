package com.example.firstapp.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.firstapp.models.Contact;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM Contact ORDER BY contactName")
    List<Contact> getAll();

    @Query("SELECT * FROM Contact WHERE id IN (:id)")
    Contact getById(int id);

    @Query("SELECT * FROM Contact WHERE isFavourite IN (:isFavourite) ORDER BY contactName")
    List<Contact> getAllFavourite(boolean isFavourite);

    @Insert
    void insert(Contact... contacts);

    @Delete
    void delete(Contact contact);

    @Update
    void update(Contact contact);
}
