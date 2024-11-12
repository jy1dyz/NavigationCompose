package kg.study.navigationcompose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.koin.compose.koinInject

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navigator = koinInject<Navigator>()

    ObserveAsEvents(flow = navigator.navigationActions) { action ->
        when(action) {
            is NavigationAction.Navigate -> navController.navigate(action.destination)
            is NavigationAction.NavigateUp -> navController.navigateUp()
        }
    }
    NavHost(navController = navController, startDestination = navigator.destination) {
        navigation<Destination.HomeGraph>(startDestination = Destination.HomeScreen) {
            composable<Destination.HomeScreen> {
                HomeScreen()
            }

            composable<Destination.DetailScreen> { backStackEntry ->
                val args = backStackEntry.toRoute<Destination.DetailScreen>()
                DetailScreen(args.itemId)
            }
        }
    }
}