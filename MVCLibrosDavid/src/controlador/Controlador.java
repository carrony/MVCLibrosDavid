package controlador;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import dao.EditorialDAO;
import dao.LibroDAO;
import modelo.Editorial;
import modelo.Libro;
import vista.DialogoEditoriales;
import vista.DialogoLibros;
import vista.NuevaEditorial;
import vista.VentanaPpal;

public class Controlador {

	// objetos de la vista, las ventanas de la aplicación
	private VentanaPpal ventanaPpal;
	private NuevaEditorial nuevaEditorial;
	private DialogoEditoriales dialogoEditoriales;
	private DialogoLibros dialogoLibros;
	
	// objetos DAO para el acceso a bases de datos
	private EditorialDAO editorialDAO;
	private LibroDAO libroDAO;
	
	public Controlador() {
		// instanciamos las ventanas de la aplicación
		this.ventanaPpal = new VentanaPpal();
		this.nuevaEditorial = new NuevaEditorial();
		this.dialogoEditoriales = new DialogoEditoriales();
		this.dialogoLibros = new DialogoLibros();
		
		// Establecemos los controladores para las vistas
		this.ventanaPpal.setControlador(this);
		this.nuevaEditorial.setControlador(this);
		this.dialogoEditoriales.setControlador(this);
		this.dialogoLibros.setControlador(this);
		
		// instanciamos los DAO
		editorialDAO = new EditorialDAO();
		libroDAO = new LibroDAO();
	}
	
	public void iniciarPrograma() {
		this.ventanaPpal.setVisible(true);
	}

	public void mostrarEditoriales() {
		ArrayList<Editorial> lista = 
				editorialDAO.obtenerEditoriales();
		this.dialogoEditoriales.setListaEditoriales(lista);
		this.dialogoEditoriales.setVisible(true);
	}

	public void mostrarNuevaEditorial() {
		this.nuevaEditorial.setEditorial(null);
		this.nuevaEditorial.setVisible(true);
	}
	
	public void mostrarActualizarEditorial(int codEitorial) {
		Editorial e = editorialDAO.obtenerEditorial(codEitorial);
		this.nuevaEditorial.setEditorial(e);
		this.nuevaEditorial.setVisible(true);
		
	}
	
	public void mostrarLibros() {
		ArrayList<Libro> lista = 
				libroDAO.obtenerLibros();
		this.dialogoLibros.setListaLibros(lista);
		this.dialogoLibros.setVisible(true);
	}

	public void insertarEditorial(Editorial ed) {
		int res = editorialDAO.insertarEditorial(ed);
		if (res==0) {
			JOptionPane.showMessageDialog(nuevaEditorial, 
					"Error no se ha podido insertar");
		} else {
			JOptionPane.showMessageDialog(nuevaEditorial, 
					"Editorial añadida correctamente");
			nuevaEditorial.setVisible(false);
		}
	}

	

	public void actualizarEditorial(Editorial ed) {
		int res = editorialDAO.actualizarEditorial(ed);
		if (res==0) {
			JOptionPane.showMessageDialog(nuevaEditorial,
					"Error. No se ha podido actualizar");
		} else {
			JOptionPane.showMessageDialog(nuevaEditorial,
					"Editorial actualizada correctamente");
			this.nuevaEditorial.setVisible(false);
		}
		mostrarEditoriales();

	}

	public void eliminarEditorial(int codEditorial) {
		int res = editorialDAO.eliminarEditorial(codEditorial);
		if (res==0) {
			JOptionPane.showMessageDialog(nuevaEditorial,
					"Error. No se ha podido eliminar");
		} else {
			JOptionPane.showMessageDialog(nuevaEditorial,
					"Editorial eliminada correctamente");
			this.nuevaEditorial.setVisible(false);
		}
		mostrarEditoriales();
		
	}
	
	
	
}
