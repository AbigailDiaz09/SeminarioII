
import java.util.Random;

public class Bacteria {
    private String[] secuencias;
    private int puntajeBLOSUM;
    private double fitness;

    // Constructor que inicializa las secuencias y calcula el puntaje inicial
    public Bacteria(String[] secuencias) {
        this.secuencias = secuencias;
        this.puntajeBLOSUM = calcularPuntajeBLOSUM();
        this.fitness = 0;
    }

    public String[] getSecuencias() {
        return secuencias;
    }

    public int getPuntajeBLOSUM() {
        return puntajeBLOSUM;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    // Método que simula el cálculo del puntaje BLOSUM (usando valores aleatorios por ahora)
    private int calcularPuntajeBLOSUM() {
        Random random = new Random();
        return random.nextInt(100); // Puntaje aleatorio entre 0 y 100
    }

    // Método que realiza el "Tumbo" (inserta un gap en las secuencias)
    public void tumbo() {
        Random random = new Random();
        for (int i = 0; i < secuencias.length; i++) {
            int pos = random.nextInt(secuencias[i].length() + 1);
            secuencias[i] = new StringBuilder(secuencias[i]).insert(pos, "-").toString();
        }
        puntajeBLOSUM = calcularPuntajeBLOSUM();  // Actualiza el puntaje BLOSUM después del Tumbo
    }

    // Método que realiza el "Nado" (si la mutación es favorable, sigue explorando)
    public void nado() {
        // Puedes agregar lógica para evaluar si la bacteria debe continuar "nadando"
        tumbo(); // Por ahora, simplemente ejecuta otro tumbo
    }
}
