package mx.edu.ittepic.ladm_u2_practica3_ismaelcastaneda

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            var enviar = Intent(this, Main2Activity::class.java)

            enviar.putExtra("j1", editText.text.toString())
            enviar.putExtra("j2", editText2.text.toString())
            enviar.putExtra("j3", editText3.text.toString())
            enviar.putExtra("j4", editText4.text.toString())

            startActivityForResult(enviar, 1)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val res = data?.extras?.getString("resultado")?:"sin resultado"
            textView1.setText("Ganador: $res")
        }
    }
}
