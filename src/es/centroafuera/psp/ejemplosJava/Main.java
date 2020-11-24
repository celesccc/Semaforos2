package es.centroafuera.psp.ejemplosJava;

public class Main {

    public static void main(String[] args) {

        Batalla batalla = new Batalla();

        for (int i = 0; i < 15; i++){
            Jugador jugador = new Jugador(batalla);
            jugador.setName("Jugador "+ i);
            jugador.start();
        }
    }
}
