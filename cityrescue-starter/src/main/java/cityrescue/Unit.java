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
    public int WORK;
    public int UnitID;
    int Stationid;
    //public abstract boolean canHandle(IncidentType type);
    public abstract int get_unit_id();
    public abstract String unitview();
}





