package servidor_cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import servidor_profesional.HiloProfesional1;
import servidor_profesional.HiloProfesional2;
import servidor_profesional.HiloProfesional3;

public class Servidor_cliente {
	public static void main(String[] args) {
		try {
			// Crea un servidor de socket para comunicarse con clientes.
			ServerSocket server = new ServerSocket(9000);
			System.out.println("Esperando llamadas...");

			while (true) {
				// Espera la llamada de un cliente y lo identifica.
				Socket cliente = server.accept();
				System.out.println("Llamada recibida");
				BufferedReader bf = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
				String s = bf.readLine();

				// Crea un nuevo hilo para atender al cliente dependiendo del
				// tipo de app.
				switch (s) {
				case "aaa":
					System.out.println("Obtener todos los datos");
					new HiloCliente1(cliente).start();
					break;
				case "bbb":
					System.out.println("Guardar valoracion");
					new HiloCliente2(cliente).start();
					break;
				case "ccc":
					System.out.println("Obtener perfil especifico");
					new HiloCliente3(cliente).start();
					break;
				}
			}
		} catch (IOException ex) {
			ex.getStackTrace();
		}

	}

}
