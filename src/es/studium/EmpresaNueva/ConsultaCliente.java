package es.studium.EmpresaNueva;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;

public class ConsultaCliente extends Frame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;
	TextArea listado = new TextArea(13,40);
	Button exportarPdf = new Button("Exportar PDF");
	BaseDatos bd = new BaseDatos();
	Connection conexion = null;
	
	public ConsultaCliente()
	{
		setTitle("Consulta Clientes");
		setLayout(new FlowLayout());
		// Conectar con la BD
		conexion = bd.conectar();
		listado.setText(bd.consultarClientes(conexion));
		bd.desconectar(conexion);
		add(listado);
		exportarPdf.addActionListener(this);
		add(exportarPdf);
		addWindowListener(this);
		setSize(400,300);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		setVisible(false);
	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	

}
