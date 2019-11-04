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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


//
// Nota: si esta clase extendiera la clase Thread, y el procesamiento lo hiciera el método "run()",
// ¡Podríamos realizar un procesado concurrente! 
//
public class Hebra_cine extends Thread {
    // Atributos para comunicación socket con TCP
	// Referencia a un socket para enviar/recibir las peticiones/respuestas
	private Socket socketServicio;
	// stream de lectura (por aquí se recibe lo que envía el cliente)
	private BufferedReader inReader;
	// stream de escritura (por aquí se envía los datos al cliente)
	private PrintWriter outPrinter;
	
	// Para que la respuesta sea siempre diferente, usamos un generador de números aleatorios.
	private Random random;
        
    // Atributos propios del asistente
        private int identidad;
        private CCD ccd;
	// Constructor que tiene como parámetro una referencia al socket abierto en por otra clase
	public Hebra_cine(Socket socketServicio, int identidad) {
            try {
                // Elementos de comunicación 
                this.socketServicio=socketServicio;
                this.inReader = new BufferedReader(new InputStreamReader(this.socketServicio.getInputStream()));
                this.outPrinter = new PrintWriter(this.socketServicio.getOutputStream(),true);
                random=new Random();
                
                // Elementos de identificación
                this.identidad = identidad;
            } catch (IOException ex) {
                Logger.getLogger(Hebra_cine.class.getName()).log(Level.SEVERE, null, ex);
            }
                
	}
	
	
	// Aquí es donde se realiza el procesamiento realmente:
	public void run(){
		try {
                    System.out.println("   2º) Acude asistente " + this.identidad );
			// Obtiene los flujos de escritura/lectura
                        // BufferedReader inReader;
			// PrintWriter outPrinter;
                        
			// Lee la frase a Yodaficar:
			////////////////////////////////////////////////////////
			// read ... datosRecibidos.. (Completar)
			////////////////////////////////////////////////////////
                        String peticion= inReader.readLine();
                        System.out.println("    2.1º) Leida petición del cliente ");
                        ccd = new CCD(peticion);
                        System.out.println(" codigo: "+ ccd.getCode());
                        System.out.println(" cuerpo: "+ ccd.getBody() );
			// Yoda reinterpreta el mensaje:
			String respuesta=yodaDo(peticion);
                        System.out.println("    2.2º) Generada respuesta ");
			// Convertimos el String de respuesta en una array de bytes:
			
			// Enviamos la traducción de Yoda:
			////////////////////////////////////////////////////////
			// ... write ... datosEnviar... datosEnviar.length ... (Completar)
			////////////////////////////////////////////////////////
                        //outPrinter = new PrintWriter(socketServicio.getOutputStream(), true);
			outPrinter.println(respuesta);
                        System.out.println("    2.3º) Enviada respuesta ");
			
                        System.out.println("   3º) Esperando nueva interacción.");
			
		} catch (IOException e) {
			System.err.println("Error al obtener los flujso de entrada/salida.");
		}

	}

	// Yoda interpreta una frase y la devuelve en su "dialecto":
	private String yodaDo(String peticion) {
		// Desordenamos las palabras:
		String[] s = peticion.split(" ");
		String resultado="";
		
		for(int i=0;i<s.length;i++){
			int j=random.nextInt(s.length);
			int k=random.nextInt(s.length);
			String tmp=s[j];
			
			s[j]=s[k];
			s[k]=tmp;
		}
		
		resultado=s[0];
		for(int i=1;i<s.length;i++){
		  resultado+=" "+s[i];
		}
		
		return resultado;
	}
        
        /**
         * Tras el primer mensaje del cliente,
         * el asistente proporciona una lista de películas a elegir.
         * 
         * 1000 código que espera recibir
         *  200 código que envia si todo correcto
         *  300 código de error que envia si no hay Saludo
         * 
         * 
         */
        private boolean hola_lista_peliculas() throws IOException{
            String peticion = this.inReader.readLine();
            
            return true;
        }
        
}
