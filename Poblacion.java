import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Poblacion {
    private List<Bacteria> bacterias;

    // Constructor que crea la población de bacterias con secuencias proporcionadas
    public Poblacion(int numBacterias, int numSecuencias) {
        bacterias = new ArrayList<>();
        for (int i = 0; i < numBacterias; i++) {
            String[] secuencias = new String[numSecuencias];
            for (int j = 0; j < numSecuencias; j++) {
                secuencias[j] = generarSecuenciaAleatoria(10);  // Puedes cambiar la longitud de la secuencia
            }
            bacterias.add(new Bacteria(secuencias));
        }
    }

    public List<Bacteria> getBacterias() {
        return bacterias;
    }

    // Genera una secuencia aleatoria de proteínas (o ADN)
    private String generarSecuenciaAleatoria(int longitud) {
        Random random = new Random();
        String caracteres = "ACGT";  // Para ADN, puedes cambiarlo si es ARN o proteínas
        StringBuilder secuencia = new StringBuilder(longitud);
        for (int i = 0; i < longitud; i++) {
            secuencia.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }
        return secuencia.toString();
    }

    // Aplica la quimiotaxis (atracción y repulsión entre bacterias)
    public void aplicarQuimiotaxis() {
        for (int i = 0; i < bacterias.size(); i++) {
            for (int j = 0; j < bacterias.size(); j++) {
                if (i != j) {
                    int diff = Math.abs(bacterias.get(i).getPuntajeBLOSUM() - bacterias.get(j).getPuntajeBLOSUM());
                    if (diff < 10) {
                        // Bacterias similares se atraen (incrementan fitness)
                        bacterias.get(i).setFitness(bacterias.get(i).getFitness() + 1);
                    } else {
                        // Bacterias muy diferentes se repelen (disminuyen fitness)
                        bacterias.get(i).setFitness(bacterias.get(i).getFitness() - 1);
                    }
                }
            }
        }
    }

    // Elimina las bacterias más débiles y clona las más fuertes
    public void eliminarYClonar() {
        // Ordenar bacterias por fitness
        bacterias.sort((b1, b2) -> Double.compare(b2.getFitness(), b1.getFitness()));

        // Eliminar la mitad inferior de las bacterias (las más débiles)
        int numEliminar = bacterias.size() / 2;
        for (int i = 0; i < numEliminar; i++) {
            bacterias.remove(bacterias.size() - 1);
        }

        // Clonar las bacterias más fuertes
        int numClonar = numEliminar;
        for (int i = 0; i < numClonar; i++) {
            Bacteria bacteriaClonada = new Bacteria(bacterias.get(i).getSecuencias());
            bacterias.add(bacteriaClonada);
        }
    }

    // Inserta nuevas bacterias aleatorias para mantener la diversidad
    public void agregarNuevasBacterias(int numNuevas) {
        int numSecuencias = bacterias.get(0).getSecuencias().length;
        for (int i = 0; i < numNuevas; i++) {
            String[] secuencias = new String[numSecuencias];
            for (int j = 0; j < numSecuencias; j++) {
                secuencias[j] = generarSecuenciaAleatoria(10);
            }
            bacterias.add(new Bacteria(secuencias));
        }
    }

    // Balancea las secuencias para asegurar que tengan la misma longitud
    public void balancearSecuencias() {
        int longitudMaxima = 0;
        // Determinar la longitud máxima de las secuencias
        for (Bacteria bacteria : bacterias) {
            for (String secuencia : bacteria.getSecuencias()) {
                if (secuencia.length() > longitudMaxima) {
                    longitudMaxima = secuencia.length();
                }
            }
        }

        // Ajustar las secuencias para que todas tengan la misma longitud
        for (Bacteria bacteria : bacterias) {
            String[] secuencias = bacteria.getSecuencias();
            for (int i = 0; i < secuencias.length; i++) {
                while (secuencias[i].length() < longitudMaxima) {
                    secuencias[i] += "-";
                }
            }
        }
    }
}
