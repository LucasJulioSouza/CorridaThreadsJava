package corridaDeThreads;
import java.util.Random;

public class corridaDeThreads {
    private static int[] valoresCorredores = new int[4];
    private static final int META = 50;

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Corredor(0));
        Thread thread2 = new Thread(new Corredor(1));
        Thread thread3 = new Thread(new Corredor(2));
        Thread thread4 = new Thread(new Corredor(3));

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

    private static synchronized void atualizarValor(int id, int valor) {
        valoresCorredores[id] += valor;
        if (valoresCorredores[id] >= META) {
            System.out.println("Corredor " + (id + 1) + " venceu!");
            System.exit(0);
        }
    }

    private static class Corredor implements Runnable {
        private int id;
        private Random random = new Random();

        public Corredor(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            while (true) {
                int valorSorteado = random.nextInt(6); // Sorteia um valor de 0 a 5
                atualizarValor(id, valorSorteado);

                // Imprimir status das threads
                System.out.println("Corredor " + (id + 1));
                System.out.println("Valor sorteado: " + valorSorteado);
                System.out.println("Total de pontos: " + valoresCorredores[id]);
                System.out.println();

                try {
                    Thread.sleep(500); // Simula um pequeno atraso
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
