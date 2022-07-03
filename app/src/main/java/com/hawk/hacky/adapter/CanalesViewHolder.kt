package com.hawk.hacky.adapter

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hawk.hacky.databinding.CanalesBinding
import com.hawk.hacky.provider.Canales
import com.hawk.hacky.view.DetalleActivity

class CanalesViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = CanalesBinding.bind(view)

    fun render (canal: Canales){

        binding.title.text = canal.nombre
        binding.categoria.text = canal.categoria
        Glide.with(binding.image.context).load(canal.img).into(binding.image)

        binding.carView.setOnClickListener {

            val intent = Intent(binding.carView.context, DetalleActivity::class.java)
            intent.putExtra("img", canal.img)
            intent.putExtra("link", canal.link)
            intent.putExtra("nombre", canal.nombre)
            intent.putExtra("desc", canal.descripcion)
            intent.putExtra("btn", "Canal")
            binding.carView.context.startActivity(intent)
        }


    }

}