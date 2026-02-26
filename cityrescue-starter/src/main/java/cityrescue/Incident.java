package cityrescue;

import cityrescue.enums.IncidentStatus;
import cityrescue.enums.IncidentType;

public class Incident {
    public int x;
    public int y;
    public IncidentStatus status;
    public IncidentType Type;
    private static int NextIncidentID = 1;
    private final int IncidentID;
    public int severity;
    
    public Incident(int x, int y, IncidentType Type, int severity ){
        this.x = x;
        this.y = y;
        this.status = IncidentStatus.REPORTED;
        this.Type = Type;
        this.IncidentID = NextIncidentID++;
        this.severity = severity;
        this.UnitID = null;


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
    public getincidentid(){
        return this.IncidentID;

    }
    public String incidentview(){
        String view_string = "Incident" + this.IncidentID + " TYPE=" + this.Type + " SEV=" + this.severity + "LOC=(" + this.x +"," + this.y + ") STATUS=" + this.status +"UNIT=" + this.UnitID;
        return view_string;
    }
        
    }


}
