package com.karolis.futbolfx;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class addTeamController {
    @FXML
    private TextField teamName, playedMatches, winnedMatches, drawMatches ;

    @FXML
    private void add() throws IOException {
        
        String name = teamName.getText();
        
        try {
        // Leer los valores de los TextField
        int played = Integer.parseInt(playedMatches.getText());
        int winned = Integer.parseInt(winnedMatches.getText());
        int draw = Integer.parseInt(drawMatches.getText());

        // Hacer algo con los valores leídos
        System.out.println("Valor 1: " + played);
        System.out.println("Valor 2: " + winned);
        System.out.println("Valor 3: " + draw);

    } catch (NumberFormatException e) {
        // Manejar el caso en el que uno de los valores no sea un número entero válido
        System.out.println("Error: uno de los valores no es un número entero válido.");
    }

    }
    
}