/*
 * PONTIFICIA UNIVERSIDAD JAVERIANA
 * Introduccion a los Sistemas Distribuidos
 * Taller 04 - Sockets TCP/UDP y Threads
 * Autor: Jose D. Medina
 * Fecha: 8 de mayo de 2026
 *
 * Archivo: serUDPsocket.java
 * Descripcion: Servidor UDP que escucha datagramas en el puerto 6000 y
 * finaliza cuando recibe un mensaje que inicia con "fin".
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

/**
 * Servidor UDP basico.
 *
 * UDP trabaja con datagramas independientes: no hay conexion persistente ni
 * confirmacion automatica de entrega.
 */
public class serUDPsocket {

    private static final int PUERTO = 6000;
    private static final int TAMANO_BUFFER = 256;
    private static final String MENSAJE_FIN = "fin";

    public static void main(String[] args) {
        System.out.println("Prueba de sockets UDP (servidor)");

        try (DatagramSocket socket = new DatagramSocket(PUERTO)) {
            System.out.println("Socket UDP creado en puerto " + PUERTO);
            System.out.println("Recibiendo mensajes...");

            boolean fin = false;
            while (!fin) {
                byte[] buffer = new byte[TAMANO_BUFFER];
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                socket.receive(paquete);

                String mensaje = new String(paquete.getData(), 0, paquete.getLength(), StandardCharsets.UTF_8);
                System.out.println("Mensaje recibido: " + mensaje + " desde " + paquete.getAddress());
                fin = mensaje.startsWith(MENSAJE_FIN);
            }

            System.out.println("Servidor UDP finalizado correctamente.");
        } catch (IOException e) {
            System.err.println("Error en servidor UDP: " + e.getMessage());
            System.exit(1);
        }
    }
}
