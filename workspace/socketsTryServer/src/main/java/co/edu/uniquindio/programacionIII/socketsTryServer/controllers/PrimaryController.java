package co.edu.uniquindio.programacionIII.socketsTryServer.controllers;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;

public class PrimaryController implements Runnable {
	//para que la clas este siempre a la escucha podemos implementar la interface de Runnable

    @FXML
    private Label lblTextos;
    
    //Se crea el hilo correspondiente
    
    public PrimaryController() {
    	Thread miHilo = new Thread(this);
    	miHilo.start();
    }
 
	@Override
	public void run() {
		//Podemos probar este Hilo con un syso y con solo cargar la aplicacion deberia de implimir algo en consola
		//System.out.println("Oeeee");
		
		//Una vez comprobado el hilo podemos ya pasar a la escucha con el puesto necesario
		
		try {
			ServerSocket servidor= new ServerSocket(9998);
			//Ahora lo que hay que hacer es decirle que acepte cualquier conexion que le llegue del exterior
			Socket miSocket= servidor.accept();
			//Ahora debemos hacer un flujo de entratda con el socked que se acaba de recibir
			DataInputStream flujoEntrada= new DataInputStream(miSocket.getInputStream());
			//Ahora debemos saber leer que nos llega en el flujo de entrada
			String mensajeTexto= flujoEntrada.readUTF();
			//Y ahora se procede a escribirse en su el textArea
			lblTextos.setText(mensajeTexto);
			//cerramos la conexion
			flujoEntrada.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    
    
    
    

}
