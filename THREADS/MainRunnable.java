/*
 * PONTIFICIA UNIVERSIDAD JAVERIANA
 * Introduccion a los Sistemas Distribuidos
 * Taller 04 - Sockets TCP/UDP y Threads
 * Autor: Jose D. Medina
 * Fecha: 8 de mayo de 2026
 *
 * Archivo: MainRunnable.java
 * Descripcion: Ejecuta la simulacion concurrente usando la interfaz Runnable.
 */

package tallerThreads;

/**
 * Adaptador Runnable que permite procesar una compra dentro de un hilo.
 */
public class MainRunnable implements Runnable {

    private final Cliente cliente;
    private final Cajera cajera;
    private final long initialTime;

    public MainRunnable(Cliente cliente, Cajera cajera, long initialTime) {
        this.cajera = cajera;
        this.cliente = cliente;
        this.initialTime = initialTime;
    }

    public static void main(String[] args) throws InterruptedException {
        Cliente cliente1 = new Cliente("Cliente 1", new int[] {2, 2, 1, 5, 2, 3});
        Cliente cliente2 = new Cliente("Cliente 2", new int[] {1, 3, 5, 1, 1});

        Cajera cajera1 = new Cajera("Cajera 1");
        Cajera cajera2 = new Cajera("Cajera 2");

        long initialTime = System.currentTimeMillis();
        Thread proceso1 = new Thread(new MainRunnable(cliente1, cajera1, initialTime));
        Thread proceso2 = new Thread(new MainRunnable(cliente2, cajera2, initialTime));

        proceso1.start();
        proceso2.start();

        proceso1.join();
        proceso2.join();
    }

    @Override
    public void run() {
        this.cajera.procesarCompra(this.cliente, this.initialTime);
    }
}
