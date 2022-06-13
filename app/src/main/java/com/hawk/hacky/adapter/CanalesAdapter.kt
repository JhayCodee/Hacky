package com.hawk.hacky.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hawk.hacky.R
import com.hawk.hacky.provider.Canales

class CanalesAdapter(private val canaleslIst: List<Canales>): RecyclerView.Adapter<CanalesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CanalesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CanalesViewHolder(layoutInflater.inflate(R.layout.canales, parent, false))
    }

    override fun onBindViewHolder(holder: CanalesViewHolder, position: Int) {
        val item = canaleslIst[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = canaleslIst.count()

}