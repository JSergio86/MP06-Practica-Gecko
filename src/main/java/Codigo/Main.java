package Codigo;

import java.io.IOException;

public class Main {
  /**
   * Metodo main para ejecutar los metodos creados en la clase Scrapping.
   * @param args
   * @throws IOException
   */

  public static void main(String[] args) throws IOException {
    Scrapping scrapping = new Scrapping();

    //Nombre de los usuarios que queremos mirar las estadisticas
    String nombre = "Sergio";
    String hastag = "4720";

    /**
     * Permite añadir los metodos que hemos creado.
     */

    //Podemos ejecutar el metodo que queramos por ejemplo si solo queremos ver las estadisticas de las armas pues se ejecuta solo ese metodo

    scrapping.VerTodasLasStats(nombre, hastag);
    //scrapping.Jugador(nombre, hastag);
    //scrapping.Armas(nombre, hastag);
    //scrapping.Mapas(nombre, hastag);
    //scrapping.Partidas(nombre, hastag);
    //scrapping.Agentes(nombre, hastag);



  }

}
