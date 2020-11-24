package es.centroafuera.psp.ejemplosJava;

public class Jugador extends Thread {

    Batalla batalla;

    public Jugador(Batalla batalla) {
        this.batalla = batalla;
    }

    public int puntuacion;

    @Override
    public void run() {
        try {
            batalla.addJugador(this);
            batalla.lucha(this);
            batalla.puntuacion(this);
            batalla.salir();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
