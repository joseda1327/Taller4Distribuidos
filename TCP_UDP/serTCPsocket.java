/*
 * PONTIFICIA UNIVERSIDAD JAVERIANA
 * Introduccion a los Sistemas Distribuidos
 * Taller 04 - Sockets TCP/UDP y Threads
 * Autor: Jose D. Medina
 * Fecha: 8 de mayo de 2026
 *
 * Archivo: serTCPsocket.java
 * Descripcion: Servidor TCP que escucha en el puerto 6001, recibe
 * mensajes UTF enviados por un cliente y finaliza cuando recibe "fin".
 */

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Servidor TCP basico.
 *
 * TCP trabaja orientado a conexion: primero acepta una conexion del cliente
 * y despues lee los datos sobre el flujo asociado al socket.
 */
public class serTCPsocket {

    private static final int PUERTO = 6001;
    private static final String MENSAJE_FIN = "fin";

    public static void main(String[] args) {
        System.out.println("\n=** SOCKETS TCP <<SERVIDOR>> **=");
        System.out.println("Esperando conexion en puerto " + PUERTO + "...");

        try (ServerSocket servidor = new ServerSocket(PUERTO);
             Socket socketCliente = servidor.accept();
             DataInputStream entrada = new DataInputStream(socketCliente.getInputStream())) {

            System.out.println("Cliente conectado desde: " + socketCliente.getInetAddress());
            boolean fin = false;

            while (!fin) {
                String mensaje = entrada.readUTF();
                System.out.println("Mensaje recibido: " + mensaje);
                fin = mensaje.startsWith(MENSAJE_FIN);
            }

            System.out.println("Servidor TCP finalizado correctamente.");
        } catch (IOException e) {
            System.err.println("Error en servidor TCP: " + e.getMessage());
            System.exit(1);
        }
    }
}
