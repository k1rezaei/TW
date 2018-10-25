import java.lang.Math;
import java.util.ArrayList;

public class Player{
    public Army army=null;
    public int gills =30000;
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
            gills+=500;
            blocks.remove(block);
            return;
        }
        System.out.println("not possible");
    }
    public void upgradeBlock(int id){
        Block block=getBlock(id);
        if(block==null || block.cap==30 || gills<Math.pow(500,(block.cap-15)/5)){
            System.out.println("not possible");
            return;
        }
    }
    public double getScore(){
        double ret=0;
        for(Block block:blocks){
            ret+=block.getScore();
        }
        return ret;
    }
    public void addUnit(int blockId,Unit unit){
        Block block=getBlock(blockId);
        if(block==null || unit.addCost()>gills || block.units.size()>block.cap){
            System.out.println("not possible");
            return;
        }
        gills-=unit.addCost();
        block.addUnit(unit);
    }
    public void removeUnit(int blockId,int unitId){
        Block block=getBlock(blockId);
        if(block==null){
            System.out.println("not possible");
        }
        gills-=block.removeUnit(unitId);
    }
    public void upgradeUnit(int blockId,int unitId){
        Block block=getBlock(blockId);
        if(block==null){
            System.out.println("not possible");
        }
        Unit unit=block.getUnit(unitId);
        if(unit==null && unit.upgradeCost()<=gills){
            gills-=unit.upgradeCost();
            unit.upgrade();
            return;
        }
        System.out.println("not possible");

    }


}