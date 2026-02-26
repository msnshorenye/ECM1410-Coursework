package cityrescue;

import cityrescue.enums.*;
import cityrescue.exceptions.*;
import CityMap.Citymap;
import Station.Station;
import Unit.*;
import Incident.Incident;
import java.util.Arrays;
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
        this.tick = 0;
        this.TheMap = new CityMap(width, height);
        this.Obstaclearray = new String[width][height];
        
        
        TheMap.newcreategrid();
        Rescue_map = this.TheMap.grid;


        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int[] getGridSize() {
        // TODO: implement
        int[] Gridsize = this.TheMap.getGridSize();

        return Gridsize;

        throw new UnsupportedOperationException("Not implemented yet");
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
         for(i=0;i=Incidentarray.length;i++){
            if (Incidendarray[i].isequal(null)){
                break;
            }
        Station Newstation = new Station(x,y,name);
        this.Stationarray[i] = Newstation;
        
        
        throw new UnsupportedOperationException("Not implemented yet");

    }

    @Override
    public void removeStation(int stationId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        for (int x = 0; x<=this.Stationarray.length; i++){
            if (this.Stationarray[i].getid() == stationId){
                this.Stationarray[i] = null;
            }
        }
        
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void setStationCapacity(int stationId, int maxUnits) throws IDNotRecognisedException, InvalidCapacityException {
        // TODO: implement
        for (int x = 0; x<=this.Stationarray.length; i++){
            if (this.Stationarray[i].getid() == stationId){
                this.Stationarray[i].stationmaxcapacity = maxUnits;
            }
        }
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int[] getStationIds() {
        int [] stationIdlist = new int[this.Stationarray.length];
        for (int i = 0; i <= this.Stationarray.length; i++){
            Station current_station = Stationarray[i];
            stationIdlist[i] = current_station.getid();
        }
            
        // TODO: implement
        return stationIdlist;
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int addUnit(int stationId, UnitType type) throws IDNotRecognisedException, InvalidUnitException, IllegalStateException {
        // TODO: implement
        for(i=0;i=Unitarray.length;i++){
            if (Unitarray[i].isequal(null)){
                break;
            }
        }
        int length = i;
        switch(type) {
        case AMBULANCE:
            this.Unitarray[length+1] = new Ambulance(stationId);
            System.out.println("HE::O");
            break;
        case FIRE_ENGINE:
            this.Unitarray[length+1] = new Fire_engine(stationId);
            System.out.println("HE::O");
            break;
        case POLICE_CAR:
            this.Unitarray[length+1] = new POLICE_CAR(stationId);
            System.out.println("HE::O");
            break;
        }
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void decommissionUnit(int unitId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        String status = Unitarray[unitId - 1].STATUS;

        if (!status.equals("EN_ROUTE") || !status.equals("AT_SCENE")){
            
        }
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void transferUnit(int unitId, int newStationId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        for (int x = 0; x<=this.Unitarray.length; x++){
            if (this.Unitarray[i].getid() == unitId){
                this.Unitarray[i].Stationid = newStationId;
            }
        }
        

        
        
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void setUnitOutOfService(int unitId, boolean outOfService) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int[] getUnitIds() {
        // TODO: implement
        int [] UNitIdlist = new int[this.UNitIdlist.length];
        for (int i = 0; i <= this.Unitarray.length; i++){
            Unit current_unit = Unitarray[i];
            UNitIdlist[i] = current_unit.getid();
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
        for(i=0;i=Incidentarray.length;i++){
            if (Incidendarray[i].isequal(null)){
                break;
            }
        }
        this.Incidentarray[i] = new Incident(x,y,type,severity);
        return i;
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void cancelIncident(int incidentId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        for (int x = 0; x<=this.length; x++){
            if (this.Incidentarray[i].getid() == incidentId){
                this.Incidentarray[i].cancelIncident(IncidentStatus.CANCELLED);
            }
        }

        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void escalateIncident(int incidentId, int newSeverity) throws IDNotRecognisedException, InvalidSeverityException, IllegalStateException {
        // TODO: implement
        for(i=0;i=Incidentarray.length;i++){
            if (Incidendarray[i].isequal(incidentId)){
                this.Incidentarray[i].severity = newSeverity;
                break;
            }
        
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int[] getIncidentIds() {
        // TODO: implement
        int [] Incidentidlist = new int[this.Incidentarray.length];
        for (int i = 0; i <= this.Incidentarray.length; i++){
            Incident current_Incident = Incidentarray[i];
            incidentIdlist[i] = current_Incident.getincidentid();
            

        }
            
        // TODO: implement
        return incidentIdlist;
        
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String viewIncident(int incidentId) throws IDNotRecognisedException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void dispatch() {
        // TODO: implement
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
