package cityrescue;
import cityrescue.Unit;
import cityrescue.enums.UnitStatus;
import cityrescue.enums.UnitType;
class Police_car extends Unit{
    public Police_car(int Stationid){
        super();
        // this.UnitID = -1;
        // this.TYPE = UnitType.POLICE_CAR;
        // this.Stationid = Stationid;
        // this.STATUS = UnitStatus.IDLE;
        // this.WORK =0;
        // this.xloc =-1;
        // this.yloc = -1;
        // this.AssignedIncidentId = -1;
    }
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
        return 3;
    }
    @Override
    public UnitType get_unit_type(){
        return UnitType.POLICE_CAR;
    }
}