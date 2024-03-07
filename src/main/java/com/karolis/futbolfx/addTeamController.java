package com.karolis.futbolfx;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

/**
 * Controller class for adding a team.
 * @author kjaks
 */
public class addTeamController {
    private PrimaryController primaryController;

    /**
     * Sets the primary controller. So we can use the refreshTable method
     * @param primaryController The primary controller to set.
     */
    public void setPrimaryController(PrimaryController primaryController) {
        this.primaryController = primaryController;
    }

    @FXML
    private TextField teamName, playedMatches, winnedMatches, drawMatches;

    Alert alert = new Alert(AlertType.INFORMATION);
    private String name;
    private int played, won, draw, lost, points;

    SoccerFile dataFile = new SoccerFile();

      /**
     * Handles the action of adding a team.
     * @throws IOException if an I/O error occurs.
     */
    @FXML
    private void add() throws IOException {
        name = teamName.getText();

        try {
            /**
             * Read the values from the text fields
             */
            played = Integer.parseInt(playedMatches.getText());
            won = Integer.parseInt(winnedMatches.getText());
            draw = Integer.parseInt(drawMatches.getText());
        } catch (NumberFormatException e) {
            /**
             * Handle the case where one of the values is not a valid integer
             */
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("¡Debes completar el formulario!");
            /**
             * Show the alert as a pop-up
             */
            alert.showAndWait();
            /**
             * Exit the method if an exception occurs
             */
            return;
        }

        if (name.isEmpty()) {
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Debes ponerle un nombre al equipo!");
            alert.showAndWait();
        } else if (played < 0 || won < 0 || draw < 0) {
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Ningún número puede ser negativo!");
            alert.showAndWait();
        } else if (won > played) {
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("El número de partidos ganados no puede ser mayor que el de partidos jugados!");
            alert.showAndWait();
        } else if (draw > (played - won)) {
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Los partidos empatados no pueden ser más que los partidos jugados menos los ganados!");
            alert.showAndWait();
        } else {
            
            /**
             * Calculate lost matches
             */
            lost = played - won - draw;
            
            /**
             *  Points. Calculated as wonMatches * 3 + drawnMatches
             */
            points = won * 3 + draw;

            /**
             *  Write all data to the file
             */
            int result = dataFile.save(name, played, won, draw, lost, points);
            if (result == 0) {
                teamName.clear();
                playedMatches.clear();
                winnedMatches.clear();
                drawMatches.clear();
                alert.setTitle("Equipo añadido!");
                alert.setHeaderText(null);
                alert.setContentText("Equipo añadido con éxito!");

                PrimaryController.getInstance().refreshTable();

                alert.showAndWait();
            } else {
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Error al guardar el equipo");
                alert.showAndWait();
            }
        }
    }
}
