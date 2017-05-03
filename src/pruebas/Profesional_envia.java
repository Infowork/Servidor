package pruebas;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javabeans.Adicionales;
import javabeans.Datos;
import javabeans.Localizacion;
import javabeans.Puntuacion;

public class Profesional_envia {

	public static void main(String[] args) {
		try {
			Socket sc = new Socket("localhost", 8000);

			PrintStream salida = new PrintStream(sc.getOutputStream());
			salida.println("aaa");
			
			
			JSONObject jdatos=new JSONObject();
				jdatos.put("dni", "584963E");
				jdatos.put("empresa","Jose Fontanería");
				jdatos.put("nombre", "Jose Perez");
				jdatos.put("telefono", 745822366);
				jdatos.put("email", "jose@josefontaneria.com");
				jdatos.put("contrasena","jose222");
				jdatos.put("urgente", false);
				jdatos.put("empleo","Fontanero");
				
			
			JSONObject jloc=new JSONObject();
				jloc.put("dni", "584963E");
				jloc.put("latitud", 40.402281);
				jloc.put("longitud", -3.735135);
				
			
			JSONObject jad=new JSONObject();
				jad.put("dni", "584963E");
				jad.put("titulo", "Fontanería para todos!");
				jad.put("descripcion", "Pruebe nuestros increíbles servicios de fontanería en su domicilio o local comercial a un módico precio!!");
			
				
			
			JSONArray jarray=new JSONArray();
			jarray.add(jdatos);
			jarray.add(jloc);
			jarray.add(jad);
			
			
			System.out.println(jarray.toJSONString());
			salida.println(jarray.toJSONString());
			sc.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
