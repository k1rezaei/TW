import java.util.ArrayList;

public class Player {
    public Army army = null;
    public int gills = 30000;
    public double baseScore = 0;
    public ArrayList<Block> blocks = new ArrayList<>();

    public int getNextId() {
        int mx = 0;
        for (Block block : blocks) {
            mx = Math.max(mx, block.id);
        }
        return mx + 1;
    }

    public void addBlock() {
        if (gills >= 500) {
            gills -= 500;
            int id = getNextId();
            Block block = new Block(id);
            blocks.add(block);
            System.out.println(id);
        } else {
            System.out.println("not possible");
        }
    }

    public Block getBlock(int id) {
        for (Block block : blocks) {
            if (block.id == id) {
                return block;
            }
        }
        return null;
    }

    public void removeBlock(int id) {
        Block block = getBlock(id);
        if (block != null) {
            if (army.parBlock == block) {
                army = null;
            }
            gills -= block.remove();
            blocks.remove(block);
            return;
        }
        System.out.println("not possible");
    }

    public void upgradeBlock(int id) {
        Block block = getBlock(id);
        if (block == null || block.cap == 25 || gills < block.upgradeCost()) {
            System.out.println("not possible");
            return;
        }
        gills -= block.upgradeCost();
        block.cap += 5;
    }

    public double getScore() {
        double ret = baseScore;
        for (Block block : blocks) {
            ret += block.getScore();
        }
        return ret;
    }

    public void addUnit(int blockId, Unit unit) {
        Block block = getBlock(blockId);
        if (block == null || unit.addCost() > gills || block.units.size() >= block.cap) {
            System.out.println("not possible");
            return;
        }
        if (unit instanceof Army) {
            if (army == null) {
                army = (Army) unit;
            } else {
                System.out.println("not possible");
                return;
            }
        }
        gills -= unit.addCost();
        block.addUnit(unit);
    }

    public void removeUnit(int blockId, int unitId) {
        Block block = getBlock(blockId);
        if (block == null) {
            System.out.println("not possible");
            return;
        }
        Unit unit = block.getUnit(unitId);
        if (unit instanceof Army) {
            army = null;
        }
        if ((unit instanceof Bazar) && gills < 500) {
            System.out.println("not possible");
            return;
        }
        gills -= block.removeUnit(unitId);
    }

    public void upgradeUnit(int blockId, int unitId) {
        Block block = getBlock(blockId);
        if (block == null) {
            System.out.println("not possible");
        }
        Workplace work = (Workplace) (block.getUnit(unitId));
        if (work.level >= work.maxLevel) {
            System.out.println("not possible");
            return;
        }
        if (work != null && work.upgradeCost() <= gills) {
            gills -= work.upgradeCost();
            work.upgrade();
            return;
        }
        System.out.println("not possible");
    }

    public void upgradeHome(int blockId, int unitId, int floor, int units) {
        Block block = getBlock(blockId);
        if (block == null) {
            System.out.println("not possible");
            return;
        }
        Home home = (Home) (block.getUnit(unitId));
        if (home != null && gills >= home.upgradeCost(floor, units)) {
            gills -= home.upgradeCost(floor, units);
            home.upgrade(floor, units);
        } else {
            System.out.println("not possible");
        }
    }

    public void attack(int blockId) {
        Player other = Game.players[1 - Game.turn];
        Block block = other.getBlock(blockId);
        if (gills >= 5000 && army != null && block != null && (block.defence == null || (block.defence.level < army.level))) {
            baseScore += block.getScore();
            other.removeBlock(blockId);
            other.gills -= 500;
            gills -= 5000;
        } else {
            System.out.println("not possible");
            return;
        }
    }

    public void loot(int blockId) {
        Player other = Game.players[1 - Game.turn];
        Block block = other.getBlock(blockId);
        if (army != null && block != null && block.defence == null) {
            gills += 500 * block.units.size();
        } else {
            System.out.println("not possible");
            return;
        }
    }

    public int getIncome() {
        int income = 0;
        for (Block block : blocks) {
            income += block.getIncome();
        }
        return income;
    }

}