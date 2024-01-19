package com.training.compose.lessons.scaffold

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.training.compose.lessons.navigation.FirstScreen
import com.training.compose.lessons.navigation.SecondScreen

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ScaffoldDemo() {
    MaterialTheme {
        val navController = rememberNavController()
        val items = listOf(
            Screen.FirstScreen,
            Screen.SecondScreen
        )
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "My App")
                    }, colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            },
            floatingActionButtonPosition = FabPosition.Center,
            bottomBar = {
                BottomNavigation {
                    val navStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navStackEntry?.destination
                    items.forEach { screen ->
                        BottomNavigationItem(
                            icon = { Icon(screen.icon, contentDescription = "Add") },
                            label = { Text(text = screen.title) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    // Pop up to the start destination of the graph to
                                    // avoid building up a large stack of destinations
                                    // on the back stack as users select items
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    // Avoid multiple copies of the same destination when
                                    // reselecting the same item
                                    launchSingleTop = true
                                    // Restore state when reselecting a previously selected item
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        ) {
            NavHost(
                navController,
                startDestination = Screen.FirstScreen.route,
                Modifier.padding(it)
            ) {
                composable(Screen.FirstScreen.route) {
                    FirstScreen {
                        navController.navigate(Screen.SecondScreen.route)
                    }
                }
                composable(Screen.SecondScreen.route) {
                    val secret = it.arguments?.getString("message")?: "No Message"
                    SecondScreen(secretMessage = secret)
                }
            }
        }
    }
}

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    data object FirstScreen : Screen("firstScreen", "First Screen", Icons.Default.Home)
    data object SecondScreen :
        Screen("secondScreen?message={message}", "Second Screen", Icons.Default.MailOutline)

}