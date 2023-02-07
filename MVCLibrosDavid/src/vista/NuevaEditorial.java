package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import modelo.Editorial;
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

public class NuevaEditorial extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtAño;
	private Controlador controlador;
	private Editorial editorial;
	private JLabel lblTitulo;


	/**
	 * Create the frame.
	 */
	public NuevaEditorial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[44.00][][][][][][][grow]"));
		
		lblTitulo = new JLabel("Inserción de Editoriales");
		lblTitulo.setOpaque(true);
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBackground(Color.BLACK);
		lblTitulo.setFont(new Font("Verdana", Font.BOLD, 20));
		contentPane.add(lblTitulo, "cell 0 0 2 1,grow");
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		contentPane.add(lblNewLabel_1, "cell 0 2");
		
		txtNombre = new JTextField();
		contentPane.add(txtNombre, "cell 1 2,growx");
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Año");
		contentPane.add(lblNewLabel_2, "cell 0 4,alignx trailing");
		
		txtAño = new JTextField();
		contentPane.add(txtAño, "cell 1 4,growx");
		txtAño.setColumns(10);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panel, "cell 0 7 2 1,grow");
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarEditorial();

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
	
	protected void insertarEditorial() {
		try {
			String nombre =txtNombre.getText();
			int anio = Integer.parseInt(txtAño.getText());
			
			Editorial ed = new Editorial();
			ed.setNombre(nombre);
			ed.setAnio(anio);
			
			if (this.editorial==null) {
				controlador.insertarEditorial(ed);
			} else {
				ed.setCodEditorial(editorial.getCodEditorial());
				controlador.actualizarEditorial(ed);
			}
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, 
					"Introduzca un año corecto");
		}
		
		
	}

	public void setControlador(Controlador controlador) {
		this.controlador=controlador;
	}

	public void setEditorial(Editorial e) {
		this.editorial=e;
		if (e!=null) {
			lblTitulo.setText("Modificar Editorial");
			this.txtNombre.setText(e.getNombre());
			this.txtAño.setText(""+e.getAnio());
		}else {
			lblTitulo.setText("Insertar Editorial");
			this.txtNombre.setText("");
			this.txtAño.setText("");
		}
		
	}

}
