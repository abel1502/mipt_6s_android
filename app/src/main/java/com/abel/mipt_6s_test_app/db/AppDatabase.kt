package com.abel.mipt_6s_test_app.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RestaurantEntity::class, CommercialEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao
}