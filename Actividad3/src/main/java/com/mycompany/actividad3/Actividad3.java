/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.actividad3;

import java.io.FileReader;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
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
                    // Verifica si se proporcionó un solo argumento.
                    
                    // Construye la ruta completa al archivo JSON utilizando el argumento proporcionado.
                    String separador = File.separator;
                    String filename = "src" + separador + "main" + separador + "java" + separador + "com" + separador + "mycompany" + separador + "actividad3" + separador + "jsonmc-master" + separador + args[0];
                    File carpeta = new File(filename);
                    
                    // Si existe la carpeta pasamos todos los archivos a un [] los recorremos y leemos con LeerJSON y después mostramos los datos
                    if (carpeta.exists() && carpeta.isDirectory()) {
                        File[] archivos = carpeta.listFiles();
                        
                        for (File archivo : archivos) {
                            JSONObject jsonObject = LeerJSON(archivo.getAbsolutePath());
                            MostrarPersonas(jsonObject);
                        }
                        
                        String puesto = args[0].substring(0, args[0].length() - 1);
                        boolean crear = preguntarBoolean("¿Quiere crear un nuevo " + puesto + "?");
                        
                        while(crear){
                            crearPersona(filename + separador);
                            crear = preguntarBoolean("¿Quiere crear un nuevo " + puesto + "?");
                        }
                        
                        System.out.println("Se ha cerrado el programa");
                               
                    }else{
                        // Si no se ha encontrado la carpeta
                        System.out.println("Error: No se ha encontrado la carpeta");
                    }                      }
            case 2 ->                 {
                    // Verifica si se proporcionó dos argumentos
                    
                    // Construye la ruta completa al archivo JSON utilizando el argumento proporcionado.
                    String separador = File.separator;
                    String filename = "src" + separador + "main" + separador + "java" + separador + "com" + separador + "mycompany" + separador + "actividad3" + separador + "jsonmc-master" + separador + args[0] + separador + args[1];
                    File carpeta = new File(filename);
                    
                    // Si existe la carpeta pasamos todos los archivos a un [] los recorremos y leemos con LeerJSON y después mostramos los datos
                    if (carpeta.exists() && carpeta.isDirectory()) {
                        File[] archivos = carpeta.listFiles();
                        
                        for (File archivo : archivos) {
                            JSONObject jsonObject = LeerJSON(archivo.getAbsolutePath());
                            MostrarPeliculas(jsonObject);
                        }
                    }else{
                        // Si no se ha encontrado la carpeta
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
        
        // Extrae la información de personas del objeto JSON.
        String name = json.has("name") ? json.getString("name") : "Sin datos";
        String birthname = json.has("birthname") ? json.getString("birthname") : "Sin datos";
        String birthdate = json.has("birthdate") ? json.getString("birthdate") : "Sin datos";
        String birthplace = json.has("birthplace") ? json.getString("birthplace") : "Sin datos";
        
        // Imprime la información
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
        
        // Extrae la información de las películas del objeto JSON.
        String name = json.getString("name");
        int year = json.getInt("year");
        int runtime = json.getInt("runtime");
        JSONArray categories = json.getJSONArray("categories");
        String releaseDate = json.getString("release-date");
        String director = json.getString("director");
        JSONArray writers = json.getJSONArray("writer");
        JSONArray actors = json.getJSONArray("actors");
        String storyline = json.getString("storyline");
        
        // Imprime los datos
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
    
    private static void crearPersona(String filename){
        String name = preguntar("Introduce el name: ");
        String birthname = preguntar("Introduce el birthname: ");
        String birthdate = preguntar("Introduce el birthdate: ");
        String birthplace = preguntar("Introduce el birthplace: ");
        
        JSONObject persona = new JSONObject();
        
        persona.put("name",name);
        persona.put("birthname",birthname);
        persona.put("birthdate",birthdate);
        persona.put("birthplace",birthplace);
        
        try (FileWriter file = new FileWriter(filename + name + ".json")) {
            file.write(persona.toString());
            System.out.println("Archivo creado exitosamente.");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    private static String preguntar(String mensaje) {
        Scanner scanner = new Scanner(System.in);
        String input;
        
        do {
            System.out.print(mensaje + ": ");
            input = scanner.nextLine().trim(); // trim() elimina espacios en blanco al principio y al final
        } while (input.isEmpty()); // Continuar solicitando mientras el input esté vacío

        return input;
    }
    
    private static boolean preguntarBoolean(String mensaje) {
        
        Scanner scanner = new Scanner(System.in);
        boolean inputValido = false;
        boolean respuesta = false;

        do {
            System.out.print(mensaje + ": ");
            String input = scanner.nextLine().trim().toLowerCase(); // trim() elimina espacios en blanco y toLowerCase() convierte a minúsculas
            if (input.equals("true") || input.equals("false")) {
                respuesta = Boolean.parseBoolean(input);
                inputValido = true;
            } else {
                System.out.println("Por favor, ingresa 'true' o 'false'.");
            }
        } while (!inputValido);

        return respuesta;
    }
}
