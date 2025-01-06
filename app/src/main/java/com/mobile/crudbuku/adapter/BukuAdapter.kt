package com.mobile.crudbuku.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mobile.crudbuku.DetailPage
import com.mobile.crudbuku.R
import com.mobile.crudbuku.helper.DbHelper
import com.mobile.crudbuku.model.ModelBuku
import com.mobile.crudbuku.screenpage.UpdateNoteActivity

class BukuAdapter(
    private var listBuku : List<ModelBuku>,
    val context: Context
) : RecyclerView.Adapter<BukuAdapter.BukuViewHolder>() {

    private val db : DbHelper = DbHelper(context)

    class BukuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtpenulis: TextView = itemView.findViewById(R.id.txtPenulis)
        val txtjudul: TextView = itemView.findViewById(R.id.txtJudul)
        val txtisi: TextView = itemView.findViewById(R.id.txtIsi)

        val btnEdit : ImageView = itemView.findViewById(R.id.btnEditItem)
        val btnDelete : ImageView = itemView.findViewById(R.id.btnDeleteItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BukuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_data_buku,
            parent, false
        )
        return BukuViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listBuku.size
    }

    override fun onBindViewHolder(holder: BukuViewHolder, position: Int) {
        val nBuku = listBuku[position]
        holder.txtisi.text = nBuku.isi
        holder.txtpenulis.text = nBuku.penulis
        holder.txtjudul.text = nBuku.judul

        holder.itemView.setOnClickListener{

            val intent = Intent(holder.itemView.context, DetailPage::class.java)
            intent.putExtra("Judul", nBuku.judul)
            intent.putExtra("Penulis", nBuku.penulis)
            intent.putExtra("Isi", nBuku.isi)
            holder.itemView.context.startActivity(intent)


        }

        holder.btnDelete.setOnClickListener(){
            db.deleteBuku(nBuku.id)
            refreshData(db.getAllDataBuku())
            Toast.makeText(holder.itemView.context,
                "Berhasil Delete Data ${nBuku.penulis}", Toast.LENGTH_LONG
            ).show()
        }

        holder.btnEdit.setOnClickListener(){
            val intent = Intent(holder.itemView.context, UpdateNoteActivity::class.java).apply {
                putExtra("id_buku", nBuku.id)
            }
            holder.itemView.context.startActivity(intent)
        }
    }
    //untuk refersh data
    fun refreshData(newBuku: List<ModelBuku>) {
        listBuku = newBuku
        notifyDataSetChanged()

    }
}