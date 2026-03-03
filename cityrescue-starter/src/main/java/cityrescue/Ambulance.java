package cityrescue;
import cityrescue.Unit;
import cityrescue.enums.UnitType;
import cityrescue.enums.UnitStatus;
class Ambulance extends Unit{
    public Ambulance(int Stationid){
        super();
        // this.UnitID = -1;
        // this.ticks = 2;
        // this.TYPE = UnitType.AMBULANCE;
        // this.Stationid = Stationid;
        // this.STATUS = UnitStatus.IDLE;
        // this.xloc = -1;
        // this.yloc = -1;
        // this.AssignedIncidentId = -1;
        // this.WORK = 0;
    }   
    @Override
    public void set_station(int StatID){
        this.Stationid = StatID;
    }
    
     public int get_unit_id(){
        return this.UnitID;
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
    @Override
    public int get_unit_ticks(){
        return 2;
    }
    @Override
    public UnitType get_unit_type(){
        return UnitType.AMBULANCE;
    }
}