package com.example.post35.navigation

import androidx.annotation.StringRes
import com.example.post35.R
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen(val route: String, @StringRes val resourceId: Int) {

    @Serializable
    data object BehaviorChanges : Screen(
        route = "behaviorChanges",
        resourceId = R.string.label_behavior_changes
    )

    @Serializable
    data object CoreFunctionality : Screen(
        route = "coreFunctionality",
        resourceId = R.string.label_core_functionality
    )

    @Serializable
    data object DevTools : Screen(
        route = "developerTools",
        resourceId = R.string.label_developer_tools
    )

    @Serializable
    data object PrivateSpace : Screen(
        route = "privateSpace",
        resourceId = R.string.label_private_space
    )

    @Serializable
    data object UserInterface : Screen(
        route = "userInterface",
        resourceId = R.string.label_user_interface
    )
}