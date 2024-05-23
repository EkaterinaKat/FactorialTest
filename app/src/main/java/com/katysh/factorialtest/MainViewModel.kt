package com.katysh.factorialtest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _errorLd = MutableLiveData<Boolean>()
    val errorLd: LiveData<Boolean>
        get() = _errorLd

    private val _factorialLd = MutableLiveData<String>()
    val factorialLd: LiveData<String>
        get() = _factorialLd

    private val _isLaunchingLd = MutableLiveData<Boolean>()
    val isLaunchingLd: LiveData<Boolean>
        get() = _isLaunchingLd

    fun calculate(value: String?) {
        _isLaunchingLd.value = true

        if (value.isNullOrBlank()) {
            _isLaunchingLd.value = false
            _errorLd.value = true
            return
        }

        viewModelScope.launch {
            val num = value.toLong()
            //calculate
            delay(1000)
            _isLaunchingLd.value = false
            _factorialLd.value = num.toString()
        }
    }
}