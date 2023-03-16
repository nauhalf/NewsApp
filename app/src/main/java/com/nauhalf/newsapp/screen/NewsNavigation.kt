package com.nauhalf.newsapp.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nauhalf.newsapp.screen.news.NewsScreen
import com.nauhalf.newsapp.screen.webview.WebViewScreen

@ExperimentalMaterial3Api
@Composable
fun NewsNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = NewsRoute.NEWS.path,
    ) {
        composable(route = NewsRoute.NEWS.path) {
            NewsScreen(navController = navController)
        }

        composable(
            route = "${NewsRoute.DETAIL_NEWS.path}?title={title}&url={url}",
            arguments = listOf(
                navArgument(name = "title") {
                    type = NavType.StringType
                    defaultValue = ""
                },

                navArgument(name = "url") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            WebViewScreen(navController = navController)
        }
    }
}
