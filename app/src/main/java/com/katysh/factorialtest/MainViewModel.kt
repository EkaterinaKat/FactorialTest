package com.katysh.factorialtest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
            //todo calculate
            delay(1000)
            _stateLd.value = Result(num.toString())
        }
    }
}