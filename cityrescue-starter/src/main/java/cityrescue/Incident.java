package cityrescue;

import cityrescue.enums.IncidentStatus;
import cityrescue.enums.IncidentType;

public class Incident {
    public int x;
    public int y;
    public IncidentStatus status;
    public IncidentType Type;
    private static int NextIncidentID = 1;
    public int IncidentID;
    public int severity;
    public int Unitid;
    
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
   public int GetIncidentsUnitId(){
    return this.Unitid;
   }
   public void setIncidentUnitId(int NewUnitID){
    this.Unitid = NewUnitID;

   }
   public int GetSeverity(){
    return this.severity;
   }
    public String incidentview(){
        String view_string = "I# " + this.IncidentID + " TYPE=" + this.Type + " SEV=" + this.severity + "LOC=(" + this.x +"," + this.y + ") STATUS=" + this.status +"UNIT=" + this.Unitid;
        return view_string;
    }
        
    }



