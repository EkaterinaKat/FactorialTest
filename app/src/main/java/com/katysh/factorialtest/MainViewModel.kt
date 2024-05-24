package com.katysh.factorialtest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigInteger

class MainViewModel : ViewModel() {

    private val _stateLd = MutableLiveData<State>()
    val stateLd: LiveData<State>
        get() = _stateLd

    fun calculate(value: String?) {
        _stateLd.value = Progress

        if (value.isNullOrBlank()) {
            _stateLd.value = Error
            return
        }

        viewModelScope.launch {
            val num = value.toLong()
            val result = withContext(Dispatchers.Default) {
                factorial(num)
            }
            _stateLd.value = Factorial(result)
        }
    }

    private fun factorial(num: Long): String {
        var result = BigInteger.ONE
        for (i in 1..num) {
            result = result.multiply(BigInteger.valueOf(i))
        }
        return result.toString()
    }
}