package com.hawk.hacky.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
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

    private var done = false

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

    // configuras los recycler
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

    // carga los shimmer antes que se muestro los recyler con info
    fun getData() {

        if (!done) {
            Handler(Looper.getMainLooper()).postDelayed({
                getInfoFromDb("Cursos")
                getInfoFromDb("Canales")
            },3000)
            done = true
        }
        else {
            getInfoFromDb("Canales")
            getInfoFromDb("Cursos")
            binding.cursosshimmer.isVisible = false
            binding.canalesshimmer.isVisible = false

        }
        binding.canalesshimmer.stopShimmer()
        binding.cursosshimmer.stopShimmer()
    }

    // obtiene los datos de firebase para cargar los recyler view
    fun getInfoFromDb(categoria: String) {

        dbref = FirebaseDatabase.getInstance().getReference(categoria)

        if (categoria == "Cursos") {

            dbref.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    if (snapshot.exists()) {

                        var i = 0

                        for (cursoSnapshot in snapshot.children) {

                            if (i < 3) {
                                val curso = cursoSnapshot.getValue(Cursos::class.java)
                                cursosArrayList.add(curso!!)
                            }
                            i++
                        }
                        binding.cursosRV.adapter = CursosAdapter(cursosArrayList)
                        binding.cursosshimmer.isVisible = false
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("sin implementar")
                }

            })

        }
        else {
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
                        binding.canalesshimmer.isVisible = false
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("sin implementar")
                }

            })
        }

    }

}