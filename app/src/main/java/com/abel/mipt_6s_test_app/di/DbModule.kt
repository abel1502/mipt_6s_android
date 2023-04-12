package com.abel.mipt_6s_test_app.di

import android.content.Context
import androidx.room.Room
import com.abel.mipt_6s_test_app.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DbModule {
    @Provides
    fun database(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "food_delivery"
    ).build()

    @Provides
    fun restaurantDao(db: AppDatabase) = db.restaurantDao()
}