import java.util.Scanner;

public class Juego {
    private Jugador[] jugadores = new Jugador[2];
    private Tablero tablero;
    private int turno = 0;

    public Juego() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del Jugador 1:");
        String nombreJugador1 = scanner.nextLine();
        System.out.println("Ingrese el color para el Jugador 1:");
        String colorJugador1 = scanner.nextLine();
        jugadores[0] = new Jugador(nombreJugador1, 'X', colorJugador1);

        System.out.println("Ingrese el nombre del Jugador 2:");
        String nombreJugador2 = scanner.nextLine();
        System.out.println("Ingrese el color para el Jugador 2:");
        String colorJugador2 = scanner.nextLine();
        jugadores[1] = new Jugador(nombreJugador2, 'O', colorJugador2);

        System.out.println("Ingrese el tamaño del tablero (3, 4 o 5):");
        int tamañoTablero;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingrese un número válido (3, 4 o 5):");
                scanner.next(); 
            }
            tamañoTablero = scanner.nextInt();
            if (tamañoTablero < 3 || tamañoTablero > 5) {
                System.out.println("Tamaño inválido. Por favor, ingrese 3, 4 o 5:");
            }
        } while (tamañoTablero < 3 || tamañoTablero > 5);
        tablero = new Tablero(tamañoTablero);

        scanner.close();
    }

    public void iniciarPartida() {
        Scanner scanner = new Scanner(System.in);
        boolean juegoTerminado = false;

        while (!juegoTerminado) {
            System.out.println("Turno de " + jugadores[turno].getNombre() + " (" + jugadores[turno].getFicha() + ")");
            tablero.mostrarTablero();

            int fila, columna;
            do {
                System.out.println("Ingrese fila y columna: ");
                fila = scanner.nextInt();
                columna = scanner.nextInt();
            } while (!tablero.colocarFicha(fila, columna, jugadores[turno].getFicha()));

            if (tablero.verificarGanador(jugadores[turno].getFicha())) {
                tablero.mostrarTablero();
                System.out.println("Ganador: " + jugadores[turno].getNombre());
                juegoTerminado = true;
            } else if (tablero.verificarEmpate()) {
                tablero.mostrarTablero();
                System.out.println("Empate!");
                juegoTerminado = true;
            }

            turno = (turno + 1) % 2;
        }
        scanner.close();
    }
}