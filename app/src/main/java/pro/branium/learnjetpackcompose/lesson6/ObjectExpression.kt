package pro.branium.learnjetpackcompose.lesson6

import androidx.lifecycle.ViewModel

// delegate thường gặp:
// - by lazy { .... }
// - by viewModels() => by viewModels { ... }
// - by activityViewModels() => by activityViewModels { ... }

class XViewModel : ViewModel() {
    fun doSomething() {
    }
}

class Repository {
    fun doSomething() {

    }
}

fun main() {
//    val viewModel: XViewModel by activityViewModels()
}
