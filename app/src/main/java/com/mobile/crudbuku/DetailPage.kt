package com.mobile.crudbuku

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailPage : AppCompatActivity() {

    private lateinit var txtJudul : TextView
    private lateinit var txtPenulis : TextView
    private lateinit var txtIsi : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_page)

        txtJudul = findViewById(R.id.txtJudul)
        txtPenulis = findViewById(R.id.txtPenulis)
        txtIsi = findViewById(R.id.txtIsi)

        val detailJudul = intent.getStringExtra("Judul")
        val detailPenulis = intent.getStringExtra("Penulis")
        val detilIsi = intent.getStringExtra("Isi")

        txtJudul.setText(detailJudul)
        txtPenulis.setText(detailPenulis)
        txtIsi.setText(detilIsi)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
}