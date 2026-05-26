package validacionseguradenumeros;

public class Main {

    public static void main(String[] args) {
        String[] entradas = {"10", "-5", "hola"};

        for (String texto : entradas) {
            try {
                int numero = ValidadorNumeros.leerNumeroSeguro(texto);
                System.out.println("Entrada válida: " + texto + " → número = " + numero);
            } catch (NumberFormatException e) {
                System.out.println("Error de formato para '" + texto + "': " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Error de validación para '" + texto + "': " + e.getMessage());
            } finally {
                System.out.println("Fin de procesamiento de entrada: " + texto + "\n");
            }
        }
    }
}
