package servidor_profesional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javabeans.Datos;

public class HiloProfesional5 extends Thread{

	Socket sc;
	Datos datos;
	GestionProfesional gp;


	public HiloProfesional5(Socket sc) {
		this.sc = sc;
		gp=new GestionProfesional();
		
	}

	@Override
	public void run() {
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			System.out.println("Hilo 5");
			String dni = bf.readLine();
			System.out.println(dni);
			
			Boolean res=gp.existeDni(dni);
			PrintStream salida = new PrintStream(sc.getOutputStream());
			System.out.println(res);
			salida.println(res);
			
			sc.close();

		} catch (IOException ex) {
			ex.getStackTrace();
		}
	}
}
