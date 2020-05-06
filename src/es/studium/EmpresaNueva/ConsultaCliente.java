package es.studium.EmpresaNueva;

import java.awt.Button;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

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

	public void actionPerformed(ActionEvent ae)
	{
		// Se crea el documento 
		Document documento = new Document();
		try 
		{ 
			// Se crea el OutputStream para el fichero donde queremos dejar el pdf. 
			FileOutputStream ficheroPdf = new FileOutputStream("Clientes.pdf");
			PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(22);
			// Se abre el documento. 
			documento.open();
			Paragraph titulo = new Paragraph("Informe de Clientes", 
					FontFactory.getFont("arial", // fuente 
							22, // tamaño 
							Font.ITALIC, // estilo 
							BaseColor.BLUE)); // color
			titulo.setAlignment(Element.ALIGN_CENTER);
			documento.add(titulo);
			// Sacar los datos
			conexion = bd.conectar();
			String[] cadena = bd.consultarClientes(conexion).split("\n");
			bd.desconectar(conexion);
			PdfPTable tabla = new PdfPTable(3); // Se indica el número de columnas
			tabla.setSpacingBefore(5); // Espaciado ANTES de la tabla
			tabla.addCell("#");
			tabla.addCell("Nombre Cliente");
			tabla.addCell("Fecha Alta");
			// En cada posición de cadena tenemos un registro completo
			// cadena[0] = "1-Fulanito-12/12/2020"
			String[] subCadena;
			// En subCadena, separamos cada campo por -
			// subCadena[0] = 1
			// subCadena[1] = Fulanito
			// subCadena[2] = 12/12/2020
			for (int i = 0; i < cadena.length; i++) 
			{
				subCadena = cadena[i].split("-");
				for(int j = 0; j < 3;j++)
				{
					tabla.addCell(subCadena[j]);
				}
			}
			documento.add(tabla); 
			documento.close(); 
			//Abrimos el archivo PDF recién creado 
			try 
			{
				File path = new File ("Clientes.pdf"); 
				Desktop.getDesktop().open(path); 
			}
			catch (IOException ex) 
			{
				System.out.println("Se ha producido un error al abrir el archivo PDF"); 
			}
		}
		catch ( Exception e ) 
		{ 
			System.out.println("Se ha producido un error al generar el archivo PDF");  
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
