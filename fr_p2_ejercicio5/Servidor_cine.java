package fr_p2_ejercicio5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
public class Servidor_cine {

	public static void main(String[] args) {
	
		// Puerto de escucha
		int port=8989;
		
		try {
			// Abrimos el socket en modo pasivo, escuchando el en puerto indicado por "port"
			//////////////////////////////////////////////////
			// ...serverSocket=... (completar)
			//////////////////////////////////////////////////
			ServerSocket socketServicio = new ServerSocket(port);
                        System.out.println(" Creando lanzadera de comunicación. ");
			// Mientras ... siempre!
                        int asistente = 1;
			do {
				// Aceptamos una nueva conexión con accept()
				/////////////////////////////////////////////////
				// socketServicio=... (completar)
				//////////////////////////////////////////////////
                                //System.out.println("\n Servidor escuchando peticiones ");
				Socket socketConexion = socketServicio.accept();
                                
				// Creamos un objeto de la clase ProcesadorYodafy, pasándole como 
				// argumento el nuevo socket, para que realice el procesamiento
				// Este esquema permite que se puedan usar hebras más fácilmente.
                                System.out.println(" 1º) Servidor llamando a asistente online ");
                                Hebra_cine asistente_online = new Hebra_cine(socketConexion, asistente);
				asistente_online.start();
				
                                asistente++;
			} while (true);
			
		} catch (IOException e) {
			System.err.println("Error al escuchar en el puerto "+port);
		}

	}

}
