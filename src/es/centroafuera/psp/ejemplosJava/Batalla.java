package es.centroafuera.psp.ejemplosJava;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Batalla {

    private Jugador ganadorTemporal;
    private int puntuacionFinal = 0;
    private int jugadoresFin = 0;
    private String ganador = "";

    private static final int MAXIMO = 10;
    private final Semaphore semaforo = new Semaphore(MAXIMO, true);

    public void addJugador (Jugador jugador) {
        try {
            semaforo.acquire();
            System.out.println(jugador.getName() + " se ha unido a la batalla");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void lucha (Jugador jugador) throws InterruptedException {
        System.out.println("Entra " + jugador.getName());

        int sleepingTime = (int) (Math.random() * 5000) + 1000;
        Thread.sleep(sleepingTime);
        jugadoresFin++;

        if (ganadorTemporal == null) {
            System.out.println(jugador.getName() + " ha sido el primero, ¡tiene bonus!");
            ganadorTemporal = jugador;
        } /*else {
            System.out.println(jugador.getName() + " vs " + ganadorTemporal.getName());
            Thread.sleep(1000);
            ganadorTemporal = ganador(jugador, ganadorTemporal);
            System.out.println("Ganador: " + ganadorTemporal.getName());
            semaforo.release();
        }*/
    }

    public void puntuacion (Jugador jugador) {
       Random r = new Random();
       jugador.puntuacion = r.nextInt();

       if (ganadorTemporal.getName().equals(jugador.getName())) {
           jugador.puntuacion = jugador.puntuacion * 2;
       }

       if (jugador.puntuacion > puntuacionFinal) {
           puntuacionFinal = jugador.puntuacion;
           ganador = jugador.getName();
       }
    }

    public void salir () {
        if (jugadoresFin == 10) {
            System.out.println("Mueren 5 jugadores.");
            for (int i=0; i<5;i++)
                semaforo.release();
        } else if (jugadoresFin == 15) {
            System.out.println("Fin de la batalla");
            System.out.println("Ganador: " + ganador + " - Con una puntuación de " + puntuacionFinal);
        }
    }


}
