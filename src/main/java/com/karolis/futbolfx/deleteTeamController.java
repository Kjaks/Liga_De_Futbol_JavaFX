package com.karolis.futbolfx;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author kjaks
 */
public class deleteTeamController {
     private PrimaryController primaryController;
    
    public void setPrimaryController(PrimaryController primaryController) {
        this.primaryController = primaryController;
    }
     SoccerFile dataFile = new SoccerFile();
    
    @FXML
    private TextField deleteTeamName;
    private String name, teamText;
    int result;
    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @FXML
    private void delete(){    
    
    name = deleteTeamName.getText();
    
    result = dataFile.delete(name);
    
    if (name.equals("")){
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Escribe el nombre de un equipo!");
        // Mostrar la alerta como un pop-up
        alert.showAndWait();
    }
    else if(result == -1){    
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("El equipo " + name + " no existe!");
        // Mostrar la alerta como un pop-up
        alert.showAndWait();
    } else{
        deleteTeamName.clear();
        PrimaryController.getInstance().refreshTable();
        alert.setTitle("Borrado completado!");
        alert.setHeaderText(null);
        alert.setContentText("Equipo borrado con exito!");
        // Mostrar la alerta como un pop-up
        alert.showAndWait();
    }
    
    }
}
