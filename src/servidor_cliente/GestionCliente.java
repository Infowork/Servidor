package servidor_cliente;

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
import javabeans.Valoracion;

public class GestionCliente {
	private String nombredb="tuprofesional";
    private String ip="localhost";
    private String port="3306";
    private String user="root";
    private String password="root";

    //Obtiene los datos de la persona a partir del parametro dni
	private Persona obtenerDatosPersona(String dni){
		Persona p=null;
		Datos datos=null;
		Localizacion loc=null;
		Adicionales ad=null;
		Puntuacion punt=null;
		System.out.println(dni);
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
			System.out.println(datos);
			
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
			int total=0;
			double suma=0;
			ArrayList<String> comentarios=new ArrayList<>();
			while (rs4.next()){
				suma=suma+rs4.getDouble(4);
				total++;
				String s=rs4.getString(3);
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

	private ArrayList<String> obtenerDnis(String cat){
		ArrayList<String> lista=new ArrayList<>();
		try {
			// Establecer conexión con BD
			Connection cn = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+nombredb, user, password);

			// 2.1 Enviar instrucción SQL datos
			Statement st = cn.createStatement();
			String sql = "select * from empleados where empleo='"+cat+"'";
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()){
				String dni=rs.getString(3);
				lista.add(dni);
			}
			
			// 4. Cierre de conexión
			cn.close();
		
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return lista;
	}
	
	
	public ArrayList<Persona> obtenerDatosCategoria (String cat){
		ArrayList<Persona> listapers=new ArrayList<>();
		ArrayList<String> listadnis=obtenerDnis(cat);
		for(int i=0;i<listadnis.size();i++){
			Persona p=obtenerDatosPersona(listadnis.get(i));
			listapers.add(p);
		}
		return listapers;
	}
	
	public void guardarValoracion(Valoracion val){
		try {
			// Establecer conexión con BD
			Connection cn = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+nombredb, user, password);

			// 2.2 Enviar instrucción SQL localizacion
			Statement st = cn.createStatement();
			String sql2 = "insert into puntuacion(dni,review,puntuacion)";
			sql2 += " values('" + val.getDni() + "','" + val.getTexto() + "'," + val.getPuntuacion() + ")";
			st.execute(sql2);
						
			// 4. Cierre de conexión
			cn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	//Obtiene los datos de la persona a partir de alguno de los parametros del Javabean datos.
	public Persona obtenerPersonaPorParametros(Datos dat){
		
		String dni=null;
		try {
			// Establecer conexión con BD
			Connection cn = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+nombredb, user, password);

			// 2.2 Enviar instrucción SQL localizacion
			Statement st = cn.createStatement();
			String sql = "select * from datos where (empresa='"+dat.getEmpresa()+"' or nombre='"+dat.getNombre()+"' "
					+ "or telefono="+dat.getTelefono()+" or email='"+dat.getEmail()+"')";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				dni=rs.getString(1);
			}
			
				
			// 4. Cierre de conexión
			cn.close();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return obtenerDatosPersona(dni);
	
	}
}
