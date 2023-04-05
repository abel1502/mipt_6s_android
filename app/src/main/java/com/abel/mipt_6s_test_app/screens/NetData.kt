package com.abel.mipt_6s_test_app.screens

import com.abel.mipt_6s_test_app.net.RemoteCommercial
import com.abel.mipt_6s_test_app.net.RemoteRestaurant


data class Restaurant(
    val name: String,
    val deliveryTime: String,
    val image: String,
) {
    companion object {
        fun fromRemote(remote: RemoteRestaurant): Restaurant {
            return Restaurant(
                name = remote.name,
                deliveryTime = remote.deliveryTime,
                image = remote.image,
            )
        }
    }
}

data class Commercial(
    val picture: String,
    val url: String,
) {
    companion object {
        fun fromRemote(remote: RemoteCommercial): Commercial {
            return Commercial(
                picture = remote.picture,
                url = remote.url,
            )
        }
    }
}