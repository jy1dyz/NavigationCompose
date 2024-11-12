package kg.study.navigationcompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainVM: ViewModel() {

    fun loadDataAndNavigate(navController: NavController, itemId: String) {
        viewModelScope.launch {
            // Имитируем задержку для загрузки данных
            delay(2000L)
            // Переход на экран с деталями после загрузки
            navController.navigate("details/$itemId")
        }
    }
}