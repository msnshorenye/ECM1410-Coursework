package cityrescue;
import cityrescue.enums.UnitType;
import cityrescue.enums.UnitStatus;

abstract class Unit {
    int ticks = 0;
    public UnitType TYPE;
    public int xloc;
    public int yloc;
    public UnitStatus STATUS;
    public int AssignedIncidentId=-1;
    int WORK;
    public int UnitID;
    public static int NextID = 1;
    int Stationid;
    //public abstract boolean canHandle(IncidentType type);
}





