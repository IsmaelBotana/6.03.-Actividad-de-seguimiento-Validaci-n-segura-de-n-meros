package validacionseguradenumeros;

public class ValidadorNumeros {

    public static int parsearNumero(String texto) {
        if (texto == null) {
            throw new NumberFormatException("El texto no puede ser null");
        }

        String valor = texto.trim();
        if (valor.isEmpty()) {
            throw new NumberFormatException("El texto no es un número válido");
        }

        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("El texto no es un número válido: " + valor);
        }
    }

    public static void validarPositivo(int numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("El número no puede ser negativo: " + numero);
        }
    }

    public static int leerNumeroSeguro(String texto) {
        int numero = parsearNumero(texto);
        validarPositivo(numero);
        return numero;
    }
}
