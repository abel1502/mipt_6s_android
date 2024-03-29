package com.abel.mipt_6s_test_app.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RestaurantDao {
    @Query("SELECT count(*) FROM commercial LIMIT 1")
    fun isNonEmpty(): Boolean

    @Query("SELECT * FROM restaurants")
    fun getAllRestaurants(): List<RestaurantEntity>

    @Query("SELECT * FROM restaurants WHERE category = :category")
    fun getCategoryRestaurants(category: String): List<RestaurantEntity>

    // Must be a single row
    @Query("SELECT * FROM commercial LIMIT 1")
    fun getCommercial(): CommercialEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg restaurants: RestaurantEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(commercial: CommercialEntity)

    @Delete
    fun delete(restaurant: RestaurantEntity)

    @Delete
    fun delete(commercial: CommercialEntity)
}