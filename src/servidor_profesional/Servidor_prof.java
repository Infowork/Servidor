package servidor_profesional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor_prof {

	public static void main(String[] args) {
		try {
			// Crea un servidor de socket para comunicarse con clientes.
			ServerSocket server = new ServerSocket(8000);
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
					System.out.println("Guardar datos");
					new HiloProfesional1(cliente).start();
					break;
				case "bbb":
					System.out.println("Modificar Datos");
					new HiloProfesional2(cliente).start();
					break;
				case "ccc":
					System.out.println("Recibir todos los datos");
					new HiloProfesional3(cliente).start();
					break;
				case "ddd":
					System.out.println("Comprobar email/contrasena");
					new HiloProfesional4(cliente).start();
					break;
				case "eee":
					System.out.println("Comprobar DNI");
					new HiloProfesional5(cliente).start();
					break;
				}
			}
		} catch (IOException ex) {
			ex.getStackTrace();
		}

	}

}
