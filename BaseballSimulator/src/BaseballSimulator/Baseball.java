package BaseballSimulator;

import java.util.Random;

public class Baseball {
    static Team team1 = new Team("Odds Team");
    static Team team2 = new Team("Evens Team");
    static Random r = new Random();

    public static void main(String[] args) {
        parseTeam1();
        parseTeam2();
        System.out.println(team1.getName() + " at " + team2.getName());
        int runs1 = 0;
        int runs2 = 0;
        int outs1 = 0;
        int outs2 = 0;
        int inning = 0;
        int limit = 8;
        int i1 = 1;
        int i2 = 1;
        while(inning <= limit) {
            inning++;
            System.out.println("Top of Inning " + inning);
            int bases = 0;
            while(outs1 < 3) {
                if(i1 == 10) {
                    i1 = 1;
                }
                if(r.nextDouble() <= team1.getPlayer(i1).getBattingAverage()) {
                    bases++;
                    if(bases == 4) {
                        runs1++;
                        System.out.println(team1.getName() + " scored on a hit by " + team1.getPlayer(i1).getName());
                        bases--;
                    }
                    else {
                        System.out.println(team1.getPlayer(i1).getName() + " got a hit!");
                    }
                }
                else {
                    outs1++;
                    System.out.println(team1.getPlayer(i1).getName() + " got out.");
                }
                i1++;
            }
            bases = 0;
            if(inning < limit || runs1 >= runs2) {
                System.out.println("Bottom of Inning " + inning);
                while (outs2 < 3) {
                    if (i2 == 10) {
                        i2 = 1;
                    }
                    if (r.nextDouble() <= team2.getPlayer(i2).getBattingAverage()) {
                        bases++;
                        if (bases == 4) {
                            runs2++;
                            bases--;
                            System.out.println(team2.getName() + " scored on a hit by " + team2.getPlayer(i2).getName());
                        } else {
                            System.out.println(team2.getPlayer(i2).getName() + " got a hit!");
                        }
                    } else {
                        outs2++;
                        System.out.println(team2.getPlayer(i2).getName() + " got out.");
                    }
                    i2++;
                }
            }
            if(inning >= 8 && runs1 == runs2) {
                ++limit;
            }
            outs1 = 0;
            outs2 = 0;
            System.out.println();
        }
        System.out.println("Final score:");
        System.out.println(team1.getName() + ": " + runs1);
        System.out.println(team2.getName() + ": " + runs2);
        if(runs1 > runs2) {
            System.out.print(team1.getName() + " wins");
        }
        else {
            System.out.print(team2.getName() + " wins");
        }
        if(inning > 9) {
            System.out.print(" in " + inning + " innings");
        }
        System.out.print("!");
    }

    public static void parseTeam1() {
        team1.addPlayer(new Player("Player 1", .133, 1));
        team1.addPlayer(new Player("Player 3", .286, 2));
        team1.addPlayer(new Player("Player 5", .357, 3));
        team1.addPlayer(new Player("Player 7", .401, 4));
        team1.addPlayer(new Player("Player 9", .210, 5));
        team1.addPlayer(new Player("Player 11", .306, 6));
        team1.addPlayer(new Player("Player 13", .885, 7));
        team1.addPlayer(new Player("Player 15", .077, 8));
        team1.addPlayer(new Player("Player 17", .253, 9));
        team1.addPlayer(new Player("Player 19", .500, 10));
    }

    public static void parseTeam2() {
        team2.addPlayer(new Player("Player 2", .077, 1));
        team2.addPlayer(new Player("Player 4", .306, 2));
        team2.addPlayer(new Player("Player 6", .885, 3));
        team2.addPlayer(new Player("Player 8", .401, 4));
        team2.addPlayer(new Player("Player 10", .286, 5));
        team2.addPlayer(new Player("Player 12", .357, 6));
        team2.addPlayer(new Player("Player 14", .210, 7));
        team2.addPlayer(new Player("Player 16", .133, 8));
        team2.addPlayer(new Player("Player 18", .500, 9));
        team2.addPlayer(new Player("Player 20", .253, 10));
    }
}