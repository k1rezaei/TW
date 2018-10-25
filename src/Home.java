public class Home {
    int numberOfUnits=0;
    int numberOfFloors=0;

    private Home(){

    }
    Home(int f,int u){
        Home home = new Home();
        home.numberOfUnits = u;
        home.numberOfFloors = f;
    }

    int upgradeCost(int addedFloor, int addedUnit){
        return  addedUnit*50 + addedFloor*300;
    }

    int addCost(int f,int u){
        return  u*100+f*300+700;
    }

    void upgrade(int f,int u){
        if(f == 1) numberOfFloors++;
        if(u == 1) numberOfUnits++;
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
}
