package com.karolis.futbolfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author kjaks
 */
public class searchTeamController implements Initializable{
    SoccerFile dataFile = new SoccerFile();
    
    @FXML
    private TextField SearchTeamName, selectTeamName;
    
    @FXML
    private Label oldName, oldPlayed, oldWins, oldDraw;
    
    private static String[] team;
    private static String name, teamText;

    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @FXML
    private void search(){    
    
    name = SearchTeamName.getText();
    
    teamText = dataFile.get(name);
    
    if (name.equals("")){
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Escribe el nombre de un equipo!");
        // Mostrar la alerta como un pop-up
        alert.showAndWait();
    }
    else if(teamText == null){    
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("El equipo " + name + " no existe!");
        // Mostrar la alerta como un pop-up
        alert.showAndWait();
    } else{
        team = teamText.split(";");
        SearchTeamName.clear();
        alert.setTitle("Informacion Equipo");
        alert.setHeaderText(null);
        alert.setContentText(
                "Nombre del equipo: " + team[0] 
                + "\nPartidos Jugados: " + team[1] 
                + "\nPartidos Ganados: " + team[2] 
                + "\nPartidos Empatados: " + team[3] 
                + "\nPartidos perdidos: " + team[4] 
                + "\nPuntos: " + team[5]);
        // Mostrar la alerta como un pop-up
        alert.showAndWait();
    }
    
    }
    
    @FXML
    private void modifyTeamButton() throws IOException {
    name = selectTeamName.getText();

     teamText = dataFile.get(name);

    if (name.equals("")) {
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Escribe el nombre de un equipo!");
        // Mostrar la alerta como un pop-up
        alert.showAndWait();
    } else if (teamText == null) {    
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("El equipo " + name + " no existe!");
        // Mostrar la alerta como un pop-up
        alert.showAndWait();
    } else {
        // Obtener el Stage de la ventana actual
        Stage currentStage = (Stage) selectTeamName.getScene().getWindow();

        // Cargar el archivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("modifyTeam.fxml"));
        Parent root = loader.load();
        
        // Crear la escena
        Scene scene = new Scene(root);
        
        // Obtener el escenario principal
        Stage stage = new Stage();
        
        // Establecer la escena en el escenario principal y mostrarlo
        stage.setScene(scene);
        stage.show();
        
        // Cerrar la ventana actual
        currentStage.close();
    } 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(this.oldName != null){
         team = teamText.split(";");

        oldName.setText("Nombre anterior: " + team[0]) ;
        oldPlayed.setText("Partidos jugados anteriores: " + team[1]); 
        oldWins.setText("Partidos Ganados anteriores: " + team[2]);
        oldDraw.setText("Partidos empatados anteriores: " + team[3]);
        }
    }
}
