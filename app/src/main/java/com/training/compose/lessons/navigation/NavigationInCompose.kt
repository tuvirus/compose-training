package com.training.compose.lessons.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.training.compose.lessons.themes.MyCustomTheme


// use the / for obligatory parameters and ? for optional parameters
@Composable
fun NavigationInComposeScreen() {
    MyCustomTheme {
        val navController = rememberNavController()

        NavHost(navController =navController, startDestination = "firstScreen"){
            composable("firstScreen"){
                FirstScreen{
                    navController.navigate("secondScreen?message=Hello from first Screen yo!")
                }
            }
            composable("secondScreen?message={message}", arguments = listOf(
                navArgument("message"){
                    type = NavType.StringType
                    defaultValue = "No message"
                })
            ){
                val secretMessage = it.arguments?.getString("message")
                SecondScreen(secretMessage?: "No message")
            }
        }
    }
}

@Composable
fun FirstScreen(navigateToScreenEvent: () -> Unit){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(text = "Hello From First Screen")
            Button(onClick = navigateToScreenEvent) {
              Text(text = "To Second Screen")
            }
        }

    }
}

@Composable
fun SecondScreen(secretMessage: String){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Hello From Second Screen, i have a message -> $secretMessage")
    }
}



@Preview(showBackground = true)
@Composable
private fun NavigationInComposePreview() {
    NavigationInComposeScreen()
}