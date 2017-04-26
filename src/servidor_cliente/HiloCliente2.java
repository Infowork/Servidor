package servidor_cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javabeans.Datos;
import javabeans.Valoracion;

public class HiloCliente2 extends Thread{
	
	Socket sc;
	Datos datos;
	
	GestionCliente gp;


	public HiloCliente2(Socket sc) {
		this.sc = sc;
		gp=new GestionCliente();
		
	}

	@Override
	public void run() {
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			System.out.println("Hilo 2 - Cliente");
			String json = bf.readLine();
			System.out.println(json);
			gp.guardarValoracion(descomponer(json));
			
			sc.close();

		} catch (IOException ex) {
			ex.getStackTrace();
		}
	}
	
	private Valoracion descomponer(String json){
		Valoracion val=null;
		try{
			JSONParser parser=new JSONParser();
			JSONObject jval=(JSONObject) parser.parse(json);
			val=new Valoracion(jval.get("dni").toString(),jval.get("texto").toString(),Integer.parseInt(jval.get("puntuacion").toString()));
		}catch(ParseException ex){
			ex.printStackTrace();
		}
		return val;
	}

}
