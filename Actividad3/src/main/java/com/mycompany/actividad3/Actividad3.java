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
import java.util.ArrayList;
import java.util.List;
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
            case 1 -> {
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
                    
                    // Preguntamos si queremos crear un nuevo actor / director
                    String puesto = args[0].substring(0, args[0].length() - 1); // Eliminamos la s final
                    boolean crear = preguntarBoolean("¿Quiere crear un nuevo " + puesto + "?");

                    while (crear) {
                        crearPersona(filename + separador);
                        crear = preguntarBoolean("¿Quiere crear un nuevo " + puesto + "?");
                    }

                    System.out.println("Se ha cerrado el programa");

                } else {
                    // Si no se ha encontrado la carpeta
                    System.out.println("Error: No se ha encontrado la carpeta");
                }
            }
            case 2 -> {
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
                    
                    // Preguntamos si queremos crear una nueva pelicula
                    String puesto = args[0].substring(0, args[0].length() - 1); // Eliminar la s del plural
                    boolean crear = preguntarBoolean("¿Quiere crear unA nueva " + puesto + " del año " + args[1] + "?" );
                    
                    while(crear){
                        crearPelicula(filename + separador,args[1]);
                        crear = preguntarBoolean("¿Quiere crear unA nueva " + args[0] + " del año " + args[1] + "?" );
                    }
                    
                    System.out.println("Error: No se ha encontrado la carpeta");
                    
                } else {
                    // Si no se ha encontrado la carpeta
                    System.out.println("Error: No se ha encontrado la carpeta");
                }
            }
            default ->
                System.out.println("Numero de argumentos incorrecto");
        }

    }

    /**
     *
     * Lee y parsea un archivo JSON desde la ubicación especificada.
     *
     * @param filename La ruta completa del archivo JSON
     * @return Un objeto JSONObject que representa el contenido del archivo JSON. Si hay un error al leer o parsear el archivo, se devuelve null.
     */
    private static JSONObject LeerJSON(String filename) {

        try {
            
            // Abre el archivo con la ruta en 'filename'
            FileReader file = new FileReader(filename);
            
            // Crea un StringBuilder para construir la cadena JSON
            StringBuilder myJson = new StringBuilder();

            int i;
            // Lee el archivo carácter por carácter
            while ((i = file.read()) != -1) {
                myJson.append((char) i);
            }
            
             // Cierra el archivo
            file.close();
            
            // Convierte la cadena JSON en un objeto JSON
            return new JSONObject(myJson.toString());
        } catch (IOException | JSONException e) {
            System.out.println("Error al leer el archivo JSON.");
            return null;
        }
    }

    /**
     * Muestra información de personas contenida en un objeto JSON.
     *
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
    
    /**
     * Crea un archivo JSON para crear un actor / director
     * 
     * @param filename La ruta donde almacenaremos el archivo JSON
     */
    private static void crearPersona(String filename) {
        
        // Preguntamos los datos
        String name = preguntar("Introduce el name: ");
        String birthname = preguntar("Introduce el birthname: ");
        String birthdate = preguntar("Introduce el birthdate: ");
        String birthplace = preguntar("Introduce el birthplace: ");
        
        // Creamos el objeto JSON
        JSONObject persona = new JSONObject();
        
        // Metemos los datos en el objeto JSON
        persona.put("name", name);
        persona.put("birthname", birthname);
        persona.put("birthdate", birthdate);
        persona.put("birthplace", birthplace);
        
        // Creamos el fichero
        try ( FileWriter file = new FileWriter(filename + name + ".json")) {
            file.write(persona.toString());
            System.out.println("Archivo creado exitosamente.");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    /**
     * Crea un archivo JSON para una pelicula
     * 
     * @param filename La ruta donde almacenaremos el archivo JSON
     * @param year El año de la pelicula
     */
    public static void crearPelicula(String filename,String year) {
        
        // Obtener los valores necesarios utilizando la función 'preguntar'
        String name = preguntar("Introduce el name: ");
        int runtime = Integer.parseInt(preguntar("Introduce el runtime (en minutos): "));

        String[] categoriesArray = preguntar("Introduce las categorías (separadas por comas): ").split(",");
        List<String> categories = new ArrayList<>();
        for (String category : categoriesArray) {
            categories.add(category.trim());
        }

        String releaseDate = preguntar("Introduce la fecha de lanzamiento (YYYY-MM-DD): ");
        String director = preguntar("Introduce el director: ");

        String[] writersArray = preguntar("Introduce los escritores (separados por comas): ").split(",");
        List<String> writers = new ArrayList<>();
        for (String writer : writersArray) {
            writers.add(writer.trim());
        }

        String[] actorsArray = preguntar("Introduce los actores (separados por comas): ").split(",");
        List<String> actors = new ArrayList<>();
        for (String actor : actorsArray) {
            actors.add(actor.trim());
        }

        String storyline = preguntar("Introduce la trama: ");

        // Construir el objeto JSON
        JSONObject pelicula = new JSONObject();
        pelicula.put("name", name);
        pelicula.put("year", year);
        pelicula.put("runtime", runtime);
        pelicula.put("categories", new JSONArray(categories));
        pelicula.put("release-date", releaseDate);
        pelicula.put("director", director);
        pelicula.put("writer", new JSONArray(writers));
        pelicula.put("actors", new JSONArray(actors));
        pelicula.put("storyline", storyline);

        // Escribir el objeto JSON en un archivo
        String ruta = filename + name + ".json";
        try ( FileWriter file = new FileWriter(ruta)) {
            file.write(pelicula.toString(4)); // El 4 indica la cantidad de espacios para la indentación
            System.out.println("Archivo creado exitosamente: " + ruta);
        } catch (IOException e) {
        }
    }
    
    /**
     * Pregunta al usuario un dato y se asegura que no este vacio
     * @param mensaje El mensaje con el dato que solicitamos
     * @return Un string con el dato
     */
    private static String preguntar(String mensaje) {
        
        // Creamos un objeto scanner
        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            System.out.print(mensaje);
            input = scanner.nextLine().trim(); // trim() elimina espacios en blanco al principio y al final
        } while (input.isEmpty()); // Continuar solicitando mientras el input esté vacío
        
        // Devolvemos la cadena con el dato
        return input;
    }
    
    /**
     * Pregunta al usuario un booleano y se asegura que no este vacio
     * @param mensaje El mensaje con lo que solicitamos
     * @return Un booleano
     */
    private static boolean preguntarBoolean(String mensaje) {
        
        // Creamos un objeto scanner
        Scanner scanner = new Scanner(System.in);
        
        // Un booleano que es la respuesta del usuario y otro que es si es valido (es decir que no esta vacio)
        boolean inputValido = false;
        boolean respuesta = false;
        
        // Pedimos el booleano
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
        
        // Devolvemos le booleano
        return respuesta;
    }
}
