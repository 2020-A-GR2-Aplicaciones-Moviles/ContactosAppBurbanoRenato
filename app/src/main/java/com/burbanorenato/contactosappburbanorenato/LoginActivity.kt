package com.burbanorenato.contactosappburbanorenato

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AlertDialogLayout
import kotlinx.android.synthetic.main.activity_login.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var email = editTextTextEmailAddress.text;
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
        })
    }

}

