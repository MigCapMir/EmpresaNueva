package es.studium.EmpresaNueva;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AltaFactura extends Frame implements WindowListener, ActionListener 
{
	private static final long serialVersionUID = 1L;
	Label lblCliente = new Label("Cliente");
	Label lblFecha = new Label("Fecha");
	Choice listado = new Choice();
	TextField txtFecha = new TextField(10);
	Button btnAceptar = new Button("Aceptar");
	Button btnLimpiar = new Button("Limpiar");
	BaseDatos bd = new BaseDatos();
	Date fecha;
	Connection conexion = null;
	String[] cadena;

	public AltaFactura()
	{
		setTitle("Alta Factura 1/2");
		setLayout(new GridLayout(3,2));
		add(lblCliente);
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
		add(lblFecha);
		// Añadir fecha de hoy
		fecha = new Date();
		txtFecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(fecha));
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
		// Desconectar
		bd.desconectar(conexion);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(btnLimpiar.equals(arg0.getSource()))
		{
			listado.select(0);
			fecha = new Date();
			txtFecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(fecha));
		}
		else if(btnAceptar.equals(arg0.getSource()))
		{
			// Conectar BD
			conexion = bd.conectar();
			// Hacer INSERT
			String[] idClienteFK = listado.getSelectedItem().split("-");
			String[] fechaAmericana = txtFecha.getText().split("/");
			String sentencia = "INSERT INTO facturas VALUES(null,'"+fechaAmericana[2]+"-"+fechaAmericana[1]+"-"+fechaAmericana[0]+"',0,"+idClienteFK[0]+")";
			// Feedback
			int idFacturaFK = bd.altaFactura(conexion, sentencia);
			if(idFacturaFK!=0)
			{
				listado.select(0);
				fecha = new Date();
				txtFecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(fecha));
				// Redirección
				new AltaFactura2(idFacturaFK);
			}
			else
			{
				// Mensaje de error
				System.out.println("Incorrecto");
			}
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
		// TODO Auto-generated method stub
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
