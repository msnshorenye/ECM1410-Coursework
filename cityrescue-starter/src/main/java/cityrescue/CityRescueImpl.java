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
import java.lang.Math;
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
    public int current_station_num;
    public int current_unit_num;
    public int current_incident_num;
    public int current_obstacle_num;
    public int width;
    public int height;

    




    // TODO: add fields (map, arrays for stations/units/incidents, counters, tick, etc.)
    public CityRescueImpl(){
        this.Stationarray = new Station[Max_Stations];
        this.Incidentarray = new Incident[Max_incidents];
        this.Unitarray = new Unit[Max_units]; 
    }

    @Override
    public void initialise(int width, int height) throws InvalidGridException {


        // TODO: implement
        this.ticks = 0;
        this.TheMap = new CityMap(width, height);
        this.width = width;
        this.height = height;
        this.Obstaclearray = new String[width][height];
        this.current_incident_num = 0;
        this.current_obstacle_num = 0;
        this.current_station_num = 0;
        this.current_unit_num = 0;
        
        
        TheMap.newcreategrid();
        String[][]Rescue_map = this.TheMap.grid;


        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int[] getGridSize() {
        // TODO: implement
        int[] Gridsize = this.TheMap.getGridSize();

        return new int [] {this.width, this.height};

        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void addObstacle(int x, int y) throws InvalidLocationException {
        this.Obstaclearray[x][y] = "obstacle";
        this.current_obstacle_num += 1;
        
        // TODO: implement
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void removeObstacle(int x, int y) throws InvalidLocationException {
        // TODO: implement
        this.Obstaclearray[x][y] = " ";
        this.current_obstacle_num -= 1;
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int addStation(String name, int x, int y) throws InvalidNameException, InvalidLocationException {
        // TODO: implement
         for(int i=0;i<=this.Stationarray.length;i++){
            if (this.Stationarray[i]==(null)){
                int length = i;
                Station Newstation = new Station(name, x, y);
                this.Stationarray[length] = Newstation;
                this.current_station_num += 1;
                Newstation.id = this.current_station_num;
                return (int) (Newstation.id);
            }
        }
        return 1;
        //throw new UnsupportedOperationException("Not implemented yet");

    }

    @Override
    public void removeStation(int stationId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        for (int x = 0; x<=this.Stationarray.length; x++){
            if (this.Stationarray[x].GetId() == stationId){
                this.Stationarray[x] = null;
                this.current_station_num -= 1;
            }
        }
        
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void setStationCapacity(int stationId, int maxUnits) throws IDNotRecognisedException, InvalidCapacityException {
        // TODO: implement
        for (int x = 0; x<=this.Stationarray.length; x++){

            if (this.Stationarray[x].GetId() == stationId){
                this.Stationarray[x].stationmaxcapacity = maxUnits;
            }
        }
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int[] getStationIds() {
        int [] stationIdlist = new int[this.Stationarray.length];
        for (int i = 0; i <= this.Stationarray.length; i++){
            Station current_station = Stationarray[i];
            stationIdlist[i] = current_station.GetId();
        }
            Arrays.sort(stationIdlist, Comparator.nullsLast(String::compareTo));
        // TODO: implement
        return stationIdlist;
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int addUnit(int stationId, UnitType type) throws IDNotRecognisedException, InvalidUnitException, IllegalStateException {
        // TODO: implement
        int length;        
        for(int i=0;i<Unitarray.length;i++){
            length = i;
            if (Unitarray[i] == (null)){
                switch(type) {
                    case AMBULANCE:
                        Ambulance ambulance = new Ambulance(stationId);
                        this.Unitarray[length] = ambulance;
                        for (int x =0; x< this.Stationarray.length; x++){
                            Station CurrentStation = this.Stationarray[x];
                            if (CurrentStation != null) {
                                
                            int CurrentStationid = CurrentStation.GetId();
                            if (CurrentStationid == stationId) {
                                ambulance.xloc = CurrentStation.x;
                                ambulance.yloc = CurrentStation.y;


                            }
                            }
                            
                        }

                        System.out.println("HE::O");
                        break;
                    case FIRE_ENGINE:
                        Fire_engine fire_engine = new Fire_engine(stationId);
                        this.Unitarray[length] = fire_engine;
                        for (int x =0; x< this.Stationarray.length; x++){
                            Station CurrentStation = this.Stationarray[x];
                            if (CurrentStation != null) {
                            int CurrentStationid = CurrentStation.GetId();
                            if (CurrentStationid == stationId) {
                                 fire_engine.xloc = CurrentStation.x;
                                 fire_engine.yloc = CurrentStation.y;
                            }
                            }
                        }
                        System.out.println("HE::O");
                        break;
                    case POLICE_CAR:
                        Police_car police = new Police_car(stationId);
                        this.Unitarray[length] = police;
                        for (int x =0; x< this.Stationarray.length; x++){
                            Station CurrentStation = this.Stationarray[x];
                            if (CurrentStation != null ){
                            int CurrentStationid = CurrentStation.GetId();
                            if (CurrentStationid == stationId) {
                                 police.xloc = CurrentStation.x;
                                 police.yloc = CurrentStation.y;


                            }
                            }
                        }
                        System.out.println("HE::O");
                        break;
                }
            break;
            }
        }
        int one = 1;
        return (one);
        //throw new UnsupportedOperationException("Not implemented yet");
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

        
       // throw new UnsupportedOperationException("Not implemented yet");
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
        

        //throw new UnsupportedOperationException("Not implemented yet");
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

    
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int[] getUnitIds() {
        // TODO: implement
        int [] UNitIdlist = new int[this.Unitarray.length];
        for (int i = 0; i <= this.Unitarray.length; i++){
            int length = i;
                switch(this.Unitarray[length].TYPE) {
                    case AMBULANCE:
                        Ambulance ambulance = (Ambulance)this.Unitarray[length];
                        int neededid = ambulance.get_unit_id();
                        UNitIdlist[i] = neededid;
                        break;
                    case FIRE_ENGINE:
                        Fire_engine fire_engine = (Fire_engine) this.Unitarray[length];
                        neededid = fire_engine.get_unit_id();
                        UNitIdlist[i] = neededid;
                        break; 
                    case POLICE_CAR:
                        Police_car police = (Police_car) this.Unitarray[length];
                        neededid = police.get_unit_id();
                        UNitIdlist[i] = neededid;
                        break;
                }
        }
            
        Arrays.sort(UNitIdlist, Comparator.nullsLast(String::compareTo));
        return UNitIdlist;
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String viewUnit(int unitId) throws IDNotRecognisedException {
        // TODO: implement
        String UnitStrings = "";
        for (int i =0 ; i<Unitarray.length;i++){
            if (Unitarray[i] != null){
            if (Unitarray[i].UnitID == unitId){
                int length = i;
                switch(this.Unitarray[length].TYPE) {
                    case AMBULANCE:
                        Ambulance ambulance = (Ambulance)this.Unitarray[length];
                        String temp = ambulance.unitview();
                        UnitStrings = temp;
                        break;
                    case FIRE_ENGINE:
                        Fire_engine fire_engine = (Fire_engine) this.Unitarray[length];
                        String temp2 = fire_engine.unitview();
                        UnitStrings =  temp2;
                        break; 
                    case POLICE_CAR:
                        Police_car police = (Police_car) this.Unitarray[length];
                        String temp3 = police.unitview();
                        UnitStrings =  temp3;
                        break;
                }
            }
            }
        }
        return UnitStrings;
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int reportIncident(IncidentType type, int severity, int x, int y) throws InvalidSeverityException, InvalidLocationException {
        // TODO: implement
        for(int i=0;i<this.Incidentarray.length;i++){
            if (this.Incidentarray[i] == (null)){
                int length = i;
                Incident newinci = new Incident(x,y,type,severity);
                this.Incidentarray[length] = newinci;
                this.current_incident_num += 1;

            

                return this.current_incident_num;
                
            }
        
        }
        return 0;
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void cancelIncident(int incidentId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        for (int x = 0; x<=this.Incidentarray.length; x++){
            if ((this.Incidentarray[x].getincidentid()) == incidentId){
                this.Incidentarray[x].CancelIncidentstatus(IncidentStatus.CANCELLED);
                this.current_incident_num -= 1;
            }
        }

        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void escalateIncident(int incidentId, int newSeverity) throws IDNotRecognisedException, InvalidSeverityException, IllegalStateException {
        // TODO: implement
        for(int i=0;i<=this.Incidentarray.length;i++){
            int needid = this.Incidentarray[i].getincidentid();
            if (needid == incidentId){
                this.Incidentarray[i].severity = newSeverity;
                break;
            }
        }
        //throw new UnsupportedOperationException("Not implemented yet");
    }


    @Override
    public int[] getIncidentIds() {
        // TODO: implement
        int [] Incidentidlist = new int[this.Incidentarray.length];
        for (int i = 0; i <= this.Incidentarray.length; i++){
            Incident current_Incident = Incidentarray[i];
            Incidentidlist[i] = current_Incident.getincidentid();

                   
        }
        Arrays.sort(Incidentidlist, Comparator.nullsLast(String::compareTo));

        // TODO: implement
        return Incidentidlist;
        
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String viewIncident(int incidentId) throws IDNotRecognisedException {
        // TODO: implement
        for (int i=0;i<this.Incidentarray.length;i++){
            if (this.Incidentarray[i] != null){
    
                Incident currentIncidentViewed = this.Incidentarray[i];
                int current_Incident_id_viewed = currentIncidentViewed.getincidentid();
                System.out.println(current_Incident_id_viewed);
                if (current_Incident_id_viewed == incidentId){
                    return(currentIncidentViewed.incidentview());

            }
           
            }
        }
            
        return("incident not found");

            }
        
        //throw new UnsupportedOperationException("Not implemented yet");
    

    @Override
    public void dispatch() {
        // TODO: implement
        int [] Incidentidlist = getIncidentIds();
        for (int i = 0; i< Incidentidlist.length; i++){
            int current_incident = Incidentidlist[i];
            if (current_incident == null){
                break; 
            }
            for (int x = 0; x< this.Incidentarray.length; x++){
                
                Incident Current_Incident = this.Incidentarray[x];
                
                if (Current_Incident.getincidentid() == current_id){
                    int Incident_x = Current_Incident.x;
                    int Incident_y = Current_Incident.y;
                    int ChosenUnitId = -1;
                    int SmallestDistance = -1;
                    int ChosenHomeStationId = -1;
                    for (int y = 0; y< this.Unitarray.length; y++){
                        Unit CurrentUnit = this.Unitarray[y];
                        if  (CurrentUnit == null){
                            break;

                        }
                        else{
                            if (CurrentUnit.STATUS == UnitStatus.IDLE){
                                int CurrentUnitx = CurrentUnit.xloc;
                                int CurrentUnity = CurrentUnit.yloc;
                                int Currentdistance = Math.abs(Incident_x - CurrentUnitx) + (Math.abs(Incident_y- CurrentUnity));
                                if (Currentdistance < SmallestDistance){
                                    SmallestDistance = Currentdistance;
                                    ChosenUnitId = CurrentUnit.UnitID;  
                                }
                                else if(Currentdistance == SmallestDistance){
                                    int CurrentChosenUnitId = CurrentUnit.UnitID;
                                    if (CurrentChosenUnitId < ChosenUnitId){
                                        ChosenUnitId = CurrentChosenUnitId;
                                    }
                                    else if (CurrentChosenUnitId == ChosenUnitId){
                                        int CurrentChosenHomeStationId = CurrentUnit.Stationid;
                                        if (CurrentChosenHomeStationId < ChosenHomeStationId){
                                            ChosenHomeStationId = CurrentChosenHomeStationId;
                                        }
                                        
                                    }
                                    }
                                    
                                }
                            }
                        }
                        for (int a = 0; a < this.Unitarray.length; a++){
                            Unit Curr_unit = this.Unitarray[a];
                            if (ChosenUnitId == Curr_unit.UnitID){
                                Curr_unit.STATUS = UnitStatus.EN_ROUTE;
                                Curr_unit.AssignedIncidentId = Current_Incident.getincidentid();
                                
                            }
                        }
                    }

                    }
                }
                
                
                
            
        

        
        

        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void tick() {
        // TODO: implement
        // 1) Move En_route units first by ascending unit id
        int [] UNITLIST = getUnitIds();
        for (int i = 0 ; i<UNITLIST.length;i++){
           Unit tempUnit = (Unit) UNITLIST[i];
            if (tempUnit.STATUS == UnitStatus.EN_ROUTE){
                int X = tempUnit.xloc;  
                int Y = tempUnit.yloc;
                int [][] potential = new int[4][2];  // 4 coordinates each with y,x
                int x;
                int y;
                if(Y+1>this.height){
                    //N is valid
                    y = Y+1;
                    x = X;
                    potential[0][0] = y;
                    potential[0][1] = x;
                }
                if (X+1> this.width) {
                    //E is valid
                    y = Y;
                    x = X+1;
                    potential[1][0] = y;
                    potential[1][1] = x;
                }
                if(Y-1>0){
                    //S is valid
                    y = Y-1;
                    x = X;
                    potential[2][0] = y;
                    potential[2][1] = x;
                }  
                if(X-1>0){
                    //W is valid
                    y = Y;
                    x = X-1;
                    potential[3][0] = y;
                    potential[3][1] = x;
                } 
                int I_xloc ;
                int I_yloc;
                for (int t = 0; t<this.Incidentarray.length;t++){
                    if (this.Incidentarray[t] == tempUnit.AssignedIncidentId){
                        Incident incident = (Incident)this.Incidentarray[t];
                        I_xloc = incident.x;
                        I_yloc = incident.y;
                        break;
                    }
                }
                int OG_MAN = (Maths.abs((I_xloc-X)))-(Maths.abs((I_yloc-Y)));
                for (int z=0;z<potential.length;z++){
                    int xLOC = this.potential[z][1];
                    int yLOC = this.potential[z][0];

                    if (this.Obstaclearray[yloc][xloc] != "obstacle"){
                        int New_Score =  (Math.abs((I_xloc-xloc))-(Math.abs((I_yloc-yloc))));
                        if (New_Score <OG_MAN){
                            //CHANging location
                            
                            System.out.println("HELLO");
                        }
                    } 
                } 
            }
        
        
        }
        // 2) Mark arrivals
        // 3) process on scene work 
        // 4) resolve completed incidents by ascending unit id 
        
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String getStatus() {

        String StatusString = "TICK=" + this.ticks +"\n STATIONS="+ this.current_station_num +" UNITS="+ this.current_unit_num + " INCIDENTS="+ this.current_incident_num+ " Obstacles="+this.current_obstacle_num ;
        String IncidentString = "INCIDENTS\n ";
        for (int x = 0 ; x<Incidentarray.length;x++){
            if (this.Incidentarray[x] != null){
                String TEMP = Incidentarray[x].incidentview();
                IncidentString = IncidentString + "\n U#" +  x+" "+ TEMP;
            }
        }
        String UnitStrings = "UNITS";
        for (int i = 0; i<this.Unitarray.length;i++) {
            int length = i;
            if (this.Unitarray[i] !=(null)) {
                
                switch(this.Unitarray[length].TYPE) {
                    case AMBULANCE:
                        Ambulance ambulance = (Ambulance)this.Unitarray[length];
                        String temp = ambulance.unitview();
                        UnitStrings = UnitStrings + "\n U#" +  length+" "+ temp;
                        break;
                    case FIRE_ENGINE:
                        Fire_engine fire_engine = (Fire_engine) this.Unitarray[length];
                        String temp2 = fire_engine.unitview();
                        UnitStrings = UnitStrings + "\n U#" + length +" "+ temp2;
                        break; 
                    case POLICE_CAR:
                        Police_car police = (Police_car) this.Unitarray[length];
                        String temp3 = police.unitview();
                        UnitStrings = UnitStrings + "\n U#" +  length+" "+ temp3;
                        break;
                }
            }
        } 
        //throw new UnsupportedOperationException("Not implemented yet");
        String REPORT = StatusString+"\n"+ IncidentString+"\n"+UnitStrings;
        return (REPORT);
    }
 public static void main(String[] args) {
    System.out.println("Hello");
 }
}
