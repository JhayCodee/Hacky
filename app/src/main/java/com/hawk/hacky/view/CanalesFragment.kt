package com.hawk.hacky.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.hawk.hacky.R
import com.hawk.hacky.adapter.CanalesAdapter
import com.hawk.hacky.adapter.CursosAdapter
import com.hawk.hacky.databinding.FragmentCanalesBinding
import com.hawk.hacky.databinding.FragmentHomeBinding
import com.hawk.hacky.provider.Canales
import com.hawk.hacky.provider.Cursos

class CanalesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCanalesBinding.inflate(inflater)
        return binding.root
    }

    // view binding
    private var _binding : FragmentCanalesBinding? = null
    private val binding: FragmentCanalesBinding get() = _binding!!

    // db
    private lateinit var dbref : DatabaseReference
    private lateinit var canalesRecyclerView: RecyclerView
    private lateinit var canalessArrayList : ArrayList<Canales>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadView()
    }

    // carga la vista del recycler view
    fun loadView() {

        canalesRecyclerView = binding.canalesRV
        canalesRecyclerView.layoutManager = LinearLayoutManager(context)
        canalesRecyclerView.setHasFixedSize(true)
        canalessArrayList = arrayListOf<Canales>()

        getCanales()
    }


    fun getCanales() {

        dbref = FirebaseDatabase.getInstance().getReference("Canales")

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {


                    for (canalSnapshot in snapshot.children) {

                        val canal = canalSnapshot.getValue(Canales::class.java)
                        canalessArrayList.add(canal!!)
                    }

                    binding.canalesRV.adapter = CanalesAdapter(canalessArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}