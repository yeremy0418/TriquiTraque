public class Jugador {
    private String nombre;
    private char ficha;
    private String color;

    public Jugador(String nombre, char ficha, String color) {
        this.nombre = nombre;
        this.ficha = ficha;
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public char getFicha() {
        return ficha;
    }

    public String getColor() {
        return color;
    }
}
