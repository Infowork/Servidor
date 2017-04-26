package servidor_profesional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javabeans.Adicionales;
import javabeans.Datos;
import javabeans.Localizacion;
import javabeans.Persona;
import javabeans.Puntuacion;

public class GestionProfesional {
	private String nombredb="tuprofesional";
    private String ip="localhost";
    private String port="3306";
    private String user="root";
    private String password="root";

	public void guardarDatos(Datos datos,Localizacion loc,Adicionales ad) {
		try {
			// Establecer conexión con BD
			Connection cn = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+nombredb, user, password);

			// 2.1 Enviar instrucción SQL datos
			Statement st = cn.createStatement();
			String sql = "insert into datos(dni,empresa,nombre,telefono,email,contrasena,urgente)";
			sql += " values('" + datos.getDni() + "','" + datos.getEmpresa() + "','" + datos.getNombre() + "',"
					+ datos.getTelefono()+",'" + datos.getEmail() + "','" + datos.getContrasena() + "',"
							+ datos.isUrgente()+")";
			st.execute(sql);
			
			// 2.2 Enviar instrucción SQL localizacion
			String sql2 = "insert into localizacion(dni,latitud,longitud)";
			sql2 += " values('" + loc.getDni() + "'," + loc.getLatitud() + "," + loc.getLongitud() + ")";
			st.execute(sql2);
						
			// 2.3 Enviar instrucción SQL adicionales
			String sql3 = "insert into adicionales(dni,titulo,descripcion)";
			sql3 += " values('" + ad.getDni() + "','" + ad.getTitulo() + "','" + ad.getDescripcion() + "')";
			st.execute(sql3);
			
			//2.4 Enviar instruccion SQL empleo/categoria
			String sql4="insert into empleados(empleo,dni)";
			sql4+=" values('"+datos.getEmpleo()+"','"+datos.getDni()+"')";
			st.execute(sql4);

			// 4. Cierre de conexión
			cn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public boolean existeDni(String dni){

		boolean res=false;
		try {
			// 1. establecer conexión con BD
			Connection cn = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+nombredb, user, password);
			// 2. Enviar instrucción SQL
			Statement st = cn.createStatement();
			String sql = "select * from datos where dni='" + dni + "'";
			// 3. Manipulacion resultados
			ResultSet rs = st.executeQuery(sql);
			
			if (rs.next()) {
				res = true;
			}
			// 4. cierre conexión
			cn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return res;
	}
	
	public void modificarDatos(Datos datos,Localizacion loc,Adicionales ad){
		try {
			// Establecer conexión con BD
			Connection cn = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+nombredb, user, password);

			// 2.1 Enviar instrucción SQL datos
			Statement st = cn.createStatement();
			String sql = "update datos set empresa='" + datos.getEmpresa() + "',nombre='" + datos.getNombre() + "',"
					+ "telefono="+ datos.getTelefono()+",email='" + datos.getEmail() + "',"
					+ "contrasena='" + datos.getContrasena() + "',urgente="+ datos.isUrgente()+" "
					+ "where dni='" + datos.getDni()+"'";
			
			st.executeUpdate(sql);
			
			
			// 2.2 Enviar instrucción SQL localizacion
			String sql2 = "update localizacion set latitud=" + loc.getLatitud() + ",longitud=" + loc.getLongitud()
					+ "where dni='" + loc.getDni()+"'";

			st.executeUpdate(sql2);
						
			// 2.3 Enviar instrucción SQL adicionales
			String sql3 = "update adicionales set titulo='" + ad.getTitulo() + "',descripcion='" + ad.getTitulo() + "'"
					+ "where dni='" + ad.getDni()+"'";
			st.executeUpdate(sql3);

			// 4. Cierre de conexión
			cn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public Persona obtenerDatosPersona(String dni){
		Persona p=null;
		Datos datos=null;
		Localizacion loc=null;
		Adicionales ad=null;
		Puntuacion punt=null;
		try {
			// Establecer conexión con BD
			Connection cn = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+nombredb, user, password);

			// 2.1 Enviar instrucción SQL datos
			Statement st = cn.createStatement();
			String sql = "select * from datos where dni='"+dni+"'";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()){
				datos=new Datos(rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getBoolean(8),null);
			}
			
			// 2.2 Enviar instrucción SQL localizacion
			String sql2 = "select * from localizacion where dni='"+dni+"'";
			ResultSet rs2 = st.executeQuery(sql2);
			while (rs2.next()){
				loc=new Localizacion(rs2.getString(2),rs2.getDouble(3),rs2.getDouble(4));
			}
						
			// 2.3 Enviar instrucción SQL adicionales
			String sql3 = "select * from adicionales where dni='"+dni+"'";
			ResultSet rs3 = st.executeQuery(sql3);
			while (rs3.next()){
				ad=new Adicionales(rs3.getString(2),rs3.getString(3),rs3.getString(4));
			}
			
			// 2.4 Enviar instrucción SQL puntuacion
			String sql4 = "select * from puntuacion where dni='"+dni+"'";
			ResultSet rs4 = st.executeQuery(sql4);
			//Atencion!!!!
			int total=0;
			double suma=0;
			ArrayList<String> comentarios=new ArrayList<>();
			while (rs4.next()){
				suma=suma+rs4.getDouble(4);
				total++;
				String s=rs4.getString(3);
				System.out.println(s);
				comentarios.add(s);
			}
			punt=new Puntuacion(dni,comentarios,suma/total);
			// 4. Cierre de conexión
			cn.close();
			p=new Persona(datos,loc,ad,punt);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		
		return p;
	}

	public boolean comprobarInicioSesion(Datos datos){
		Boolean res=false;
		try {
			// 1. establecer conexión con BD
			Connection cn = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+nombredb, user, password);
			// 2. Enviar instrucción SQL
			Statement st = cn.createStatement();
			String sql = "select * from datos where (email='" + datos.getEmail() + "' and contrasena='"+datos.getContrasena()+"')";
			// 3. Manipulacion resultados
			ResultSet rs = st.executeQuery(sql);
			
			if (rs.next()) {
				res = true;
			}
			// 4. cierre conexión
			cn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return res;
	}
}
