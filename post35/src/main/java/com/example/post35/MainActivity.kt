package com.example.post35

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.post35.theme.Android15SnippetTheme
import com.example.post35.navigation.Screen
import com.example.post35.screen.BehaviorChangesScreen
import com.example.post35.screen.DeveloperToolsScreen
import com.example.post35.screen.PrivateSpaceScreen
import com.example.post35.service.NotificationHelper

class MainActivity : ComponentActivity() {

    private val notificationHelper = NotificationHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { App() }
    }

    @Composable
    fun App() {
        val navController = rememberNavController()

        Android15SnippetTheme {
            NavHost(
                navController = navController,
                startDestination = Screen.BehaviorChanges
            ) {
                composable<Screen.BehaviorChanges> {
                    BehaviorChangesScreen(
                        notificationHelper = notificationHelper,
                        onNextClick = {
                            navController.navigate(Screen.DevTools)
                        }
                    )
                }

                composable<Screen.DevTools> {
                    DeveloperToolsScreen(
                        onNextClick = {
                            navController.navigate(Screen.PrivateSpace)
                        }
                    )
                }

                composable<Screen.PrivateSpace> {
                    PrivateSpaceScreen(notificationHelper)
                }
            }
        }
    }
}