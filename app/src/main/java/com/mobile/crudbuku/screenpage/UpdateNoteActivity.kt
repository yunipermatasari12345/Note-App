package com.mobile.crudbuku.screenpage

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mobile.crudbuku.R
import com.mobile.crudbuku.databinding.ActivityUpdateNoteBinding
import com.mobile.crudbuku.helper.DbHelper
import com.mobile.crudbuku.model.ModelBuku

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var db : DbHelper
    private var bukuId : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DbHelper(this)

        bukuId = intent.getIntExtra("id_buku",-1)
        if (bukuId == -1){
            finish()
            return
        }

        //proses menampilkan data edit

        val buku = db.getBukuById(bukuId)
        binding.etEditPenulis.setText(buku.penulis)
        binding.etEditIsi.setText(buku.isi)
        binding.etEditJudul.setText(buku.judul)


        //update dari button
        binding.btnEditNote.setOnClickListener(){
            val newPenulis = binding.etEditPenulis.text.toString()
            val newIsi = binding.etEditIsi.text.toString()
            val newJudul = binding.etEditJudul.text.toString()

            val updateBuku = ModelBuku(bukuId, newPenulis, newIsi, newJudul)
            db.updateBuku(updateBuku)
            finish()
            Toast.makeText(this,"Update Success", Toast.LENGTH_LONG).show()
        }
    }
}