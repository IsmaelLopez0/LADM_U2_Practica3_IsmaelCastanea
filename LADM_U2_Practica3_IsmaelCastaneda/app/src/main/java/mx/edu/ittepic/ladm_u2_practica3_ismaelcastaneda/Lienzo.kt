package mx.edu.ittepic.ladm_u2_practica3_ismaelcastaneda

import android.app.AlertDialog
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View

class Lienzo (p:Main2Activity, nombres: Array<String>): View(p){

    var puntero = p

    var j1 = Jugador(nombres[0], 1)
    var j2 = Jugador(nombres[1], 2)
    var j3 = Jugador(nombres[2], 3)
    var j4 = Jugador(nombres[3], 4)

    var d1 = "Dado 1: -"
    var d2 = "Dado 2: -"
    var turno = ""
    var turnoSig = "Siguiente en tirar: ${j1.obtenerNombre()}"
    var ganador = ""

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        var paint = Paint()

        paint.color = Color.BLACK
        paint.textSize = 50f
        j1.dibujar(canvas, paint)
        j2.dibujar(canvas, paint)
        j3.dibujar(canvas, paint)
        j4.dibujar(canvas, paint)

        paint.color = Color.LTGRAY
        canvas.drawRect(350f, 300f, 550f, 470f, paint)
        paint.color = Color.BLACK
        canvas.drawText("Tirar", 400f, 400f, paint)

        canvas.drawText(turno, 150f, 600f, paint)
        canvas.drawText(d1, 100f, 670f, paint)
        canvas.drawText(d2, 350f, 670f, paint)
        canvas.drawText(turnoSig, 150f, 740f, paint)

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if(event.action == MotionEvent.ACTION_DOWN){
            if(event.x >=350 && event.x <= 550){
                if(event.y >=300 && event.y <= 470){
                    puntero.hilo.calcular()
                }
            }
        }
        if(j4.obtenerTurnos()==0){
            puntero.resultado(ganador)
        }
        return super.onTouchEvent(event)
    }
}