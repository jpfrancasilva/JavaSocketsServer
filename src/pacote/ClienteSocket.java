package pacote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.omg.CORBA.portable.UnknownException;

public class ClienteSocket {
	public static void main(String[] args) {
		try {
			final Socket cliente = new Socket("127.0.0.1", 9999);
			
			//lendo mensagem do servidor
			new Thread() {
				public void run() {
					try {
						BufferedReader leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
						
						while(true) {
							String mensagem = leitor.readLine();
							System.out.println("O servidor disse: " + mensagem);
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();
			
			//escrevendo para o servidor
			PrintWriter escritor = new PrintWriter(cliente.getOutputStream(), true);
			BufferedReader leitorTerminal = new BufferedReader(new InputStreamReader(System.in));
			
			String mensagemTerminal = "";
			while( (mensagemTerminal = leitorTerminal.readLine()) != null ) {
				escritor.println(mensagemTerminal);
				
				if(mensagemTerminal.equalsIgnoreCase("::SAIR")) {
					System.exit(0);
				}
			}
			
		}catch(UnknownException e) {
			System.out.println("o endereço passado é inválido!");
			e.printStackTrace();
		}catch(IOException err) {
			System.out.println("o servidor pode estar fora do ar");
			err.printStackTrace();
		}
	}
}
