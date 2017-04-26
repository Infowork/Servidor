package servidor_profesional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javabeans.Adicionales;
import javabeans.Datos;
import javabeans.Localizacion;



public class HiloProfesional1 extends Thread{
	Socket sc;
	Datos datos;
	Localizacion loc;
	Adicionales ad;
	GestionProfesional gp;


	public HiloProfesional1(Socket sc) {
		this.sc = sc;
		gp=new GestionProfesional();
		
	}

	@Override
	public void run() {
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			String json = bf.readLine();
			System.out.println(json);
			descomponer(json);
			
			if(gp.existeDni(datos.getDni())){
				//Mandar respuesta a la app
			}else{
				gp.guardarDatos(datos,loc,ad);
			}
			sc.close();

		} catch (IOException ex) {
			ex.getStackTrace();
		}
	}
	
	private void descomponer(String json){
		
		//Obtener JSONArray a partir del String
		 JSONParser parser=new JSONParser();
		 try {
			JSONArray jarray=(JSONArray) parser.parse(json);
			JSONObject jobdatos=(JSONObject) jarray.get(0);
			JSONObject joblocalizacion=(JSONObject) jarray.get(1);
			JSONObject jobadicionales=(JSONObject) jarray.get(2);
			//JSONObject jobpuntuacion=(JSONObject) jarray.get(3);
			
			datos= new Datos(jobdatos.get("dni").toString(),
					jobdatos.get("empresa").toString(),
					jobdatos.get("nombre").toString(),
					Integer.parseInt(jobdatos.get("telefono").toString()),
					jobdatos.get("email").toString(),
					jobdatos.get("contrasena").toString(),
					Boolean.parseBoolean(jobdatos.get("urgente").toString()),
					jobdatos.get("empleo").toString());
			
			loc=new Localizacion( joblocalizacion.get("dni").toString(),
					Double.parseDouble(joblocalizacion.get("latitud").toString()),
					Double.parseDouble(joblocalizacion.get("longitud").toString()));
			
			ad=new Adicionales(jobadicionales.get("dni").toString(),
					jobadicionales.get("titulo").toString(),
					jobadicionales.get("descripcion").toString());
			
			/*punt=new Puntuacion(jobpuntuacion.get("dni").toString(),
					jobpuntuacion.get("review").toString(),
					Double.parseDouble(jobpuntuacion.get("puntuacion").toString()));*/
			
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		
	}
}

