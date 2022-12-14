package com.example.repuestosconejo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.repuestosconejo.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.*


class MainActivity : AppCompatActivity() {
            lateinit var mBtn: Button
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
         loadLocate()
        val actionBar=supportActionBar
        actionBar!!.title=resources.getString(R.string.app_name)
        mBtn=findViewById(R.id.mChangeLang)
        mBtn.setOnClickListener{
            showChangeLang()
        }

        FirebaseApp.initializeApp(this)
        auth = Firebase.auth


        binding.tvRegistrarse.setOnClickListener { Registro() }
        binding.btnIngresar.setOnClickListener {
            Login()

            val gso = GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN
            )
                .requestIdToken(getString(R.string.default_web_client_id_r))
                .requestEmail()
                .build()
            googleSignInClient = GoogleSignIn.getClient(this, gso)
            binding.btGoogle.setOnClickListener { googleSignIn() }
        }
    }

    private fun showChangeLang() {
        val listItmes= arrayOf("Español","English","日本","Português")

        val mBuilder=AlertDialog.Builder(this@MainActivity)
        mBuilder.setTitle("Cambiar idioma")
        mBuilder.setSingleChoiceItems(listItmes,-1){dialog,which ->
            if(which==0){
                setLocate("es")
                recreate()
            }else if(which==1){
                setLocate("en")
                recreate()
            }else if(which==2){
                setLocate("ja")
                recreate()
            }else if(which==3){
                setLocate("pt")
                recreate()
            }
            dialog.dismiss()

        }
        val mDialog=mBuilder.create()
        mDialog.show()
    }

    private fun setLocate(Lang: String) {
        val locale= Locale(Lang)
        Locale.setDefault(locale)
        val config= Configuration()
        config.locale=locale
        baseContext.resources.updateConfiguration(config,baseContext.resources.displayMetrics)

        val editor=getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang",Lang)
        editor.apply()

    }

    private fun loadLocate(){
        val sharedPreferences=getSharedPreferences("Settings",Activity.MODE_PRIVATE)
        val language=sharedPreferences.getString("My_Lang","")
        if (language != null) {
            setLocate(language)
        }
    }


    private fun googleSignIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, 5000)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 5000) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {

                val cuenta = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(cuenta.idToken!!)
            } catch (e: ApiException) {


            }
        }


    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    refresca(user)
                } else {
                    refresca(null)

                }
            }

    }

    private fun Login() {

        val email = binding.correoUsuario.text.toString()
        val contra = binding.etContra.text.toString()

        Log.d("Autenticandose", "Haciendo llamado de autenticacion")
        //Utilizo el objeto auth para hacer el registro...
        auth.signInWithEmailAndPassword(email, contra)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {//Si se logro... se creo el usuario
                    Log.d("Autenticando", "se autentico")
                    val user = auth.currentUser
                    refresca(user)
                } else {//Si no se logro, hubo un error...
                    Log.e("Autenticando", "Error de Autenticacion")
                    Toast.makeText(baseContext, "Fallo", Toast.LENGTH_LONG).show()
                    refresca(null)

                }
            }
        Log.d("Autenticando", "Sale del proceso...")
    }


    private fun Registro() {
        val email = binding.correoUsuario.text.toString()
        val contra = binding.etContra.text.toString()
        Log.d("Registrandose","Haciendo llamado a creacion")
        auth.createUserWithEmailAndPassword(email, contra).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Log.d("Registrandose","se registro")
                val user = auth.currentUser
                refresca(user)
            } else {
                Log.e("Registrandose","Error de registro")
                Toast.makeText(baseContext, "Error", Toast.LENGTH_LONG).show()
                refresca(null)
            }
        }
        Log.d("Registrandose","Sale del proceso...")
    }

    private fun refresca(user: FirebaseUser?) {
        if (user != null) {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

    }
    public override fun onStart(){
        super.onStart()
        val usuario=auth.currentUser
        refresca(usuario)
    }

}