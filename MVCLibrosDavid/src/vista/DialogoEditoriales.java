package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.Editorial;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogoEditoriales extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private Controlador controlador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogoEditoriales dialog = new DialogoEditoriales();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogoEditoriales() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[][grow]"));
		{
			JLabel lblNewLabel = new JLabel("Listado de Editoriales");
			lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 16));
			contentPanel.add(lblNewLabel, "cell 0 0");
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "cell 0 1,grow");
			{
				table = new JTable();
				table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"C\u00F3digo Editorial", "Nombre", "A\u00F1o"
					}
				) {
					Class[] columnTypes = new Class[] {
						Integer.class, String.class, Integer.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
					boolean[] columnEditables = new boolean[] {
						false, false, false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
				table.getColumnModel().getColumn(0).setPreferredWidth(95);
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						llamarActualizar();
					}
				});
				{
					JButton btnEliminar = new JButton("Eliminar");
					btnEliminar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							llamarEliminar();
						}
					});
					buttonPane.add(btnEliminar);
				}
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
			{
				JButton btnCerrar = new JButton("Cerrar");
				btnCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				btnCerrar.setActionCommand("Cancel");
				buttonPane.add(btnCerrar);
			}
		}
	}

	protected void llamarEliminar() {
		int fila= table.getSelectedRow();
		if (fila==-1) {
			JOptionPane.showMessageDialog(this, 
					"Debe seleccionar una editorial");
			return;
		}
		DefaultTableModel modelo = (DefaultTableModel) 
				table.getModel();
		int codEditorial= (int) modelo.getValueAt(fila, 0);
		controlador.eliminarEditorial(codEditorial);
		
	}

	protected void llamarActualizar() {
		int fila= table.getSelectedRow();
		if (fila==-1) {
			JOptionPane.showMessageDialog(this, 
					"Debe seleccionar una editorial");
			return;
		}
		DefaultTableModel modelo = (DefaultTableModel) 
				table.getModel();
		int codEditorial= (int) modelo.getValueAt(fila, 0);
		controlador.mostrarActualizarEditorial(codEditorial);
	}

	public void setControlador(Controlador controlador) {
		this.controlador=controlador;
	}

	public void setListaEditoriales(ArrayList<Editorial> lista) {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);
		for(Editorial editorial: lista) {
			Object [] fila = {
					editorial.getCodEditorial(),
					editorial.getNombre(),
					editorial.getAnio()
			};
			modelo.addRow(fila);
		}
		
	}

}
