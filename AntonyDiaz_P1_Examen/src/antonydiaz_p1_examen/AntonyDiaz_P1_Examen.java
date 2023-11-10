/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package antonydiaz_p1_examen;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author antob
 */
public class AntonyDiaz_P1_Examen {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menú:");
            System.out.println("1. Dígito más solitario y más popular");
            System.out.println("2. Almacenamiento en cintas magnéticas");
            System.out.println("3. Descifra la combinación");
            System.out.println("4. Salir");

            System.out.print("Ingrese la opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese número: ");
                    int numero = scanner.nextInt();
                    System.out.println(calcularSoledadPopularidad(numero));
                    break;

                case 2:
                    System.out.print("Ingrese una cadena: ");
                    String cadena = scanner.next();
                    almacenarEnCintaMagnetica(cadena);
                    break;

                case 3:
                    combinacion();
                    break;

                case 4:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
            }
        }
    }

    // Ejercicio 1
    public static String calcularSoledadPopularidad(int numero) {
        String numeroStr = Integer.toString(numero);
        int longitud = numeroStr.length();
        int minSoledad = Integer.MAX_VALUE;
        int maxPopularidad = Integer.MIN_VALUE;
        char solitario = ' ';
        char popular = ' ';

        for (int i = 0; i < longitud; i++) {
            int digito = Character.getNumericValue(numeroStr.charAt(i));
            int soledad = 0;
            int popularidad = 0;

            for (int j = 0; j < longitud; j++) {
                int distancia = Math.abs(i - j);
                int digitoActual = Character.getNumericValue(numeroStr.charAt(j));

                if (distancia <= digito) {
                    soledad += digitoActual;
                }

                if (distancia == digito) {
                    popularidad += digitoActual;
                }
            }

            if (soledad < minSoledad) {
                minSoledad = soledad;
                solitario = (char) (digito + '0');
            }

            if (popularidad > maxPopularidad) {
                maxPopularidad = popularidad;
                popular = (char) (digito + '0');
            }
        }

        return "El dígito más solitario es el " + solitario + " porque su nivel de soledad es " + minSoledad +
                " y el más popular es " + popular + " porque su nivel de popularidad es " + maxPopularidad;
    }

    // Ejercicio 2
    public static void almacenarEnCintaMagnetica(String cadena) {
        for (char caracter : cadena.toCharArray()) {
            int valorAscii = (int) caracter;
            String binarioConParidad = convertirABinarioConParidad(valorAscii);
            System.out.println("Carácter: " + caracter + ", Valor en binario con paridad: " + binarioConParidad);
        }
    }

    public static String convertirABinarioConParidad(int asciiValue) {
        String binario = convertirADecimalBinario(asciiValue);
        int unos = contarUnos(binario);
        char bitParidad = (unos % 2 == 0) ? '0' : '1';
        return binario + bitParidad;
    }

    public static String convertirADecimalBinario(int decimal) {
        StringBuilder binario = new StringBuilder();
        while (decimal > 0) {
            binario.insert(0, decimal % 2);
            decimal /= 2;
        }
        while (binario.length() < 8) {
            binario.insert(0, '0');
        }
        return binario.toString();
    }

    public static int contarUnos(String binario) {
        int count = 0;
        for (int i = 0; i < binario.length(); i++) {
            if (binario.charAt(i) == '1') {
                count++;
            }
        }
        return count;
    }

    // Ejercicio 3
    public static void combinacion() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Seleccione la dificultad:");
        System.out.println("1. Fácil");
        System.out.println("2. Media");
        System.out.println("3. Difícil");
        int dificultad = scanner.nextInt();

        int tamano = 0;
        int intentosRestantes = 0;

        switch (dificultad) {
            case 1:
                tamano = 6;
                intentosRestantes = 26;
                break;
            case 2:
                tamano = 8;
                intentosRestantes = 22;
                break;
            case 3:
                tamano = 10;
                intentosRestantes = 18;
                break;
            default:
                System.out.println("Opción no válida. Saliendo del programa.");
                return;
        }

        String combinacion = generarCombinacionAleatoria(tamano, random);
        String progreso = "-".repeat(tamano);

        while (intentosRestantes > 0 && !combinacion.equals(progreso)) {
            System.out.println("Progreso: " + progreso);
            System.out.println("Intentos restantes: " + intentosRestantes);
            System.out.print("Ingrese un caracter: ");
            char caracter = scanner.next().charAt(0);
            progreso = actualizarProgreso(combinacion, progreso, caracter);
            intentosRestantes--;
        }

        if (combinacion.equals(progreso)) {
            System.out.println("¡Combinación correcta! La bomba ha sido desactivada.");
        } else {
            System.out.println("¡La bomba ha explotado! Fin del programa.");
        }
    }

    public static String generarCombinacionAleatoria(int tamano, Random random) {
        StringBuilder combinacion = new StringBuilder();
        for (int i = 0; i < tamano; i++) {
            char caracter = (char) (random.nextInt(26) + 'A'); // Genera una letra aleatoria mayúscula
            combinacion.append(caracter);
        }
        return combinacion.toString();
    }

    public static String actualizarProgreso(String combinacion, String progreso, char caracter) {
        StringBuilder nuevoProgreso = new StringBuilder(progreso);
        for (int i = 0; i < combinacion.length(); i++) {
            if (combinacion.charAt(i) == caracter) {
                nuevoProgreso.setCharAt(i, '+');
            }
        }
        return nuevoProgreso.toString();
    }
    }
    

