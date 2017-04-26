package servidor_cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javabeans.Adicionales;
import javabeans.Datos;
import javabeans.Localizacion;
import javabeans.Persona;
import javabeans.Puntuacion;

public class HiloCliente1 extends Thread{
	Socket sc;
	Datos datos;
	Localizacion loc;
	Adicionales ad;
	Puntuacion punt;
	GestionCliente gp;


	public HiloCliente1(Socket sc) {
		this.sc = sc;
		gp=new GestionCliente();
		
	}

	@Override
	public void run() {
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			System.out.println("Hilo 1 - Cliente");
			String cat = bf.readLine();
			System.out.println(cat);
			
			String json=listaPersonas(gp.obtenerDatosCategoria(cat));
			PrintStream salida = new PrintStream(sc.getOutputStream());
			System.out.println(json);
			salida.println(json);
			
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
			int total=0;
			for(int i=0;i<punt.getReview().size();i++){
				jpunt.put("review"+i, punt.getReview().get(i));
				total++;
			}
			jpunt.put("puntuacion", punt.getPuntuacion());
			jpunt.put("total", total);
			
		JSONArray jarray=new JSONArray();
		jarray.add(jdatos);
		jarray.add(jloc);
		jarray.add(jad);
		jarray.add(jpunt);
		System.out.println(jarray.toJSONString());
		return jarray;
	}
	
	private String listaPersonas(ArrayList<Persona> pers){
		JSONArray lista=new JSONArray();
		for(int i=0;i<pers.size();i++){
			JSONArray jpers=componer(pers.get(i));
			lista.add(jpers);
		}
		return lista.toJSONString();
	}
}
