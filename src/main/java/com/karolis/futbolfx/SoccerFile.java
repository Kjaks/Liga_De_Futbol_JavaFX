package com.karolis.futbolfx;
/** 
 * SoccerFile Class
 * 
 * This class contains the logic with the data file, that is, all the routines
 * for reading and writing data from the file. It doesn't produce any console output.
 */

import java.io.*;

class SoccerFile {

    private final String NOMBRE_FICH_DATOS = "liga.txt";  // Name of the data file
    public final int MAX_EQUIPOS = 20;                    // Max number of teams in the file

    /**
     * Adds a team to the data file.
     * TODO: Check that no more than 20 teams are ever saved in the file.
     *
     * @param nombreEq Name of the team
     * @param partJug Played matches
     * @param partGan Won matches
     * @param partEmp Drawn matches
     * @param partPer Lost matches
     * @param puntos Team points
     *
     * @return Returns 0 if everything goes well or -1 in case of error.
     */

    public int save(String nombreEq, int partJug, int partGan, int partEmp, int partPer, int puntos) {
        int resultado;
        try {
            FileWriter f = new FileWriter(NOMBRE_FICH_DATOS, true);  // The true is for appending the team to the file
            f.write(nombreEq + ";" + partJug + ";" + partGan + ";" + partEmp + ";" + partPer + ";" + puntos + ";\n");
            f.close();
            resultado = 0;
        }
        catch (Exception e) {
            resultado = -1;
            System.err.println("Error al escribir en el fichero de datos");
        }
        return resultado;
    }

        /**
         * Returns all the content of the data file as an array of Strings
         * (one row of the file in each position of the array)
         *
         * @return An array of Strings with the content of the data file
         */
    public String[] getAll() {
        String[] datos = new String[MAX_EQUIPOS];   // MAX_EQUIPOS is the max number of teams we can inserte in the data file
        try {
            FileReader f = new FileReader(NOMBRE_FICH_DATOS);
            BufferedReader br = new BufferedReader(f);
            String linea;
            int i = 0;
            while ((linea = br.readLine()) != null) {
                datos[i] = linea;
                i++;
            }
        }
        catch (Exception e) {
            System.err.println("Error al leer el fichero de datos");
            datos = null;
        }
        return datos;
    }

    /**
     * Searches for a team in the file based on its name.
     * @return null if not found or a string with the file line if found.
     */
    public String get(String nombreEq) {
        String linea = null;
        boolean encontrado = false;
        try {
            FileReader f = new FileReader(NOMBRE_FICH_DATOS);
            BufferedReader br = new BufferedReader(f);
            while ((linea = br.readLine()) != null) {
                String[] teamInfo = linea.split(";");
                if (teamInfo[0].equals(nombreEq)) {
                    encontrado = true;
                    break;
                }
            }
        }
        catch (Exception e) {
            System.err.println("Error al leer el fichero de datos");
            linea = null;
        }
        if (!encontrado) linea = null;
        return linea;
    }

    /**
     * Searches for a team in the file based on its name and returns its position in the file.
     * @return The position (row) of the team in the data file (if found) or -1 if the team does not exist.
     */
    public int getPos(String nombreEq) {
        String linea = null;
        int posicion = -1;
        int i = 0;
        try {
            FileReader f = new FileReader(NOMBRE_FICH_DATOS);
            BufferedReader br = new BufferedReader(f);
            while ((linea = br.readLine()) != null) {
                String[] teamInfo = linea.split(";");
                if (teamInfo[0].equals(nombreEq)) {
                    posicion = i;
                    break;
                }
                i++;
            }
        }
        catch (Exception e) {
            System.err.println("Error al leer el fichero de datos");
            posicion = -1;
        }
        return posicion;
    }

    /**
     * Deletes a team from the file based on its name.
     * @param nombreEq Name of the team to be deleted.
     * @return 0 if deletion is successful or -1 in case of error.
     */
    public int delete(String nombreEq) {
        int result = -1;              // Result of the erasing
        int pos = getPos(nombreEq);   // We get the position of the team in the data file
        if (pos != -1) {
            try {
                // We are going to copy liga.txt to a temporary file (named temp), except for the row we want to delete
                File fSource = new File(NOMBRE_FICH_DATOS);
                File fDest = new File("temp");
                BufferedReader br = new BufferedReader(new FileReader(fSource)); // Open liga.txt for reading
                FileWriter newFile = new FileWriter(fDest); // Create a temporary file
                int numLinea = 0; // Counter for the number of lines
                String linea = null;
                while ((linea = br.readLine()) != null) {
                    if (numLinea != pos) { // pos is the line we do not want to copy to the new file
                        newFile.write(linea + "\n");
                    }
                    numLinea++;
                }
                // Delete the old file liga.txt and rename temp to be called liga.txt
                br.close();
                newFile.close();
                fSource.delete();
                fDest.renameTo(fSource);
                result = 0;
            }
            catch (Exception e) {
                System.err.println("Error al leer el fichero de datos");
                result = -1;
            }
        }
        return result;
    }

        /**
         * Updates a team in the data file.
         * @param pos Position of the team in the data file
         * @param nombreEq Name of the team
         * @param partJug Played matches
         * @param partGan Won matches
         * @param partEmp Drawn matches
         * @param partPer Lost matches
         * @param puntos Team points
         * @return 0 if the update finishes successfully or -1 in case of error
         */
            public int update(int pos, String nombreEq, int partJug, int partGan, int partEmp, int partPer, int puntos) {
                int result = -1; // Update result
                try {
                    // We are going to copy liga.txt to a temporary file (named temp), changing the row we want to update
                    File fSource = new File(NOMBRE_FICH_DATOS);
                    File fDest = new File("temp");
                    BufferedReader br = new BufferedReader(new FileReader(fSource)); // Open liga.txt for reading
                    FileWriter newFile = new FileWriter(fDest); // Create a temporary file
                    int numLinea = 0; // Counter for the number of lines
                    String linea = null;
                    while ((linea = br.readLine()) != null) {
                        if (numLinea == pos) { // pos is the line we want to modify
                            newFile.write(nombreEq + ";" + partJug + ";" + partGan + ";" + partEmp + ";" + partPer + ";" + puntos + ";\n");
                        } else {
                            newFile.write(linea + "\n");
                        }
                        numLinea++;
                    }
                    // Delete the old file liga.txt and rename temp to be called liga.txt
                    br.close();
                    newFile.close();
                    fSource.delete();
                    fDest.renameTo(fSource);
                    result = 0;
                } catch (Exception e) {
                    System.err.println("Error reading the data file");
                    result = -1;
                }
                return result;
            }

    /**
     * Sorts the data file according to the number of points (from highest to lowest).
     * @return 0 in case of success or -1 if there is any error
     */
    public int sort() {
        int result = -1;

        // Load all data into a String array
        String datos[] = getAll();

        if (datos == null) { // No data to sort
            result = -1;
        } else {
            // Create an array with the points
            int puntos[] = new int[MAX_EQUIPOS];
            for (int i = 0; i < datos.length; i++) {
                if (datos[i] != null) {
                    datos[i] = datos[i].substring(0, datos[i].length() - 1);
                    puntos[i] = Integer.parseInt(datos[i].split(";")[5]);
                } else {
                    puntos[i] = -1;
                }
            }

            // Sort the points array using the bubble sort method.
            // Each time we swap two positions, we also swap the array with all the data
            for (int i = 0; i < datos.length; i++) {
                for (int j = 0; j < datos.length - i - 1; j++) { // correction here
                    if (puntos[j] < puntos[j + 1]) {
                        // Swap points
                        int aux = puntos[j];
                        puntos[j] = puntos[j + 1];
                        puntos[j + 1] = aux;
                        // Swap teams in the data array
                        String auxStr = new String(datos[j]);
                        datos[j] = datos[j + 1];
                        datos[j + 1] = auxStr;
                    }
                }
            }

            // Write the sorted data array to liga.txt, replacing the old data
            try {
                FileWriter f = new FileWriter(NOMBRE_FICH_DATOS);
                for (int i = 0; i < datos.length; i++) {
                    if (datos[i] != null) {
                        f.write(datos[i] + ";\n");
                    }
                }
                result = 0;
                f.close();
            } catch (Exception e) {
                System.err.println("Error writing to the file");
                result = -1;
            }
        }
        return result;
    }

}