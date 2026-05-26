package validacionseguradenumeros;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ValidadorNumerosTest {

    private static final double MAX_TEST_1 = 2.0;
    private static final double MAX_TEST_2 = 2.0;
    private static final double MAX_TEST_3 = 2.0;
    private static final double MAX_TEST_4 = 2.0;
    private static final double MAX_TEST_5 = 2.0;

    private static double puntosTest1 = 0.0;
    private static double puntosTest2 = 0.0;
    private static double puntosTest3 = 0.0;
    private static double puntosTest4 = 0.0;
    private static double puntosTest5 = 0.0;

    private static Class<?> cargarClaseAlumno() throws ClassNotFoundException {
        Class<?> mainClass = Main.class;
        String paquete = mainClass.getPackageName();
        return Class.forName(paquete + ".ValidadorNumeros");
    }

    private static Method obtenerMetodo(String nombre, Class<?>... tiposParametros) throws Exception {
        Class<?> clase = cargarClaseAlumno();
        Method metodo = clase.getDeclaredMethod(nombre, tiposParametros);
        metodo.setAccessible(true);
        return metodo;
    }

    private static Object invocarStatic(String nombreMetodo, Class<?>[] tiposParametros, Object[] args) throws Throwable {
        try {
            Method metodo = obtenerMetodo(nombreMetodo, tiposParametros);
            return metodo.invoke(null, args);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    private static boolean existeMetodo(String nombreMetodo, Class<?>... tiposParametros) {
        try {
            obtenerMetodo(nombreMetodo, tiposParametros);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static double total() {
        return puntosTest1 + puntosTest2 + puntosTest3 + puntosTest4 + puntosTest5;
    }

    @Test
    @Order(1)
    void testParsearNumeroCorrecto() {
        try {
            assertTrue(existeMetodo("parsearNumero", String.class),
                    "Debe existir el método parsearNumero(String).");

            Object resultado = invocarStatic(
                    "parsearNumero",
                    new Class<?>[]{String.class},
                    new Object[]{"10"}
            );

            assertNotNull(resultado, "parsearNumero(\"10\") no debe devolver null.");
            assertTrue(resultado instanceof Integer,
                    "parsearNumero(String) debe devolver int / Integer.");
            assertEquals(10, ((Integer) resultado).intValue(),
                    "parsearNumero(\"10\") debe devolver 10.");

            puntosTest1 = MAX_TEST_1;

        } catch (AssertionError e) {
            throw e;
        } catch (Throwable e) {
            fail("Error al comprobar parsearNumero(\"10\"): "
                    + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    @Order(2)
    void testParsearNumeroIncorrecto() {
        try {
            assertTrue(existeMetodo("parsearNumero", String.class),
                    "Debe existir el método parsearNumero(String).");

            Throwable ex = assertThrows(Throwable.class, () -> {
                invocarStatic(
                        "parsearNumero",
                        new Class<?>[]{String.class},
                        new Object[]{"hola"}
                );
            }, "parsearNumero(\"hola\") debe lanzar una excepción.");

            assertTrue(ex instanceof NumberFormatException,
                    "parsearNumero(\"hola\") debe lanzar NumberFormatException.");

            puntosTest2 = MAX_TEST_2;

        } catch (AssertionError e) {
            throw e;
        } catch (Throwable e) {
            fail("Error al comprobar parsearNumero(\"hola\"): "
                    + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    @Order(3)
    void testValidarPositivoCorrecto() {
        try {
            assertTrue(existeMetodo("validarPositivo", int.class),
                    "Debe existir el método validarPositivo(int).");

            assertDoesNotThrow(() -> {
                try {
                    invocarStatic(
                            "validarPositivo",
                            new Class<?>[]{int.class},
                            new Object[]{5}
                    );
                } catch (Throwable t) {
                    if (t instanceof RuntimeException re) {
                        throw re;
                    }
                    throw new RuntimeException(t);
                }
            }, "validarPositivo(5) no debería lanzar excepción.");

            puntosTest3 = MAX_TEST_3;

        } catch (AssertionError e) {
            throw e;
        } catch (Throwable e) {
            fail("Error al comprobar validarPositivo(5): "
                    + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    @Order(4)
    void testValidarNegativo() {
        try {
            assertTrue(existeMetodo("validarPositivo", int.class),
                    "Debe existir el método validarPositivo(int).");

            Throwable ex = assertThrows(Throwable.class, () -> {
                invocarStatic(
                        "validarPositivo",
                        new Class<?>[]{int.class},
                        new Object[]{-3}
                );
            }, "validarPositivo(-3) debe lanzar una excepción.");

            assertTrue(ex instanceof IllegalArgumentException,
                    "validarPositivo(-3) debe lanzar IllegalArgumentException.");

            puntosTest4 = MAX_TEST_4;

        } catch (AssertionError e) {
            throw e;
        } catch (Throwable e) {
            fail("Error al comprobar validarPositivo(-3): "
                    + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    @Order(5)
    void testLeerNumeroSeguro() {
        try {
            assertTrue(existeMetodo("leerNumeroSeguro", String.class),
                    "Debe existir el método leerNumeroSeguro(String).");

            Object resultado = invocarStatic(
                    "leerNumeroSeguro",
                    new Class<?>[]{String.class},
                    new Object[]{"8"}
            );

            assertNotNull(resultado, "leerNumeroSeguro(\"8\") no debe devolver null.");
            assertTrue(resultado instanceof Integer,
                    "leerNumeroSeguro(String) debe devolver int / Integer.");
            assertEquals(8, ((Integer) resultado).intValue(),
                    "leerNumeroSeguro(\"8\") debe devolver 8.");

            Throwable ex1 = assertThrows(Throwable.class, () -> {
                invocarStatic(
                        "leerNumeroSeguro",
                        new Class<?>[]{String.class},
                        new Object[]{"abc"}
                );
            }, "leerNumeroSeguro(\"abc\") debe lanzar excepción.");

            assertTrue(ex1 instanceof NumberFormatException,
                    "leerNumeroSeguro(\"abc\") debe lanzar NumberFormatException.");

            Throwable ex2 = assertThrows(Throwable.class, () -> {
                invocarStatic(
                        "leerNumeroSeguro",
                        new Class<?>[]{String.class},
                        new Object[]{"-5"}
                );
            }, "leerNumeroSeguro(\"-5\") debe lanzar excepción.");

            assertTrue(ex2 instanceof IllegalArgumentException,
                    "leerNumeroSeguro(\"-5\") debe lanzar IllegalArgumentException.");

            puntosTest5 = MAX_TEST_5;

        } catch (AssertionError e) {
            throw e;
        } catch (Throwable e) {
            fail("Error al comprobar leerNumeroSeguro(String): "
                    + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @AfterAll
    static void mostrarInformeFinal() {
        System.out.println();
        System.out.println("==================================================");
        System.out.println("   INFORME DE CORRECCIÓN - VALIDADOR NÚMEROS");
        System.out.println("==================================================");
        System.out.printf("1. parsearNumero(\"10\") ............. %.2f / %.2f%n", puntosTest1, MAX_TEST_1);
        System.out.printf("2. parsearNumero(\"hola\") ........... %.2f / %.2f%n", puntosTest2, MAX_TEST_2);
        System.out.printf("3. validarPositivo(5) ............... %.2f / %.2f%n", puntosTest3, MAX_TEST_3);
        System.out.printf("4. validarPositivo(-3) .............. %.2f / %.2f%n", puntosTest4, MAX_TEST_4);
        System.out.printf("5. leerNumeroSeguro(...) ............ %.2f / %.2f%n", puntosTest5, MAX_TEST_5);
        System.out.println("--------------------------------------------------");
        System.out.printf("NOTA FINAL .......................... %.2f / 10.00%n", total());
        System.out.println("==================================================");
        System.out.println();
    }
}
