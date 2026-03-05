package cityrescue;
import cityrescue.Unit.*;
import cityrescue.enums.UnitType;
import cityrescue.enums.UnitStatus;
/**
 * Fire engine class is a subclass of unit 
 * that services incident objects of type fire
 * The constructor uses the abstract classes 
 * Unit Set methods to give default values to
 * the Fire engines attributes which are
 * the attributes outlined in the Unit
 * abstract class other than the 
 * attributes returned by overriden methods.
 * 
 */
class Fire_engine extends Unit{
/**
* The constructor uses the abstract classes 
 * Unit Set methods to give default values to
 * the Fire engines attributes which are
 * the attributes outlined in the Unit
 * abstract class other than the 
 * attributes returned by overriden methods.
 * 
 * @param Stationid The Id is given to be set as an attribute
 * that stores which Station the Unit(a fire engine) created is stationed
 * 
 */
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
     * of ticks that a Fire engine takes to complete an Incident at the scene.
     */
    @Override
    public int get_unit_ticks(){
        return 4;
    }
    /**
     * Fire engine overides the get unit type method 
     * to return the Fire engine type.
     *  This means long switch and if statements are not required as
     * a Unit subclass object can call a method to get its exact 
     * type regardless of which subclass object has been created.
     */
    @Override
    
    public UnitType get_unit_type(){
        return UnitType.FIRE_ENGINE;
    }

}