package Codigo;

import Objetos.*;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scrapping {

    WebDriver driver;

    /**
     * Constructor que permite poder Scrappear
     * @throws IOException
     */
    public Scrapping() throws IOException {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
    }

    /**
     * Metodo que permite mirar todas las estadisticas del jugador, agente, mapa, arma y partida .
     * @param nombre Nombre del usuario del cual queremos ver las estadisticas.
     * @param hastag Hashtag del usuario del cual queremos ver las estadisticas.
     * @throws IOException
     */

    public void VerTodasLasStats(String nombre, String hastag) throws IOException {
        Jugador(nombre, hastag);
        Armas(nombre, hastag);
        Mapas(nombre, hastag);
        Partidas(nombre, hastag);
        Agentes(nombre, hastag);

    }
    /**
     * Metodo que permite ver las estadisticas generales del jugador.
     *
     * @param nombre Nombre del usuario del cual queremos ver las estadisticas.
     * @param hastag Hashtag del usuario del cual queremos ver las estadisticas.
     * @throws IOException
     */


    public void Jugador(String nombre, String hastag) throws IOException {

        String baseUrl = "https://tracker.gg/valorant/profile/riot/" + nombre + "%23" + hastag + "/overview";

        //URL donde entrara a cojer la información
        driver.get(baseUrl);

        //Selección de los webelement para pillar la información
        WebElement lista = driver.findElement(By.className("main"));
        ArrayList<WebElement> filasStat = (ArrayList<WebElement>) lista.findElements(By.className("stat"));

        List<Jugador> listaStats = new ArrayList<>();

        for (int i=0; i<1;i++) {
            List<WebElement> columnasStats= lista.findElements(By.className("value"));
            WebElement statRank= driver.findElement(By.className("highlighted__content"));
            List<WebElement> columnasRank= statRank.findElements(By.className("stat__value"));

            //añadir en la lista el objeto que hemos creado con la información
            listaStats.add(new Jugador(columnasRank.get(0).getText(),columnasStats.get(0).getText(), columnasStats.get(1).getText(), columnasStats.get(2).getText(), columnasStats.get(3).getText(), columnasStats.get(4).getText(), columnasStats.get(5).getText(), columnasStats.get(6).getText(), columnasStats.get(7).getText(), columnasStats.get(8).getText(), columnasStats.get(9).getText(), columnasStats.get(10).getText(), columnasStats.get(11).getText()));

        }

        //Permite escribir el fichero CSV estructuradamente

        try (FileWriter writer = new FileWriter("SalidasCSV/EstadisticasJugador.csv")) {
            ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
            mappingStrategy.setType(Jugador.class);

            String[] columns = {"rank, wins, kills, deaths, assists, scoreRound, KAD, killsRounds, plants, firstBloods, clutches, flawless, aces"};
            mappingStrategy.setColumnMapping(columns);
            writer.write("\"rank\",\"wins\",\"kills\",\"deaths\",\"assists\",\"scoreRound\",\"KAD\",\"killsRounds\",\"plants\",\"firstBloods\",\"clutches\",\"flawless\",\"aces\"\n");

            StatefulBeanToCsv beanWriter = new StatefulBeanToCsvBuilder(writer)
                    .withMappingStrategy(mappingStrategy)
                    .build();
            for(int i=0; i< listaStats.size();i++){
                writer.write(listaStats.get(i).getRank()+", "+listaStats.get(i).getWins()+", "+listaStats.get(i).getKills()+", "+listaStats.get(i).getDeaths()+", " +
                        ""+listaStats.get(i).getAssists()+", "+listaStats.get(i).getScoreRound()+", "+listaStats.get(i).getKAD()+", "+listaStats.get(i).getKillsRounds()+", "+listaStats.get(i).getPlants()
                        +", "+listaStats.get(i).getFirstBloods()+", "+listaStats.get(i).getClutches()+", "+listaStats.get(i).getFlawless()+", "+listaStats.get(i).getAces());
            }
         // No funciona beanWriter.write(listaStats);
        }


    }


    /**
     * Metodo que permite ver las estadisticas de lar armas.
     * @param nombre Nombre del usuario del cual queremos ver las estadisticas.
     * @param hastag Hashtag del usuario del cual queremos ver las estadisticas.
     * @throws IOException
     */
    public void Armas(String nombre, String hastag) throws IOException {

        String baseUrl = "https://tracker.gg/valorant/profile/riot/" + nombre + "%23" + hastag + "/weapons";

        driver.get(baseUrl);


        WebElement listaArmas = driver.findElement(By.className("segment-used__table"));
        WebElement armas = listaArmas.findElement(By.tagName("tbody"));
        ArrayList<WebElement> filasArmas = (ArrayList<WebElement>) armas.findElements(By.tagName("tr"));
        List<Arma> listaStatsArmas = new ArrayList<>();

        for (WebElement stat : filasArmas) {
            List<WebElement> columnasArmas = stat.findElements(By.tagName("td"));
            List<String> arma_y_tipo = List.of(columnasArmas.get(0).getText().split("\n"));
            listaStatsArmas.add(new Arma(arma_y_tipo.get(0), arma_y_tipo.get(1), columnasArmas.get(1).getText(), columnasArmas.get(2).getText(), columnasArmas.get(3).getText(), columnasArmas.get(4).getText(), columnasArmas.get(5).getText(), columnasArmas.get(6).getText()));
        }

        try (FileWriter writer = new FileWriter("SalidasCSV/EstadisticasWeapons.csv")) {
            ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
            mappingStrategy.setType(Arma.class);

            String[] columns = {"name", "type", "kills", "deaths", "headshots", "damage_round", "kills_round", "longest_kill"};
            mappingStrategy.setColumnMapping(columns);
            writer.write("\"name\",\"type\",\"kills\",\"deaths\",\"headshots\",\"damage_round\",\"kills_round\",\"longest_kill\"\n");

            StatefulBeanToCsv beanWriter = new StatefulBeanToCsvBuilder(writer)
                    .withMappingStrategy(mappingStrategy)
                    .build();
            beanWriter.write(listaStatsArmas);
        } catch (CsvRequiredFieldEmptyException e) {
            throw new RuntimeException(e);
        } catch (CsvDataTypeMismatchException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo que permite ver las estadisticas de los jugadores en cada mapa.
     * @param nombre Nombre del usuario del cual queremos ver las estadisticas.
     * @param hastag Hashtag del usuario del cual queremos ver las estadisticas.
     * @throws IOException
     */

    public void Mapas(String nombre, String hastag) throws IOException {

        String baseUrl = "https://tracker.gg/valorant/profile/riot/" + nombre + "%23" + hastag + "/maps";

        driver.get(baseUrl);

        WebElement listaMapas = driver.findElement(By.className("st-content__category"));
        ArrayList<WebElement> statsMapas = (ArrayList<WebElement>) listaMapas.findElements(By.className("st-content__item"));

        List<Mapa> listaStatsMapas = new ArrayList<>();

        for (WebElement stat : statsMapas) {
            List<WebElement> columnasMapas = stat.findElements(By.className("st-content__item-value"));
            listaStatsMapas.add(new Mapa(columnasMapas.get(0).getText(), columnasMapas.get(1).getText(), columnasMapas.get(2).getText(), columnasMapas.get(3).getText(), columnasMapas.get(4).getText(), columnasMapas.get(5).getText(), columnasMapas.get(6).getText()));
        }

        try (FileWriter writer = new FileWriter("SalidasCSV/EstadisticasMapas.csv")) {
            ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
            mappingStrategy.setType(Mapa.class);

            String[] columns = {"name", "win", "wins", "losses", "KD", "ADR", "ACS",};
            mappingStrategy.setColumnMapping(columns);
            writer.write("\"name\",\"win\",\"wins\",\"losses\",\"KD\",\"ADR\",\"ACS\"\n");

            StatefulBeanToCsv beanWriter = new StatefulBeanToCsvBuilder(writer)
                    .withMappingStrategy(mappingStrategy)
                    .build();
            beanWriter.write(listaStatsMapas);
        } catch (CsvRequiredFieldEmptyException e) {
            throw new RuntimeException(e);
        } catch (CsvDataTypeMismatchException e) {
            throw new RuntimeException(e);


        }
    }

    /**
     * Metodo que permite ver las estadisticas de los jugadores de cada partida.
     * @param nombre Nombre del usuario del cual queremos ver las estadisticas.
     * @param hastag  Hashtag del usuario del cual queremos ver las estadisticas.
     * @throws IOException
     */

    public void Partidas(String nombre, String hastag) throws IOException {

        String baseUrl = "https://tracker.gg/valorant/profile/riot/" + nombre + "%23" + hastag + "/matches?playlist=competitive";

        driver.get(baseUrl);

        WebElement lista = driver.findElement(By.className("trn-grid"));
        ArrayList<WebElement> statsPartidas = (ArrayList<WebElement>) lista.findElements(By.className("trn-gamereport-list__group"));

        List<Partida> listaStatsMapas = new ArrayList<>();

        //Permite ver las ultimas 10 partidas
        for (int i=0; i<10;i++) {
            List<WebElement> nameMatch = driver.findElements(By.className("match__name"));
            List<WebElement> nameScore = driver.findElements(By.className("match__score"));
            List<WebElement> nameType = driver.findElements(By.className("match__subtitle"));

            listaStatsMapas.add(new Partida(nameMatch.get(i).getText(),nameType.get(i).getText() ,nameScore.get(i).getText()));
        }

        try (FileWriter writer = new FileWriter("SalidasCSV/EstadisticasPartidas.csv")) {
            ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
            mappingStrategy.setType(Partida.class);

            String[] columns = {"nameMap", "type", "result"};
            mappingStrategy.setColumnMapping(columns);
            writer.write("\"nameMap\",\"type\",\"result\"\n");

            StatefulBeanToCsv beanWriter = new StatefulBeanToCsvBuilder(writer)
                    .withMappingStrategy(mappingStrategy)
                    .build();
            beanWriter.write(listaStatsMapas);
        } catch (CsvRequiredFieldEmptyException e) {
            throw new RuntimeException(e);
        } catch (CsvDataTypeMismatchException e) {
            throw new RuntimeException(e);

        }


    }

    /**
     * Metodo que permite ver las estadisticas de cada jugador de sus agentes.
     * @param nombre Nombre del usuario del cual queremos ver las estadisticas.
     * @param hastag Hashtag del usuario del cual queremos ver las estadisticas.
     * @throws IOException
     */
    public void Agentes(String nombre, String hastag) throws IOException {

        String baseUrl = "https://tracker.gg/valorant/profile/riot/" + nombre + "%23" + hastag + "/agents";

        driver.get(baseUrl);

        WebElement listAgentes = driver.findElement(By.className("st-content__category"));
        ArrayList<WebElement> statsAgentes = (ArrayList<WebElement>) listAgentes.findElements(By.className("st-content__item"));

        List<Agente> listaStatsAgentes = new ArrayList<>();

        for (WebElement stat : statsAgentes) {
            List<WebElement> columnasAgentes = stat.findElements(By.className("st-content__item-value"));
            List<String> agente_y_tipo = List.of(columnasAgentes.get(0).getText().split("\n"));
            listaStatsAgentes.add(new Agente(agente_y_tipo.get(0), agente_y_tipo.get(1), columnasAgentes.get(1).getText(), columnasAgentes.get(2).getText(), columnasAgentes.get(3).getText(), columnasAgentes.get(4).getText(), columnasAgentes.get(5).getText(), columnasAgentes.get(6).getText(), columnasAgentes.get(7).getText(), columnasAgentes.get(8).getText()));
        }

        try (FileWriter writer = new FileWriter("SalidasCSV/EstadisticasAgentes.csv")) {
            ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
            mappingStrategy.setType(Agente.class);

            String[] columns = {"name", "type", "timePlayed", "matches", "win", "KD", "ADR","ACS", "HS", "KAST"};
            mappingStrategy.setColumnMapping(columns);
            writer.write("\"name\",\"type\",\"timePlayed\",\"matches\",\"win\",\"KD\",\"ADR\",\"ACS\",\"HS\",\"KAST\"\n");

            StatefulBeanToCsv beanWriter = new StatefulBeanToCsvBuilder(writer)
                    .withMappingStrategy(mappingStrategy)
                    .build();
            beanWriter.write(listaStatsAgentes);
        } catch (CsvRequiredFieldEmptyException e) {
            throw new RuntimeException(e);
        } catch (CsvDataTypeMismatchException e) {
            throw new RuntimeException(e);


        }
    }
}
