package cityrescue;
import cityrescue.Unit.*;
import cityrescue.enums.UnitType;
import cityrescue.enums.UnitStatus;
class Fire_engine extends Unit{
    public Fire_engine(int Stationid){
        SetUnitID(-1);
        set_xloc(-1);
        set_yloc(-1);
        set_status(UnitStatus.IDLE);
        set_WORK(0);
        set_station(Stationid);
        SetAssignedIncidentId(-1);
        SetUnitType(UnitType.FIRE_ENGINE);


        //this.ticks = 4;
        //this.TYPE = UnitType.FIRE_ENGINE; 
        // this.Stationid = starterUnit.get_station();
        // this.STATUS = UnitStatus.IDLE;
        // this.xloc = starterUnit.get_xloc();
        // this.yloc =-1;
        // this.AssignedIncidentId =-1;
        // this.WORK = 0;
    } 
    // public void main(String[] args) {
    //     Fire_engine fire = new Fire_engine();
    //     fire.get_data();
    // }
    @Override
    public int get_unit_ticks(){
        return 4;
    }
    @Override
    public UnitType get_unit_type(){
        return UnitType.FIRE_ENGINE;
    }

}