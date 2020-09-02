package com.burbanorenato.contactosappburbanorenato

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.epnfis.contactosapp.CONTACTS_FILENAME
import com.epnfis.contactosapp.LOGIN_KEY
import com.epnfis.contactosapp.PASSWORD_KEY
import com.epnfis.contactosapp.SECRET_FILENAME
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class LoginActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance();
        //InicializarArchivoDePreferencias()
        //LeerDatosDeArchivoPreferenciasEncriptado()
        buttonView.setOnClickListener() {

            val email = editTextTextEmailAddress.text.toString()
            val password = editTextTextPassword.text.toString()
            AutenticarUsuario(email, password)
/*
                    Toast.makeText(this, ""+editTextTextEmailAddress.text.toString()+ " " +editTextTextPassword.text.toString(), Toast.LENGTH_SHORT).show()
            var intent = Intent(this,PrincipalTmpActivity2::class.java)
            intent.putExtra(LOGIN_KEY,editTextTextEmailAddress.text.toString())
            startActivity(intent)
            if(editTextTextEmailAddress.text.equals("renato@epn.edu.ec") and editTextTextPassword.text.equals("12345678")){
                Toast.makeText(this, "Entró aquí", Toast.LENGTH_SHORT).show()
                //finish()
            }
        }*/

            //buttonRegister.setOnClickListener {


            //EscribirDatosEnArchivoPreferenciasEncriptado()
            //Toast.makeText(this, "Mensaje grabado", Toast.LENGTH_LONG).show()
            ////////
            /*
            val sendIntent = Intent(this, activity2::class.java)apply{
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, textMessage)
                type = "text/plain"
            }
             */

            /*var email = editTextTextEmailAddress.text;
        var contraseña = editTextTextPassword.text;
        val pattern: Pattern =
            Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")

        button.setOnClickListener(View.OnClickListener {
            val mather: Matcher = pattern.matcher(email)
            if(mather.find()==false){
                Toast.makeText(this, "El correo erroneo", Toast.LENGTH_SHORT).show();
            }
            if(contraseña.length<8){
                Toast.makeText(this, "La contraseña es erronea", Toast.LENGTH_SHORT).show();
            }
        })*/

            //  }


        }
    }
    fun AutenticarUsuario(email:String, password:String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    var intent = Intent(this,PrincipalTmpActivity2::class.java)
                    intent.putExtra(LOGIN_KEY,auth.currentUser!!.email)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(baseContext, task.exception!!.message,
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    /*
fun ValidarDatos(): Boolean {
        fun CharSequence?.isValidEmail() =
            !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
        if (editTextTextEmailAddress.text.isNullOrEmpty()) {
            editTextTextEmailAddress.setError(getString(R.string.editTextTextEmailAddress_hint))
            editTextTextEmailAddress.requestFocus()
            return false
        }
        if (!editTextTextEmailAddress.text.isValidEmail()) {
            editTextTextEmailAddress.setError(getString(R.string.email_NoValido))
            editTextTextEmailAddress.requestFocus()
            return false
        }
        if (editTextTextPassword.text.isNullOrEmpty()) {
            editTextTextPassword.setError(getString(R.string.editTextTextPassword_hint))
            editTextTextPassword.requestFocus()
            return false
        }
        if (editTextTextPassword.text.length < MIN_PASSWORD_LENGTH) {
            editTextTextPassword.setError(getString(R.string.password_longitudNoValida))
            editTextTextPassword.requestFocus()
            return false
        }
        return true
    }

    fun EscribirDatosEnArchivoPreferencias() {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        if (checkBoxLogin.isChecked) {
            checkBoxLogin
            val editor = sharedPref.edit()
            editor.putString(LOGIN_KEY, editTextTextEmailAddress.text.toString())
            editor.putString(PASSWORD_KEY, editTextTextPassword.text.toString())
            editor.commit()
        } else {
            val editor = sharedPref.edit()
            editor.putString(LOGIN_KEY, "")
            editor.putString(PASSWORD_KEY, "")
            editor.commit()
        }
    }

    fun LeerDatosDeArchivoPreferencias() {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        editTextTextEmailAddress.setText(sharedPref.getString(LOGIN_KEY, ""))
        editTextTextPassword.setText(sharedPref.getString(PASSWORD_KEY, ""))
    }
*/
    fun InicializarArchivoDePreferencias(){
        val masterKeyAlias: String = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        sharedPreferences = EncryptedSharedPreferences.create(
            SECRET_FILENAME,//filename
            masterKeyAlias,
            this,//context
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun EscribirDatosEnArchivoPreferenciasEncriptado() {
         if (checkBoxLogin.isChecked) {
            sharedPreferences.edit()
                .putString(LOGIN_KEY, editTextTextEmailAddress.text.toString())
                .putString(PASSWORD_KEY, editTextTextPassword.text.toString())
                .apply()
        } else {
            sharedPreferences
                .edit()
                .putString(LOGIN_KEY, "")
                .putString(PASSWORD_KEY, "")
                .apply()
        }
    }
    fun LeerDatosDeArchivoPreferenciasEncriptado() {
        editTextTextEmailAddress.setText(sharedPreferences.getString(LOGIN_KEY, ""))
        editTextTextPassword.setText(sharedPreferences.getString(PASSWORD_KEY, ""))
    }

    fun EscribirDatosEnArchivoInterno(){
        openFileOutput(CONTACTS_FILENAME, Context.MODE_PRIVATE).bufferedWriter().use {fos ->
            fos.write(editTextTextEmailAddress.text.toString())
            fos.write(System.lineSeparator())
            fos.write(editTextTextEmailAddress.text.toString())
        }
    }

    fun LeerDatosEnArchivoInterno() {
        openFileInput(CONTACTS_FILENAME).bufferedReader().use {
            val datoLeido = it.readText()
            if(datoLeido.isNullOrEmpty()) return
            val textArray = datoLeido.split(System.lineSeparator())
            val login = textArray[0]
            val clave = textArray[1]
        }
    }

    fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }
    fun EscribirDatosEnArchivoExterno(){
        if (isExternalStorageWritable()) {
            FileOutputStream(File(getExternalFilesDir(null),CONTACTS_FILENAME)).bufferedWriter().use { outputStream ->
                outputStream.write("dato1")
                outputStream.write(System.lineSeparator())
                outputStream.write("dato2")
            }
        }
    }

    fun isExternalStorageReadable(): Boolean {
        return Environment.getExternalStorageState() in
                setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)
    }
    fun LeerDatosEnArchivoExterno(){
        if (isExternalStorageReadable()) {
            FileInputStream(File(getExternalFilesDir(null),CONTACTS_FILENAME)).bufferedReader().use {
                val datoLeido = it.readText()
                val textArray = datoLeido.split(System.lineSeparator())
                val texto1 = textArray[0]
                val texto2 = textArray[1]
            }
        }
    }

}

