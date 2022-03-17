package com.example.dummydashboard.presentation.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dummydashboard.databinding.ActivityMainBinding

class LogInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.loginButton.setOnClickListener {
//            if (binding.usernameField.text.toString().trim() == "admin"
//                && binding.passwordField.text.toString() == "admin"
//            ) {
//                Intent(this, DashboardActivity::class.java).also {
//                    startActivity(it)
//                    finish()
//                }
//            } else {
//                Snackbar.make(view, "Wrong credentials", Snackbar.LENGTH_SHORT).show()
//            }

            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }

        }
    }
}