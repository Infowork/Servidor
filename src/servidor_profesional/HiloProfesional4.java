package servidor_profesional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javabeans.Datos;

public class HiloProfesional4 extends Thread{
	Socket sc;
	Datos datos;
	GestionProfesional gp;


	public HiloProfesional4(Socket sc) {
		this.sc = sc;
		gp=new GestionProfesional();
		
	}

	@Override
	public void run() {
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			System.out.println("Hilo 4");
			String json = bf.readLine();
			System.out.println(json);
			
			Datos datos=descomponer(json);
			Boolean res=gp.comprobarInicioSesion(datos);
			PrintStream salida = new PrintStream(sc.getOutputStream());
			System.out.println(res);
			salida.println(res);
			
			sc.close();

		} catch (IOException ex) {
			ex.getStackTrace();
		}
	}
	private Datos descomponer(String json){
		
		Datos datos=null;
		//Obtener JSONArray a partir del String
		 JSONParser parser=new JSONParser();
		 try {
			
			JSONObject jobdatos=(JSONObject) parser.parse(json);
			
			datos= new Datos(null,null,null,0,jobdatos.get("email").toString(),jobdatos.get("contrasena").toString(),false,null);
			
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		 
		 return datos;
		
	}
	
}