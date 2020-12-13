package com.nindikiranaf.indieband

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_indieband.view.*

class IndieBandAdapter (var list: List<IndieBand>, val sharedPref: SharedPref) : RecyclerView.Adapter<IndieBandAdapter.ViewHolder>(){
    var onItemClickListener: ((IndieBand) -> Unit)? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(indieBand: IndieBand){
            with(itemView){
                Glide.with(this).load(indieBand.gambar).into(itemImage)
                itemNama.text = indieBand.nama_band
                itemGenre.text = indieBand.genre
                itemAsal.text = indieBand.asal
                itemTh_Aktif.text = indieBand.th_aktif
                itemLagu_Fav.text = indieBand.lagu_fav
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_indieband,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val indieBand = list.get(position)
        holder.bind(indieBand)
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(indieBand)
        }
        holder.itemView.itemGenre.visibility = if(sharedPref.genre) View.VISIBLE else View.GONE
        holder.itemView.itemAsal.visibility = if(sharedPref.asal) View.VISIBLE else View.GONE
        holder.itemView.itemTh_Aktif.visibility = if(sharedPref.th_aktif) View.VISIBLE else View.GONE
        holder.itemView.itemLagu_Fav.visibility = if(sharedPref.lagu_fav) View.VISIBLE else View.GONE
    }

    override fun getItemCount(): Int {
        return list.size
    }
}