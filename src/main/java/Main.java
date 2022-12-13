import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {
    Scrapping scrapping = new Scrapping();

    String nombre = "Sergio";
    String hastag = "4720";

    //scrapping.URL(nombre, hastag);
    //scrapping.Armas(nombre, hastag);
    //scrapping.Mapas(nombre, hastag);
    scrapping.Partidas(nombre, hastag);
    //scrapping.Agentes(nombre, hastag);



  }

}
