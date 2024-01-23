import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class challenge {

    private static boolean archivoGenerado = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            mostrarMenu();

            int opcion = obtenerOpcionValida(scanner);

            switch (opcion) {
                case 1:
                    generarYGuardarNumeros();
                    archivoGenerado = true;
                    break;
                case 2:
                    if (archivoGenerado) {
                        ordenarYGuardarNumeros();
                    } else {
                        System.out.println("Primero debe generar y guardar numeros antes de ordenar.");
                    }
                    break;
                case 3:
                    if (archivoGenerado) {
                        buscarNumeroEnArchivo();
                    } else {
                        System.out.println("Primero debe generar y guardar numeros antes de buscar.");
                    }
                    break;
                case 4:
                    if (archivoGenerado) {
                        mostrarNumerosDelArchivo();
                    } else {
                        System.out.println("Primero debe generar y guardar números antes de mostrar.");
                    }
                    break;
                case 5:
                    System.out.println("¡Tschuss!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no valida. Por favor, ingrese una opción valida.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n1. Generar numeros aleatorios y guardar en un archivo de texto");
        System.out.println("2. Ordenar numeros y guardar en un nuevo archivo de texto");
        System.out.println("3. Buscar un numero en el archivo de texto previamente generado");
        System.out.println("4. Mostrar numeros del archivo de texto");
        System.out.println("5. Salir");
        System.out.print("Ingrese la opcion: ");
    }

    private static int obtenerOpcionValida(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Por favor, ingrese un número válido.");
                mostrarMenu();
            }
        }
    }

    private static void generarYGuardarNumeros() {
        try {
            FileWriter fileWriter = new FileWriter("numeros.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            for (int i = 0; i < 10; i++) {
                int numeroAleatorio = (int) (Math.random() * 100);
                printWriter.println(numeroAleatorio);
            }

            printWriter.close();
            System.out.println("Numeros generados y guardados en 'numeros.txt'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void ordenarNumeros(ArrayList<Integer> numeros) {
        int n = numeros.size();
        boolean intercambiado;

        do {
            intercambiado = false;
            for (int i = 1; i < n; i++) {
                if (numeros.get(i - 1) > numeros.get(i)) {
                    int temp = numeros.get(i - 1);
                    numeros.set(i - 1, numeros.get(i));
                    numeros.set(i, temp);
                    intercambiado = true;
                }
            }
        } while (intercambiado);
    }

    private static void ordenarYGuardarNumeros() {
        try {
            ArrayList<Integer> numeros = leerNumerosDesdeArchivo();

            ordenarNumeros(numeros);

            FileWriter fileWriter = new FileWriter("numeros_ordenados.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            for (int numero : numeros) {
                printWriter.println(numero);
            }

            printWriter.close();
            System.out.println("Numeros ordenados y guardados en 'numeros_ordenados.txt'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void buscarNumeroEnArchivo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número a buscar: ");
        int numeroBuscado = scanner.nextInt();

        ArrayList<Integer> numeros = leerNumerosDesdeArchivo();
        if (numeros.contains(numeroBuscado)) {
            System.out.println("El número " + numeroBuscado + " está en el archivo.");
        } else {
            System.out.println("El número " + numeroBuscado + " no está en el archivo.");
        }
    }

    private static void mostrarNumerosDelArchivo() {
        ArrayList<Integer> numeros = leerNumerosDesdeArchivo();
        System.out.println("Numeros en el archivo:");
        for (int numero : numeros) {
            System.out.println(numero);
        }
    }

    private static ArrayList<Integer> leerNumerosDesdeArchivo() {
        ArrayList<Integer> numeros = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader("numeros.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                numeros.add(Integer.parseInt(linea));
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numeros;
    }
}
