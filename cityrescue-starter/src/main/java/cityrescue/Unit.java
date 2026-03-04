package cityrescue;
import cityrescue.enums.UnitType;
import cityrescue.enums.UnitStatus;

abstract class Unit {
    private int ticks = 0; //get rid of
    private UnitType TYPE; //get rid of
    private int xloc = -1; //privated
    private int yloc =-1; // privated
    private UnitStatus STATUS = UnitStatus.IDLE; //
    private int AssignedIncidentId=-1;
    private int WORK;
    private int UnitID =-1;
    private int Stationid =-1;
    //public abstract boolean canHandle(IncidentType type);
    public abstract int get_unit_ticks(); 
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
    public void set_WORK(int new_work){
        this.WORK = new_work;
    }
    public int get_WORK(){
        return this.WORK;
    }
    public int get_station(){
        return this.Stationid;
    }
    public void set_station(int newstation){
        this.Stationid = newstation;
    }
    public void set_unit_ticks(int newticks){
        this.ticks = newticks; 
    }
    public void SetAssignedIncidentId(int newincidentid){
        this.AssignedIncidentId = newincidentid; 
    }
    public int GetAssignedIncidentId(){
        return this.AssignedIncidentId;
    }
      public String unitview(){
        String view_string;
        if (this.WORK != 0){
            view_string = "U#" + this.UnitID + " TYPE=" + this.TYPE + " HOME=" + this.Stationid + "LOC=(" + this.xloc +"," + this.yloc + ") STATUS=" + this.STATUS +" INCIDENT=" + this.AssignedIncidentId+" WORK="+this.WORK;
        }
        else{
            view_string = "U#" + this.UnitID + " TYPE=" + this.TYPE + " HOME=" + this.Stationid + "LOC=(" + this.xloc +"," + this.yloc + ") STATUS=" + this.STATUS +" INCIDENT=" + this.AssignedIncidentId;

        }
        return view_string;
    }
    public void SetUnitType(UnitType Utype){
        this.TYPE = Utype;
    }
}





