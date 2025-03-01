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
    data object DevTools : Screen(
        route = "developerChanges",
        resourceId = R.string.label_developer_tools
    )
}