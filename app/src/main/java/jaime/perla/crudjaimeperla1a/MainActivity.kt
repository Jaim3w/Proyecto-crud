package jaime.perla.crudjaimeperla1a

import Modelo.Cl
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val  txtnombre= findViewById<EditText>(R.id.txtNombre)
        val txtprecio = findViewById<EditText>(R.id.txtPrecio)
        val txtcantidad = findViewById<EditText>(R.id.txtCantidad)
        val btnAgregar = findViewById<Button>(R.id.btnAgregar)

   //2-programar el boton agregar
        btnAgregar.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO){
            //guardar datos
            //crear un objeto de la clase conexion
            val objConexion = Cl().CadenaConexion()

            //2- crear una variable que sea igual a un prepareStatement
            val addProducto = objConexion?.prepareStatement("insert into tbProductos1A values (?,?,?)")!!
            addProducto.setString(1,txtnombre.text.toString())
            addProducto.setInt(2,txtprecio.text.toString().toInt())
            addProducto.setInt(3,txtcantidad.text.toString().toInt())
            addProducto.executeUpdate()
            }
        }
    }
}