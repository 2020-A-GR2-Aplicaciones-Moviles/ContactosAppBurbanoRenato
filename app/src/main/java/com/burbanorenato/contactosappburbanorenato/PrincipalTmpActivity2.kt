package com.burbanorenato.contactosappburbanorenato

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.epnfis.contactosapp.ContactoAdapter
import com.epnfis.contactosapp.ContactoModelClass
import com.epnfis.contactosapp.LOGIN_KEY
import com.epnfis.contactosapp.PERMISSION_REQUEST_CODE
import com.epnfis.contactosapp.database.ContactosSQLiteOpenHelper
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_principal_tmp2.*
import kotlinx.android.synthetic.main.activity_principal_tmp2.buttonView
import kotlinx.android.synthetic.main.activity_principal_tmp2.editTextTextEmailAddress
import kotlinx.android.synthetic.main.activity_principal_tmp2.textViewEmailAddress
import kotlinx.android.synthetic.main.activity_principal_tmp2.textViewFirstName
import kotlinx.android.synthetic.main.activity_principal_tmp2.textViewLastName
import org.json.JSONObject

class PrincipalTmpActivity2 : AppCompatActivity() {
    var contactos = arrayListOf<ContactoModelClass>()
    var selectedContactPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal_tmp2)

        val username = intent.getStringExtra(LOGIN_KEY)
        supportActionBar?.title = supportActionBar?.title.toString() + " - " + username.substringBefore("@")

        // Get the support action bar
        val actionBar = supportActionBar
        // Set the action bar title, subtitle and elevation
        actionBar!!.title = supportActionBar?.title.toString()
        actionBar.subtitle = username.substringBefore("@")
        actionBar.elevation = 4.0F
        ConsultarContactos()


        /*contactos.add(ContactoModelClass(1,"Victor","Velepucha","09911223344", "victor.velepucha@epn.edu.ec"))
        contactos.add(ContactoModelClass(2,"Juan","Perez","0991234567", "juan.perez@epn.edu.ec"))
        contactos.add(ContactoModelClass(3,"Michelle","Salinas","0995225599", "michelle.salinas@epn.edu.ec"))
        contactos.add(ContactoModelClass(4,"Rosa","Vallejo","+593995225100", "rosa.vallejo@epn.edu.ec"))
        val contactoAdaptador = ContactoAdapter(this, contactos)
        listViewContacts.adapter = contactoAdaptador*/

        listViewContacts.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show()

            selectedContactPosition = position
            editTextUserId.setText(contactos[selectedContactPosition].userId.toString())
            editTextTextFirstName.setText(contactos[selectedContactPosition].firstName.toString())
            editTextTextLastName.setText(contactos[selectedContactPosition].lastName.toString())
            editTextPhoneNumber.setText(contactos[selectedContactPosition].phoneNumber.toString())
            editTextTextEmailAddress.setText(contactos[selectedContactPosition].emailAddress.toString())
        }

        buttonSave.setOnClickListener {
            val id = editTextUserId.text.toString().toInt()
            val nombre = editTextTextFirstName.text.toString()
            val apellido = editTextTextLastName.text.toString()
            val telefono = editTextPhoneNumber.text.toString()
            val email = editTextTextEmailAddress.text.toString()
            //contactos.add(ContactoModelClass(id,nombre,apellido,telefono, email))

            val respuesta = ContactosSQLiteOpenHelper(this).addContacto(ContactoModelClass(id,nombre,apellido,telefono,email))
            if(respuesta > -1){
                //val contactoAdaptador = ContactoAdapter(this, contactos)
                //listViewContacts.adapter = contactoAdaptador
                Toast.makeText(this,"Contacto añadido", Toast.LENGTH_LONG).show()
                limpiarCamposEditables()
            }
            else{
                Toast.makeText(this,"Error al grabar Contacto", Toast.LENGTH_LONG).show()
            }

        }

        buttonView.setOnClickListener {
            contactos = ContactosSQLiteOpenHelper(this).readContacto()

            listViewContacts.adapter = ContactoAdapter(this, contactos)

            limpiarCamposEditables()
        }

        buttonUpdate.setOnClickListener {
            contactos[selectedContactPosition].userId = editTextUserId.text.toString().toInt()
            contactos[selectedContactPosition].firstName = editTextTextFirstName.text.toString()
            contactos[selectedContactPosition].lastName = editTextTextLastName.text.toString()
            contactos[selectedContactPosition].phoneNumber = editTextPhoneNumber.text.toString()
            contactos[selectedContactPosition].emailAddress = editTextTextEmailAddress.text.toString()
            listViewContacts.adapter = ContactoAdapter(this, contactos)

            val respuesta = ContactosSQLiteOpenHelper(this).updateContacto(contactos[selectedContactPosition])
            if(respuesta > -1){
                //val contactoAdaptador = ContactoAdapter(this, contactos)
                //listViewContacts.adapter = contactoAdaptador
                Toast.makeText(this,"Contacto actualizado",Toast.LENGTH_LONG).show()

                limpiarCamposEditables()
            }
            else{
                Toast.makeText(this,"Error al actualizar Contacto", Toast.LENGTH_LONG).show()
            }
            limpiarCamposEditables()
        }

        buttonDelete.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("Confirmación de Eliminación")
            dialogBuilder.setIcon(android.R.drawable.ic_dialog_alert)
            dialogBuilder.setMessage("¿Esta seguro que desea eliminar el contacto ${contactos[selectedContactPosition].firstName} ${contactos[selectedContactPosition].lastName}?")
            dialogBuilder.setPositiveButton("Delete", DialogInterface.OnClickListener { _, _ ->
                contactos.removeAt(selectedContactPosition)
                val contactoAdaptador = ContactoAdapter(this, contactos)
                listViewContacts.adapter = contactoAdaptador
                Toast.makeText(this,"Contacto eliminado",Toast.LENGTH_LONG).show()
                val respuesta = ContactosSQLiteOpenHelper(this).deleteContacto(contactos[selectedContactPosition])
                if(respuesta > -1){
                    //val contactoAdaptador = ContactoAdapter(this, contactos)
                    //listViewContacts.adapter = contactoAdaptador
                    Toast.makeText(this,"Contacto eliminado",Toast.LENGTH_LONG).show()

                    limpiarCamposEditables()
                }
                else{
                    Toast.makeText(this,"Error al eliminar Contacto", Toast.LENGTH_LONG).show()
                }
                limpiarCamposEditables()
                limpiarCamposEditables()
            })
            dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                //pass
            })
            dialogBuilder.create().show()
        }

    }
    private fun limpiarCamposEditables() {
        editTextUserId.setText("")
        editTextTextFirstName.setText("")
        editTextTextLastName.setText("")
        editTextPhoneNumber.setText("")
        editTextTextEmailAddress.setText("")
    }

    fun onClickEnviar(view: View){
        makeCall()
    }
    fun makeCall() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:" + editTextPhoneNumber)
            startActivity(intent)
        } else {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:" + editTextPhoneNumber)
            val result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE)
            if (result == PackageManager.PERMISSION_GRANTED) {
                startActivity(intent)
            } else {
                requestForCallPermission()
            }
        }
    }
    private fun requestForCallPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CALL_PHONE)) {
        } else {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CALL_PHONE), PERMISSION_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions:
    Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
// If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED)) {
                    Toast.makeText(this, "Permission for call was granted!",
                        Toast.LENGTH_SHORT).show();
                    makeCall()
                } else {
                    Toast.makeText(this, "Permission for call was denied!",
                        Toast.LENGTH_SHORT).show();
                }
                return
            }
// Add other 'when' lines to check for other
// permissions this app might request.
            else -> {
// Ignore all other requests.
            }
        }
    }

    fun ConsultarContactos() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.androidhive.info/contacts/"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                val jsonObj = JSONObject(response.toString())
                val contacts = jsonObj.getJSONArray("contacts")
                for (i in 0 until contacts.length()) {
                    val c = contacts.getJSONObject(i)
                    val id = c.getString("id").substring(1).toInt()
                    val name = c.getString("name")
                    val nombre = name.substringBefore(" ")
                    val apellido = name.substringAfter(" ")
                    val email = c.getString("email")
                    //val address = c.getString("address")
                    //val gender = c.getString("gender")
                    val phone = c.getJSONObject("phone")
                    val mobile = phone.getString("mobile")
                    val home = phone.getString("home")
                    //val office = phone.getString("office")
                    val respuesta = ContactosSQLiteOpenHelper(this).addContacto(
                        ContactoModelClass(
                            id,
                            nombre,
                            apellido,
                            mobile,
                            email
                        )
                    )

                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, "Error to read Webservice: ${error.message}", Toast.LENGTH_SHORT).show()
                Log.d("MYTAG",error.message)
            }
        )
        queue.add(jsonObjectRequest)
    }

}