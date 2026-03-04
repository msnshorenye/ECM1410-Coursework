package cityrescue;

import cityrescue.enums.IncidentStatus;
import cityrescue.enums.IncidentType;

public class Incident {
    private int x;
    private int y;
    private IncidentStatus status;
    private IncidentType Type;
    private static int NextIncidentID = 1;
    private int IncidentID;
    private int severity;
    private int Unitid;
    
    public Incident(int x, int y, IncidentType Type, int severity ){
        this.x = x;
        this.y = y;
        this.status = IncidentStatus.REPORTED;
        this.Type = Type;
        this.IncidentID = NextIncidentID++;
        this.severity = severity;
        this.Unitid = 0;
        


    }
    
    public boolean CancelIncidentstatus(IncidentStatus newstatus){
        if (newstatus == IncidentStatus.CANCELLED){
            this.status = newstatus;
            return true;}
        else{
            return false;
        }
    }
    
    public int SetIncidentseverity( int newseverity){
        if (newseverity >= 0 && newseverity <= 5){
            this.severity = newseverity;
        }
        return this.severity;

    }
    public int getincidentid(){
        return  this.IncidentID;

    }
    public int setincidentid(int newid){
        this.IncidentID = newid;
        return(this.IncidentID);
    

    }
    public int setincidentsUnitid(int newid){
        this.Unitid = newid;
        return this.Unitid;
    }
   public int GetIncidentX(){
    return this.x;

   }
   public int GetIncidentY(){
    return this.y;
   }
   public void SetIncidentx(int  newx){
    this.x =newx;
}
public void SetIncidenty(int newy){
    this.y =newy;
}
public int GetIncidentsUnitId(){
    return this.Unitid;
}
public void setIncidentUnitId(int NewUnitID){
    this.Unitid = NewUnitID;

}
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



