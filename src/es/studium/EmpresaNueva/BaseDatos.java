package es.studium.EmpresaNueva;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDatos
{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/empresaNueva?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC";
	String login = "remotoEmpresa";
	String password = "Studium2020;";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	public Connection conectar()
	{
		try
		{
			//Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			//Establecer la conexión con la BD Empresa
			connection = DriverManager.getConnection(url, login, password);
			System.out.println("Conectando...");
		}
		catch (ClassNotFoundException cnfe)
		{
			System.out.println("Error 1-"+cnfe.getMessage());
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return connection;
	}

	public String consultarClientes(Connection c)
	{
		String resultado = "";
		String[] fechaEuropea;
		try
		{
			String sentencia = "SELECT * FROM clientes";
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			while (rs.next())
			{
				fechaEuropea = (rs.getString("fechaAlta")).split("-");
				resultado = resultado + rs.getInt("idCliente") + "-" +
						rs.getString("nombreCliente") + "-" +
						fechaEuropea[2]+"/"+fechaEuropea[1]+"/"+fechaEuropea[0]+"\n";
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}

	public int altaCliente(Connection c, String sentencia)
	{
		int resultado = 1;
		try
		{
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			if((statement.executeUpdate(sentencia))==1)
			{
				resultado = 0;
			}
			else
			{
				resultado = 1;
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}

	public String consultarClientesChoice(Connection c)
	{
		String resultado = "";
		String[] fechaEuropea;
		try
		{
			String sentencia = "SELECT * FROM clientes";
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			while (rs.next())
			{
				fechaEuropea = (rs.getString("fechaAlta")).split("-");
				resultado = resultado + rs.getInt("idCliente") + "-" +
						rs.getString("nombreCliente") + "-" +
						fechaEuropea[2]+"/"+fechaEuropea[1]+"/"+fechaEuropea[0]+"#";
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}

	public int borrarCliente(Connection c, int idCliente)
	{
		int resultado = 1;
		try
		{
			String sentencia = "DELETE FROM clientes WHERE idCliente = "+ idCliente;
			//Crear una sentencia
			statement = c.createStatement();
			// Ejecutar la sentencia SQL
			if((statement.executeUpdate(sentencia))==1)
			{
				resultado = 0;
			}
			else
			{
				resultado = 1;
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}

	public String consultarCliente(Connection c, int idCliente)
	{
		String resultado = "";
		String[] fechaEuropea;
		try
		{
			String sentencia = "SELECT * FROM clientes WHERE idCliente="+idCliente;
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			rs.next();
			fechaEuropea = (rs.getString("fechaAlta")).split("-");
			resultado = rs.getInt("idCliente") + "-" +
					rs.getString("nombreCliente") + "-" +
					fechaEuropea[2]+"/"+fechaEuropea[1]+"/"+fechaEuropea[0];
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	
	public int modificarCliente(Connection c, String sentencia)
	{
		int resultado = 1;
		try
		{
			//Crear una sentencia
			statement = c.createStatement();
			// Ejecutar la sentencia SQL
			if((statement.executeUpdate(sentencia))==1)
			{
				resultado = 0;
			}
			else
			{
				resultado = 1;
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	
	public int altaFactura(Connection c, String sentencia)
	{
		int resultado = 0; // INSERT incorrecto
		try
		{
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			if((statement.executeUpdate(sentencia))==1)
			{
				String sentenciaConsulta = "SELECT idFactura FROM facturas ORDER BY 1 DESC LIMIT 1";
				ResultSet rs = statement.executeQuery(sentenciaConsulta);
				if(rs.next())
				{
					resultado = rs.getInt("idFactura");
				}
			}
			else
			{
				resultado = 0;
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	public String consultarServiciosChoice(Connection c)
	{
		String resultado = "";
		try
		{
			String sentencia = "SELECT * FROM servicios ORDER BY 2";
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			while (rs.next())
			{
				resultado = resultado + rs.getInt("idServicio") + "-" +
						rs.getString("descripcionServicio") + "-" +
						rs.getString("precioServicio")+"#";
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	
	public int altaLineaFactura(Connection c, String sentencia)
	{
		int resultado = 0;
		try
		{
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			if((statement.executeUpdate(sentencia))==1)
			{
				resultado = 0;
			}
			else
			{
				resultado = 1;
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return(resultado);
	}
	
	public int actualizarFactura(Connection c, String sentencia)
	{
		int resultado = 0;
		try
		{
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			if((statement.executeUpdate(sentencia))==1)
			{
				resultado = 0;
			}
			else
			{
				resultado = 1;
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return(resultado);
	}
	
	public String consultarFacturas(Connection c)
	{
		String resultado = "";
		String[] fechaEuropea;
		try
		{
			String sentencia = "SELECT idFactura, fechaFactura, nombreCliente FROM facturas, clientes WHERE idCliente = idClienteFK ORDER BY idFactura";
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			while (rs.next())
			{
				fechaEuropea = (rs.getString("fechaFactura")).split("-");
				resultado = resultado + rs.getInt("idFactura") + "-"
						+ fechaEuropea[2]+"/"+fechaEuropea[1]+"/"+fechaEuropea[0]+ "-"+ rs.getString("nombreCliente")+"\n";
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	public String consultarFacturasChoice(Connection c)
	{
		String resultado = "";
		String[] fechaEuropea;
		try
		{
			String sentencia = "SELECT idFactura, fechaFactura, nombreCliente FROM facturas, clientes WHERE idCliente = idClienteFK ORDER BY idFactura";
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			while (rs.next())
			{
				fechaEuropea = (rs.getString("fechaFactura")).split("-");
				resultado = resultado + rs.getInt("idFactura") + "-"
						+ fechaEuropea[2]+"/"+fechaEuropea[1]+"/"+fechaEuropea[0]+ "-"+ rs.getString("nombreCliente")+"#";
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	public String consultarFactura(Connection c, int idFactura)
	{
		String resultado = "";
		String[] fechaEuropea;
		try
		{
			String sentencia = "SELECT idFactura, fechaFactura, totalFactura, nombreCliente FROM facturas, clientes WHERE idFactura = "+idFactura+" AND idCliente = idClienteFK ORDER BY idFactura";
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			rs.next();
			fechaEuropea = (rs.getString("fechaFactura")).split("-");
			resultado = resultado + fechaEuropea[2] + "/" + fechaEuropea[1] + "/" + fechaEuropea[0] + "-"+ rs.getString("nombreCliente") + "-" + rs.getDouble("totalFactura");
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	public String consultarDetallesFactura(Connection c, int idFactura)
	{
		String resultado = "";
		try
		{
			String sentencia = "SELECT cantidad, descripcionServicio, precioServicio, precioServicio*cantidad AS subtotal FROM lineasfactura, servicios WHERE idFacturaFK = "+idFactura+" AND idServicioFK = idServicio";
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			while(rs.next())
			{
				resultado = resultado + rs.getInt("cantidad") + "-" + rs.getString("descripcionServicio") + "-" + rs.getDouble("precioServicio") + "-" + rs.getDouble("subtotal")+"\n";
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	public void desconectar(Connection c)
	{
		try
		{
			if(c!=null)
			{
				c.close();
			}
		}
		catch (SQLException e)
		{
			System.out.println("Error 3-"+e.getMessage());
		}
	}
}
