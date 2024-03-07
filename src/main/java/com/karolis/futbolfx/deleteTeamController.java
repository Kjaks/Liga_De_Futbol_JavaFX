package com.karolis.futbolfx;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * Controller class for deleting a team.
 * Handles the deletion of a team from the system.
 * @author kjaks
 */
public class deleteTeamController {
    private PrimaryController primaryController;
    
    /**
     * Sets the primary controller. So we can use the refreshTable method
     * @param primaryController The primary controller to set.
     */
    public void setPrimaryController(PrimaryController primaryController) {
        this.primaryController = primaryController;
    }
    
    SoccerFile dataFile = new SoccerFile();
    
    @FXML
    private TextField deleteTeamName;
    private String name, teamText;
    int result;
    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    /**
     * Deletes a team from the system.
     * Reads the team name from the input field and  delete the corresponding team.
     */
    @FXML
    private void delete(){    
        name = deleteTeamName.getText();
        
        result = dataFile.delete(name);
        
        if (name.equals("")){
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Escribe el nombre de un equipo!");
            alert.showAndWait();
        }
        else if(result == -1){    
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("El equipo " + name + " no existe!");
            alert.showAndWait();
        } else{
            deleteTeamName.clear();
            PrimaryController.getInstance().refreshTable();
            alert.setTitle("Borrado completado!");
            alert.setHeaderText(null);
            alert.setContentText("Equipo borrado correctamente!");
            alert.showAndWait();
        }
    }
}
