public class Defence extends Workplace {
    private static final int UPGRADE_COST =  5000;
    private static final int ADD_COST = 10000;
    private static final int REMOVE_COST = -10000;
    public static final int MAX_LEVEL = 5;

    public Defence() {
        super(15, 30, 0);
        return ;
    }

    public static int upgradeCost() {
        return UPGRADE_COST;
    }

    public static int addCost () {
        return ADD_COST;
    }

    public void upgrade() {
        upgradeDates.add(Game.days);
        level ++;
    }

    public int remove () {
        return REMOVE_COST;
    }

}
