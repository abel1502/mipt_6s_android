package com.abel.mipt_6s_test_app.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abel.mipt_6s_test_app.net.RemoteCommercial

@Entity(tableName = "commercial")
data class CommercialEntity(
    // How do I make it autoincrement?
    @PrimaryKey @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "picture")
    val picture: String,
    @ColumnInfo(name = "url")
    val url: String,
)

fun RemoteCommercial.toCommercialEntity(): CommercialEntity =
    CommercialEntity(
        id = 0,
        picture = picture,
        url = url,
    )

fun CommercialEntity.toRemoteCommercial(): RemoteCommercial =
    RemoteCommercial(
        picture = picture,
        url = url,
    )
