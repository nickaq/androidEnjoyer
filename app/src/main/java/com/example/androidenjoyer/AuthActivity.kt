package com.example.androidenjoyer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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
        sendInfoToMainScreen()

    }

    fun sendInfoToMainScreen() {
        val buttonSend = findViewById<Button>(R.id.RegisterButton)
        buttonSend.setOnClickListener {
            val emailText = findViewById<EditText>(R.id.EmailTextEditing)
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("email", emailText.text.toString())
            startActivity(intent)
        }
    }
}