/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.actividad3;

import java.io.FileReader;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import org.json.JSONException;

/**
 * Esta clase ejecuta el programa principal y contiene métodos para leer y mostrar información de archivos JSON.
 * 
 * @author Fernando
 */
public class Actividad3 {
    
    /**
     * El método principal que inicia el programa.
     * 
     * @param args Argumentos de la línea de comandos. Debe contener uno o dos argumentos.
     */
    public static void main(String[] args) {
        
        switch (args.length) {
            case 1 ->                 {
                    String separador = File.separator;
                    String filename = "src" + separador + "main" + separador + "java" + separador + "com" + separador + "mycompany" + separador + "actividad3" + separador + "jsonmc-master" + separador + args[0]; // Reemplaza con la ruta correcta a tu archivo JSON
                    File carpeta = new File(filename);
                    if (carpeta.exists() && carpeta.isDirectory()) {
                        File[] archivos = carpeta.listFiles();
                        
                        for (File archivo : archivos) {
                            JSONObject jsonObject = LeerJSON(archivo.getAbsolutePath());
                            MostrarPersonas(jsonObject);
                        }
                    }else{
                        System.out.println("Error: No se ha encontrado la carpeta");
                    }                      }
            case 2 ->                 {
                    String separador = File.separator;
                    String filename = "src" + separador + "main" + separador + "java" + separador + "com" + separador + "mycompany" + separador + "actividad3" + separador + "jsonmc-master" + separador + args[0] + separador + args[1];
                    File carpeta = new File(filename);
                    if (carpeta.exists() && carpeta.isDirectory()) {
                        File[] archivos = carpeta.listFiles();
                        
                        for (File archivo : archivos) {
                            JSONObject jsonObject = LeerJSON(archivo.getAbsolutePath());
                            MostrarPeliculas(jsonObject);
                        }
                    }else{
                        System.out.println("Error: No se ha encontrado la carpeta");
                    }                      }
            default -> System.out.println("Numero de argumentos incorrecto");
        }

    }
    /**
     * 
     * Lee y parsea un archivo JSON desde la ubicación especificada.
     * 
     * @param filename La ruta completa del archivo JSON
     * @return Un objeto JSONObject que representa el contenido del archivo JSON. 
     *         Si hay un error al leer o parsear el archivo, se devuelve null.
     */
    private static JSONObject LeerJSON(String filename) {
        
        try {
            FileReader file = new FileReader(filename);
            StringBuilder myJson = new StringBuilder();

            int i;
            while ((i = file.read()) != -1) {
                myJson.append((char) i);
            }

            file.close();

            return new JSONObject(myJson.toString());
        } catch (IOException | JSONException e) {
            System.out.println("Error al leer el archivo JSON.");
            return null;
        }
    }
    
    /**
     * Muestra información de personas contenida en un objeto JSON.
     * @param json El objeto JSON que contiene la información de la persona.
     */
    private static void MostrarPersonas(JSONObject json) {
        
        String name = json.has("name") ? json.getString("name") : "Sin datos";
        String birthname = json.has("birthname") ? json.getString("birthname") : "Sin datos";
        String birthdate = json.has("birthdate") ? json.getString("birthdate") : "Sin datos";
        String birthplace = json.has("birthplace") ? json.getString("birthplace") : "Sin datos";
        
        System.out.println();
        System.out.println("Name: " + name);
        System.out.println("Birthname: " + birthname);
        System.out.println("Birthdate: " + birthdate);
        System.out.println("Birthplace: " + birthplace);
    }
    
    /**
     * Muestra información de películas contenida en un objeto JSON.
     * 
     * @param json El objeto JSON que contiene la información de la película.
     */
    private static void MostrarPeliculas(JSONObject json) {
        
        String name = json.getString("name");
        int year = json.getInt("year");
        int runtime = json.getInt("runtime");
        JSONArray categories = json.getJSONArray("categories");
        String releaseDate = json.getString("release-date");
        String director = json.getString("director");
        JSONArray writers = json.getJSONArray("writer");
        JSONArray actors = json.getJSONArray("actors");
        String storyline = json.getString("storyline");
        
        System.out.println("");
        System.out.println("Name: " + name);
        System.out.println("Year: " + year);
        System.out.println("Runtime: " + runtime + " minutes");

        System.out.print("Categories: ");
        for (Object categoria : categories) {
            System.out.print(categoria + ", ");
        }

        System.out.println();

        System.out.println("Release Date: " + releaseDate);
        System.out.println("Director: " + director);

        System.out.print("Writers: ");
        for (Object writer : writers) {
            System.out.print(writer + ", ");
        }

        System.out.println();

        System.out.print("Actors: ");
        for (Object actor : actors) {
            System.out.print(actor + ", ");
        }
        System.out.println();

        System.out.println("Storyline: " + storyline);
    }
}
