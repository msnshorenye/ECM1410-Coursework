package cityrescue;

import cityrescue.enums.IncidentStatus;
import cityrescue.enums.IncidentType;
/**
 * Incident is an object which class is the target of the units
 *  and it has a types which match with unit. It placed onto the grid 
 * and then using get methods the closest unit is calculated 
 * from this position in the grid. It has an id that is used to identify
 * each individual object and assign 
 * these individual incidents to other
 * individual units. It also has a status
 * which goes through a lifecycle
 * from when a unit is assigned to it 
 * to when the unit arrives and completes the work. 
 */

public class Incident {
    private int x;
    private int y;
    private IncidentStatus status;
    private IncidentType Type;
    private static int NextIncidentID = 1;
    private int IncidentID;
    private int severity;
    private int Unitid;
    /**
     * Incident constructor sets status 
     * to a default value of Reported as the base 
     * enum status as That is the start of the incident
     * lifecycle Unit id is set for a default value of zero
     * and using a set method will be set in dispatch.
     * 
     * @param x parameter is passed in 
     * to set the x coordinate attribute position of the station object
     * @param y parameter is passed in 
     * to set the y coordinate attribute position of the station object
     * @param Type The Type enum is passed in to determine what type of Incident
     * should be instatiated. (This matches with a subclass of unit)
     * @param severity The severity level of the specific incident is passed in
     * and is set as the severity attribute 
     */
    
    public Incident(int x, int y, IncidentType Type, int severity ){
        this.x = x;
        this.y = y;
        this.status = IncidentStatus.REPORTED;
        this.Type = Type;
        this.IncidentID = NextIncidentID++;
        this.severity = severity;
        this.Unitid = 0;
        


    }
    /**
     * Sets Incidents object status to Cancelled if Incidentstatus enum parameter is equal to cancelled.
     * @param newstatus the Incident Status Enum which should have Cancelled 
     * otherwise it is not set.
     * @return it returns a boolean which is true or false depending
     *  on whether Incidents Status enum was set to cancelled or not.
     */
    public boolean CancelIncidentstatus(IncidentStatus newstatus){
        if (newstatus == IncidentStatus.CANCELLED){
            this.status = newstatus;
            return true;}
        else{
            return false;
        }
    }
    /**
     * Sets the severity attribute to given parameter 
     * if it is in legal range of allowed severity levels.
     * @param newseverity the integer passed to be set to the incident Objects security level
     * @return the Severity level the Incident is currently at
     * is returned regardless of whether the parameter has been set as the severity level.
     */
    public int SetIncidentseverity( int newseverity){
        if (newseverity >= 0 && newseverity <= 5){
            this.severity = newseverity;
        }
        return this.severity;

    }
    /**
     * Get method for returning the incident Object's unique id
     * @return  incident Object's unique id
     */
    public int getincidentid(){
        return  this.IncidentID;

    }
    /**
     * Set method for the incident Id so that they can be assigned on incident report
     * @param newid id set unique integer id 
     * @return the Incident Id that has been newly set.
     */
    public int setincidentid(int newid){
        this.IncidentID = newid;
        return(this.IncidentID);
    

    }
    /**
     * Set method that sets the incidents liked unit Id. 
     * @param newid the id that the incident is now being assigned to
     * @return the new unit Id that has been set
     */
    public int setincidentsUnitid(int newid){
        this.Unitid = newid;
        return this.Unitid;
    }
    /**
     * Get method for x coordinate 
     * @return the x coordinate of the incident
     */
   public int GetIncidentX(){
    return this.x;

   }
   /**
    * Get method for the Incident y value is used also for calculating manhatten distance.
    * @return returns the Incident y position.
    */
   public int GetIncidentY(){
    return this.y;
   }
   /**
    * Get method for the Incident x value is
    * used for calculating Manhatten distance from the different units.
    * @param newx
    */
   public void SetIncidentx(int  newx){
    this.x =newx;
}
/**
 * Set incidents Y coordinate to a new coordinate done 
 * usually when creating the incident.
 * @param newy New y coordinate to be set 
 */
public void SetIncidenty(int newy){
    this.y =newy;
}
/**
 * Get method for the assigned Units id for the incident.
 * @return returns The unit assigned to the incidents id.
 */
public int GetIncidentsUnitId(){
    return this.Unitid;
}
/**
 * Set method For the Incident Unit ID that can change so that the current Unit can be assigned.
 *
 *
 * @param NewUnitID Id of the unit dispatched that is closest to the incident 
 */
public void setIncidentUnitId(int NewUnitID){
    this.Unitid = NewUnitID;

}
/**
 * Get method for  the Incident Objects severity level.
 * @return return Incident Objects severity 
 */
public int GetSeverity(){
    return this.severity;
}
/**
 * incidentview creates a string 
 * to give the current state of a particular incident object.
 * The information given is Id, Incident Type, Incident severity
 * @return
 */
public String incidentview(){
    String view_string = "I# " + this.IncidentID + " TYPE=" + this.Type + " SEV=" + this.severity + "LOC=(" + this.x +"," + this.y + ") STATUS=" + this.status +"UNIT=" + this.Unitid;
    return view_string;
}
/**
 * Get Method for Incident Object's Status enum.
 * @return Incident Object's Status enum.
 */
public IncidentStatus get_IncidentStatus(){
    return(this.status);
} 

/**
 *Set method for Incidents status 
 * so that it can be changed 
 * if it has been resolved after ticks for instance. 
 * @param newstatus Incident status enum given 
 */
public void SetIncidentStatus(IncidentStatus newstatus){
    this.status = newstatus;
}
/**
 * Set method for Incidents Type 
 * so that it can be changed 
 * after it has been created. 
 * @param newType Enum IncidentType that is to be changed to the objects Enum.
 */
public void SetIncidentType(IncidentType newType){
    this.Type = newType;
}
/**
 * Get method for Incident object's Enum Type E.G.(Medical..)
 * @return Enum Incident type of Incident object
 */
public IncidentType GetIncidentType(){
    return this.Type;
}
}



