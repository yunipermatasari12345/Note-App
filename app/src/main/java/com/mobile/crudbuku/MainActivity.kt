package com.mobile.crudbuku

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.crudbuku.adapter.BukuAdapter
import com.mobile.crudbuku.databinding.ActivityMainBinding
import com.mobile.crudbuku.helper.DbHelper
import com.mobile.crudbuku.screenpage.TambahDataBuku

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db : DbHelper
    private lateinit var bukuAdapter: BukuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DbHelper(this)
        bukuAdapter = BukuAdapter(db.getAllDataBuku(),this)

        binding.rvDataBuku.layoutManager = LinearLayoutManager(this)
        binding.rvDataBuku.adapter = bukuAdapter

        //silahkan buat detail page
        //ketika diklik item nya akan pindah

        binding.btnPageTambah.setOnClickListener{
            val intent = Intent(this,TambahDataBuku::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val newBuku = db.getAllDataBuku()
        bukuAdapter.refreshData(newBuku)

    }
}