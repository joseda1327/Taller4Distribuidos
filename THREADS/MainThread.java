/*
 * PONTIFICIA UNIVERSIDAD JAVERIANA
 * Introduccion a los Sistemas Distribuidos
 * Taller 04 - Sockets TCP/UDP y Threads
 * Autor: Jose D. Medina
 * Fecha: 8 de mayo de 2026
 *
 * Archivo: MainThread.java
 * Descripcion: Ejecuta la simulacion concurrente usando clases que heredan
 * de Thread.
 */

package tallerThreads;

/**
 * Programa principal para la version concurrente basada en Thread.
 */
public class MainThread {

    public static void main(String[] args) throws InterruptedException {
        Cliente cliente1 = new Cliente("Cliente 1", new int[] {2, 2, 1, 5, 2, 3});
        Cliente cliente2 = new Cliente("Cliente 2", new int[] {1, 3, 5, 1, 1});

        long initialTime = System.currentTimeMillis();
        CajeraThread cajera1 = new CajeraThread("Cajera 1", cliente1, initialTime);
        CajeraThread cajera2 = new CajeraThread("Cajera 2", cliente2, initialTime);

        cajera1.start();
        cajera2.start();

        cajera1.join();
        cajera2.join();
    }
}
