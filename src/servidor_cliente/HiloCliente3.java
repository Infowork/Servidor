package servidor_cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import org.json.simple.parser.ParseException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import javabeans.Adicionales;
import javabeans.Datos;
import javabeans.Localizacion;
import javabeans.Persona;
import javabeans.Puntuacion;


public class HiloCliente3 extends Thread{

	Socket sc;
	Datos datos;
	Localizacion loc;
	Adicionales ad;
	Puntuacion punt;
	GestionCliente gp;


	public HiloCliente3(Socket sc) {
		this.sc = sc;
		gp=new GestionCliente();
		
	}

	@Override
	public void run() {
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			System.out.println("Hilo 3 - Cliente");
			String json = bf.readLine();
			System.out.println(json);
			//Descomponer JSON en un objeto Datos
			datos=descomponer(json);
			//Obtener objeto Persona y componer JSON
			Persona p=gp.obtenerPersonaPorParametros(datos);
			//Enviar JSON
			PrintStream salida = new PrintStream(sc.getOutputStream());
			JSONArray jarray=componer(p);
			System.out.println(jarray.toJSONString());
			salida.println(jarray.toJSONString());
			
			sc.close();

		} catch (IOException ex) {
			ex.getStackTrace();
		}
	}
	
	private JSONArray componer(Persona p){
		Datos datos=p.getDatos();
		JSONObject jdatos=new JSONObject();
			jdatos.put("dni", datos.getDni());
			jdatos.put("empresa",datos.getEmpresa());
			jdatos.put("nombre", datos.getNombre());
			jdatos.put("telefono", datos.getTelefono());
			jdatos.put("email", datos.getEmail());
			jdatos.put("contrasena",datos.getContrasena());
			jdatos.put("urgente", datos.isUrgente());
			jdatos.put("empleo", datos.getEmpleo());
			
		Localizacion loc=p.getLoc();
		JSONObject jloc=new JSONObject();
			jloc.put("dni", loc.getDni());
			jloc.put("latitud", loc.getLatitud());
			jloc.put("longitud", loc.getLongitud());
			
		Adicionales ad=p.getAd();
		JSONObject jad=new JSONObject();
			jad.put("dni", ad.getDni());
			jad.put("titulo", ad.getTitulo());
			jad.put("descripcion", ad.getDescripcion());
		Puntuacion punt=p.getPunt();
		JSONObject jpunt=new JSONObject();
			jpunt.put("dni", punt.getDni());
			jpunt.put("total",punt.getReview().size());
			for(int i=0;i<punt.getReview().size();i++){
				jpunt.put("review"+i, punt.getReview().get(i));
			}
			jpunt.put("puntuacion", punt.getPuntuacion());
			
		JSONArray jarray=new JSONArray();
		jarray.add(jdatos);
		jarray.add(jloc);
		jarray.add(jad);
		jarray.add(jpunt);
		System.out.println(jarray.toJSONString());
		return jarray;
	}
	
	private Datos descomponer(String json){
		Datos datos=null;
		try{
		JSONParser parser=new JSONParser();
		JSONObject jobdatos=(JSONObject) parser.parse(json);
		datos= new Datos(jobdatos.get("dni").toString(),
				jobdatos.get("empresa").toString(),
				jobdatos.get("nombre").toString(),
				Integer.parseInt(jobdatos.get("telefono").toString()),
				jobdatos.get("email").toString(),
				jobdatos.get("contrasena").toString(),
				Boolean.parseBoolean(jobdatos.get("urgente").toString()),
				jobdatos.get("empleo").toString());
		}catch(ParseException ex){
			ex.printStackTrace();
		}
		System.out.println(datos.toString());
		return datos;
	}
}
