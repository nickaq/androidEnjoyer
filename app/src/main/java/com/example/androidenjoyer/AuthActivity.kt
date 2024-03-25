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

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_auth)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        validateEmailAndPass()
    }

    fun validateEmailAndPass() {
        val buttonSend = findViewById<Button>(R.id.RegisterButton)
        buttonSend.setOnClickListener {

            //Pass Validation
            val passText = findViewById<EditText>(R.id.PasswordTextEditing)
            val passTextToString = passText.text.toString()
            val validationPatternForPass = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$"
            val isMatchPass = Regex(validationPatternForPass).matches(passTextToString)
            if (!isMatchPass) passText.error = "min 8 characters 1 letter and 1 number"

            // Email Validation
            val emailText = findViewById<EditText>(R.id.EmailTextEditing)
            val emailTextToString = emailText.text.toString()
            val validationPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"
            val isMatchEmail = Regex(validationPattern).matches(emailTextToString)
            if (!isMatchEmail) emailText.error = "test@gmail.com example"

            if (isMatchEmail && isMatchPass) sendInfoToMainScreen()
        }
    }

    fun sendInfoToMainScreen() {
        val buttonSend = findViewById<Button>(R.id.RegisterButton)
        buttonSend.setOnClickListener {
            val emailText = findViewById<EditText>(R.id.EmailTextEditing)
            val beforeParseEmail = emailText
                .text
                .toString()
                .trim()
                .substringBefore("@")
                .split(".")
                .joinToString(" ") { word -> word.replaceFirstChar { it.uppercase() } }
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("email", beforeParseEmail)
            startActivity(intent)
        }
    }
}