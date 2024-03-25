package com.salt.apps.moov.ui.navigation

sealed class MoovScreen(val route: String) {
    data object HOME : MoovScreen("Home")
    data object DETAIL : MoovScreen("Detail")
    data object FAVORITE : MoovScreen("Favorite")
}