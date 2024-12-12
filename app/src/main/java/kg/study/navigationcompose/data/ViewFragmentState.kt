package kg.study.navigationcompose.data

data class ViewFragmentState(
    val loading: Boolean = true,
    val item: Item? = null,
    val error: String? = null
)


