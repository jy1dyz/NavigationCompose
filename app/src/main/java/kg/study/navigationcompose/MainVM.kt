package kg.study.navigationcompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeVM(private val navigator: Navigator): ViewModel() {

    fun loadDataAndNavigate(itemId: String) {
        viewModelScope.launch {
            // Имитируем задержку для загрузки данных
            delay(2000L)
            // Переход на экран с деталями после загрузки
            navigator.navigate(destination = Destination.DetailScreen(itemId))
        }
    }
}


class DetailVM(private val navigator: Navigator): ViewModel() {

    fun navigateUp() {
        viewModelScope.launch {
            navigator.navigateUp()
        }
    }

    fun openViewFragment() {
        viewModelScope.launch {
            navigator.navigate(destination = Destination.ViewFragmentScreen)
        }
    }
}