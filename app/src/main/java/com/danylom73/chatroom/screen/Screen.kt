package com.danylom73.chatroom.screen

sealed class Screen(val route: String) {
    object SignInScreen: Screen("sign_in_screen")
    object SignUpScreen: Screen("sign_up_screen")
    object ChatRoomScreen: Screen("chat_room_screen")
    object ChatScreen: Screen("chat_screen")
}