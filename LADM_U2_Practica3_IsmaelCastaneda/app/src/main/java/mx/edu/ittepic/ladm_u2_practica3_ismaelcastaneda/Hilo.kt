package mx.edu.ittepic.ladm_u2_practica3_ismaelcastaneda

import android.app.AlertDialog
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*
import kotlin.random.Random
import android.widget.Toast.makeText as makeText1

class Hilo (p: Main2Activity) : Thread() {

    private var puntero = p
    private var iniciaPartida = true
    private var pausa = true
    private var d1 = 0
    private var d2 = 0
    private var turnoJugador = 1
    private var turno = ""
    private var turnoSig = ""


    override fun run() {
        super.run()
        while(iniciaPartida){
            sleep(10)
            puntero.runOnUiThread {
                if(!pausa){
                    d1 = Random.nextInt(1, 6)
                    d2 = Random.nextInt(1, 6)
                    when(turnoJugador){
                        1 -> {
                            puntero.lienzo!!.j1.restarTurno()
                            puntero.lienzo!!.j1.darPuntos((d1 + d2))
                            turnoJugador++
                            turno = puntero.lienzo!!.j1.obtenerNombre()
                            turnoSig = puntero.lienzo!!.j2.obtenerNombre()
                        }
                        2 -> {
                            puntero.lienzo!!.j2.restarTurno()
                            puntero.lienzo!!.j2.darPuntos((d1 + d2))
                            turnoJugador++
                            turno = puntero.lienzo!!.j2.obtenerNombre()
                            turnoSig = puntero.lienzo!!.j3.obtenerNombre()
                        }
                        3 -> {
                            puntero.lienzo!!.j3.restarTurno()
                            puntero.lienzo!!.j3.darPuntos((d1 + d2))
                            turnoJugador++
                            turno = puntero.lienzo!!.j3.obtenerNombre()
                            turnoSig = puntero.lienzo!!.j4.obtenerNombre()
                        }
                        4 -> {
                            puntero.lienzo!!.j4.restarTurno()
                            puntero.lienzo!!.j4.darPuntos((d1 + d2))
                            turnoJugador = 1
                            turno = puntero.lienzo!!.j4.obtenerNombre()
                            turnoSig = puntero.lienzo!!.j1.obtenerNombre()
                        }
                    }
                    puntero.lienzo!!.turno = "Puntuación del turno de ${turno}:"
                    puntero.lienzo!!.d1 = "Dado 1: ${d1}"
                    puntero.lienzo!!.d2 = "Dado 2: ${d2}"
                    puntero.lienzo!!.turnoSig = "Siguiente en tirar: ${turnoSig}"

                    if(puntero.lienzo!!.j4.obtenerTurnos()==0){
                        var puntos = arrayOf(puntero.lienzo!!.j1.obtenerPuntos(),
                            puntero.lienzo!!.j2.obtenerPuntos(),
                            puntero.lienzo!!.j3.obtenerPuntos(),
                            puntero.lienzo!!.j4.obtenerPuntos())
                        terminar(puntos)
                        puntero.lienzo!!.turnoSig = "Juego terminado"
                    }
                    pausa = true
                    puntero.lienzo!!.invalidate()
                }
            }
        }
    }

    fun calcular(){
        pausa = false
    }

    fun terminar(puntos: Array<Int>){
        var titulo = "Empate"
        var p = puntos.sortedArrayDescending()
        var ganador = "Empate"
        var msj = "Hay un empate con ${p[0]}, puntos."
        if( puntero.lienzo!!.j1.obtenerPuntos()>puntero.lienzo!!.j2.obtenerPuntos() &&
            puntero.lienzo!!.j1.obtenerPuntos()>puntero.lienzo!!.j3.obtenerPuntos() &&
            puntero.lienzo!!.j1.obtenerPuntos()>puntero.lienzo!!.j4.obtenerPuntos() )
        {
            ganador = puntero.lienzo!!.j1.obtenerNombre()
            msj = "El jugador ${ganador} ganó con ${puntero.lienzo!!.j1.obtenerPuntos()} puntos."
        }
        if( puntero.lienzo!!.j2.obtenerPuntos()>puntero.lienzo!!.j1.obtenerPuntos() &&
            puntero.lienzo!!.j2.obtenerPuntos()>puntero.lienzo!!.j3.obtenerPuntos() &&
            puntero.lienzo!!.j2.obtenerPuntos()>puntero.lienzo!!.j4.obtenerPuntos() )
        {
            ganador = puntero.lienzo!!.j2.obtenerNombre()
            msj = "El jugador ${ganador} ganó con ${puntero.lienzo!!.j2.obtenerPuntos()} puntos."
        }
        if( puntero.lienzo!!.j3.obtenerPuntos()>puntero.lienzo!!.j1.obtenerPuntos() &&
            puntero.lienzo!!.j3.obtenerPuntos()>puntero.lienzo!!.j2.obtenerPuntos() &&
            puntero.lienzo!!.j3.obtenerPuntos()>puntero.lienzo!!.j4.obtenerPuntos() )
        {
            ganador = puntero.lienzo!!.j3.obtenerNombre()
            msj = "El jugador ${ganador} ganó con ${puntero.lienzo!!.j3.obtenerPuntos()} puntos."
        }
        if( puntero.lienzo!!.j4.obtenerPuntos() > puntero.lienzo!!.j1.obtenerPuntos() &&
            puntero.lienzo!!.j4.obtenerPuntos() > puntero.lienzo!!.j2.obtenerPuntos() &&
            puntero.lienzo!!.j4.obtenerPuntos() > puntero.lienzo!!.j3.obtenerPuntos() )
        {
            ganador = puntero.lienzo!!.j4.obtenerNombre()
            msj = "El jugador ${ganador} ganó con ${puntero.lienzo!!.j4.obtenerPuntos()} puntos."
        }

        iniciaPartida = false
        puntero.lienzo!!.ganador = ganador
        mensaje(titulo, msj, ganador)
    }


    fun mensaje(t: String, m: String, ganador: String){
        AlertDialog.Builder(puntero)
            .setTitle(t)
            .setMessage(m)
            .setPositiveButton("Nueva partida"){ d, i ->
                puntero.resultado(ganador)
            }
            .show()
    }
}