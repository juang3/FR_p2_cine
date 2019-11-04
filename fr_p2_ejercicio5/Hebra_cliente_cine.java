package fr_p2_ejercicio5;

//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Hebra_cliente_cine {

	public static void main(String[] args) {
		// Nombre del host donde se ejecuta el servidor:
		String host="localhost";
		// Puerto en el que espera el servidor:
		int port=8989;
		
		// Socket para la conexión TCP
		Socket socketServicio=null;
		
		try {
			// Creamos un socket que se conecte a "host" y "port":
			//////////////////////////////////////////////////////
			// socketServicio= ... (Completar)
			//////////////////////////////////////////////////////			
			socketServicio = new Socket(host, port);
                        
                        BufferedReader inReader = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));
                        PrintWriter outPrinter = new PrintWriter(socketServicio.getOutputStream(), true);
			
			// Enviamos el array por el outputStream;
			//////////////////////////////////////////////////////
			// ... .write ... (Completar)
			//////////////////////////////////////////////////////
			outPrinter.print("1000@Al monte del volcán debes ir sin demora \n");
                        
                        
			// Aunque le indiquemos a TCP que queremos enviar varios arrays de bytes, sólo
			// los enviará efectivamente cuando considere que tiene suficientes datos que enviar...
			// Podemos usar "flush()" para obligar a TCP a que no espere para hacer el envío:
			//////////////////////////////////////////////////////
			// ... .flush(); (Completar)
			//////////////////////////////////////////////////////
                        outPrinter.flush();
                        System.out.println(" Enviada cadena");
                        
			// Leemos la respuesta del servidor. Para ello le pasamos un array de bytes, que intentará
			// rellenar. El método "read(...)" devolverá el número de bytes leídos.
			//////////////////////////////////////////////////////
			// bytesLeidos ... .read... buferRecepcion ; (Completar)
			//////////////////////////////////////////////////////
			System.out.println(" Esperando respuesta de Yoda ");
                        
                        String respuesta = inReader.readLine();
                        System.out.println(" Recibida respuesta de Yoda ");
                        
			// Mostremos la cadena de caracteres recibidos:
			System.out.println("\t Recibido: ");
                        System.out.println(respuesta);
//			for(int i=0;i<bytesLeidos;i++){
//				System.out.print((char)buferRecepcion[i]);
//			}
			
			// Una vez terminado el servicio, cerramos el socket (automáticamente se cierran
			// el inpuStream  y el outputStream)
			//////////////////////////////////////////////////////
			// ... close(); (Completar)
			//////////////////////////////////////////////////////
			socketServicio.close();
                        
			// Excepciones:
		} catch (UnknownHostException e) {
			System.err.println("Error: Nombre de host no encontrado.");
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
	}
}
