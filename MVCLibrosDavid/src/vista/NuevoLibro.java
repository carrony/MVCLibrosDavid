package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import modelo.Editorial;
import modelo.Libro;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class NuevoLibro extends JFrame {

	private JPanel contentPane;
	private JTextField txtISBN;
	private JTextField txtTitulo;
	private Controlador controlador;
	private Editorial editorial;
	private JLabel lblTitulo;
	private JTextField txtCodEditorial;
	private JTextField txtNumPags;
	private JTextField txtPrecio;
	private JTextField txtPrecioCD;
	private JSpinner spinnerAnio;
	private JSpinner spinnerCantidad;
	private Object libro;


	/**
	 * Create the frame.
	 */
	public NuevoLibro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 472, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][grow]", "[44.00][][][][][][][][][][][][grow]"));
		
		lblTitulo = new JLabel("Inserción de Libro");
		lblTitulo.setOpaque(true);
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBackground(Color.BLACK);
		lblTitulo.setFont(new Font("Verdana", Font.BOLD, 20));
		contentPane.add(lblTitulo, "cell 1 0 2 1,grow");
		
		JLabel lblNewLabel_1 = new JLabel("ISBN:");
		contentPane.add(lblNewLabel_1, "cell 1 2,alignx right");
		
		txtISBN = new JTextField();
		contentPane.add(txtISBN, "cell 2 2,growx");
		txtISBN.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Título:");
		contentPane.add(lblNewLabel_2, "cell 1 4,alignx trailing");
		
		txtTitulo = new JTextField();
		contentPane.add(txtTitulo, "cell 2 4,growx");
		txtTitulo.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Cod. Editorial:");
		contentPane.add(lblNewLabel, "cell 1 6,alignx right");
		
		txtCodEditorial = new JTextField();
		contentPane.add(txtCodEditorial, "cell 2 6,growx");
		txtCodEditorial.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Año:");
		contentPane.add(lblNewLabel_3, "cell 1 7,alignx right");
		
		spinnerAnio = new JSpinner();
		spinnerAnio.setModel(new SpinnerNumberModel(Integer.valueOf(2023), Integer.valueOf(1900), null, Integer.valueOf(1)));
		contentPane.add(spinnerAnio, "cell 2 7");
		
		JLabel lblNewLabel_4 = new JLabel("Num. Págs:");
		contentPane.add(lblNewLabel_4, "cell 1 8,alignx trailing,aligny center");
		
		txtNumPags = new JTextField();
		contentPane.add(txtNumPags, "cell 2 8,growx");
		txtNumPags.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Precio:");
		contentPane.add(lblNewLabel_5, "cell 1 9,alignx trailing");
		
		txtPrecio = new JTextField();
		contentPane.add(txtPrecio, "cell 2 9,growx");
		txtPrecio.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Cantidad:");
		contentPane.add(lblNewLabel_6, "cell 1 10,alignx right");
		
		spinnerCantidad = new JSpinner();
		spinnerCantidad.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		contentPane.add(spinnerCantidad, "cell 2 10");
		
		JLabel lblNewLabel_7 = new JLabel("PrecioCD:");
		contentPane.add(lblNewLabel_7, "cell 1 11,alignx trailing");
		
		txtPrecioCD = new JTextField();
		contentPane.add(txtPrecioCD, "cell 2 11,growx");
		txtPrecioCD.setColumns(10);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panel, "cell 1 12 2 1,grow");
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarLibro();

			}
		});
		panel.add(okButton);
		
		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		panel.add(cancelButton);
	}
	
	protected void insertarLibro() {
		try {
			String nombre =txtISBN.getText();
			String titulo = txtTitulo.getText();
			int codEditorial = 
					Integer.parseInt(txtCodEditorial.getText());
			int anio = (int) spinnerAnio.getValue();
			int numPaginas = 
					Integer.parseInt(txtNumPags.getText());
			float precio = Float.parseFloat(txtPrecio.getText());
			
			float precioCD = 
					Float.parseFloat(txtPrecioCD.getText());
			int cantidad = (int) spinnerCantidad.getValue();
			
			Libro l = new Libro(nombre, titulo, codEditorial, anio, numPaginas, precio, cantidad, precioCD);
			
			if (this.libro==null) {
				controlador.insertarLibro(l);
			} else {
				controlador.actualizarLibro(l);
			}
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, 
					"Introduzca un año correcto");
		}
		
		
	}

	public void setControlador(Controlador controlador) {
		this.controlador=controlador;
	}

	public void setLibro(Libro l) {
		this.libro=l;
		if (l!=null) {
			lblTitulo.setText("Modificar Libro");
			this.txtISBN.setText(l.getIsbn());
			this.txtTitulo.setText(l.getTitulo());
			this.txtCodEditorial.setText(""+l.getCodEditorial());
			this.spinnerAnio.setValue(l.getAnio());
			this.txtNumPags.setText(""+l.getNumPags());
			this.txtPrecio.setText(""+l.getPrecio());
			this.spinnerCantidad.setValue(l.getCantidad());
			this.txtPrecioCD.setText(""+l.getPrecioCD());
		}else {
			lblTitulo.setText("Insertar Libro");
			this.txtISBN.setText("");
			this.txtTitulo.setText("");
			this.txtCodEditorial.setText("");
			this.spinnerAnio.setValue(2023);
			this.txtNumPags.setText("");
			this.txtPrecio.setText("");
			this.spinnerCantidad.setValue(1);
			this.txtPrecioCD.setText("");
		}
		
	}

}
