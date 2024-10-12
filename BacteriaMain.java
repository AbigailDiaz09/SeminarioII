import java.util.Scanner;

public class BacteriaMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar los datos de la población
        System.out.print("Ingrese el número de bacterias: ");
        int numBacterias = scanner.nextInt();
        System.out.print("Ingrese el número de secuencias por bacteria: ");
        int numSecuencias = scanner.nextInt();

        // Crear la población inicial
        Poblacion poblacion = new Poblacion(numBacterias, numSecuencias);

        System.out.print("Ingrese el número de generaciones: ");
        int numGeneraciones = scanner.nextInt();

        // Ciclo de generaciones
        for (int generacion = 0; generacion < numGeneraciones; generacion++) {
            System.out.println("\nGeneración " + (generacion + 1) + ":");

            // Aplicar Tumbo y Nado a cada bacteria
            for (Bacteria bacteria : poblacion.getBacterias()) {
                bacteria.tumbo();
                bacteria.nado();
            }

            // Aplicar quimiotaxis
            poblacion.aplicarQuimiotaxis();

            // Eliminar bacterias débiles y clonar bacterias fuertes
            poblacion.eliminarYClonar();

            // Insertar nuevas bacterias aleatorias
            poblacion.agregarNuevasBacterias(2);  // Puedes cambiar el número de nuevas bacterias

            // Balancear secuencias
            poblacion.balancearSecuencias();
        }

        scanner.close();
    }
}
