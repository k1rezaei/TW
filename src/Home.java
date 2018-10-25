public class Home extends Unit {
    int numberOfUnits = 0;
    int numberOfFloors = 0;

    public Home(int numOfFloors, int numOfUnits) {
        numberOfUnits = numOfUnits;
        numberOfFloors = numOfFloors;
    }

    public int upgradeCost(int addedFloor, int addedUnit) {
        return addedFloor * 300 + addedFloor * (numberOfUnits + addedUnit) * 50 + addedUnit * numberOfFloors * 50;
    }

    public int addCost() {
        return numberOfUnits * numberOfFloors * 100 + numberOfFloors * 300 + 700;
    }

    public void upgrade(int numOfFloors, int numOfUnits) {
        if (numOfFloors == 1) numberOfFloors++;
        if (numOfUnits == 1) numberOfUnits++;
    }

    public void upgrade() {
    }

    public double getScore() {
        double person = parBlock.getMultiplier();
        double sumPerson = 5 * person;
        double unit = 2 + sumPerson;
        double sumUnit = numberOfUnits * unit;
        double floor = 3 + sumUnit + sumPerson * numberOfUnits * 2;
        double sumFloor = floor * numberOfFloors;
        double home = 10 + sumFloor +  numberOfFloors * sumUnit * 2 +  numberOfUnits * numberOfFloors * sumPerson * 3;
        return home+sumFloor+sumUnit*numberOfFloors+sumPerson*numberOfFloors*numberOfUnits;
    }

    public int remove() {
        return 0;
    }

    int getPopulation() {
        return numberOfFloors * numberOfUnits * 5;
    }
}
