package kg.study.navigationcompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.study.navigationcompose.data.Item
import kg.study.navigationcompose.data.ViewFragmentState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ViewViewModel : ViewModel() {

    val state: MutableStateFlow<ViewFragmentState> = MutableStateFlow(ViewFragmentState())

    init {
        loadItem()
    }

    private fun loadItem() {
        viewModelScope.launch {
            delay(2000)
            val newState = ViewFragmentState(
                loading = false,
                item = Item(1, "Трансформер")
            )
            state.value = newState
        }
    }
}