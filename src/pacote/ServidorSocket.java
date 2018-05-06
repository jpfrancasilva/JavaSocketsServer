package pacote;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket {
	public static void main(String[] args) {
		
		ServerSocket servidor = null;
		try {
			System.out.println("Startando o servidor...");
			servidor = new ServerSocket(9999);
			System.out.println("Server startado!");
			
			while(true) {
				Socket cliente = servidor.accept();
				new GerenciadorDeClientes(cliente);
			}
		}catch(IOException e) {
			try {
				if(servidor != null) {
					servidor.close();
				}
			}catch(IOException err) {
				
			}
			System.err.println("a porta está ocupada ou o server foi fechado");
			e.printStackTrace();
		}
	}
}
