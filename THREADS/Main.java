/*
 * PONTIFICIA UNIVERSIDAD JAVERIANA
 * Introduccion a los Sistemas Distribuidos
 * Taller 04 - Sockets TCP/UDP y Threads
 * Autor: Jose D. Medina
 * Fecha: 8 de mayo de 2026
 *
 * Archivo: Main.java
 * Descripcion: Ejecuta la simulacion secuencial de dos cajeras y dos clientes.
 */

package tallerThreads;

/**
 * Programa principal para la version secuencial.
 */
public class Main {

    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("Cliente 1", new int[] {2, 2, 1, 5, 2, 3});
        Cliente cliente2 = new Cliente("Cliente 2", new int[] {1, 3, 5, 1, 1});

        Cajera cajera1 = new Cajera("Cajera 1");
        Cajera cajera2 = new Cajera("Cajera 2");

        long initialTime = System.currentTimeMillis();
        cajera1.procesarCompra(cliente1, initialTime);
        cajera2.procesarCompra(cliente2, initialTime);
    }
}
