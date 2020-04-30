package es.studium.EmpresaNueva;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;

public class BajaCliente extends Frame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;
	Choice listado = new Choice();
	Button btnAceptar = new Button("Aceptar");
	Button btnLimpiar = new Button("Limpiar");
	BaseDatos bd = new BaseDatos();
	Connection conexion = null;
	Dialog dlgMensaje = new Dialog(this,"¿Está seguro?", true);
	Label mensaje = new Label("¿Está seguro/a de eliminar este cliente?");
	Button btnSi = new Button("Sí");
	Button btnNo = new Button("No");
	String[] cadena;
	Dialog dlgMensajeFeedback = new Dialog(this,"Mensaje", true);
	Label mensajeFeedback = new Label("");
	int idClienteBorrar = 0;

	public BajaCliente()
	{
		setTitle("Baja Cliente");
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
	public void actionPerformed(ActionEvent e)
	{
		if(btnLimpiar.equals(e.getSource()))
		{
			listado.select(0);
		}
		else if(btnAceptar.equals(e.getSource()))
		{
			if(listado.getSelectedItem().equals("Seleccionar un cliente..."))
			{

			}
			else
			{
				// Coger el elemento seleccionado
				String[] tabla = listado.getSelectedItem().split("-");
				// El idCliente que quiero borrar está en tabla[0]
				idClienteBorrar = Integer.parseInt(tabla[0]);
				dlgMensaje.setSize(180,120);
				dlgMensaje.setLayout(new FlowLayout());
				dlgMensaje.addWindowListener(this);
				dlgMensaje.add(mensaje);
				btnSi.addActionListener(this);
				btnNo.addActionListener(this);
				dlgMensaje.add(btnSi);
				dlgMensaje.add(btnNo);
				dlgMensaje.setLocationRelativeTo(null);
				dlgMensaje.setVisible(true);
			}
		}
		else if(btnSi.equals(e.getSource()))
		{
			// Conectar BD
			conexion = bd.conectar();
			// Ejecutar DELETE
			if((bd.borrarCliente(conexion, idClienteBorrar))==0)
			{
				// Todo bien
				mensajeFeedback.setText("Baja de Cliente correcta");
				dlgMensajeFeedback.setTitle("Baja Cliente");
				dlgMensajeFeedback.setSize(180,120);
				dlgMensajeFeedback.setLayout(new FlowLayout());
				dlgMensajeFeedback.addWindowListener(this);
				dlgMensajeFeedback.add(mensajeFeedback);
				dlgMensajeFeedback.setLocationRelativeTo(null);
				dlgMensajeFeedback.setVisible(true);
			}
			else
			{
				// Error
				mensajeFeedback.setText("Error en Baja de Cliente");
				dlgMensajeFeedback.setTitle("Baja Cliente");
				dlgMensajeFeedback.setSize(180,120);
				dlgMensajeFeedback.setLayout(new FlowLayout());
				dlgMensajeFeedback.addWindowListener(this);
				dlgMensajeFeedback.add(mensajeFeedback);
				dlgMensajeFeedback.setLocationRelativeTo(null);
				dlgMensajeFeedback.setVisible(true);
			}
			// Desconectar BD
			bd.desconectar(conexion);
		}
		else // btnNo
		{
			dlgMensaje.setVisible(false);
		}

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
		if(dlgMensaje.isActive())
		{
			dlgMensaje.setVisible(false);
		}
		else if(dlgMensajeFeedback.isActive())
		{
			dlgMensajeFeedback.setVisible(false);
		}
		else
		{
			setVisible(false);
		}
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
