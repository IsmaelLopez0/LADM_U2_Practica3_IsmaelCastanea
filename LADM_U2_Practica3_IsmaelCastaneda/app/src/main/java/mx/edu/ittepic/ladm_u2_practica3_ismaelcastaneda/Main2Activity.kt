package mx.edu.ittepic.ladm_u2_practica3_ismaelcastaneda

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    var hilo = Hilo(this)
    var lienzo : Lienzo ?= null

    /*var j1 = Jugador()
    var j2 = Jugador()
    var j3 = Jugador()
    var j4 = Jugador()

    var nomJ1 = ""
    var nomJ2 = ""
    var nomJ3 = ""
    var nomJ4 = ""*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var nombres = Array<String>(4) { "" }
        nombres[0] = getIntent().getStringExtra("j1")
        nombres[1] = getIntent().getStringExtra("j2")
        nombres[2] = getIntent().getStringExtra("j3")
        nombres[3] = getIntent().getStringExtra("j4")
        lienzo = Lienzo(this, nombres)
        //setContentView(R.layout.activity_main2)
        setContentView(lienzo)

        hilo.start()

        /*button2.setOnClickListener {
            hilo.iniciar()
        }*/
    }

    fun resultado(res: String){
        val intent = Intent()
        intent.putExtra("resultado", res)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

}
