import java.lang.Math;
import java.util.ArrayList;

public class Player{
    public Army army=null;
    public int gills =30000;
    public ArrayList<Block> blocks=new ArrayList<>();
    int getNextId(){
        int mx=0;
        for(Block block:blocks){
            mx=Math.max(mx,block.id);
        }
        return mx+1;
    }
    void addBlock(){
        int id=getNextId();
        Block block =new Block(id);
        blocks.add(block);
        System.out.println(id);
    }
    void removeBlock(int id){
        for(Block block:blocks){
            if(block.id==id){
                gills-=block.remove();
                blocks.remove(block);
                return;
            }
        }
        System.out.println("not possible");
    }
    double getScore(){
        double ret=0;
        for(Block block:blocks){
            ret+=block.getScore();
        }
        return ret;
    }

}