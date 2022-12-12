package Objetos;

public class Armas {
    String name;
    String type;
    String kills;
    String deaths;
    String headshots;
    String damage_round;
    String kills_round;
    String longest_kill;

    public  Armas(){}

    public Armas(String name, String type, String kills, String deaths, String headshots, String damage_round, String kills_round, String longest_kill) {
        this.name = name;
        this.type = type;
        this.kills = kills;
        this.deaths = deaths;
        this.headshots = headshots;
        this.damage_round = damage_round;
        this.kills_round = kills_round;
        this.longest_kill = longest_kill;
    }


    @Override
    public String toString() {
        return "Armas{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", kills='" + kills + '\'' +
                ", deaths='" + deaths + '\'' +
                ", headshots='" + headshots + '\'' +
                ", damage_round='" + damage_round + '\'' +
                ", kills_round='" + kills_round + '\'' +
                ", longest_kill='" + longest_kill + '\'' +
                '}';
    }
}
