public class Bazar extends Workplace{
    private static final int UPGRADE_COST =  5000;
    private static final int ADD_COST = 6000;
    private static final int REMOVE_COST = 500;

    public Bazar() {
        super(5, 50, 20,3);
        return ;
    }

    public int upgradeCost() {
        return (level + 1) * UPGRADE_COST;
    }

    public int addCost () {
        return ADD_COST;
    }

    public void upgrade() {
        upgradeDates.add(Game.days);
        level ++;
    }

    public int remove () {
        return REMOVE_COST;
    }

    public double getMultiplier() {
        return (double)(1 + 0.2 * level);
    }

}
