package com.danylom73.chatroom.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.danylom73.chatroom.view_model.AuthViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SignUpScreen.route
    ) {
        composable(Screen.SignUpScreen.route) {
            SignUpScreen(
                onNavigateToSignIn = {
                    navController.navigate(Screen.SignInScreen.route)
                },
                authViewModel = authViewModel,
                modifier = modifier
            )
        }
        composable(Screen.SignInScreen.route) {
            SignInScreen(
                onNavigateToSignUp = {
                    navController.navigate(Screen.SignUpScreen.route)
                },
                authViewModel = authViewModel,
                onSignInSuccess = {
                    navController.navigate(Screen.ChatRoomScreen.route)
                },
                modifier = modifier
            )
        }
        composable(Screen.ChatRoomScreen.route) {
            ChatRoomListScreen(
                onJoinClicked = {
                    navController.navigate("${Screen.ChatScreen.route}/${it.id}")
                },
                modifier = modifier
            )
        }
        composable("${Screen.ChatScreen.route}/{roomId}") {
            val roomId: String = it.arguments?.getString("roomId") ?: ""
            ChatScreen(roomId = roomId, modifier = modifier)
        }
    }
}