package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.ConexionBD;
import modelo.Editorial;

public class EditorialDAO {

	private ConexionBD conexion;
	
	
	public EditorialDAO() {
		this.conexion = new ConexionBD();
	}
	
	/**
	 * Método de la clase DAO que recupera todas las editoriales
	 * de la Base de datos y las añade a un arralist de editoriales
	 * @return
	 */
	public ArrayList<Editorial> obtenerEditoriales() {
		// Obtiene un objeto connection para conectar a la base
		// de datos
		Connection con = conexion.getConexion();
		Statement consulta = null;
		ResultSet resultado = null;
		ArrayList<Editorial> lista = new ArrayList<Editorial>();
		
		try {
			consulta= con.createStatement();
			resultado = consulta.executeQuery(
					"select * from editoriales"
					);
			while(resultado.next()) {
				int codEditorial = resultado.getInt("codEditorial");
				String nombre = resultado.getString("nombre");
				int anio = resultado.getInt("anio");
				
				Editorial ed = new Editorial(codEditorial,nombre,anio);
				lista.add(ed);
			}

		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta: "+
								e.getMessage());
		} finally {
			try {
				resultado.close();
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "
									+e.getMessage());
			}
		}
		return lista;
	}
	
	
	/**
	 * Método de la clase DAO que recupera la editorial con el código
	 * pasado por parámetro
	 * @return
	 */
	public Editorial obtenerEditorial(int codEditorial) {
		// Obtiene un objeto connection para conectar a la base
		// de datos
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		ResultSet resultado = null;
		Editorial ed=null;
		
		try {
			consulta= con.prepareStatement(
					"select * from editoriales "
					+ "where codEditorial=?");
			consulta.setInt(1, codEditorial);
			resultado = consulta.executeQuery();
					
					
			if(resultado.next()) {
				String nombre = resultado.getString("nombre");
				int anio = resultado.getInt("anio");
				
				ed = new Editorial(codEditorial,nombre,anio);

			}

		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta: "+
								e.getMessage());
		} finally {
			try {
				resultado.close();
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "
									+e.getMessage());
			}
		}
		return ed;
	}
	
	/**
	 * Método de la clase DAO que recupera todas las editoriales
	 * que se han creado despues del año especificado como parámetro
	 * @return
	 */
	public ArrayList<Editorial> obtenerEditorialesAPartirDe(
			int anioDesde, String letra) {
		// Obtiene un objeto connection para conectar a la base
		// de datos
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		ResultSet resultado = null;
		ArrayList<Editorial> lista = new ArrayList<Editorial>();
		
		try {
			consulta= con.prepareStatement(
					"select nombre, anio  from editoriales "
					+ "where anio>? and nombre like '?%' ");
			consulta.setInt(1, anioDesde);
			consulta.setString(2, letra);

			resultado = consulta.executeQuery();
			while(resultado.next()) {
				int codEditorial = resultado.getInt("codEditorial");
				String nombre = resultado.getString("nombre");
				int anio = resultado.getInt("anio");
				
				Editorial ed = new Editorial(codEditorial,nombre,anio);
				lista.add(ed);
			}

		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta: "+
								e.getMessage());
		} finally {
			try {
				resultado.close();
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "
									+e.getMessage());
			}
		}
		return lista;
	}
	
	/*
	 * Inserta una nueva editorial con los datos del objeto pasado 
	 * como parámetro
	 */
	public int insertarEditorial(Editorial editorial) {
		// Obtiene un objeto connection para conectar a la base
		// de datos
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado=0;
		
		try {
			consulta= con.prepareStatement(
					"insert into editoriales (nombre,anio) "
					+ "values(?,?)");
			
			consulta.setString(1, editorial.getNombre());
			consulta.setInt(2, editorial.getAnio());
			resultado = consulta.executeUpdate();
					

		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta: "+
								e.getMessage());
		} finally {
			try {
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "
									+e.getMessage());
			}
		}
		return resultado;
	}
	
	/*
	 * Modifica una editorial con los datos del objeto pasado 
	 * como parámetro, cambiando el nombre ye l año
	 */
	public int actualizarEditorial(Editorial editorial) {
		// Obtiene un objeto connection para conectar a la base
		// de datos
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado=0;
		
		try {
			consulta= con.prepareStatement( 
					"update editoriales "
					+ "set nombre=?, anio=? "
					+ "where codEditorial=?");
			
			consulta.setString(1, editorial.getNombre());
			consulta.setInt(2, editorial.getAnio());
			consulta.setInt(3, editorial.getCodEditorial());
			resultado = consulta.executeUpdate();
					

		} catch (SQLException e) {
			System.out.println("Error al realizar la actualización: "+
								e.getMessage());
		} finally {
			try {
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "
									+e.getMessage());
			}
		}
		return resultado;
	}
	
	/**
	 * Método que borra la editorial pasada como parámetro
	 * @param editorial
	 * @return
	 */
	public int eliminarEditorial(int codEditorial) {
		// Obtiene un objeto connection para conectar a la base
		// de datos
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado=0;
		
		try {
			consulta= con.prepareStatement( 
					"delete from editoriales "
					+ "where codEditorial=?");
			

			consulta.setInt(1, codEditorial);
			resultado = consulta.executeUpdate();
					

		} catch (SQLException e) {
			System.out.println("Error al realizar la eliminación: "+
								e.getMessage());
		} finally {
			try {
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "
									+e.getMessage());
			}
		}
		return resultado;
	}
}
