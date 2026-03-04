package cityrescue;
import cityrescue.Unit;
import cityrescue.enums.UnitType;
import cityrescue.enums.UnitStatus;
class Ambulance extends Unit{
    public Ambulance(int Stationid){
        SetUnitID(-1);
        set_xloc(-1);
        set_yloc(-1);
        set_status(UnitStatus.IDLE);
        set_WORK(0);
        set_station(Stationid);
        SetAssignedIncidentId(-1);
        SetUnitType(UnitType.AMBULANCE);
        
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
    public int get_unit_ticks(){
        return 2;
    }
    
    @Override
    public UnitType get_unit_type(){
        return UnitType.AMBULANCE;
    }
}