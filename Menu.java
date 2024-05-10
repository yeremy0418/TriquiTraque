import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {
    private JTextField nombreJugador1;
    private JTextField nombreJugador2;
    private JButton colorJugador1;
    private JButton colorJugador2;
    private JComboBox<String> tamañoTablero;
    private JButton iniciarJuego;
    private Color seleccionColorJugador1 = Color.RED;
    private Color seleccionColorJugador2 = Color.BLUE;

    public Menu() {
        initUI();
    }

    public Color getSeleccionColorJugador1() {
        return seleccionColorJugador1;
    }

    public Color getSeleccionColorJugador2() {
        return seleccionColorJugador2;
    }

    private void initUI() {
        setTitle("Triquitraque");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 20, 10, 20);

        Font font = new Font("Segoe UI", Font.PLAIN, 14);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel labelJugador1 = new JLabel("Nombre Jugador 1:");
        labelJugador1.setFont(font);
        add(labelJugador1, gbc);

        gbc.gridx = 1;
        nombreJugador1 = new JTextField(10);
        nombreJugador1.setFont(font);
        add(nombreJugador1, gbc);

        gbc.gridx = 2;
        colorJugador1 = new JButton("Color Jugador 1");
        colorJugador1.setFont(font);
        colorJugador1.addActionListener(e -> seleccionColorJugador1 = JColorChooser.showDialog(null, "Seleccione Color",
                seleccionColorJugador1));
        add(colorJugador1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel labelJugador2 = new JLabel("Nombre Jugador 2:");
        labelJugador2.setFont(font);
        add(labelJugador2, gbc);

        gbc.gridx = 1;
        nombreJugador2 = new JTextField(10);
        nombreJugador2.setFont(font);
        add(nombreJugador2, gbc);

        gbc.gridx = 2;
        colorJugador2 = new JButton("Color Jugador 2");
        colorJugador2.setFont(font);
        colorJugador2.addActionListener(e -> seleccionColorJugador2 = JColorChooser.showDialog(null, "Seleccione Color",
                seleccionColorJugador2));
        add(colorJugador2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        JLabel labelTamañoTablero = new JLabel("Tamaño del Tablero:");
        labelTamañoTablero.setFont(font);
        add(labelTamañoTablero, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 1;
        tamañoTablero = new JComboBox<>(new String[] { "3x3", "4x4", "5x5" });
        tamañoTablero.setFont(font);
        add(tamañoTablero, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        iniciarJuego = new JButton("Iniciar Juego");
        iniciarJuego.setFont(font);
        iniciarJuego.addActionListener(this::iniciarJuego);
        add(iniciarJuego, gbc);

        getContentPane().setBackground(Color.WHITE);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void iniciarJuego(ActionEvent e) {
        int tamaño = Integer.parseInt(((String) tamañoTablero.getSelectedItem()).substring(0, 1));
        String jugador1 = nombreJugador1.getText();
        String jugador2 = nombreJugador2.getText();
        this.dispose();
        SwingUtilities.invokeLater(
                () -> new TableroGUI(tamaño, jugador1, seleccionColorJugador1, jugador2, seleccionColorJugador2, this));
    }
}