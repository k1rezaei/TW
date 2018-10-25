import java.util.ArrayList;

public class Block {
    public int id;
    public int cap;
    public ArrayList<Unit> units=new ArrayList<>();
    Block(int id){
        this.id=id;
    }
    int remove(){
        return 0;
    }
    double getScore(){
        return 0;
    }
    void addUnit(Unit unit){
        units.add(unit);
    }
}
