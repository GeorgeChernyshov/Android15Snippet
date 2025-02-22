package com.example.pre35

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pre35.theme.Android15SnippetTheme
import com.example.pre35.navigation.Screen
import com.example.pre35.screen.BehaviorChangesScreen

class MainActivity : ComponentActivity() {

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
                    BehaviorChangesScreen()
                }
            }
        }
    }
}