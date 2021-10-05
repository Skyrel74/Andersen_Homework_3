package ru.skyrel74.andersenhomework3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.skyrel74.andersenhomework3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {

        binding.btnTask1.setOnClickListener {
            startActivity(Intent(this, FirstActivity::class.java))
        }

        binding.btnTask2.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}