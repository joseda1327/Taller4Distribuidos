/*
 * PONTIFICIA UNIVERSIDAD JAVERIANA
 * Introduccion a los Sistemas Distribuidos
 * Taller 04 - Sockets TCP/UDP y Threads
 * Autor: Jose D. Medina
 * Fecha: 8 de mayo de 2026
 *
 * Archivo: cliTCPsocket.java
 * Descripcion: Cliente TCP que se conecta al host indicado por argumento,
 * envia mensajes de consola al puerto 6001 y termina al enviar "fin".
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Cliente TCP basico.
 *
 * Uso: java cliTCPsocket <servidor>
 */
public class cliTCPsocket {

    private static final int PUERTO = 6001;
    private static final String MENSAJE_FIN = "fin";

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Uso: java cliTCPsocket <servidor>");
            System.exit(1);
        }

        System.out.println("Prueba de sockets TCP (CLIENTE)");

        try (BufferedReader entradaConsola = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Capturando direccion de host... ");
            InetAddress direccion = InetAddress.getByName(args[0]);
            System.out.println("ok");

            System.out.print("Creando socket... ");
            try (Socket socket = new Socket(direccion, PUERTO);
                 DataOutputStream salida = new DataOutputStream(socket.getOutputStream())) {

                System.out.println("ok");
                System.out.println("Introduce mensajes a enviar:");

                String mensaje;
                do {
                    mensaje = entradaConsola.readLine();
                    if (mensaje == null) {
                        mensaje = MENSAJE_FIN;
                    }
                    salida.writeUTF(mensaje);
                } while (!mensaje.startsWith(MENSAJE_FIN));
            }

            System.out.println("Cliente TCP finalizado correctamente.");
        } catch (IOException e) {
            System.err.println("Error en cliente TCP: " + e.getMessage());
            System.exit(1);
        }
    }
}
