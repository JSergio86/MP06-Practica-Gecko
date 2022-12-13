package Objetos;

public class Jugadores {
    String wins;
    String kills;
    String deaths;
    String assists;
    String scoreRound;
    String KAD;
    String killsRounds;
    String plants;
    String firstBloods;
    String clutches;
    String flawless;
    String aces;

    public Jugadores(String wins, String kills, String deaths, String assists, String scoreRound, String KAD, String killsRounds, String plants, String firstBloods, String clutches, String flawless, String aces) {
        this.wins = wins;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.scoreRound = scoreRound;
        this.KAD = KAD;
        this.killsRounds = killsRounds;
        this.plants = plants;
        this.firstBloods = firstBloods;
        this.clutches = clutches;
        this.flawless = flawless;
        this.aces = aces;
    }

    public Jugadores(String wins) {
        this.wins=wins;
    }

    @Override
    public String toString() {
        return "Jugadores{" +
                "wins='" + wins + '\'' +
                ", kills='" + kills + '\'' +
                ", deaths='" + deaths + '\'' +
                ", assists='" + assists + '\'' +
                ", scoreRound='" + scoreRound + '\'' +
                ", KAD='" + KAD + '\'' +
                ", killsRounds='" + killsRounds + '\'' +
                ", plants='" + plants + '\'' +
                ", firstBloods='" + firstBloods + '\'' +
                ", clutches='" + clutches + '\'' +
                ", flawless='" + flawless + '\'' +
                ", aces='" + aces + '\'' +
                '}';
    }
}
