package es.studium.EmpresaNueva;

import java.awt.Button;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;

public class ConsultaFactura extends Frame implements WindowListener, ActionListener, ItemListener
{
	private static final long serialVersionUID = 1L;
	TextArea txaListado = new TextArea(13,40);
	Button exportarPdf = new Button("Exportar PDF");
	Choice choLista = new Choice();
	BaseDatos bd = new BaseDatos();
	Connection conexion = null;
	String[] cadena;
	
	public ConsultaFactura()
	{
		setTitle("Consulta Facturas");
		setLayout(new FlowLayout());
		// Conectar con la BD
		conexion = bd.conectar();
		txaListado.setText(bd.consultarFacturas(conexion));
		add(txaListado);
		exportarPdf.addActionListener(this);
		// Rellenar
		choLista.add("Seleccionar una factura para editar...");
		cadena = (bd.consultarFacturasChoice(conexion)).split("#");
		for(int i = 0; i < cadena.length; i++)
		{
			choLista.add(cadena[i]);
		}
		choLista.addItemListener(this);
		add(choLista);
		bd.desconectar(conexion);
		add(exportarPdf);
		addWindowListener(this);
		setSize(400,300);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0)
	{
		setVisible(false);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemStateChanged(ItemEvent arg0)
	{
		// Averiguar elemento seleccionado
		String[] seleccionado = choLista.getSelectedItem().split("-");
		// seleccionado[0] --> idFactura
		new DetalleFactura(Integer.parseInt(seleccionado[0]));
	}

}
