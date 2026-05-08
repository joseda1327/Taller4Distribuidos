/*
 * PONTIFICIA UNIVERSIDAD JAVERIANA
 * Introduccion a los Sistemas Distribuidos
 * Taller 04 - Sockets TCP/UDP y Threads
 * Autor: Jose D. Medina
 * Fecha: 8 de mayo de 2026
 *
 * Archivo: Cajera.java
 * Descripcion: Simula la atencion de una cajera para procesar los productos
 * de un cliente de forma secuencial.
 */

package tallerThreads;

/**
 * Cajera que procesa una compra producto por producto.
 */
public class Cajera {

    private final String nombre;

    public Cajera(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Procesa la compra de un cliente y muestra marcas de tiempo relativas.
     *
     * @param cliente cliente que sera procesado.
     * @param timeStamp tiempo inicial de la simulacion.
     */
    public void procesarCompra(Cliente cliente, long timeStamp) {
        System.out.println("La cajera " + this.nombre
                + " comienza a procesar la compra del cliente " + cliente.getNombre()
                + " en el tiempo: " + tiempoTranscurrido(timeStamp) + "seg");

        int[] productos = cliente.getCarroCompra();
        for (int i = 0; i < productos.length; i++) {
            esperarXsegundos(productos[i]);
            System.out.println("Procesado el producto " + (i + 1)
                    + " del cliente " + cliente.getNombre()
                    + " -> Tiempo: " + tiempoTranscurrido(timeStamp) + "seg");
        }

        System.out.println("La cajera " + this.nombre
                + " ha terminado de procesar " + cliente.getNombre()
                + " en el tiempo: " + tiempoTranscurrido(timeStamp) + "seg");
    }

    private long tiempoTranscurrido(long timeStamp) {
        return (System.currentTimeMillis() - timeStamp) / 1000;
    }

    private void esperarXsegundos(int segundos) {
        try {
            Thread.sleep(segundos * 1000L);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
