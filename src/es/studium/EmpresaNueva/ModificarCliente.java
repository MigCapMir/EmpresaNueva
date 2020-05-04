package es.studium.EmpresaNueva;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;

public class ModificarCliente extends Frame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;
	Label lblNombre = new Label("Nombre");
	Label lblFecha = new Label("Fecha");
	TextField txtNombre = new TextField(10);
	TextField txtFecha = new TextField(10);
	Button btnAceptar = new Button("Aceptar");
	Button btnLimpiar = new Button("Limpiar");
	BaseDatos bd = new BaseDatos();
	Connection conexion = null;
	String[] cadena;
	Dialog dlgMensaje = new Dialog(this,"Mensaje", true);
	Label mensaje = new Label("");
	int idClienteEditar;
	Utilidades utilidad = new Utilidades();
	
	public ModificarCliente(int idClienteEditar)
	{
		this.idClienteEditar = idClienteEditar; 
		// Conectar BD
		conexion = bd.conectar();
		cadena = (bd.consultarCliente(conexion, idClienteEditar)).split("-");
		// cadena[0] = idCliente
		// cadena[1] = nombreCliente
		// cadena[2] = fechaAlta
		setTitle("Modificar Cliente");
		setLayout(new GridLayout(3,2));
		add(lblNombre);
		txtNombre.setText(cadena[1]);
		add(txtNombre);
		add(lblFecha);
		txtFecha.setText(cadena[2]);
		add(txtFecha);
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
			txtNombre.selectAll();
			txtNombre.setText("");
			txtFecha.selectAll();
			txtFecha.setText("");
		}
		else // btnAceptar
		{
			// Hacer UPDATE
			String[] fechaAmericana = txtFecha.getText().split("/");
			String sentencia = "UPDATE clientes SET nombreCliente = '"+txtNombre.getText()+"', fechaAlta = '"+fechaAmericana[2]+"-"+fechaAmericana[1]+"-"+fechaAmericana[0]+"' WHERE idCliente = "+idClienteEditar;
			utilidad.registrarLog("Jorge",sentencia);
			// Feedback
			if((bd.modificarCliente(conexion, sentencia))==0)
			{
				// Todo bien
				mensaje.setText("Modificación de Cliente correcta");
				dlgMensaje.setTitle("Modificar Cliente");
				dlgMensaje.setSize(180,120);
				dlgMensaje.setLayout(new FlowLayout());
				dlgMensaje.addWindowListener(this);
				dlgMensaje.add(mensaje);
				dlgMensaje.setLocationRelativeTo(null);
				dlgMensaje.setVisible(true);
			}
			else
			{
				// Error
				mensaje.setText("Error en Modificación de Cliente");
				dlgMensaje.setTitle("Modificar Cliente");
				dlgMensaje.setSize(180,120);
				dlgMensaje.setLayout(new FlowLayout());
				dlgMensaje.addWindowListener(this);
				dlgMensaje.add(mensaje);
				dlgMensaje.setLocationRelativeTo(null);
				dlgMensaje.setVisible(true);
			}
			// Desconectar
			bd.desconectar(conexion);
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
		if(dlgMensaje.isActive())
		{
			dlgMensaje.setVisible(false);
		}
		else
		{
			setVisible(false);
		}
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

}
