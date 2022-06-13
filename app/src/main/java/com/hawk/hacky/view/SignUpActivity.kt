package com.hawk.hacky.view

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hawk.hacky.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.iniciarSesion.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnRegistrar.setOnClickListener {
            register()
        }

    }

    private fun register() {
        var bandera = 0
        //ya revisa si son iguales y 1 True , 0 False
        bandera = valPass()
        // validacion para no aceptar espacios en blanco ni campo vacío
        if ((binding.txtNombre.editText?.text.toString().trim{ it <= ' '}.isEmpty() &&
                    binding.txtEmail.editText?.text.toString().trim{ it <= ' '}.isEmpty() &&
                    binding.txtContra.editText?.text.toString().trim{ it <= ' '}.isEmpty() &&
                    binding.txtConfirmarContra.editText?.text.toString().trim{ it <= ' '}.isEmpty()
                    )) {
            Toast.makeText(this, "Debe llenar los campos", Toast.LENGTH_SHORT).show()
        }
        else {
            if(bandera!=1){
                Toast.makeText(this, "Contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }else{
                // si no hay campos vacios registra en la db
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.txtEmail.editText?.text.toString().trim() ,binding.txtContra.editText?.text.toString().trim()).addOnCompleteListener { Task ->
                    if (Task.isSuccessful) {

                        //Si todo esta bien hace el usuario pero ya no importa si NULL!

                        val firebaseUser: FirebaseUser = Task.result!!.user!!
                        Toast.makeText(this, "Usted se ha registrado con exito! :D", Toast.LENGTH_SHORT).show()
                        //Baila con  las cosas de Firebase
                        DBcosas()
                        val intent = Intent(this,MainActivity::class.java)//Menu principal
                        startActivity(intent)
                    }
                }
            }
        }

    }

    private  fun valPass():Int{

        val contra1:String = binding.txtContra.editText?.text.toString().trim()
        val contra2:String = binding.txtConfirmarContra.editText?.text.toString().trim()
        //Tomorrowp={}\=p
        val resultado = if (contra1 == contra2){
            1
        }
        else {
            0
        }
        return resultado
    }

    private fun DBcosas(){
        val db = Firebase.firestore
        //Creamos un mapa osea el usuario a añadir
        val user = hashMapOf(
            "Nombre" to binding.txtNombre.editText?.text.toString().trim(),
            "Correo" to binding.txtEmail.editText?.text.toString().trim(),
            "Contraseña" to binding.txtContra.editText?.text.toString().trim()
        )

        db.collection("Usuarios")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG, "Se añadio usuario con  ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error al añadir el documento", e)
            }
    }
}

