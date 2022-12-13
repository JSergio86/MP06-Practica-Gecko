package Objetos;

public class Agentes {
    String name;
    String type;
    String timePlayed;
    String matches;
    String win;
    String KD;
    String ADR;
    String ACS;
    String HS;
    String KAST;

    public Agentes(String name, String type, String timePlayed, String matches, String win, String KD, String ADR, String ACS, String HS, String KAST) {
        this.name = name;
        this.type = type;
        this.timePlayed = timePlayed;
        this.matches = matches;
        this.win = win;
        this.KD = KD;
        this.ADR = ADR;
        this.ACS = ACS;
        this.HS = HS;
        this.KAST = KAST;
    }

    @Override
    public String toString() {
        return "Agentes{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", timePlayed='" + timePlayed + '\'' +
                ", matches='" + matches + '\'' +
                ", win='" + win + '\'' +
                ", KD='" + KD + '\'' +
                ", ADR='" + ADR + '\'' +
                ", ACS='" + ACS + '\'' +
                ", HS='" + HS + '\'' +
                ", KAST='" + KAST + '\'' +
                '}';
    }
}
