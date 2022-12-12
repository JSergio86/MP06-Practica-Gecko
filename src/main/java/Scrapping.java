import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
     * @param nombre Nombre del usuario del cual queremos ver las estadisticas
     * @param hastag Hastag del usuario del cual queremos ver las estadisticas
     * @throws IOException
     */
    public void URL(String nombre, String hastag) throws IOException {
        FileWriter fileWriter = new FileWriter("src/Estadisticas.csv");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String baseUrl = "https://tracker.gg/valorant/profile/riot/"+nombre+"%23"+hastag+"/overview";

        driver.get(baseUrl);
        WebElement highLightedContent =driver.findElement(By.className("highlighted__content"));
        WebElement stats = highLightedContent.findElement(By.className("stat"));


        WebElement main = driver.findElement(By.className("main"));
        ArrayList<WebElement> statsMain = (ArrayList<WebElement>) main.findElements(By.className("value"));

        bufferedWriter.write("Wins, Kills, Deaths, Assists, Score/Round, KAD Ratio, Kills/Round,Plants, First Bloods, Clutches, Flawless,Aces\n ");

        for(int x = 0; x < statsMain.size(); x++){
            String tmpStat = statsMain.get(x).getText();
            bufferedWriter.write(tmpStat+", ");
        }

        bufferedWriter.close();

    }

    /**
     * Metodo que permite ver las estadisticas de cada arma.
     * @param nombre Nombre del usuario del cual queremos ver las estadisticas
     * @param hastag Hastag del usuario del cual queremos ver las estadisticas
     * @throws IOException
     */

    public void Armas(String nombre, String hastag) throws IOException {
        FileWriter fileWriter = new FileWriter("src/EstadisticasWeapons.csv");

        String baseUrl = "https://tracker.gg/valorant/profile/riot/"+nombre+"%23"+hastag+"/weapons";

        driver.get(baseUrl);

        fileWriter.write("Arma, Tipo, Kills, Deaths, Headshot%, Damage/Round, Kills/Round, Longest Kill distance\n ");

        WebElement listaArmas = driver.findElement(By.className("segment-used__table"));
        WebElement armas = listaArmas.findElement(By.tagName("tbody"));
        ArrayList<WebElement> statsArmas = (ArrayList<WebElement>) armas.findElements(By.tagName("tr"));

        for(WebElement stat: statsArmas){

            fileWriter.write(stat.getText()+"\n");
        }









    }
}
