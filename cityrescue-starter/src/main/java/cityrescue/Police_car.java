package cityrescue;
import cityrescue.Unit;
import cityrescue.enums.UnitStatus;
import cityrescue.enums.UnitType;
class Police_car extends Unit{
    public Police_car(int Stationid){
        this.UnitID = NextID++;
        this.ticks = 3;
        this.TYPE = UnitType.POLICE_CAR;
        this.Stationid = Stationid;
        this.STATUS = UnitStatus.IDLE;
        this.xloc =-1;
        this.yloc = -1;
        this.AssignedIncidentId = -1;
    }
    public void set_station(int StatID){
        this.Stationid = StatID;
    }
     public int get_unit_id(){
        return this.UnitID;
    }
    public String unitview(){
        String view_string = "U#" + this.UnitID + " TYPE=" + this.TYPE + " HOME=" + this.Stationid + " LOC=(" + this.xloc +"," + this.yloc + ") STATUS=" + this.STATUS +" INCIDENT=" + this.AssignedIncidentId ;
        return view_string;
    }
}