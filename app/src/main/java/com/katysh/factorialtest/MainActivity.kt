package com.katysh.factorialtest

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.katysh.factorialtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        observeViewModel()
        binding.buttonCalculate.setOnClickListener {
            Log.i("tag54434", "*****")
            viewModel.calculate(binding.editTextNumber.text.toString())
        }
    }

    private fun observeViewModel() {
        viewModel.errorLd.observe(this) {
            if (it) {
                Toast.makeText(this, "ERROR!", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.factorialLd.observe(this) {
            binding.textViewFactorial.text = it
        }
        viewModel.isLaunchingLd.observe(this) {
            if (it) {
                binding.progressBarLoading.visibility = View.VISIBLE
                binding.buttonCalculate.isEnabled = false
            } else {
                binding.progressBarLoading.visibility = View.GONE
                binding.buttonCalculate.isEnabled = true
            }

        }
    }
}