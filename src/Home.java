public class Home extends Unit{
    int numberOfUnits=0;
    int numberOfFloors=0;

    public Home(int numOfFloors,int numOfUnits){
        numberOfUnits = numOfUnits;
        numberOfFloors = numOfFloors;
    }

    public int upgradeCost(int addedFloor, int addedUnit){
        return  addedFloor*300 + addedFloor * (numberOfUnits + addedUnit)* 50 + addedUnit * numberOfFloors * 50;
    }

    public int addCost(int numOfFloors,int numOfUnits){
        return  numOfUnits * 100 + numOfFloors * 300 + 700;
    }

    public void upgrade(int numOfFloors,int numOfUnits){
        if(numOfFloors == 1) numberOfFloors++;
        if(numOfUnits == 1) numberOfUnits++;
    }

    public void upgrade () {}

    double getScore(){
        int person = parBlock.getMultiplier();
        int sumPerson = 5*person;
        int unit = 2+sumPerson;
        int sumUnit = numberOfUnits*unit;
        int floor = 3+sumUnit+sumPerson*2;
        int sumFloor = floor*numberOfFloors;
        int home = 10+sumFloor+sumUnit*2+sumPerson*3;
        return home;
    }

    public int remove (){
        return 0;
    }

    int getPopulation(){
        return numberOfFloors*numberOfUnits*5;
    }
}
