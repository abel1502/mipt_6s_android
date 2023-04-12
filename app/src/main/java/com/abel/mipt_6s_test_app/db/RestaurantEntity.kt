package com.abel.mipt_6s_test_app.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abel.mipt_6s_test_app.net.RemoteRestaurant

@Entity(tableName = "restaurants")
data class RestaurantEntity(
    @PrimaryKey @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "logo")
    val logo: String,
    @ColumnInfo(name = "deliveryTime")
    val deliveryTime: String,
    @ColumnInfo(name = "category")
    val category: String,
)

fun RemoteRestaurant.toRestaurantEntity(category: String): RestaurantEntity =
    RestaurantEntity(
        id = id + (if (category == "nearest") 1000 else 0),
        name = name,
        logo = image,
        deliveryTime = deliveryTime,
        category = category,
    )

fun RestaurantEntity.toRemoteRestaurant(): RemoteRestaurant =
    RemoteRestaurant(
        id = id - (if (category == "nearest") 1000 else 0),
        name = name,
        image = logo,
        deliveryTime = deliveryTime,
    )
