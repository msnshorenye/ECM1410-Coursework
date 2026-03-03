package cityrescue;
import cityrescue.enums.UnitType;
import cityrescue.enums.UnitStatus;

abstract class Unit {
    int ticks = 0; //get rid of
    public UnitType TYPE; //get rid of
    public int xloc; //privated
    public int yloc; // privated
    private UnitStatus STATUS; //
    public int AssignedIncidentId=-1;
    public int WORK;
    public int UnitID;
    int Stationid;
    //public abstract boolean canHandle(IncidentType type);
    public abstract String unitview();
    public abstract int get_unit_ticks(); 
    public abstract void set_station(int StatID);
    public abstract UnitType get_unit_type();

    public Unit() {}

    public int get_unit_id(){
        return this.UnitID;
    }
    public void SetUnitID(int NewUnitID){
        this.UnitID = NewUnitID;

    }
    public void set_xloc(int new_x){
        this.xloc = new_x;
    }
    public int get_xloc(){
        return this.xloc;
    }
    public void set_yloc(int new_y){
        this.yloc = new_y;
    }
    public int get_yloc(){
        return this.yloc;
    }
    public void set_status(UnitStatus status){
        this.STATUS = status;
    }
    public UnitStatus get_status(){
        return this.STATUS;
    }
    public void set_incidentid(int new_id){
        this.AssignedIncidentId = new_id;
    }
    public int get_incidentid(){
        return AssignedIncidentId;
    }
    public void set_WORK(int reset){
        this.WORK = reset;
    }
    public int get_WORK(){
        return this.WORK;
    }
    public int get_station(){
        return this.Stationid;
    }
}





