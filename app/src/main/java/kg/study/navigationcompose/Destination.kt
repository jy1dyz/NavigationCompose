package kg.study.navigationcompose

import kotlinx.serialization.Serializable

sealed interface Destination {

    @Serializable
    data object HomeGraph: Destination

    @Serializable
    data object HomeScreen: Destination

    @Serializable
    data class DetailScreen(val itemId: String?): Destination
}
