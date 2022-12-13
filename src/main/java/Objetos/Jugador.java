package Objetos;

public class Jugador {
    String rank;
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

    public Jugador(String rank,String wins, String kills, String deaths, String assists, String scoreRound, String KAD, String killsRounds, String plants, String firstBloods, String clutches, String flawless, String aces) {
        this.rank=rank;
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


    public String getRank() {
        return rank;
    }

    public String getWins() {
        return wins;
    }

    public String getKills() {
        return kills;
    }

    public String getDeaths() {
        return deaths;
    }

    public String getAssists() {
        return assists;
    }

    public String getScoreRound() {
        return scoreRound;
    }

    public String getKAD() {
        return KAD;
    }

    public String getKillsRounds() {
        return killsRounds;
    }

    public String getPlants() {
        return plants;
    }

    public String getFirstBloods() {
        return firstBloods;
    }

    public String getClutches() {
        return clutches;
    }

    public String getFlawless() {
        return flawless;
    }

    public String getAces() {
        return aces;
    }

    @Override
    public String toString() {
        return "Jugadores{" +
                "rank='" + rank + '\'' +
                ", wins='" + wins + '\'' +
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
