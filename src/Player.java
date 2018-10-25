import java.lang.Math;
import java.util.ArrayList;

public class Player{
    public Army army=null;
    public int gills =30000;
    public double baseScore=0;
    public ArrayList<Block> blocks=new ArrayList<>();
    public int getNextId(){
        int mx=0;
        for(Block block:blocks){
            mx=Math.max(mx,block.id);
        }
        return mx+1;
    }
    public void addBlock(){
        if(gills>=500) {
            int id = getNextId();
            Block block = new Block(id);
            blocks.add(block);
            System.out.println(id);
        }
        else{
            System.out.println("not possible");
        }
    }
    public Block getBlock(int id){
        for(Block block:blocks){
            if(block.id==id){
                return block;
            }
        }
        return null;
    }
    public void removeBlock(int id){
        Block block =getBlock(id);
        if(block!=null){
            gills-=block.remove();
            blocks.remove(block);
            return;
        }
        System.out.println("not possible");
    }
    public void upgradeBlock(int id){
        Block block=getBlock(id);
        if(block==null || block.cap==25 || gills<block.upgradeCost()){
            System.out.println("not possible");
            return;
        }
        gills-=block.upgradeCost();
        block.cap+=5;
    }
    public double getScore(){
        double ret=baseScore;
        for(Block block:blocks){
            ret+=block.getScore();
        }
        return ret;
    }
    public void addUnit(int blockId,Unit unit){
        Block block=getBlock(blockId);
        if(block==null || unit.addCost()>gills || block.units.size()>=block.cap){
            System.out.println("not possible");
            return;
        }
        if(unit instanceof  Army){
            if(army==null){
                army=(Army)unit;
            }
            else{
                System.out.println("not possible");
                return;
            }
        }
        gills-=unit.addCost();
        block.addUnit(unit);
    }
    public void removeUnit(int blockId,int unitId){
        Block block=getBlock(blockId);
        if(block==null){
            System.out.println("not possible");
        }
        Unit unit =block.getUnit(unitId);
        if(unit instanceof Army){
            army=null;
        }
        if(unit instanceof  Bazar && gills<500){
            System.out.println("not possible");
            return;
        }
        gills-=block.removeUnit(unitId);
    }
    public void upgradeUnit(int blockId,int unitId){
        Block block=getBlock(blockId);
        if(block==null){
            System.out.println("not possible");
        }
        Workplace unit=(Workplace)block.getUnit(unitId);
        if(unit.level>=unit.maxLevel){
            System.out.println("not possible");
            return;
        }
        if(unit!=null && unit.upgradeCost()<=gills){
            gills-=unit.upgradeCost();
            unit.upgrade();
            return;
        }
        System.out.println("not possible");
    }
    public void upgradeHome(int blockId,int unitId,int floor,int units){
        Block block=getBlock(blockId);
        if(block==null){
            System.out.println("not possible");
        }
        Home unit=(Home)block.getUnit(unitId);
        if(unit!=null && gills>=unit.upgradeCost(floor,units)){
            gills-=unit.upgradeCost(floor,units);
            unit.upgrade();
        }
        else{
            System.out.println("not possible");
        }
    }
    public void attack(int blockId){
        if(gills<5000){
            System.out.println("not possible");
            return;
        }
        Player other=Game.players[1-Game.turn];
        Block block=other.getBlock(blockId);
        if(block==null){
            System.out.println("not possible");
            return;
        }
        baseScore+=block.getScore();
        other.removeBlock(blockId);
    }
    public void loot(int blockId){

    }
    public int getIncome(){
        int income=0;
        for(Block block :blocks){
            income+=block.getIncome();
        }
        return income;
    }



}