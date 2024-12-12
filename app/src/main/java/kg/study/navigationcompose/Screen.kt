package kg.study.navigationcompose

import android.widget.FrameLayout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen() {
    val viewModel = koinViewModel<HomeVM>()
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            Button(onClick = {
                isLoading = true
                viewModel.loadDataAndNavigate("470")
            }) {
                Text("Load Data and Go to Details")
            }
        }
    }
}


@Composable
fun DetailScreen(itemId: String?) {
    val viewModel = koinViewModel<DetailVM>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Details Screen for item ID: $itemId")
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            viewModel.openViewFragment()
//            viewModel.navigateUp()
        }) {
            Text("TO ViewFragment")
        }
    }
}

@Composable
fun OpenViewFragment() {

    val context = LocalContext.current as FragmentActivity
    val fragmentManager = context.supportFragmentManager

    FragmentContainer(fragmentManager)
}

@Composable
fun FragmentContainer(fragmentManager: FragmentManager) {
    // Создаем контейнер для фрагмента
    AndroidView(
        factory = { context ->
            FrameLayout(context).apply {
                id = R.id.view_fragment // Генерация уникального ID для контейнера
            }
        },
        modifier = Modifier.fillMaxSize()
    ) {
        fragmentManager.beginTransaction()
            .replace(
                R.id.view_fragment, //  ID совпадает с ID контейнера
                ViewFragment.newInstance()
            )
            .commit()

    }
}