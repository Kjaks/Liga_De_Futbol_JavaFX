package com.karolis.futbolfx;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private void addButton() throws IOException {
        // Cargar el archivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addTeam.fxml"));
        Parent root = loader.load();
        
        // Crear la escena
        Scene scene = new Scene(root);
        
        // Obtener el escenario principal
        Stage stage = new Stage();
        
        // Establecer la escena en el escenario principal y mostrarlo
        stage.setScene(scene);
        stage.show();
    }
}