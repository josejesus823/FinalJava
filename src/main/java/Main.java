import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;

public class Main {
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        //String texto = "                     ";
        String texto = "Cuando yo tenía seis años vi en un libro una magnífica lámina. Representaba una serpiente boa que se tragaba a una fiera. En el libro se decía:" + " Las boas tragan a sus presas enteras, sin masticarlas. Después ya no pueden moverse y duermen durante los seis meses de su digestión." + " Reflexioné mucho entonces sobre las aventuras de la selva y, a mi vez, logré trazar con un lápiz de colores mi primer dibujo. Era una obra maestra que representaba una serpiente boa digiriendo un elefante.Mostré mi obra a las personas mayores y les pregunté si mi dibujo les asustaba.Me respondieron: " + "¿Por qué habría de asustar un sombrero?" + ".Mi dibujo no representaba un sombrero. Representaba una serpiente boa que digiere un elefante.Es necesario explicar a los adultos muchas cosas, porque nunca comprenden nada por sí mismos.";
        String textoLimpio = texto.replaceAll("[^a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]", "");
        try {
            //simular el primerCatch
            if (textoLimpio.trim().isEmpty()){
                throw new IllegalArgumentException("El campo no puede estar vacío");
            }

            //simular el segundoCatch
            //System.out.print("Ingresa un número entero: ");
            //int numero = sc.nextInt();

            //simular lista vacia
            ArrayList<String> lista = null;
            int tamaño = lista.size();



            //Guardar el diccionario
            LinkedHashMap<String, Integer> frecuentesPalabras = countWords(words(textoLimpio));
            //Mostrar las palabras mas frecuentes
            mostrarTop(frecuentesPalabras);
            //Longitud Promedio
            System.out.println("La longitud promedio de las palabras es de " + longitudPromedio(words(textoLimpio)));

            DiffWords(words(textoLimpio));
            //El usuarios pide la busqueda de la palabra
            System.out.println("Hola usuario por favor ingrese una palabra: ");
            String inputUser = sc.next();
            userMatch(inputUser, frecuentesPalabras);
            mostrarFrases(texto);
        }
        catch (IllegalArgumentException e){
            System.out.println("Error el texto esta vacio: " + e.getMessage());
        }
        catch (InputMismatchException e){
            System.out.println("Ingreso un dato erroneo en la busqueda");
        }
        catch (NullPointerException e){
            System.out.println("La lista no puede estar vacia");
        }
        catch (Exception e) {
            System.out.println("Error inesperado" + e.getMessage());
        }
    }

    static ArrayList<String> words(String word){
        ArrayList<String> wordsSaved = new ArrayList<>();
        String [] wordsSplited = word.split(" ");
        for (String a : wordsSplited){
            wordsSaved.add(a.toLowerCase());
        }
        return  wordsSaved;
    }

    static LinkedHashMap<String,Integer> countWords(ArrayList<String> words){
        LinkedHashMap<String, Integer> palabrasEncontradas = new LinkedHashMap<>();

        for (String word: words){
            int countWord = palabrasEncontradas.getOrDefault(word, 0);
            palabrasEncontradas.put(word, countWord + 1);
        }
        return palabrasEncontradas;
    }

    static void mostrarTop(LinkedHashMap<String, Integer> words){
        System.out.println("Mensaje antes de la ejecucion mostrarTOP...");

        try {
            LinkedHashMap<String, Integer> wordsSorted = words.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (oldValue, newValue) -> oldValue, LinkedHashMap::new
                    ));

            System.out.println(wordsSorted);
            System.out.println("------------------------------------FIN---------------------------------------------------------");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    static double longitudPromedio(ArrayList<String> words){
        int totalWords = words.size();
        int acu = 0;
        for (String word: words){
            acu += word.length();
        }
        return (double) acu / totalWords;
    }

    static void DiffWords(ArrayList<String> words){
        ArrayList<String> newWords = new ArrayList<>();
        for (String word : words){
            if(!newWords.contains(word)){
                newWords.add(word);
            }
        }
        System.out.println("En total hay " + newWords.size() + " diferentes palabras");
        System.out.println("------------------------------------FIN---------------------------------------------------------");
    }

    static void userMatch(String user, LinkedHashMap<String, Integer> object){
        for(Map.Entry<String, Integer> diccionario: object.entrySet()){
            String clave = diccionario.getKey();
            Integer value = diccionario.getValue();
            if(user.equalsIgnoreCase(clave)){
                System.out.println("La palabra " + clave + " Aparece " + value + " veces");
            }
            else {
                System.out.println("La palabra que esta buscando no existe en el texto");
                break;
            }
        }
        System.out.println("------------------------------------FIN---------------------------------------------------------");
    }

    static void mostrarFrases(String texto){
        System.out.println("Mensaje antes de la ejecucion MOSTRAR FRASES...");

        try {
            String [] textoDividido = texto.split("\\.");
            for (String frase: textoDividido){
                System.out.println(frase.trim());
            }
            System.out.println("El total de palabras encontradas fue de " + textoDividido.length);
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



}
