package kg.study.navigationcompose

sealed interface NavigationAction {

    data class Navigate(val destination: Destination): NavigationAction

    data object NavigateUp: NavigationAction

}