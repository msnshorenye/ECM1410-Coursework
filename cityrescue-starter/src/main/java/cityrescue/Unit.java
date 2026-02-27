package cityrescue;

abstract class Unit {
    int ticks = 0;
    private UnitType type;
    int xloc;
    int yloc;
    String STATUS;
    int INCIDENT=-1;
    int WORK;
    public final int UnitID;
    public static int NextID = 1;
    int Stationid;
    //public abstract boolean canHandle(IncidentType type);
}

class Ambulance extends Unit{
    AMBULANCE(int Stationid){
        this.UnitID = NextID++;
        this.ticks = 3;
        this.TYPE = UnitType.POLICE_CAR;
        this.Stationid = Stationid;
        this.STATUS = "IDLE";

    }   
    public void set_station(int StatID){
        this.Stationid = StatID;
    }
     public int get_unit_id(){
        return this.UnitID;
    }
}

class Police_car extends Unit{
    POLICE_CAR(int Stationid){
        this.UnitID = NextID++;
        this.ticks = 3;
        this.TYPE = UnitType.POLICE_CAR;
        this.Stationid = Stationid;
        this.STATUS = "IDLE";
    }
    public void set_station(int StatID){
        this.Stationid = StatID;
    }
     public int get_unit_id(){
        return this.UnitID;
    }
}
class Fire_engine extends Unit{
    FIRE_ENGINE(int Stationid){
        this.UnitID = NextID++;
        this.ticks = 4;
        this.TYPE = UnitType.FIRE_ENGINE; 
        this.Stationid = Stationid;
        this.STATUS = "IDLE";
    }
    public void main(String[] args) {
        FIRE_ENGINE fire = new FIRE_ENGINE();
        fire.get_data();
    }
    public void get_data(){
        System.out.println(TYPE+ticks);
    }
    public void set_station(int StatID){
        this.Stationid = StatID;
    }
    public int get_unit_id(){
        return this.UnitID;
    }

    
}
