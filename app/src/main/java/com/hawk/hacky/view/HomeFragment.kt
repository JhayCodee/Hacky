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
import com.hawk.hacky.databinding.FragmentHomeBinding
import com.hawk.hacky.provider.Canales
import com.hawk.hacky.provider.Cursos

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    // view binding
    private var _binding : FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    // db
    private lateinit var dbref : DatabaseReference
    private lateinit var cursosRecyclerview : RecyclerView
    private lateinit var canalesRecyclerView: RecyclerView
    private lateinit var cursosArrayList : ArrayList<Cursos>
    private lateinit var canalessArrayList : ArrayList<Canales>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadView()
    }

    // carga la vista del recycler view
    fun loadView() {

        cursosRecyclerview = binding.cursosRV
        canalesRecyclerView = binding.canalesRV
        cursosRecyclerview.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
        canalesRecyclerView.layoutManager = LinearLayoutManager(context)
        cursosRecyclerview.setHasFixedSize(true)
        canalesRecyclerView.setHasFixedSize(true)
        cursosArrayList = arrayListOf<Cursos>()
        canalessArrayList = arrayListOf<Canales>()

        getData()
    }


    // obtiene los datos de firebase para cargar los recyler view
    fun getData() {

        getCanales()
        getCursos()
    }


    fun getCursos() {

        dbref = FirebaseDatabase.getInstance().getReference("Cursos")

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {

                    var i = 0

                    for (cursoSnapshot in snapshot.children) {

                        if (i < 5) {
                            val curso = cursoSnapshot.getValue(Cursos::class.java)
                            cursosArrayList.add(curso!!)
                        }
                        i++
                    }

                    binding.cursosRV.adapter = CursosAdapter(cursosArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getCanales() {

        dbref = FirebaseDatabase.getInstance().getReference("Canales")

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {

                    var i = 0

                    for (canalSnapshot in snapshot.children) {

                        if (i < 3) {
                            val canal = canalSnapshot.getValue(Canales::class.java)
                            canalessArrayList.add(canal!!)
                        }
                        i++
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