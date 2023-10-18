package co.edu.uniquindio.programacionIII.socketsTryServer.controllers;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class PrimaryController implements Runnable, Initializable {
	//para que la clas este siempre a la escucha podemos implementar la interface de Runnable

    @FXML
    private Label lblTextos;
    
    //Se crea el hilo correspondiente
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	Platform.runLater(this);
		
	}
 
	@Override
	public void run() {
		//Podemos probar este Hilo con un syso y con solo cargar la aplicacion deberia de implimir algo en consola
		//System.out.println("Oeeee");
		
		//Una vez comprobado el hilo podemos ya pasar a la escucha con el puesto necesario
		
		try {
			ServerSocket servidor= new ServerSocket(9998);
			System.out.println("conexion hecha");
			//Ahora lo que hay que hacer es decirle que acepte cualquier conexion que le llegue del exterior
			Socket miSocket= servidor.accept();
			System.out.println("socket aceptado");
			//Ahora debemos hacer un flujo de entratda con el socked que se acaba de recibir
			DataInputStream flujoEntrada= new DataInputStream(miSocket.getInputStream());
			System.out.println("flujo generado");
			//Ahora debemos saber leer que nos llega en el flujo de entrada
			String mensajeTexto= flujoEntrada.readUTF();
			System.out.println("texto que llego: "+mensajeTexto);
			//Y ahora se procede a escribirse en su el textArea
			lblTextos.setText(mensajeTexto);
			//cerramos la conexion
			miSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
    
    
    
    

}
