public class Home {
    int numberOfUnits=0;
    int numberOfFloors=0;

    Home(int numOfFloors,int numOfUnits){
        numberOfUnits = numOfUnits;
        numberOfFloors = numOfFloors;
    }

    int upgradeCost(int addedFloor, int addedUnit){
        return  addedUnit*50 + addedFloor*300;
    }

    int addCost(int numOfFloors,int numOfUnits){
        return  numOfUnits*100+numOfFloors*300+700;
    }

    void upgrade(int numOfFloors,int numOfUnits){
        if(numOfFloors == 1) numberOfFloors++;
        if(numOfUnits == 1) numberOfUnits++;
    }

    double getScore(){
        int person = 1;
        int sumPerson = 5*person;
        int unit = 2+sumPerson;
        int sumUnit = numberOfUnits*unit;
        int floor = 3+sumUnit+sumPerson*2;
        int sumFloor = floor*numberOfFloors;
        int home = 10+sumFloor+sumUnit*2+sumPerson*3;
        return home;
    }

    int getPopulation(){
        return numberOfFloors*numberOfUnits*5;
    }
}
