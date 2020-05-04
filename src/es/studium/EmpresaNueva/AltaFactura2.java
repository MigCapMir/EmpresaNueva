package es.studium.EmpresaNueva;

import java.awt.Button;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;

public class AltaFactura2 extends Frame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;
	Label lblFactura = new Label("Factura Nº");
	Label lblServicio = new Label("Servicio");
	Label lblCantidad = new Label("Cantidad");
	Label lblTotal = new Label("Total");
	Choice listado = new Choice();
	TextField txtCantidad = new TextField(5);
	TextField txtTotal = new TextField(8);
	TextArea txaListado = new TextArea(5,50);
	Button btnAgregar = new Button("Agregar");
	Button btnFinalizar = new Button("Finalizar");
	Panel p1 = new Panel();
	Panel p2 = new Panel();
	Panel p3 = new Panel();
	Panel p4 = new Panel();
	Panel p5 = new Panel();
	int idFacturaFK;
	BaseDatos bd = new BaseDatos();
	Connection conexion = null;
	String[] cadena;
	Double total = 0.0;

	public AltaFactura2(int idFacturaFK)
	{
		this.idFacturaFK = idFacturaFK;
		setTitle("Alta Factura 2/2");
		setLayout(new GridLayout(5,1));
		p1.setLayout(new FlowLayout());
		lblFactura.setText("Factura Nº "+idFacturaFK);
		p1.add(lblFactura);
		add(p1);
		p2.setLayout(new GridLayout(1,2));
		p2.add(lblServicio);
		// Rellenar el Choice
		listado.add("Seleccionar un servicio...");
		// Conectar BD
		conexion = bd.conectar();
		cadena = (bd.consultarServiciosChoice(conexion)).split("#");
		for(int i = 0; i < cadena.length; i++)
		{
			listado.add(cadena[i]);
		}
		p2.add(listado);
		add(p2);
		p3.setLayout(new GridLayout(1,3));
		p3.add(lblCantidad);
		p3.add(txtCantidad);
		btnAgregar.addActionListener(this);
		btnFinalizar.addActionListener(this);
		p3.add(btnAgregar);
		add(p3);
		p4.setLayout(new FlowLayout());
		p4.add(txaListado);
		add(p4);
		p5.setLayout(new GridLayout(1,3));
		p5.add(btnFinalizar);
		p5.add(lblTotal);
		txtTotal.setEnabled(false);
		p5.add(txtTotal);
		add(p5);
		addWindowListener(this);
		setSize(500,300);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		// Desconectar
		bd.desconectar(conexion);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(btnFinalizar.equals(e.getSource()))
		{
			// UPDATE de total
			String sentencia = "UPDATE facturas SET totalFactura = "+total+" WHERE idFactura = "+idFacturaFK;
			// Conectar BD
			conexion = bd.conectar();
			if(bd.actualizarFactura(conexion, sentencia)==0)
			{
				// Se ha hecho OK
			}
			else
			{
				// Error
			}
			bd.desconectar(conexion);
			setVisible(false);
		}
		else if(btnAgregar.equals(e.getSource()))
		{
			if((listado.getSelectedItem().equals("Seleccionar un servicio..."))||(txtCantidad.getText().equals("")))
			{
				// No hacemos nada
			}
			else
			{
				// Coger el servicio seleccionado

				String[] seleccionado = listado.getSelectedItem().split("-");
				// seleccionado[0] = idServicio
				// seleccionado[1] = descripcionServicio
				// seleccionado[2] = precioServicio
				// Coger la cantidad escrita
				int cantidad = Integer.parseInt(txtCantidad.getText());
				// Insertamos en el TextArea
				txaListado.setText(txaListado.getText()+"\n"+seleccionado[1]+" "+seleccionado[2]+" "+cantidad+" "+cantidad*Double.parseDouble(seleccionado[2]));
				// Calculamos el Subtotal (cantidad*precio)
				// Calcular Total
				total = total + cantidad*Double.parseDouble(seleccionado[2]);
				txtTotal.setText(total+"");
				// Alta en tabla lineasFactura
				String sentencia = "INSERT INTO lineasFactura VALUES("+idFacturaFK+","+seleccionado[0]+","+cantidad+")";
				// Conectar BD
				conexion = bd.conectar();
				if(bd.altaLineaFactura(conexion, sentencia)==0)
				{}
				else
				{
					// Error
					// Mostrar diálogo
				}
				bd.desconectar(conexion);
				// Resetear
				listado.select(0);
				txtCantidad.selectAll();
				txtCantidad.setText("");
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
