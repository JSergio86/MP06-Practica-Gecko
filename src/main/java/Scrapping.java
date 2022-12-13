import Objetos.Agentes;
import Objetos.Armas;
import Objetos.Mapas;
import Objetos.Partidas;
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

    public Scrapping() throws IOException {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
    }

    /**
     * Metodo que permite ver las estadisticas del jugador.
     *
     * @param nombre Nombre del usuario del cual queremos ver las estadisticas
     * @param hastag Hastag del usuario del cual queremos ver las estadisticas
     * @throws IOException
     */
    public void URL(String nombre, String hastag) throws IOException {
        FileWriter fileWriter = new FileWriter("src/Estadisticas.csv");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String baseUrl = "https://tracker.gg/valorant/profile/riot/" + nombre + "%23" + hastag + "/overview";

        driver.get(baseUrl);
        WebElement highLightedContent = driver.findElement(By.className("highlighted__content"));
        WebElement stats = highLightedContent.findElement(By.className("stat"));


        WebElement main = driver.findElement(By.className("main"));
        ArrayList<WebElement> statsMain = (ArrayList<WebElement>) main.findElements(By.className("value"));

        bufferedWriter.write("Wins, Kills, Deaths, Assists, Score/Round, KAD Ratio, Kills/Round,Plants, First Bloods, Clutches, Flawless,Aces\n ");

        for (int x = 0; x < statsMain.size(); x++) {
            String tmpStat = statsMain.get(x).getText();
            bufferedWriter.write(tmpStat + ", ");
        }

        bufferedWriter.close();

    }

    /**
     * Metodo que permite ver las estadisticas de cada arma.
     *
     * @param nombre Nombre del usuario del cual queremos ver las estadisticas
     * @param hastag Hastag del usuario del cual queremos ver las estadisticas
     * @throws IOException
     */

    public void Armas(String nombre, String hastag) throws IOException {

        String baseUrl = "https://tracker.gg/valorant/profile/riot/" + nombre + "%23" + hastag + "/weapons";

        driver.get(baseUrl);


        WebElement listaArmas = driver.findElement(By.className("segment-used__table"));
        WebElement armas = listaArmas.findElement(By.tagName("tbody"));
        ArrayList<WebElement> filasArmas = (ArrayList<WebElement>) armas.findElements(By.tagName("tr"));
        List<Armas> listaStatsArmas = new ArrayList<>();

        for (WebElement stat : filasArmas) {
            List<WebElement> columnasArmas = stat.findElements(By.tagName("td"));
            List<String> arma_y_tipo = List.of(columnasArmas.get(0).getText().split("\n"));
            listaStatsArmas.add(new Armas(arma_y_tipo.get(0), arma_y_tipo.get(1), columnasArmas.get(1).getText(), columnasArmas.get(2).getText(), columnasArmas.get(3).getText(), columnasArmas.get(4).getText(), columnasArmas.get(5).getText(), columnasArmas.get(6).getText()));
        }

        try (FileWriter writer = new FileWriter("SalidasCSV/EstadisticasWeapons.csv")) {
            ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
            mappingStrategy.setType(Armas.class);

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

    public void Mapas(String nombre, String hastag) throws IOException {

        String baseUrl = "https://tracker.gg/valorant/profile/riot/" + nombre + "%23" + hastag + "/maps";

        driver.get(baseUrl);

        WebElement listaMapas = driver.findElement(By.className("st-content__category"));
        ArrayList<WebElement> statsMapas = (ArrayList<WebElement>) listaMapas.findElements(By.className("st-content__item"));

        List<Mapas> listaStatsMapas = new ArrayList<>();

        for (WebElement stat : statsMapas) {
            List<WebElement> columnasMapas = stat.findElements(By.className("st-content__item-value"));
            listaStatsMapas.add(new Mapas(columnasMapas.get(0).getText(), columnasMapas.get(1).getText(), columnasMapas.get(2).getText(), columnasMapas.get(3).getText(), columnasMapas.get(4).getText(), columnasMapas.get(5).getText(), columnasMapas.get(6).getText()));
        }

        try (FileWriter writer = new FileWriter("SalidasCSV/EstadisticasMapas.csv")) {
            ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
            mappingStrategy.setType(Mapas.class);

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

    public void Partidas(String nombre, String hastag) throws IOException {

        String baseUrl = "https://tracker.gg/valorant/profile/riot/" + nombre + "%23" + hastag + "/matches?playlist=competitive";

        driver.get(baseUrl);

        WebElement lista = driver.findElement(By.className("trn-grid"));
        ArrayList<WebElement> statsPartidas = (ArrayList<WebElement>) lista.findElements(By.className("trn-gamereport-list__group"));

        List<Partidas> listaStatsMapas = new ArrayList<>();

        for (int i=0; i<8;i++) {
            List<WebElement> nameMatch = driver.findElements(By.className("match__name"));
            List<WebElement> nameScore = driver.findElements(By.className("match__score"));
            List<WebElement> nameType = driver.findElements(By.className("match__subtitle"));

            listaStatsMapas.add(new Partidas(nameMatch.get(i).getText(),nameType.get(i).getText() ,nameScore.get(i).getText()));
        }

        try (FileWriter writer = new FileWriter("SalidasCSV/EstadisticasPartidas.csv")) {
            ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
            mappingStrategy.setType(Partidas.class);

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
    public void Agentes(String nombre, String hastag) throws IOException {

        String baseUrl = "https://tracker.gg/valorant/profile/riot/" + nombre + "%23" + hastag + "/agents";

        driver.get(baseUrl);

        WebElement listAgentes = driver.findElement(By.className("st-content__category"));
        ArrayList<WebElement> statsAgentes = (ArrayList<WebElement>) listAgentes.findElements(By.className("st-content__item"));

        List<Agentes> listaStatsAgentes = new ArrayList<>();

        for (WebElement stat : statsAgentes) {
            List<WebElement> columnasAgentes = stat.findElements(By.className("st-content__item-value"));
            List<String> agente_y_tipo = List.of(columnasAgentes.get(0).getText().split("\n"));
            listaStatsAgentes.add(new Agentes(agente_y_tipo.get(0), agente_y_tipo.get(1), columnasAgentes.get(1).getText(), columnasAgentes.get(2).getText(), columnasAgentes.get(3).getText(), columnasAgentes.get(4).getText(), columnasAgentes.get(5).getText(), columnasAgentes.get(6).getText(), columnasAgentes.get(7).getText(), columnasAgentes.get(8).getText()));
        }

        try (FileWriter writer = new FileWriter("SalidasCSV/EstadisticasAgentes.csv")) {
            ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
            mappingStrategy.setType(Agentes.class);

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
