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

public class PrimaryController implements Initializable,Runnable {
    @FXML
    private Label lblTextos;

    private ServerSocket servidor;
    private boolean serverRunning = true;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Inicia el servidor en un hilo separado para no bloquear la interfaz de usuario.
        new Thread(this).start();
    }

    private void startServer() {
        try {
            servidor = new ServerSocket(9998);
            Platform.runLater(() -> lblTextos.setText("Esperando mensajes..."));

            while (serverRunning) {
                Socket miSocket = servidor.accept();
                DataInputStream flujoEntrada = new DataInputStream(miSocket.getInputStream());
                String mensajeTexto = flujoEntrada.readUTF();
                Platform.runLater(() -> lblTextos.setText(mensajeTexto));
                miSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopServer() {
        serverRunning = false;
        try {
            if (servidor != null) {
                servidor.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void run() {
		startServer();		
	}
}

