package es.studium.EmpresaNueva;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MenuPrincipal extends Frame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;
	MenuBar menuBar = new MenuBar();
	Menu clientes = new Menu("Clientes");
	MenuItem altaCliente = new MenuItem("Alta");
	MenuItem consultaCliente = new MenuItem("Consulta");
	MenuItem edicionCliente = new MenuItem("Edición");
	MenuItem bajaCliente = new MenuItem("Baja");
	
	Menu servicios = new Menu("Servicios");
	MenuItem altaServicio = new MenuItem("Alta");
	MenuItem consultaServicio = new MenuItem("Consulta");
	MenuItem edicionServicio = new MenuItem("Edición");
	MenuItem bajaServicio = new MenuItem("Baja");
	
	Menu facturas = new Menu("Facturas");
	MenuItem altaFactura = new MenuItem("Alta");
	MenuItem consultaFactura = new MenuItem("Consulta");
	
	Menu ayuda = new Menu("Ayuda");
	MenuItem mniAyuda = new MenuItem("Ayuda");
	
	public MenuPrincipal()
	{
		setTitle("Empresa Nueva");
		setLayout(new FlowLayout());
		altaCliente.addActionListener(this);
		consultaCliente.addActionListener(this);
		edicionCliente.addActionListener(this);
		bajaCliente.addActionListener(this);
		clientes.add(altaCliente);
		clientes.add(consultaCliente);
		clientes.add(edicionCliente);
		clientes.add(bajaCliente);
		altaServicio.addActionListener(this);
		consultaServicio.addActionListener(this);
		edicionServicio.addActionListener(this);
		bajaServicio.addActionListener(this);
		servicios.add(altaServicio);
		servicios.add(consultaServicio);
		servicios.add(edicionServicio);
		servicios.add(bajaServicio);
		altaFactura.addActionListener(this);
		consultaFactura.addActionListener(this);
		facturas.add(altaFactura);
		facturas.add(consultaFactura);
		mniAyuda.addActionListener(this);
		ayuda.add(mniAyuda);
		
		menuBar.add(clientes);
		menuBar.add(servicios);
		menuBar.add(facturas);
		menuBar.add(ayuda);
		
		setMenuBar(menuBar);
		
		addWindowListener(this);
		setSize(400,300);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new MenuPrincipal();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		Object pulsado = arg0.getSource();
		if(pulsado.equals(consultaCliente))
		{
			new ConsultaCliente();
		}
		else if(pulsado.equals(altaCliente))
		{
			new AltaCliente();
		}
		else if(pulsado.equals(bajaCliente))
		{
			new BajaCliente();
		}
		else if(pulsado.equals(edicionCliente))
		{
			new EdicionCliente();
		}
		else if(pulsado.equals(altaFactura))
		{
			new AltaFactura();
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
		System.exit(0);
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
