package cityrescue;
import cityrescue.Unit;
import cityrescue.enums.UnitStatus;
import cityrescue.enums.UnitType;
/**
 * The police car class is a subclass of unit 
 * that services incident objects of type crime
 * The constructor uses the abstract classes 
 * Unit Set methods to give default values to
 * the Police car class attributes which are
 * the attributes outlined in the Unit
 * abstract class other than the 
 * attributes returned by overriden methods.
 * 
 */
class Police_car extends Unit{
/**
* The constructor uses the abstract classes 
 * Unit Set methods to give default values to
 * the ambulance attributes which are
 * the attributes outlined in the Unit
 * abstract class other than the 
 * attributes returned by overriden methods.
 * @param Stationid 
 * The Id is given to be set as an attribute
 * that stores which Station the Unit(an ambulance) created is stationed
* 
*/
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
 * of ticks that a Police Car takes to complete an Incident at the scene.
 */
    @Override
    public int get_unit_ticks(){
        return 3;
    }
    /**
     * Police Car overides the get unit type method 
     * to return the Police Car type.
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