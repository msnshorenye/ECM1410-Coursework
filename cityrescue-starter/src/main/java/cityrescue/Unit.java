package cityrescue;

abstract class Unit {
    int ticks = 0;
    private UnitType type;
    int xloc;
    int yloc;
    String STATUS;
    int INCIDENT=-1;
    int WORK;

    public void main(String[] args) {
        System.out.println(TYPE+ticks);
    }

    //public abstract boolean canHandle(IncidentType type);
}

class Ambulance extends Unit{
    public void main(String[] args) {
        System.out.println("hello");
    }
    
}

class police_car extends Unit{
    POLICE_CAR(){
    this.ticks = 3;
    this.TYPE = "POLICE_CAR";
    }
}
class fire_engine extends Unit{
    FIRE_ENGINE(){
    this.ticks = 4;
    this.TYPE = "FIRE_ENGINE";
    }
    public void main(String[] args) {
        FIRE_ENGINE fire = new FIRE_ENGINE();
        fire.get_data();
    }
    public void get_data(){
        System.out.println(TYPE+ticks);
    }

}
