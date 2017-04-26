package pruebas;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Cliente_envia_valoracion {
	public static void main(String[] args) {
		try {
			Socket sc = new Socket("localhost", 9000);

			PrintStream salida = new PrintStream(sc.getOutputStream());
			salida.println("bbb");
			
			
			
			
			JSONObject jval=new JSONObject();
				jval.put("dni", "123456A");
				jval.put("puntuacion", 7);
				jval.put("texto", "Zoquete");
				
			
			
			
			
			
			
			System.out.println(jval.toJSONString());
			salida.println(jval.toJSONString());
			sc.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
