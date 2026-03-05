package cityrescue;
import cityrescue.Unit;
import cityrescue.enums.UnitType;
import cityrescue.enums.UnitStatus;
/**
 * Ambulance class is a subclass of unit 
 * that services incident objects of type medical.
 * The constructor uses the abstract classes 
 * Unit Set methods to give default values to
 * the Ambulances attributes which are
 * the attributes outlined in the Unit
 * abstract class other than the 
 * attributes returned by overriden methods.
 * 
 */
class Ambulance extends Unit{
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
    
    /**
     * Ambulance Overides Units abstract get method  unit ticks method to return an integer value.
 * @return It returns integer 2 as that is the exact amount
 * of ticks that a ambulance takes to complete an Incident at the scene.
 */
    
    @Override
    public int get_unit_ticks(){
        return 2;
    }
    
    /**
     * Ambulance overides the get unit type method 
     * to return the Ambulance type.
     *  This means long switch and if statements are not required as
     * a Unit subclass object can call a method to get its exact 
     * type regardless of which subclass object has been created.
     */
    @Override
    public UnitType get_unit_type(){
        return UnitType.AMBULANCE;
    }
}