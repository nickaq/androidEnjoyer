package com.example.androidenjoyer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidenjoyer.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        validateEmailAndPass()
    }

    fun validateEmailAndPass() {
        binding.RegisterButton.setOnClickListener {
            //Pass Validation
            val validationPatternForPass = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$"
            val isMatchPass = Regex(validationPatternForPass).matches(binding.PasswordTextEditing.text.toString())
            if (!isMatchPass) binding.PasswordTextEditing.error =
                "min 8 characters 1 letter and 1 number"

            // Email Validation
            val validationPattern = "^[a-z0-9]+[.][a-z0-9]+@gmail\\.com\$"
            val isMatchEmail = Regex(validationPattern).matches(binding.EmailTextEditing.text.toString())
            if (!isMatchEmail) binding.EmailTextEditing.error = "test@gmail.com example"

            if (isMatchEmail && isMatchPass) {
                val beforeParseEmail = binding.EmailTextEditing
                    .text
                    .toString()
                    .trim()
                    .substringBefore("@")
                    .split(".")
                    .joinToString(" ") { word ->
                        word
                            .lowercase()
                            .replaceFirstChar { it.uppercase() }
                    }
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("email", beforeParseEmail)
                startActivity(intent)
            }
        }
    }
}