package cityrescue;
import cityrescue.Unit;
import cityrescue.enums.UnitStatus;
import cityrescue.enums.UnitType;
class Police_car extends Unit{
    public Police_car(int Stationid){
        SetUnitID(-1);
        set_xloc(-1);
        set_yloc(-1);
        set_status(UnitStatus.IDLE);
        set_WORK(0);
        set_station(Stationid);
        SetAssignedIncidentId(-1);
        SetUnitType(UnitType.POLICE_CAR);
        // this.UnitID = -1;
        // this.TYPE = UnitType.POLICE_CAR;
        // this.Stationid = Stationid;
        // this.STATUS = UnitStatus.IDLE;
        // this.WORK =0;
        // this.xloc =-1;
        // this.yloc = -1;
        // this.AssignedIncidentId = -1;
    }
    
/**
 * Police car Overides Units abstract get methid get unit ticks method to return an integer value.
 * @return It returns integer 3 as that is the exact amount
 * of ticks that a Fire engine takes to complete an Incident at the scene.
 */
    @Override
    public int get_unit_ticks(){
        return 3;
    }
    /**
     * Police Car overides the get unit type method 
     * to return the Fire engine type.
     *  This means long switch and if statements are not required as
     * a Unit subclass object can call a method to get its exact 
     * type regardless of which subclass object has been created.
     * 
     */
    @Override
    public UnitType get_unit_type(){
        return UnitType.POLICE_CAR;
    }
}