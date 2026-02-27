package cityrescue;
import cityrescue.enums.*;
import cityrescue.exceptions.*;
import cityrescue.CityMap;
import cityrescue.Station;
import cityrescue.Unit;
import cityrescue.Incident;
import java.util.Arrays;
import java.util.Dictionary;
import cityrescue.Ambulance;
import cityrescue.Police_car;
import cityrescue.Fire_engine;
import java.util.Comparator;
/**
 * CityRescueImpl (Starter)
 *
 * Your task is to implement the full specification.
 * You may add additional classes in any package(s) you like.
 */
public class CityRescueImpl implements CityRescue {
    public int ticks;
    public CityMap TheMap;
    public Station [] Stationarray;
    public String [][] Obstaclearray;
    public Unit [] Unitarray;
    public Incident [] Incidentarray;
    public int Max_Stations = 20;
    public int Max_units = 50;
    public int Max_incidents = 200;

    




    // TODO: add fields (map, arrays for stations/units/incidents, counters, tick, etc.)
    public CityRescueImpl(int width, int height){
        this.Stationarray = new Station[Max_Stations];
        this.Incidentarray = new Incident[Max_incidents];
        this.Unitarray = new Unit[Max_units]; 
    }

    @Override
    public void initialise(int width, int height) throws InvalidGridException {


        // TODO: implement
        this.ticks = 0;
        this.TheMap = new CityMap(width, height);
        this.Obstaclearray = new String[width][height];
        
        
        TheMap.newcreategrid();
        String[][]Rescue_map = this.TheMap.grid;


        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int[] getGridSize() {
        // TODO: implement
        int[] Gridsize = this.TheMap.getGridSize();

        return Gridsize;

        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void addObstacle(int x, int y) throws InvalidLocationException {
        this.Obstaclearray[x][y] = "obstacle";
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void removeObstacle(int x, int y) throws InvalidLocationException {
        // TODO: implement
        this.Obstaclearray[x][y] = " ";
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int addStation(String name, int x, int y) throws InvalidNameException, InvalidLocationException {
        // TODO: implement
         for(int i=0;i<=this.Incidentarray.length;i++){
            if (this.Incidentarray[i].equals(null)){
                int length = i;
                Station Newstation = new Station(x,y,name);
                this.Stationarray[length] = Newstation;
                

                break;
            }
        }
        
        throw new UnsupportedOperationException("Not implemented yet");

    }

    @Override
    public void removeStation(int stationId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        for (int x = 0; x<=this.Stationarray.length; x++){
            if (this.Stationarray[x].GetId() == stationId){
                this.Stationarray[x] = null;
            }
        }
        
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void setStationCapacity(int stationId, int maxUnits) throws IDNotRecognisedException, InvalidCapacityException {
        // TODO: implement
        for (int x = 0; x<=this.Stationarray.length; x++){

            if (this.Stationarray[x].GetId() == stationId){
                this.Stationarray[x].stationmaxcapacity = maxUnits;
            }
        }
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int[] getStationIds() {
        int [] stationIdlist = new int[this.Stationarray.length];
        for (int i = 0; i <= this.Stationarray.length; i++){
            Station current_station = Stationarray[i];
            stationIdlist[i] = current_station.GetId();
        }
            
        // TODO: implement
        return stationIdlist;
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int addUnit(int stationId, UnitType type) throws IDNotRecognisedException, InvalidUnitException, IllegalStateException {
        // TODO: implement
        for(int i=0;i<=Unitarray.length;i++){
            if (Unitarray[i].equals(null)){
                int length = i;
                switch(type) {
                    case UnitType.AMBULANCE:
                        this.Unitarray[length] = new Ambulance(stationId);
                        System.out.println("HE::O");
                        break;
                    case UnitType.FIRE_ENGINE:
                        this.Unitarray[length] = new Fire_engine(stationId);
                        System.out.println("HE::O");
                        break;
                    case UnitType.POLICE_CAR:
                        this.Unitarray[length] = new Police_car(stationId);
                        System.out.println("HE::O");
                        break;
                }
                break;
            }
        }
        

        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void decommissionUnit(int unitId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
    for (int x = 0; x<=this.Unitarray.length; x++){
            if (this.Unitarray[x] == null){
                UnitStatus status = this.Unitarray[unitId].STATUS;
            if (!status.equals(UnitStatus.EN_ROUTE) && !status.equals(UnitStatus.AT_SCENE)){
            this.Unitarray[unitId] = null;
        }
                break;
            }
        }

        
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void transferUnit(int unitId, int newStationId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        for (int x = 0; x<=this.Unitarray.length; x++){
            if (this.Unitarray[x] == null){
                if (this.Unitarray[x].UnitID == unitId){
                    this.Unitarray[x].Stationid = newStationId;
                }
            }
        }
        

        throw new UnsupportedOperationException("Not implemented yet");
    }
    //Dictionary Skin_colour = {"Matthew":1,"Fin":2,"William King":3};

    @Override
    public void setUnitOutOfService(int unitId, boolean outOfService) throws IDNotRecognisedException, IllegalStateException {
        //TODO:implement
        if (outOfService == true){
            for (int x = 0; x<=this.Unitarray.length; x++){
                if (this.Unitarray[x].UnitID == unitId && this.Unitarray[x].STATUS == UnitStatus.IDLE){
                    this.Unitarray[x].STATUS = UnitStatus.OUT_OF_SERVICE;}
                }}
        else{
            for (int x = 0; x<= this.Unitarray.length; x++){
                if (this.Unitarray[x].UnitID == unitId){
                    this.Unitarray[x].STATUS = UnitStatus.IDLE;
                }
            }
        }

    
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int[] getUnitIds(int type) {
        // TODO: implement
        int [] UNitIdlist = new int[this.Unitarray.length];
        for (int i = 0; i <= this.Unitarray.length; i++){
            int length = i;
            UNitIdlist[i] = this.Unitarray[i].get_unit_id();
        }
            
        // TODO: implement
        return UNitIdlist;
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String viewUnit(int unitId) throws IDNotRecognisedException {
        // TODO: implement
        
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int reportIncident(IncidentType type, int severity, int x, int y) throws InvalidSeverityException, InvalidLocationException {
        // TODO: implement
        for(int i=0;i<=Incidentarray.length;i++){
            if (Incidentarray[i].equals(null)){
                int length = i;
                this.Incidentarray[length] = new Incident(x,y,type,severity);
                return this.Incidentarray[length].getincidentid();
                break;
            }
        }
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void cancelIncident(int incidentId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        for (int x = 0; x<=this.Incidentarray.length; x++){
            if ((this.Incidentarray[x].getincidentid()).equals(incidentId)){
                this.Incidentarray[x].CancelIncidentstatus(IncidentStatus.CANCELLED);
            }
        }

        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void escalateIncident(int incidentId, int newSeverity) throws IDNotRecognisedException, InvalidSeverityException, IllegalStateException {
        // TODO: implement
        for(int i=0;i<=Incidentarray.length;i++){
            if (this.Incidentdarray[i].getincidentid.equals(incidentId)){
                this.Incidentarray[i].severity = newSeverity;
                break;
            }
        }
        throw new UnsupportedOperationException("Not implemented yet");
    }


    @Override
    public int[] getIncidentIds() {
        // TODO: implement
        int [] Incidentidlist = new int[this.Incidentarray.length];
        for (int i = 0; i <= this.Incidentarray.length; i++){
            Incident current_Incident = Incidentarray[i];
            Incidentidlist[i] = current_Incident.getincidentid();

                   
        }
         Incidentidlist = Arrays.sort(Incidentidlist, Comparator.nullsLast(Comparator.naturalOrder()));

        // TODO: implement
        return Incidentidlist;
        
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String viewIncident(int incidentId) throws IDNotRecognisedException {
        // TODO: implement
        for (int i=0;i<=this.Incidentarray.length;i++){
                System.err.println(Incidentarray[i].incidentview());
            }
        
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void dispatch() {
        // TODO: implement
        //Incidentlist = getIncidentIds();
        
        

        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void tick() {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String getStatus() {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }
 public static void main(String[] args) {
    System.out.println("Hello");
 }
}
