import java.util.ArrayList;

public class Block {
    private static final int REMOVE_COST = -500;
    public int id;
    public int cap;
    public ArrayList<Unit> units = new ArrayList<>();
    public Defence defence = null;

    public Block(int id) {
        this.id = id;
        this.cap = 15;
    }

    public int remove() {
        return REMOVE_COST;
    }

    public double getScore() {
        double totalScore = 0;
        for (Unit unit : units) {
            if (unit != null) {
                totalScore += unit.getScore();
            }
        }
        return totalScore;
    }

    public void addUnit(Unit unit) {
        int maximum = 0;
        for (Unit tUnit : units) {
            maximum = Math.max(maximum, tUnit.id);
        }
        if (unit instanceof Defence) defence = (Defence) unit;
        units.add(unit);
        maximum++;
        unit.id = maximum;
        System.out.println(unit.id);
        unit.parBlock = this;
    }

    public int removeUnit(int unitId) {
        int cost = 0;

        for (Unit unit : units) {
            if (unit.id == unitId) {
                cost += unit.remove();
                if (unit instanceof Defence) defence = null;
                units.remove(unit);
                return cost;
            }
        }
        System.out.println("not possible");
        return 0;
    }

    public int getIncome() {
        int totalIncome = 0;
        for (Unit unit : units) {
            if (unit == null) continue;
            if (unit instanceof Workplace)
                totalIncome += ((Workplace) (unit)).getIncome();
            else
                totalIncome += ((Home) (unit)).getPopulation();
        }
        totalIncome *= 100;
        return totalIncome;
    }

    public double getMultiplier() {
        double mul = 1;
        for (Unit unit : units) {
            if (unit == null) continue;
            if (unit instanceof Bazar)
                mul *= ((Bazar) (unit)).getMultiplier();
        }
        return mul;
    }

    public void upgrade() {
        cap += 5;
        return;
    }

    public int upgradeCost() {
        int tavan = (cap - 15) / 5 + 1;
        int cost = 1;
        for (int i = 0; i < tavan; i++)
            cost *= 500;
        return cost;
    }

    public Unit getUnit(int unitId) {
        for (Unit unit : units) {
            if (unit == null) continue;
            if (unit.id == unitId) return unit;
        }
        return null;
    }

}
