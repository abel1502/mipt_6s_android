package com.abel.mipt_6s_test_app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.abel.mipt_6s_test_app.screens.DetailsScreen
import com.abel.mipt_6s_test_app.screens.NetScreen
import com.abel.mipt_6s_test_app.screens.SignUpScreen
import com.abel.mipt_6s_test_app.ui.theme.TestAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            TestAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "signup"
                    ) {
                        composable("signup") {
                            SignUpScreen(hiltViewModel(), navController)
                        }

                        composable("main") {
                            NetScreen(hiltViewModel(), navController)
                        }

                        composable(
                            route = "details/{id}",
                            arguments = listOf(
                                navArgument("id") {
                                    type = NavType.IntType
                                }
                            )
                        ) {
                            DetailsScreen(hiltViewModel(), navController, it.arguments?.getInt("id") ?: 0)
                        }
                    }
                }
            }
        }
    }
}
