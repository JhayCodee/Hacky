package com.hawk.hacky.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hawk.hacky.databinding.CanalesBinding
import com.hawk.hacky.provider.Canales

class CanalesViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = CanalesBinding.bind(view)

    fun render (canal: Canales){
        binding.title.text = canal.nombre
        Glide.with(binding.image.context).load(canal.img).into(binding.image)
    }

}