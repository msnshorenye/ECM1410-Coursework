package cityrescue;
import cityrescue.enums.UnitType;
import cityrescue.enums.UnitStatus;
/**
 * this is an abstract super class which is used by different subclasses to inherit universal charecterstics and attributes without Unit being able to be instantiated itsellf.
 *  it is used to set up unitsso each have the universal get and set variables for universal attributes
 * 
*/
abstract class Unit {
    // all variables set to a default value and privated
    private int ticks = 0; //get rid of
    private UnitType TYPE; //get rid of
    private int xloc = -1; //privated
    private int yloc =-1; // privated
    private UnitStatus STATUS = UnitStatus.IDLE; //
    private int AssignedIncidentId=-1;
    private int WORK;
    private int UnitID =-1;
    private int Stationid =-1;
    //public abstract boolean canHandle(IncidentType type);
    public abstract int get_unit_ticks(); // will be overiden in subclasses to specific tick time
    public abstract UnitType get_unit_type(); // will be overiden in subclasses to specific type

    public Unit() {}

    /**
     * @return UnitID 
     * this will return the private attribute Unitid which is set when unit is made
     */
    public int get_unit_id(){
        
        return this.UnitID;
    }
    /**
     * @param NewUnitID 
     * THis is a temporary id which is used when unit id is reassigned so private variable unit id is reassinged
     * This has no return as it is a set variable
     */
    public void SetUnitID(int NewUnitID){
        this.UnitID = NewUnitID;

    }
    /**
     * @param new_x
     * This is used when the Units x location needs to be changed
     * It will take value inputted and assign new value to private variable xloc
     */
    public void set_xloc(int new_x){
        this.xloc = new_x;
    }
    /**
     * @return xloc
     * This is used to access objects private variable xloc and return it 
     */
    public int get_xloc(){
        return this.xloc;
    }
    /**
     * @param new_y
     * This is used when the units y location needs to be changed
     * It will take value inputted and assign new value to private variable yloc
     */
    public void set_yloc(int new_y){
        this.yloc = new_y;
    }
    /**
     * @return yloc
     * This is used to access objects private variable yloc and return it 
     */
    public int get_yloc(){
        return this.yloc;
    }
    /**
     * @param status
     * This is used to change the status of the unit it requires a member of the enum 
     */
    public void set_status(UnitStatus status){
        this.STATUS = status;
    }
    /**
     * @return STATUS
     * This is used to access the status of the object and will return it
     */
    public UnitStatus get_status(){
        return this.STATUS;
    }
    /**
     * @param new_id
     * THis is used to recieve the new id to assign to the incident that unit has been attached too stored in AssignedIncidentId variable
     */
    public void set_incidentid(int new_id){
        this.AssignedIncidentId = new_id;
    }
    /**
     * @param new_work
     * This will change the objects Work count and will be used when either resetting work to 0 or it is being incremented in a tick 
     * it is passed a new value and willset WORK to the new value
     */
    public void set_WORK(int new_work){
        this.WORK = new_work;
    }
    /**
     * @return WORK
     * this is used to return the objects WORK value
     */
    public int get_WORK(){
        return this.WORK;
    }
    /**
     * @return Stationid
     * this will access the private variable stationid of the object and will return it
     */
    public int get_station(){
        return this.Stationid;
    }
    /**
     * @param newstation
     * this will take the new station id and set private attribute Stationid to it 
     */
    public void set_station(int newstation){
        this.Stationid = newstation;
    }
    /**
     * @param newticks
     * this will change the private variable ticks and assign passed variable to it
     */
    public void set_unit_ticks(int newticks){
        this.ticks = newticks; 
    }
    /**
     * @param newincidentid
     * this will take in a new incident and assign it to the private variable AssignedIncidentId
     */
    public void SetAssignedIncidentId(int newincidentid){
        this.AssignedIncidentId = newincidentid; 
    }
    /**
     * @return AssignedIncidentId
     * This is used to access the objects private attribute unit id and return it 
     */
    public int GetAssignedIncidentId(){
        return this.AssignedIncidentId;
    }
    /**
     * @return view_string 
     * this method will return the full list of attributes attached to the object it has two return options 
     * It will  depend on whether WORK is 0 or not as this is a required difference used in get status and view incident as work should not be outputted if it is not in use
     */
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
    /**
     * @param Utype
     * This will take a variable of enum UnitType and assign the new type to the objects private variable TYPE
     */
    public void SetUnitType(UnitType Utype){
        this.TYPE = Utype;
    }
}





