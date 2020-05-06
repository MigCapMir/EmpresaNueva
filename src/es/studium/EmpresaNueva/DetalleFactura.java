package es.studium.EmpresaNueva;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;

public class DetalleFactura extends Frame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;
	int idFactura;
	Label lblFactura = new Label("Factura Nº");
	Label lblTotal = new Label("Total");
	TextField txtTotal = new TextField(8);
	TextArea txaListado = new TextArea(5,50);
	Button btnAceptar = new Button("Aceptar");
	Button btnExportar = new Button("Exportar PDF");
	int idFacturaFK;
	BaseDatos bd = new BaseDatos();
	Connection conexion = null;
	String[] cadena;
	Double total = 0.0;

	public DetalleFactura(int idFactura)
	{
		this.idFactura = idFactura;
		setTitle("Detalle Factura");
		setLayout(new FlowLayout());
		lblFactura.setText("Factura Nº "+idFactura);
		add(lblFactura);
		// Conectar a la BD
		conexion = bd.conectar();
		// Sacar los datos de la factura concreta
		cadena = bd.consultarFactura(conexion, idFactura).split("-");
		// cadena[0] = fechaFactura
		// cadena[1] = nombreCliente
		// cadena[2] = totalFactura
		total = Double.parseDouble(cadena[2]);
		// Sacar los detalles de la factura concreta
		txaListado.setText("Fecha:"+cadena[0]+"\n"+"Cliente:"+cadena[1]+"\n"+bd.consultarDetallesFactura(conexion, idFactura));
		// SELECT * FROM lineasFactura, servicios
		// Los idServicioFK y las cantidades correspondientes
		// Por cada idServicioFK, tengo que sacar la descripcion y el precio
		add(txaListado);
		add(lblTotal);
		txtTotal.setText(total+"");
		txtTotal.setEnabled(false);
		add(txtTotal);
		btnAceptar.addActionListener(this);
		btnExportar.addActionListener(this);
		add(btnAceptar);
		add(btnExportar);
		addWindowListener(this);
		setSize(400,300);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		// Desconectar
		bd.desconectar(conexion);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(btnAceptar))
		{
			setVisible(false);
		}
		else
		{
			// Exportar a PDF
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
