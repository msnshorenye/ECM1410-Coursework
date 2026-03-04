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
    /**
     * Fire engine Overides Units abstract get methid get unit ticks method to return an integer value.
     * @return It returns integer 4 as that is the exact amount
     * of ticks that a Fire engine takes to complete an Incident.
     */
    @Override
    public int get_unit_ticks(){
        return 4;
    }
    /**
     * Fire engine overides the get unit type method 
     * to return the
     */
    @Override
    
    public UnitType get_unit_type(){
        return UnitType.FIRE_ENGINE;
    }

}