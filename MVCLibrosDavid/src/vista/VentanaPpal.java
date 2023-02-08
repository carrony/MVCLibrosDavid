package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPpal extends JFrame {

	private JPanel contentPane;
	private Controlador controlador;



	/**
	 * Create the frame.
	 */
	public VentanaPpal() {
		setTitle("Base de datos Bilioteca");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][]", "[][][][]"));
		
		JButton btnMostrarEd = new JButton("Mostrar Editoriales");
		btnMostrarEd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.mostrarEditoriales();
			}
		});
		contentPane.add(btnMostrarEd, "cell 1 1");
		
		JButton btnInsertarEd = new JButton("Insertar Editorial");
		btnInsertarEd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.mostrarNuevaEditorial();
			}
		});
		
		JButton btnMostrarLibros = new JButton("Mostrar Libros");
		btnMostrarLibros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.mostrarLibros();
			}
		});
		contentPane.add(btnMostrarLibros, "cell 3 1");
		contentPane.add(btnInsertarEd, "cell 1 3");
		
		JButton btnInsertarLibro = new JButton("Insertar Libro");
		btnInsertarLibro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.mostrarNuevoLibro();
			}
		});
		contentPane.add(btnInsertarLibro, "cell 3 3");
	}



	public void setControlador(Controlador controlador) {
		this.controlador=controlador;
		
	}



}
