package Objetos;

public class Mapa {
    String name;
    String win;
    String wins;
    String losses;
    String KD;
    String ADR;
    String ACS;

    public Mapa() {
    }

    public Mapa(String name, String win, String wins, String losses, String KD, String ADR, String ACS) {
        this.name = name;
        this.win = win;
        this.wins = wins;
        this.losses = losses;
        this.KD = KD;
        this.ADR = ADR;
        this.ACS = ACS;
    }

    @Override
    public String toString() {
        return "Mapas{" +
                "name='" + name + '\'' +
                ", win='" + win + '\'' +
                ", wins='" + wins + '\'' +
                ", losses='" + losses + '\'' +
                ", KD='" + KD + '\'' +
                ", ADR='" + ADR + '\'' +
                ", ACS='" + ACS + '\'' +
                '}';
    }
}
