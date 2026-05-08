/*
 * PONTIFICIA UNIVERSIDAD JAVERIANA
 * Introduccion a los Sistemas Distribuidos
 * Taller 04 - Sockets TCP/UDP y Threads
 * Autor: Jose D. Medina
 * Fecha: 8 de mayo de 2026
 *
 * Archivo: Cliente.java
 * Descripcion: Modelo simple de cliente con nombre y tiempos de productos
 * en su carro de compras.
 */

package tallerThreads;

import java.util.Arrays;

/**
 * Representa un cliente de supermercado.
 */
public class Cliente {

    private final String nombre;
    private final int[] carroCompra;

    /**
     * Crea un cliente con su nombre y los tiempos de procesamiento de cada producto.
     *
     * @param nombre nombre del cliente.
     * @param carroCompra arreglo con el tiempo de procesamiento de cada producto.
     */
    public Cliente(String nombre, int[] carroCompra) {
        this.nombre = nombre;
        this.carroCompra = Arrays.copyOf(carroCompra, carroCompra.length);
    }

    public String getNombre() {
        return nombre;
    }

    public int[] getCarroCompra() {
        return Arrays.copyOf(carroCompra, carroCompra.length);
    }
}
