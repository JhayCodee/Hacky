package com.hawk.hacky.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


import com.hawk.hacky.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    //auth servicio
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var Contador = 0

        // abre signUp Activity
        binding.nuevoUsuario.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.btnIniciarSesion.setOnClickListener{
            if(binding.txtEmail.editText?.text.toString().trim{ it <= ' '}.isEmpty() && binding.txtPassword.editText?.text.toString().trim{it <=' '}.isEmpty()){
                Toast.makeText(this, "Debe llenar los campos", Toast.LENGTH_SHORT).show()
            }else{
                auth.signInWithEmailAndPassword(binding.txtEmail.editText?.text.toString().trim(),
                    binding.txtPassword.editText?.text.toString().trim()).addOnCompleteListener { Task ->


                    if (Task.isSuccessful) {
                        val intent = Intent(this,  MainActivity::class.java)
                        startActivity(intent)
                    }
                    else {
                        Toast.makeText(this, "ERROR , Revise Correo O contraseña", Toast.LENGTH_SHORT).show()
                        Contador += 1
                        if (Contador == 3) {
                            Contador = 0
                            showDialog()
                        }
                    }
            }
        }
    }

}

    //implementar funciones
    private fun showDialog(){

        // iniciar tarde
        lateinit var dialog:AlertDialog

        // constructor
        val builder = AlertDialog.Builder(this)

        // Titulo
        builder.setTitle("¿Eres nuevo?")

        // Mensaje
        builder.setMessage("Parece que eres nuevo\nDeseas Crear una nueva cuenta?")

        //IMPLEMENTAR
        // On click listener for dialog buttons
        val dialogClickListener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> startActivity(Intent(this,  SignUpActivity::class.java))
                DialogInterface.BUTTON_NEGATIVE -> Toast.makeText(this, "Revise su contrasena o correo", Toast.LENGTH_SHORT).show()
            }
        }

        // Set the alert dialog positive/yes button
        builder.setPositiveButton("Si",dialogClickListener)
        // Set the alert dialog negative/no button
        builder.setNegativeButton("NO",dialogClickListener)
        // Initialize the AlertDialog using builder object
        dialog = builder.create()
        // Finally, display the alert dialog
        dialog.show()
    }
}

