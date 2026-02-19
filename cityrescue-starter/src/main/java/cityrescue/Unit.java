package cityrescue;

abstract class Unit {
    int ticks = 0;


    
    public abstract boolean canHandle(IncidentType type);
}
