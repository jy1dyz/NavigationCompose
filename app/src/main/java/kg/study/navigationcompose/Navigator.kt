package kg.study.navigationcompose

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

interface Navigator {

    val destination: Destination
    val navigationActions: Flow<NavigationAction>

    suspend fun navigate(destination: Destination)

    suspend fun navigateUp()
}

class DefaultNavigator(override val destination: Destination): Navigator {

    private val _navigationActions = Channel<NavigationAction>()

    override val navigationActions: Flow<NavigationAction>
        get() = _navigationActions.receiveAsFlow()

    override suspend fun navigateUp() {
        _navigationActions.send(NavigationAction.NavigateUp)
    }

    override suspend fun navigate(destination: Destination) {
        _navigationActions.send(NavigationAction.Navigate(destination))
    }
}