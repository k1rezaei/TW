import java.util.ArrayList;

public class Workplace {
    public ArrayList<Integer> upgradeDates = new ArrayList<Integer>();
    public int level = 0, initialScore, initialWorker, deltaWorker;

    public Workplace(int initialScore, int initialWorker, int deltaWorker) {
        this.initialScore = initialScore;
        this.initialWorker = initialWorker;
        this.deltaWorker = deltaWorker;
        this.level = 1;
        upgradeDates.add(Game.days);
        return ;
    }

    public double getScore(){
        return Math.pow(initialScore, Game.days - upgradeDates.get(0) + 1);
    }

    public int getIncome() {
        int curr = Game.days, res = initialWorker * (curr - upgradeDates.get(0));
        for (int i=1; i<upgradeDates.size(); i++) {
            res += deltaWorker * (curr - upgradeDates.get(i));
        }
        return res;
    }

}
