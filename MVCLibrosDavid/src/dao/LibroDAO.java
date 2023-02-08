package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.ConexionBD;
import modelo.Editorial;
import modelo.Libro;

public class LibroDAO {

	private ConexionBD conexion;
	
	
	public LibroDAO() {
		this.conexion = new ConexionBD();
	}
	
	/**
	 * Método de la clase DAO que recupera todas las editoriales
	 * de la Base de datos y las añade a un arralist de libros
	 * @return
	 */
	public ArrayList<Libro> obtenerLibros() {
		// Obtiene un objeto connection para conectar a la base
		// de datos
		Connection con = conexion.getConexion();
		Statement consulta = null;
		ResultSet resultado = null;
		ArrayList<Libro> lista = new ArrayList<Libro>();
		
		try {
			consulta= con.createStatement();
			resultado = consulta.executeQuery(
					"select * from libros"
					);
			while(resultado.next()) {
				String isbn = resultado.getString("isbn");
				String titulo = resultado.getString("titulo");
				int codEditorial = resultado.getInt("codEditorial");
				int anio = resultado.getInt("anio");
				int numPags = resultado.getInt("num_pags");
				float precio = resultado.getInt("precio");
				int cantidad = resultado.getInt("cantidad");
				float precioCD = resultado.getInt("precioCD");
				
				Libro l = new Libro(isbn, titulo, codEditorial, anio, numPags,
						precio, cantidad, precioCD);
				lista.add(l);
			}

		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta de libros: "+
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
	 * Método de la clase DAO que recupera el libro con el isbn
	 * pasado por parámetro
	 * @return
	 */
	public Libro obtenerLibro(String isbn) {
		// Obtiene un objeto connection para conectar a la base
		// de datos
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		ResultSet resultado = null;
		Libro l=null;
		
		try {
			consulta= con.prepareStatement(
					"select * from libros "
					+ "where isbn=?");
			consulta.setString(1, isbn);
			resultado = consulta.executeQuery();
					
					
			if(resultado.next()) {
				String titulo = resultado.getString("titulo");
				int codEditorial = resultado.getInt("codEditorial");
				int anio = resultado.getInt("anio");
				int numPags = resultado.getInt("num_pags");
				float precio = resultado.getInt("precio");
				int cantidad = resultado.getInt("cantidad");
				float precioCD = resultado.getInt("precioCD");
				
				l = new Libro(isbn, titulo, codEditorial, anio, numPags,
						precio, cantidad, precioCD);

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
		return l;
	}
	
	
	
	/*
	 * Inserta una nuevo libro con los datos del objeto pasado 
	 * como parámetro
	 */
	public int insertarLibro(Libro libro) {
		// Obtiene un objeto connection para conectar a la base
		// de datos
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado=0;
		String sql="";
		try {
			 sql= "insert into libros (isbn,titulo, codEditorial,"
			 		+" anio, num_pags, precio, cantidad, precioCD) "
					+ "values(?,?,?,?,?,?,?,?)";
			consulta= con.prepareStatement(sql);
			
			consulta.setString(1,libro.getIsbn());
			consulta.setString(2,libro.getTitulo());
			consulta.setInt(3,libro.getCodEditorial());
			consulta.setInt(4, libro.getAnio());
			consulta.setInt(5,libro.getNumPags());
			consulta.setFloat(6,libro.getPrecio());
			consulta.setInt(7,libro.getCantidad());
			consulta.setFloat(8,libro.getPrecioCD());

			
	
			resultado = consulta.executeUpdate();
					

		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta: "+
								e.getMessage());
			System.out.println(libro);
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
	 * Modifica una libro con los datos del objeto pasado 
	 * como parámetro, cambiando el nombre ye l año
	 */
	public int actualizarLibro(Libro libro) {
		// Obtiene un objeto connection para conectar a la base
		// de datos
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado=0;
		
		try {
			consulta= con.prepareStatement( 
					"update libros "
					+ "set titulo=?, codEditorial=?, anio=?, num_pags=?,"
					+ " precio=?, cantidad =?, precioCD= ? "
					+ "where isbn=?");
			
			
			consulta.setString(1,libro.getTitulo());
			consulta.setInt(2,libro.getCodEditorial());
			consulta.setInt(3, libro.getAnio());
			consulta.setInt(4,libro.getNumPags());
			consulta.setFloat(5,libro.getPrecio());
			consulta.setInt(6,libro.getCantidad());
			consulta.setFloat(7,libro.getPrecioCD());
			consulta.setString(8,libro.getIsbn());
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
	 * Método que borra el libro pasada como parámetro
	 * @param editorial
	 * @return
	 */
	public int eliminarLibro(String isbn) {
		// Obtiene un objeto connection para conectar a la base
		// de datos
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado=0;
		
		try {
			consulta= con.prepareStatement( 
					"delete from libros "
					+ "where isbn=?");
			

			consulta.setString(1, isbn);
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
