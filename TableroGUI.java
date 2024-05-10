import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableroGUI extends JFrame {
    private final Tablero tableroLogico;
    private final int tamaño;
    private final JButton[][] botones;
    private char jugadorActual = 'X'; 
    private int puntuacionJugador1 = 0;
    private int puntuacionJugador2 = 0;
    private Menu menuPrincipal;

    public TableroGUI(int tamaño, String nombreJugador1, Color colorJugador1, String nombreJugador2,
            Color colorJugador2, Menu menuPrincipal) {
        this.tamaño = tamaño;
        this.tableroLogico = new Tablero(tamaño);
        botones = new JButton[tamaño][tamaño];
        this.menuPrincipal = menuPrincipal;

        initUI();
    }

    private void initUI() {
        setTitle("Triquitraque");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(tamaño, tamaño));
        getContentPane().setBackground(new Color(240, 240, 240));

        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                JButton button = new JButton();
                botones[i][j] = button;
                button.setFont(new Font("Segoe UI", Font.PLAIN, 100)); 

                button.setFocusPainted(false); 
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        button.setBackground(new Color(230, 230, 230)); 
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        button.setBackground(new Color(255, 255, 255)); 
                    }
                });

                int finalI = i;
                int finalJ = j;
                button.addActionListener(e -> realizarJugada(finalI, finalJ));
                add(button);
            }
        }

        pack();
        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void realizarJugada(int fila, int columna) {
        if (tableroLogico.colocarFicha(fila, columna, jugadorActual)) {
            botones[fila][columna].setText(String.valueOf(jugadorActual));
            if (jugadorActual == 'X') {
                botones[fila][columna].setForeground(menuPrincipal.getSeleccionColorJugador1());
            } else {
                botones[fila][columna].setForeground(menuPrincipal.getSeleccionColorJugador2());
            }
            if (tableroLogico.verificarGanador(jugadorActual)) {
                if (jugadorActual == 'X') {
                    puntuacionJugador1++;
                } else {
                    puntuacionJugador2++;
                }
                JOptionPane.showMessageDialog(this, jugadorActual + " gana! Puntuación: " + menuPrincipal.nombreJ1() + " "
                        + puntuacionJugador1 + " - " + menuPrincipal.nombreJ2() + " " + puntuacionJugador2);
                        ;
                reiniciarJuego();
            } else if (tableroLogico.verificarEmpate()) {
                JOptionPane.showMessageDialog(this, "Empate! Puntuación: " + menuPrincipal.nombreJ1() + " " + puntuacionJugador1
                        + " - " + menuPrincipal.nombreJ2() + " " + puntuacionJugador2);
                reiniciarJuego();
            } else {
                jugadorActual = (jugadorActual == 'X') ? 'O' : 'X';
            }

        } else {
            JOptionPane.showMessageDialog(this, "Casilla ocupada o jugada inválida.");
        }
    }

    private void reiniciarJuego() {
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea jugar otra vez?", "Juego terminado",
                JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            
            tableroLogico.reiniciar(); 
            for (int i = 0; i < tamaño; i++) {
                for (int j = 0; j < tamaño; j++) {
                    botones[i][j].setText("");
                    botones[i][j].setEnabled(true);
                }
            }
            jugadorActual = 'X'; 
        } else {
            System.exit(0); 
        }
    }

}
