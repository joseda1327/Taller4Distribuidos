/*
 * PONTIFICIA UNIVERSIDAD JAVERIANA
 * Introduccion a los Sistemas Distribuidos
 * Taller 04 - Sockets TCP/UDP y Threads
 * Autor: Jose D. Medina
 * Fecha: 8 de mayo de 2026
 *
 * Archivo: CajeraThread.java
 * Descripcion: Implementacion de una cajera como subclase de Thread.
 */

package tallerThreads;

/**
 * Cajera ejecutada en un hilo independiente mediante herencia de Thread.
 */
public class CajeraThread extends Thread {

    private final String nombre;
    private final Cliente cliente;
    private final long initialTime;

    public CajeraThread(String nombre, Cliente cliente, long initialTime) {
        this.nombre = nombre;
        this.cliente = cliente;
        this.initialTime = initialTime;
    }

    @Override
    public void run() {
        System.out.println("La cajera " + this.nombre
                + " comienza a procesar la compra del cliente " + cliente.getNombre()
                + " en el tiempo: " + tiempoTranscurrido() + "seg");

        int[] productos = cliente.getCarroCompra();
        for (int i = 0; i < productos.length; i++) {
            esperarXsegundos(productos[i]);
            System.out.println("Procesado el producto " + (i + 1)
                    + " del cliente " + cliente.getNombre()
                    + " -> Tiempo: " + tiempoTranscurrido() + "seg");
        }

        System.out.println("La cajera " + this.nombre
                + " ha terminado de procesar " + cliente.getNombre()
                + " en el tiempo: " + tiempoTranscurrido() + "seg");
    }

    private long tiempoTranscurrido() {
        return (System.currentTimeMillis() - this.initialTime) / 1000;
    }

    private void esperarXsegundos(int segundos) {
        try {
            Thread.sleep(segundos * 1000L);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
