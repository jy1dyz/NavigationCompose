package kg.study.navigationcompose

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single<Navigator> {
        DefaultNavigator(destination = Destination.HomeScreen)
    }

    viewModelOf(::HomeVM)
    viewModelOf(::DetailVM)
}