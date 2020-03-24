package mx.edu.ittepic.ladm_u2_practica3_ismaelcastaneda

import android.graphics.Canvas
import android.graphics.Paint

class Jugador (){

    private var puntos = 0
    private var turnos = 4
    private var nombre = ""
    private var numJugador = -1

    constructor(nombre: String, numJugador: Int): this(){
        this.nombre = nombre
        this.numJugador = numJugador
    }

    fun darPuntos(puntos: Int){
        this.puntos += puntos
    }

    fun restarTurno(): Boolean{
        if (turnos!=0) {
            this.turnos--
            return true
        }else{
            return false
        }
    }

    fun obtenerTurnos(): Int{
        return this.turnos
    }

    fun obtenerPuntos(): Int{
        return this.puntos
    }

    fun obtenerNombre(): String{
        return this.nombre
    }

    fun dibujar(c: Canvas, p: Paint){
        var text = "${nombre} tiene ${puntos} puntos, quedan ${turnos} turnos"
        when(numJugador){
            1 -> {
                c.drawText(text, 25f, 50f, p)
            }
            2 -> {
                c.drawText(text, 25f, 120f, p)
            }
            3 -> {
                c.drawText(text, 25f, 190f, p)
            }
            4 -> {
                c.drawText(text, 25f, 260f, p)
            }
        }
    }
}