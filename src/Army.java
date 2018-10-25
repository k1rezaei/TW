public class Army extends Workplace{
    private static final int UPGRADE_COST =  20000;
    private static final int ADD_COST = 15000;
    private static final int REMOVE_COST = -10000;
    public static final int MAX_LEVEL = 5;

    public Army() {
        super(10, 100, 10);
        return ;
    }

    public  int upgradeCost() {
        return UPGRADE_COST;
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

}
