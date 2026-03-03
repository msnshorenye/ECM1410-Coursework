package cityrescue;
import cityrescue.Unit.*;
import cityrescue.enums.UnitType;
import cityrescue.enums.UnitStatus;
class Fire_engine extends Unit{
    public Fire_engine(int Stationid){
        this.UnitID = -1;
        this.ticks = 4;
        this.TYPE = UnitType.FIRE_ENGINE; 
        this.Stationid = Stationid;
        this.STATUS = UnitStatus.IDLE;
        this.xloc =-1;
        this.yloc =-1;
        this.AssignedIncidentId =-1;
        this.WORK = 0;
    } 
    // public void main(String[] args) {
    //     Fire_engine fire = new Fire_engine();
    //     fire.get_data();
    // }
    public void get_data(){
        System.out.println(this.TYPE+ " " +this.ticks);
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
}