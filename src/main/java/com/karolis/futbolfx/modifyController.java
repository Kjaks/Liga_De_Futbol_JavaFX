package com.karolis.futbolfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author kjaks
 */
public class modifyController implements Initializable{
    private PrimaryController primaryController;
    
    public void setPrimaryController(PrimaryController primaryController) {
        this.primaryController = primaryController;
    }
    
   SoccerFile dataFile = new SoccerFile();    
   searchTeamController teamInfo = new searchTeamController();
   Alert alert = new Alert(Alert.AlertType.INFORMATION);
   
   @FXML
   private TextField newTeamName, newPlayedMatches, newWinnedMatches, newDrawMatches;
       
   private String[] oldTeam = teamInfo.getTeam();
   private int result = 0, pM, wM, dM, lM, pts;
   private String name, input;
              
   @FXML
    private Label oldName, oldPlayed, oldWins, oldDraw;
       
       @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(this.oldName != null){
        oldName.setText(oldName.getText() +oldTeam[0]) ;
        oldPlayed.setText(oldPlayed.getText() +oldTeam[1]); 
        oldWins.setText(oldWins.getText() +oldTeam[2]);
        oldDraw.setText(oldDraw.getText() +oldTeam[3]);
        }
    }

    @FXML
    private void modifyFormButton(){
       int result = -1;
       boolean nuevo = false;
      
      // Nombre del equipo
      name = newTeamName.getText();
      if (name.equals("")) name = oldTeam[0];
      else{
          nuevo = true;
      }
      
      // Partidos jugados
      input = newPlayedMatches.getText();
      if (input.equals("")) {
          pM = Integer.parseInt(oldTeam[1]);
      }  // No cambiamos los datos antiguos
      else  {
          pM= Integer.parseInt(input);
          nuevo = true;
      }

      // Partidos ganados (no pueden ser más que los partidos jugados)
        input = newWinnedMatches.getText();
        
        if (input.equals("")) {
            wM = Integer.parseInt(oldTeam[2]);
        }
        else if(Integer.parseInt(input) < 1){
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Los partidos ganados no pueden ser menor que 1!");
        wM = Integer.parseInt(oldTeam[2]);
        // Mostrar la alerta como un pop-up
        alert.showAndWait();
        }
        else if ( Integer.parseInt(input) >pM){
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Los partidos ganados no pueden ser mas que los partidos jugados!");
        wM = Integer.parseInt(oldTeam[2]);
        // Mostrar la alerta como un pop-up
        alert.showAndWait();
        }
        else {
            System.out.println("PARTIDOS JUGADOS: " + pM);
            wM = Integer.parseInt(input);
            nuevo = true;
        }
      
      // Partidos empatados (no pueden ser más que los partidos jugados menos los ganados)
        input = newDrawMatches.getText();
        if (input.equals("")) {
            dM = Integer.parseInt(oldTeam[3]);
        }
        else if(Integer.parseInt(input) < 1){
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Los partidos empatados no pueden ser menor que 1!");
        dM = Integer.parseInt(oldTeam[3]);
        // Mostrar la alerta como un pop-up
        alert.showAndWait();
        }
        else if (Integer.parseInt(input) > (wM - pM)){
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Los partidos Empatados no pueden ser mayores que los partidos ganados menos los jugados!");
        dM = Integer.parseInt(oldTeam[3]);
        // Mostrar la alerta como un pop-up
        alert.showAndWait();
        }
        else {
            dM = Integer.parseInt(input);
            nuevo = true;
        }

      // Partidos perdidos (no se preguntan sino que se calculan)
      lM = pM - (wM + dM);
      
      // Puntos. Se calcula como partGanados * 3 + partEmpatados
      pts = wM * 3 + dM;

      // Enviamos los datos nuevos para que se escriban en el fichero
      result = dataFile.update(dataFile.getPos(oldTeam[0]), name, pM, wM, dM, lM, pts);
      Stage currentStage = (Stage) newTeamName.getScene().getWindow();
      if (result == -1) {
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Ha ocurrido un error al actualizar el fichero!");
        // Mostrar la alerta como un pop-up
        alert.showAndWait();      
         currentStage.close();
      }
      else if (nuevo){
        alert.setTitle("INFO");
        alert.setHeaderText(null);
        alert.setContentText("Datos actualizados con exito!");
        PrimaryController.getInstance().refreshTable();
        // Mostrar la alerta como un pop-up
        alert.showAndWait();      
         currentStage.close();

      }      
    }    
}