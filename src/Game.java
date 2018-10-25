import java.util.Scanner;

public class Game {
    static int days;
    static int turn;
    static Player[] players = new Player[2];

    public static void main(String[] args) {
        players[0] = new Player();
        players[1] = new Player();
        Scanner scanner = new Scanner(System.in);
        while (true) {

            String str = scanner.nextLine();
            str = str.toLowerCase();
            String input[] = str.split(" ");

            /// to lower_case??
            if (input[0].equals("yield")) {
                System.out.printf("%.2f", players[turn].getScore());
                System.out.print(" ");
                System.out.printf("%.2f\n", players[1 - turn].getScore());
                break;
            }
            if (input[0].equals("done")) {
                //System.err.println("FUCK");
                players[turn].gills += players[turn].getIncome();
                turn++;
                turn %= 2;
                if (turn == 0) days++;
            }
            if (input.length == 2 && input[0].equals("see") && input[1].equals("gills")) {
                System.out.println(players[turn].gills);
            }
            if (input.length == 2 && input[0].equals("see") && input[1].equals("score")) {
                System.out.printf("%.2f\n", players[turn].getScore());
            }
            if (input[0].equals("loot")) {
                players[turn].loot(Integer.parseInt(input[1]));
            }
            if (input[0].equals("attack")) {
                players[turn].attack(Integer.parseInt(input[1]));
            }
            if (input[0].equals("add")) {
                if (input[1].equals("block")) {
                    players[turn].addBlock();
                } else {
                    Unit unit = null;
                    int blockId = Integer.parseInt(input[2]);
                    if (input[1].equals("home")) {
                        int numberOfFloors = Integer.parseInt(input[3]);
                        int numberOfUnits = Integer.parseInt(input[4]);
                        if(numberOfFloors < 3 || numberOfFloors > 6 || numberOfUnits < 1 || numberOfUnits > 4){
                            System.out.println("not possible");
                            continue ;
                        }
                        unit = new Home(numberOfFloors, numberOfUnits);
                    } else if (input[1].equals("bazaar")) {
                        unit = new Bazar();
                    } else if (input[1].equals("army")) {
                        unit = new Army();
                    } else if (input[1].equals("defense")) {
                        unit = new Defence();
                    }
                    players[turn].addUnit(blockId, unit);
                }
            }

            if (input[0].equals("upgrade")) {
                int blockId = Integer.parseInt(input[1]);
                if (input.length == 2) {
                    players[turn].upgradeBlock(blockId);
                } else {
                    int unitId = Integer.parseInt(input[2]);
                    if (input.length == 3) {
                        players[turn].upgradeUnit(blockId, unitId);
                    } else {
                        int floor = 0;
                        int unit = 0;
                        if (input[3].equals("floor") || (input.length == 5 && input[4].equals("floor"))) floor = 1;
                        if (input[3].equals("unit") || (input.length == 5 && input[4].equals("unit"))) unit = 1;
                        players[turn].upgradeHome(blockId, unitId, floor, unit);
                    }
                }
            }

            if (input[0].equals("remove")) {
                int blockId = Integer.parseInt(input[1]);
                if (input.length == 2) {
                    players[turn].removeBlock(blockId);
                } else {
                    int unitId = Integer.parseInt(input[2]);
                    players[turn].removeUnit(blockId, unitId);
                }
            }
        }
    }
}
