package cityrescue;

abstract class Unit {
    int ticks = 0;
    string TYPE

    
    public abstract boolean canHandle(IncidentType type);
}

class Ambulance extends Unit{
    int ticks = 2;
    
}

class POLICE_CAR extends Unit{
    int ticks = 3;
}
abstract class FIRE_ENGINE extends Unit{
    int ticks = 4;
}