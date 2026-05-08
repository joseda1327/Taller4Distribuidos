/*
 * PONTIFICIA UNIVERSIDAD JAVERIANA
 * Introduccion a los Sistemas Distribuidos
 * Taller 04 - Sockets TCP/UDP y Threads
 * Autor: Jose D. Medina
 * Fecha: 8 de mayo de 2026
 *
 * Archivo: cliUDPsocket.java
 * Descripcion: Cliente UDP que envia datagramas al host indicado por
 * argumento, puerto 6000, y termina al enviar "fin".
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

/**
 * Cliente UDP basico.
 *
 * Uso: java cliUDPsocket <servidor>
 */
public class cliUDPsocket {

    private static final int PUERTO = 6000;
    private static final String MENSAJE_FIN = "fin";

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Uso: java cliUDPsocket <servidor>");
            System.exit(1);
        }

        System.out.println("Prueba de sockets UDP (cliente)");

        try (BufferedReader entradaConsola = new BufferedReader(new InputStreamReader(System.in));
             DatagramSocket socket = new DatagramSocket()) {

            System.out.print("Capturando direccion de host... ");
            InetAddress direccion = InetAddress.getByName(args[0]);
            System.out.println("ok");

            System.out.println("Introduce mensajes a enviar:");
            String mensaje;
            do {
                mensaje = entradaConsola.readLine();
                if (mensaje == null) {
                    mensaje = MENSAJE_FIN;
                }

                byte[] datos = mensaje.getBytes(StandardCharsets.UTF_8);
                DatagramPacket paquete = new DatagramPacket(datos, datos.length, direccion, PUERTO);
                socket.send(paquete);
            } while (!mensaje.startsWith(MENSAJE_FIN));

            System.out.println("Cliente UDP finalizado correctamente.");
        } catch (IOException e) {
            System.err.println("Error en cliente UDP: " + e.getMessage());
            System.exit(1);
        }
    }
}
