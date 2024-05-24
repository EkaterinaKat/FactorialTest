package com.katysh.factorialtest

sealed class State

data object Error : State()
data object Progress : State()
class Factorial(val value: String) : State()