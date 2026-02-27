package cityrescue;
import cityrescue.Unit;
import cityrescue.enums.UnitType;
import cityrescue.enums.UnitStatus;
class Ambulance extends Unit{
    void AMBULANCE(int Stationid){
        this.UnitID = NextID++;
        this.ticks = 3;
        this.TYPE = UnitType.POLICE_CAR;
        this.Stationid = Stationid;
        this.STATUS = UnitStatus.IDLE;

    }   
    public void set_station(int StatID){
        this.Stationid = StatID;
    }
     public int get_unit_id(){
        return this.UnitID;
    }
}