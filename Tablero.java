public class Tablero {
    private char[][] tablero;
    private final int tamaño; 

    public Tablero(int tamaño) {
        if (tamaño >= 3 && tamaño <= 5) {
            this.tamaño = tamaño;
        } else {
            throw new IllegalArgumentException("Tamaño inválido. Debe ser 3, 4 o 5.");
        }
        tablero = new char[this.tamaño][this.tamaño];
        for (int i = 0; i < this.tamaño; i++) {
            for (int j = 0; j < this.tamaño; j++) {
                tablero[i][j] = ' '; 
            }
        }
    }

    public boolean colocarFicha(int fila, int columna, char ficha) {
        if (fila >= 0 && fila < tamaño && columna >= 0 && columna < tamaño && tablero[fila][columna] == ' ') {
            tablero[fila][columna] = ficha;
            return true;
        }
        return false;
    }

    public boolean verificarGanador(char ficha) {
        
        for (int i = 0; i < tamaño; i++) {
            boolean filaCompleta = true;
            boolean columnaCompleta = true;
            for (int j = 0; j < tamaño; j++) {
                if (tablero[i][j] != ficha) {
                    filaCompleta = false;
                }
                if (tablero[j][i] != ficha) {
                    columnaCompleta = false;
                }
            }
            if (filaCompleta || columnaCompleta) {
                return true;
            }
        }

        
        boolean diagonalPrincipalCompleta = true;
        boolean diagonalSecundariaCompleta = true;
        for (int i = 0; i < tamaño; i++) {
            if (tablero[i][i] != ficha) {
                diagonalPrincipalCompleta = false;
            }
            if (tablero[i][tamaño - 1 - i] != ficha) {
                diagonalSecundariaCompleta = false;
            }
        }

        return diagonalPrincipalCompleta || diagonalSecundariaCompleta;
    }

    public boolean verificarEmpate() {
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                if (tablero[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public void mostrarTablero() {
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                System.out.print(tablero[i][j]);
                if (j < tamaño - 1)
                    System.out.print("|");
            }
            System.out.println();
            if (i < tamaño - 1)
                System.out.println("-----");
        }
    }

    public void reiniciar() {
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                tablero[i][j] = ' '; 
            }
        }
    }
}