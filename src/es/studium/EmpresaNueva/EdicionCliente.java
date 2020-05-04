package es.studium.EmpresaNueva;

import java.awt.Button;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;

public class EdicionCliente extends Frame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;
	Choice listado = new Choice();
	Button btnAceptar = new Button("Aceptar");
	Button btnLimpiar = new Button("Limpiar");
	BaseDatos bd = new BaseDatos();
	Connection conexion = null;
	String[] cadena;
	int idClienteEditar = 0;
	
	public EdicionCliente()
	{
		setTitle("Editar Cliente");
		setLayout(new FlowLayout());
		// Rellenar el Choice
		listado.add("Seleccionar un cliente...");
		// Conectar BD
		conexion = bd.conectar();
		cadena = (bd.consultarClientesChoice(conexion)).split("#");
		for(int i = 0; i < cadena.length; i++)
		{
			listado.add(cadena[i]);
		}
		add(listado);
		btnAceptar.addActionListener(this);
		btnLimpiar.addActionListener(this);
		add(btnAceptar);
		add(btnLimpiar);
		addWindowListener(this);
		setSize(400,300);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(btnLimpiar.equals(arg0.getSource()))
		{
			listado.select(0);
		}
		else if(btnAceptar.equals(arg0.getSource()))
		{
			if(listado.getSelectedItem().equals("Seleccionar un cliente..."))
			{
				// No hacemos nada
			}
			else
			{
				// Coger el elemento seleccionado
				String[] tabla = listado.getSelectedItem().split("-");
				// El idCliente que quiero editar está en tabla[0]
				idClienteEditar = Integer.parseInt(tabla[0]);
				new ModificarCliente(idClienteEditar); 
			}
		}
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
