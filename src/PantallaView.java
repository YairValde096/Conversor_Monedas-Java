import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PantallaView extends JPanel {
	/**
	 * Primera versión
	 */
	private static final long serialVersionUID = -3518306982796354534L;
	private JLabel imagenIzquierda;
	private JLabel imagenDerecha;
    public PantallaView() {
    	
    	//PRUEBA CONFIGURACION SPINNERS
		double initialValue = 1.0;
		double minValue = 0.0;
		double maxValue = 10000.0;
		double stepSize = 0.5;

		SpinnerModel model = new SpinnerNumberModel(initialValue, minValue, maxValue, stepSize);
		SpinnerModel model2 = new SpinnerNumberModel(initialValue, minValue, maxValue, stepSize);
        // Crear los paneles de la columna izquierda y derecha
        JPanel panelIzquierdo = new JPanel(new GridBagLayout());
        JPanel panelDerecho = new JPanel(new GridBagLayout());
        JComboBox<String> boxIzq = new JComboBox<String>();
        JComboBox<String> boxDer = new JComboBox<String>();
        MySQLConnector conexion = new MySQLConnector();
        new Generador_img_pais();
        JSpinner entradaNum = new JSpinner(model);
        JSpinner salidaNum = new JSpinner(model2);
        



        
        //imagen = img.generarImagenPais;
        
        //PRUEBA PARA LA FECHA
        
        String fechaActual = Fecha.fechaActual();
        String fechaSistema = Fecha.sistemaFecha();
        JLabel fechahoy = new JLabel("Fecha de hoy: " + fechaActual, SwingConstants.CENTER);
        JLabel fechaLabel = new JLabel("Fecha de actualización de divisas: " + fechaSistema, SwingConstants.CENTER);
        
        //Se ingresan los datos en las columnas.
        for (String dato : Modificador_BD.obtenercolumna(conexion,"moneda","nombre_moneda")) {
        	boxIzq.addItem(dato);
        	boxDer.addItem(dato);
        }
        //FIN PRUEBAS 
        //Se crean los listeners
        boxIzq.addActionListener(actualiza(true,boxIzq, boxDer, entradaNum, salidaNum,conexion));
        boxDer.addActionListener(actualiza(false,boxIzq, boxDer, entradaNum, salidaNum,conexion));
        
        int indexIzq = boxIzq.getSelectedIndex()+1;
        int indexDer = boxDer.getSelectedIndex()+1;
        String ISO =Modificador_BD.obtenerElemento(conexion,"pais" ,"Codigo_ISO" ,indexIzq+"" );
        String ISO2 =Modificador_BD.obtenerElemento(conexion,"pais" ,"Codigo_ISO" ,indexDer+"" );
        imagenIzquierda = new JLabel(Modifica_imagenes.obtenerImagen(ISO));
        imagenDerecha = new JLabel(Modifica_imagenes.obtenerImagen(ISO2));
        // Agregar elementos al panel izquierdo
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth =2;
        panelIzquierdo.add(imagenIzquierda, gbc);  //Posteriormente cambiar a una imagen dinámica
        gbc.gridwidth =1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        panelIzquierdo.add(new JLabel("De"), gbc);
        
        gbc.gridy = 1;
        gbc.gridx = 1;       
        panelIzquierdo.add(boxIzq, gbc);
        
        gbc.gridy = 2;
        gbc.gridx = 0;
        panelIzquierdo.add(new JLabel("Cantidad"), gbc);
        
        gbc.gridy = 2;
        gbc.gridx = 1;        
        panelIzquierdo.add(entradaNum, gbc);
        
        
        // Agregar elementos al panel derecho
        gbc.insets = new Insets(5,5,5,5);
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth =2;
        panelDerecho.add(imagenDerecha, gbc); //Posteriormente cambiar a una imagen dinámica
        gbc.gridwidth =1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        panelDerecho.add(new JLabel("A"), gbc);
        
        gbc.gridy = 1;
        gbc.gridx = 1;
        panelDerecho.add(boxDer, gbc);
        
        gbc.gridy = 2;
        gbc.gridx = 0;
        panelDerecho.add(new JLabel("Cantidad"), gbc);
        
        gbc.gridy = 2;
        gbc.gridx = 1; 
        panelDerecho.add(salidaNum, gbc);
        
        // Agregar los paneles a la vista principal
        setLayout(new GridLayout(2, 2));
        add(fechahoy);
        add(panelIzquierdo);
        add(fechaLabel);
        add(panelDerecho);
        

    }
    private ActionListener actualiza(boolean validador, JComboBox<String> boxIzq, JComboBox<String> boxDer, JSpinner entradaNum, JSpinner salidaNum, MySQLConnector conexion) {
    	MySQLConnector conecta = conexion;
    	return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double cantidadEntrada = (double) entradaNum.getValue();
                double cantidadSalida = (double) salidaNum.getValue();
                int indexIzq = boxIzq.getSelectedIndex()+1;
                int indexDer = boxDer.getSelectedIndex()+1;
                
               String ISO =Modificador_BD.obtenerElemento(conexion,"pais" ,"Codigo_ISO" ,indexIzq+"" );
               String ISO2 =Modificador_BD.obtenerElemento(conexion,"pais" ,"Codigo_ISO" ,indexDer+"" );
               ImageIcon nuevaImagen = Modifica_imagenes.obtenerImagen(ISO);
               imagenIzquierda.setIcon(nuevaImagen);
               nuevaImagen =Modifica_imagenes.obtenerImagen(ISO2);
               imagenDerecha.setIcon(nuevaImagen);
               if(validador) {
            	   try {
            	conecta.openConnection();	  
            	   double valorUSD = conecta.getData("costo","Valor_Costo", indexDer);
            	   double valorDestino = conecta.getData("costo","Valor_Costo", indexIzq);
            	   valorDestino = (valorUSD*cantidadEntrada)/valorDestino;
            	   System.out.println(valorDestino);
            	   salidaNum.setValue(valorDestino);
            	   } catch (SQLException e1) {
					System.out.println("Se falló al hacer la operacion desde la fuente");
					e1.printStackTrace();
				}
            	   finally {conecta.closeConnection();}
               }
               else {
            	   try {
            		   conecta.openConnection();
                	   double valorUSD = conecta.getData("costo","Valor_Costo",indexIzq );
                	   double valorDestino = conecta.getData("costo","Valor_Costo", indexDer);
                	   valorDestino = (valorUSD*cantidadSalida)/valorDestino;
                	   System.out.println(valorDestino);
                	   entradaNum.setValue(valorDestino);
                	   } catch (SQLException e1) {
    					System.out.println("Se falló al hacer la operacion desde el destino");
    					e1.printStackTrace();
    				}
                	   finally {conecta.closeConnection();}
                   }
            	   
               }
            };
        };


}