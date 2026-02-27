package cityrescue;
import cityrescue.enums.UnitType;
import cityrescue.enums.UnitStatus;

abstract class Unit {
    int ticks = 0;
    public UnitType TYPE;
    int xloc;
    int yloc;
    public UnitStatus STATUS;
    int INCIDENT=-1;
    int WORK;
    public int UnitID;
    public static int NextID = 1;
    int Stationid;
    //public abstract boolean canHandle(IncidentType type);
}





