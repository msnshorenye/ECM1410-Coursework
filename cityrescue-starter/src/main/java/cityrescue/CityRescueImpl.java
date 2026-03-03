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
    public int current_station_id;
    public int current_unit_num;
    public int current_unit_id;
    public int current_incident_num;
    public int current_incident_id;
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
        if (width <= 0 || height <= 0){
            throw new InvalidGridException("Invalid Grid size");
        }


        // TODO: implement
        this.ticks = 0;
        this.TheMap = new CityMap(width, height);
        this.width = width;
        this.height = height;
        this.Obstaclearray = new String[width][height];
        this.current_incident_num = 0;
        this.current_obstacle_num = 0;
        this.current_station_num = 0;
        this.current_incident_id = 0;
        this.current_station_id = 0;
        this.current_unit_id = 0;
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
        if (x<0 || y<0 || y>this.height || x>this.width){
            throw new InvalidLocationException("Out of bounds");
        }
        this.Obstaclearray[x][y] = "obstacle";
        this.current_obstacle_num += 1;
        
        // TODO: implement
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void removeObstacle(int x, int y) throws InvalidLocationException {
        if (x<0||y<0||x>this.height||y>this.width){
            throw new InvalidLocationException("Out of Bounds");
        }        
        // TODO: implement
        this.Obstaclearray[x][y] = " ";
        this.current_obstacle_num -= 1;
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int addStation(String name, int x, int y) throws InvalidNameException, InvalidLocationException {
        if (name == ""){
            throw new InvalidNameException("Must enter a name");
        }
        if (x<0 || y<0 || y>this.height || x>this.width){
            throw new InvalidLocationException("Out of bounds");
        }
        // TODO: implement
         for(int i=0;i<this.Stationarray.length;i++){
            if (this.Stationarray[i]==(null)){
                int length = i;
                Station Newstation = new Station(name, x, y);
                this.Stationarray[length] = Newstation;
                this.current_station_num += 1;
                this.current_station_id += 1;
                Newstation.id = this.current_station_id;
                return (int) (Newstation.id);
            }
        }
        return 1;
        //throw new UnsupportedOperationException("Not implemented yet");

    }

    @Override
    public void removeStation(int stationId) throws IDNotRecognisedException, IllegalStateException {

        boolean idbool = false;
        for (int i = 0; i< this.Stationarray.length; i++){
            if (this.Stationarray[i] != null){
                Station picked_Station = this.Stationarray[i];
            if (picked_Station.GetId()== stationId){
                idbool = true;
            }
           
        }}
        if (idbool == false){
            throw new IDNotRecognisedException("No Station with this id");
            
        }
        boolean HasUnit = false;
        for (int y = 0; y<this.Unitarray.length; y++){
            if (this.Unitarray[y] != null){
                Unit c_unit = this.Unitarray[y];
                if (c_unit.Stationid == stationId){
                    HasUnit = true;
                }

            }
        }
        if (HasUnit == true){
            throw new IllegalStateException("Unit in the station trying to be removed");
        }
        // TODO: implement
        for (int x = 0; x<this.Stationarray.length; x++){
            if (this.Stationarray[x].GetId() == stationId){
                this.Stationarray[x] = null;
                this.current_station_num -= 1;
            }
        }
        
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void setStationCapacity(int stationId, int maxUnits) throws IDNotRecognisedException, InvalidCapacityException {
        int[] stationlist = getStationIds();
        boolean idbool = false;
        for (int i = 0; i< stationlist.length; i++){
            
            if (stationlist[i] == stationId){
                idbool = true;
            }
           
        }
        if (idbool == false){
            throw new IDNotRecognisedException("No Station with this id");
            
        }
        int CurrentUnitNum = 0;
        for (int y = 0; y<this.Unitarray.length; y++){
            if (this.Unitarray[y] != null){
                Unit c_unit = this.Unitarray[y];
                if (c_unit.Stationid == stationId){
                    CurrentUnitNum += 1;
                }

            }
        }
        if (maxUnits<=0 || CurrentUnitNum > maxUnits){
            throw new InvalidCapacityException("Too full");
        }
        // TODO: implement
        for (int x = 0; x<this.Stationarray.length; x++){
            if( this.Stationarray[x] != (null)){
            if (this.Stationarray[x].GetId() == stationId){
                this.Stationarray[x].stationmaxcapacity = maxUnits;
            }
            }
        }
          //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int[] getStationIds() {
        int [] stationIdlist = new int[this.Stationarray.length];
        for (int i = 0; i < this.Stationarray.length; i++){
            if (Stationarray[i] != null){
                Station current_station = Stationarray[i];
                stationIdlist[i] =  current_station.GetId();
            }
        }
            Arrays.sort(stationIdlist);
        // TODO: implement
        return stationIdlist;
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int addUnit(int stationId, UnitType type) throws IDNotRecognisedException, InvalidUnitException, IllegalStateException {
        boolean StationExists =  false;
        for (int x= 0; x < this.Stationarray.length; x++){
            if (this.Stationarray[x] != (null)){
                Station Stat = this.Stationarray[x];
                if (Stat.GetId() == stationId){
                    StationExists = true;
                    System.out.println(Stat.currentcapacity+" "+Stat.stationmaxcapacity+"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    if (Stat.currentcapacity == Stat.stationmaxcapacity){
                        throw new IllegalStateException("Station is already full");
                    }
                    

                    
                }
            }
        }
        if (StationExists == false){
            throw new IDNotRecognisedException("Station of the Id given does not exist");
        }
        if (type == null){
            throw new  InvalidUnitException("Type of Unit cannot be equal to null");
        }
        

        int length;        
        //System.out.println("TYPE PASSED: " + type);
        for(int i=0;i<Unitarray.length;i++){
            length = i;
            if (Unitarray[i] == (null)){
                switch(type) {
                    case AMBULANCE:
                        //System.out.println("Ambulancee");
                        Ambulance ambulance = new Ambulance(stationId);
                        //System.out.println(ambulance.TYPE);
                        this.Unitarray[length] = ambulance;
                        for (int x =0; x< this.Stationarray.length; x++){
                            Station CurrentStation = this.Stationarray[x];
                            if (CurrentStation != null) {
                                
                            int CurrentStationid = CurrentStation.GetId();
                            if (CurrentStationid == stationId) {
                                ambulance.xloc = CurrentStation.x;
                                ambulance.yloc = CurrentStation.y;
                                this.current_unit_id += 1;
                                this.current_unit_num += 1;
                                ambulance.UnitID = this.current_unit_id;
                                CurrentStation.currentcapacity += 1;
                                //System.out.println(ambulance.TYPE);
                                return(ambulance.UnitID);
                                



                            }
                            }
                            
                        }

                        //System.out.println("HE::O");
                        break;
                    case FIRE_ENGINE:
                        //System.out.println("Fire enginge");
                        Fire_engine fire_engine = new Fire_engine(stationId);
                        this.Unitarray[length] = fire_engine;
                        for (int x =0; x< this.Stationarray.length; x++){
                            Station CurrentStation = this.Stationarray[x];
                            if (CurrentStation != null) {
                            int CurrentStationid = CurrentStation.GetId();
                            if (CurrentStationid == stationId) {
                                fire_engine.xloc = CurrentStation.x;
                                fire_engine.yloc = CurrentStation.y;
                                this.current_unit_id += 1;
                                this.current_unit_num += 1;
                                fire_engine.UnitID = this.current_unit_id;
                                CurrentStation.currentcapacity += 1;
                                return(fire_engine.UnitID);
                                 
                            }
                            }
                        }
                        //System.out.println("HE::O");
                        break;
                    case POLICE_CAR:
                        //System.out.println("Police car");
                        Police_car police = new Police_car(stationId);
                        this.Unitarray[length] = police;
                        for (int x =0; x< this.Stationarray.length; x++){
                            Station CurrentStation = this.Stationarray[x];
                            if (CurrentStation != null ){
                            int CurrentStationid = CurrentStation.GetId();
                            if (CurrentStationid == stationId) {
                                police.xloc = CurrentStation.x;
                                police.yloc = CurrentStation.y;
                                this.current_unit_id += 1;
                                this.current_unit_num += 1;
                                police.UnitID = this.current_unit_id;
                                CurrentStation.currentcapacity += 1;
                                return (police.UnitID);

                            }
                            }
                        }
                        //System.out.println("HE::O");
                        break;
                }
            break;
            }
        }
       
        return(1);
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void decommissionUnit(int unitId) throws IDNotRecognisedException, IllegalStateException {
        boolean UnitExist = false;
        for (int y =0; y<this.Unitarray.length; y++){
            if (this.Unitarray[y] != null){
                Unit CheckUnit = this.Unitarray[y];
                if (CheckUnit.UnitID == unitId){
                    UnitExist = true;
                }
            }
        
        if (UnitExist == false){
            throw new IDNotRecognisedException("Unit does not exist");
        }

        // TODO: implement
    for (int x = 0; x<this.Unitarray.length; x++){
            if (this.Unitarray[x] != null){
                Unit DecomUnit = this.Unitarray[x];
                if (DecomUnit.UnitID == unitId){
                    if (DecomUnit.STATUS == UnitStatus.EN_ROUTE || DecomUnit.STATUS == UnitStatus.AT_SCENE){
                        throw new IllegalStateException();

                    }

                    if (DecomUnit.STATUS != UnitStatus.EN_ROUTE || DecomUnit.STATUS != UnitStatus.AT_SCENE){
                        DecomUnit =null;
                        
                    }
                    
    
                }
            }
            
        }
        
        }
            }

        
       // throw new UnsupportedOperationException("Not implemented yet");
    

    @Override
    public void transferUnit(int unitId, int newStationId) throws IDNotRecognisedException, IllegalStateException {
        boolean UnitExist = false;
        for (int y =0; y<this.Unitarray.length; y++){
            if (this.Unitarray[y] != null){
                Unit CheckUnit = this.Unitarray[y];
                if (CheckUnit.UnitID == unitId){
                    UnitExist = true;
                }
            }
        }
        if (UnitExist == false){
            throw new IDNotRecognisedException("Unit does not exist");
        }

    for (int a= 0; a<this.Unitarray.length; a++){
            if (this.Unitarray[a] != null){
                Unit TransferUnit = this.Unitarray[a];
                if (TransferUnit.UnitID == unitId){
                    if (TransferUnit.STATUS == UnitStatus.EN_ROUTE || TransferUnit.STATUS == UnitStatus.AT_SCENE){
                        throw new IllegalStateException("Unit is not IDLE or OutofService");
                    }
                }
            }
        }

        // TODO: implement
        for (int x = 0; x<this.Unitarray.length; x++){
            if (this.Unitarray[x] != null){
                if (this.Unitarray[x].UnitID == unitId){
                    this.Unitarray[x].Stationid = newStationId;
                }
            }
        }
        

        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void setUnitOutOfService(int unitId, boolean outOfService) throws IDNotRecognisedException, IllegalStateException {
        boolean isin = false;
        for (int x = 0; x<this.Unitarray.length; x++){
            if (this.Unitarray[x]!= null){
            if (this.Unitarray[x].UnitID == unitId){
                isin = true;
                if(this.Unitarray[x].STATUS == UnitStatus.EN_ROUTE || this.Unitarray[x].STATUS == UnitStatus.AT_SCENE ){
                    throw new IllegalStateException("Not allowed units of types that arent OUt of service or idle");
                }
            }
        }
        }
        if (isin==false){
            throw new IDNotRecognisedException("this id is not an existing unit");
        }
        
        //TODO:implement
        if (outOfService == true){
            for (int x = 0; x<this.Unitarray.length; x++){
                if (this.Unitarray[x].UnitID == unitId && this.Unitarray[x].STATUS == UnitStatus.IDLE){
                    this.Unitarray[x].STATUS = UnitStatus.OUT_OF_SERVICE;}
                }}
        else{
            for (int x = 0; x< this.Unitarray.length; x++){
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
        for (int i = 0; i < this.Unitarray.length; i++){

            if (Unitarray[i] != null) {
                Unit unit = this.Unitarray[i];
                UNitIdlist[i] = unit.get_unit_id();
            }
        }
        //System.out.println("HJK^&67676767");
        Arrays.sort(UNitIdlist);
        return UNitIdlist;
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String viewUnit(int unitId) throws IDNotRecognisedException {
        // TODO: implement
        String UnitStrings = "";
        for (int i =0 ; i<Unitarray.length;i++){
            Unit unit = this.Unitarray[i];

            if (unit != null) {
                if (unit.get_unit_id() == unitId){
                    UnitStrings = unit.unitview();
                }
            }
        }
        if (UnitStrings == ""){
            throw new IDNotRecognisedException("ID is not in use ");
        }
        return UnitStrings;
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int reportIncident(IncidentType type, int severity, int x, int y) throws InvalidSeverityException, InvalidLocationException {
        // TODO: implement
        if(x<0||y<0||x>this.width||y>this.height){
            throw new InvalidLocationException("NOT on grid");
        }
        else if(this.Obstaclearray[y][x] != (null)){
            throw new InvalidLocationException("Obstacle already in that location");
        }
        if (severity<1 || severity>5){
            throw new InvalidSeverityException("New severerity is not valid");
        }
        for(int i=0;i<this.Incidentarray.length;i++){
            //System.out.println(this.Incidentarray[i]);
            if (this.Incidentarray[i] == (null)){
                int length = i;
                Incident newinci = new Incident(x,y,type,severity);
                this.Incidentarray[length] = newinci;
                this.current_incident_num += 1;
                this.current_incident_id += 1;
                newinci.setincidentid(this.current_incident_id);
                

            
                //System.out.println("added incident"+i);
                return this.current_incident_id;
                
            }
        
        }
        return 0;
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void cancelIncident(int incidentId) throws IDNotRecognisedException, IllegalStateException {
        boolean Isin = false;
        for (int x=0;x<Incidentarray.length;x++){
            if (this.Incidentarray[x] != null){
            if (incidentId == Incidentarray[x].getincidentid()){
                Isin = true;
            }
            }
        }
        if (Isin == false){
            throw new IDNotRecognisedException("Incident 1with this id is not found");
        }
        // TODO: implement
        for (int x = 0; x<this.Incidentarray.length; x++){
            if (this.Incidentarray[x] != (null)){ 
                if ((this.Incidentarray[x].getincidentid()) == incidentId){
                    if (this.Incidentarray[x].status == IncidentStatus.REPORTED){
                        this.Incidentarray[x].CancelIncidentstatus(IncidentStatus.CANCELLED);
                        this.current_incident_num -= 1;
                    }
                    Unit temp ;
                    if (this.Incidentarray[x].status == IncidentStatus.DISPATCHED){
                        this.Incidentarray[x].CancelIncidentstatus(IncidentStatus.CANCELLED);
                        this.current_incident_num -= 1;
                        for (int t=0;t<this.Unitarray.length;t++){
                            if (this.Unitarray[t] != (null)){
                                if (this.Unitarray[t].AssignedIncidentId == incidentId){
                                    setUnitOutOfService(Unitarray[t].get_unit_id(),true);
                                }
                            }
                        }
                    }
                    else{
                        throw new IllegalStateException("Cannot make an incedent that is  IN_PROGRESS, RESOLVED, CANCELLED");
                    }
                }
            }
        }

        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void escalateIncident(int incidentId, int newSeverity) throws IDNotRecognisedException, InvalidSeverityException, IllegalStateException {
        boolean Isin = false;
        for (int x=0;x<Incidentarray.length;x++){
            if (Incidentarray[x] != null){
            if (incidentId == Incidentarray[x].getincidentid()){
                Isin = true;
                if (Incidentarray[x].status == IncidentStatus.RESOLVED || Incidentarray[x].status == IncidentStatus.CANCELLED ){
                    throw new IllegalStateException("Incident is Resolved/Cancelled");
                }
             } }
        }
        if (Isin == false){
            throw new IDNotRecognisedException("Incident 2with this id is not found");
        }
        if (newSeverity<1 || newSeverity>5){
            throw new InvalidSeverityException("New severerity is not valid");
        }
        // TODO: implement
        for(int i=0;i<this.Incidentarray.length;i++){
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
        int[] Incidentidlist = new int[this.Incidentarray.length];
        for (int i = 0; i < this.Incidentarray.length; i++){
            if (Incidentarray[i] != null){
            Incident current_Incident = Incidentarray[i];
            Incidentidlist[i] =  current_Incident.getincidentid();
            }      
        }
        Arrays.sort(Incidentidlist);
        
        // TODO: implement
        return Incidentidlist;
        
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String viewIncident(int incidentId) throws IDNotRecognisedException {
        boolean Isin = false;
        for (int x=0;x<Incidentarray.length;x++){
            if (this.Incidentarray[x] != null){
            if (incidentId == Incidentarray[x].getincidentid()){
                Isin = true;
            }
        }
    }
        if (Isin == false){
            throw new IDNotRecognisedException("Incident 3with this id is not found");
        }
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
        

            return("incident not found");}
        
        //throw new UnsupportedOperationException("Not implemented yet");
    

    @Override
    public void dispatch() {
        // TODO: implement
        int [] Incidentidlist = getIncidentIds();
        // for (int i:Incidentidlist){
        //     System.out.println(i);
        // }
        for (int i = 0; i< Incidentidlist.length; i++){
            int current_incident = Incidentidlist[i];
            //System.out.println("hello"+current_incident);
            if (current_incident == 0){
                continue;
                //System.out.println("im breaking ts"+current_incident);
                //break; 
            }
            for (int x = 0; x< this.Incidentarray.length; x++){
                if (this.Incidentarray[x] != null){
            
                
                Incident Current_Incident = this.Incidentarray[x];
                
                if (Current_Incident.status == IncidentStatus.REPORTED){
                    
                
                
                if (Current_Incident.getincidentid() == current_incident){

                    int Incident_x = Current_Incident.x;
                    int Incident_y = Current_Incident.y;
                    int ChosenUnitId = -1;
                    int SmallestDistance = Integer.MAX_VALUE;
                    int ChosenHomeStationId = -1;
                    for (int y = 0; y< this.Unitarray.length; y++){
                        Unit CurrentUnit = this.Unitarray[y];
                        if  (CurrentUnit == null){
                            break;

                        }
                        else{
                            if (CurrentUnit.STATUS == UnitStatus.IDLE){
                                if ((CurrentUnit.TYPE== UnitType.AMBULANCE && Current_Incident.Type == IncidentType.MEDICAL)|| (CurrentUnit.TYPE == UnitType.FIRE_ENGINE && Current_Incident.Type == IncidentType.FIRE) || (CurrentUnit.TYPE == UnitType.POLICE_CAR && Current_Incident.Type == IncidentType.CRIME)){
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
                            if (this.Unitarray[a] != (null)){
                                Unit Curr_unit = this.Unitarray[a];
                                if (ChosenUnitId == Curr_unit.UnitID){
                                    Curr_unit.STATUS = UnitStatus.EN_ROUTE;
                                    Curr_unit.AssignedIncidentId = Current_Incident.getincidentid();
                                    Current_Incident.setincidentsUnitid(Curr_unit.get_unit_id());
                                }
                            }
                        }
                    }

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
        //System.out.println("RAWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWR 1");
        int [] UNITLIST = getUnitIds();
        //System.out.println("RAWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWR 2");
        //Unit tempUnit = null;
        //System.out.println("RAWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWR 3");
        for (Unit u : Unitarray) {
            Unit tempUnit = null;
            for (int i = 0 ; i<UNITLIST.length;i++){
                //System.out.println(u.get_unit_id());
                if (u != (null) ){
                    //System.out.println(u);
                    if (u.get_unit_id() == i) {
                    //System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
                    tempUnit = u;
                    }
                }
            }
            //System.out.println("RAWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWR 4");
            //System.out.println(tempUnit.TYPE+"...............................................");
            if (tempUnit != (null)){
            Incident incident = null ;
            for (int t = 0; t<this.Incidentarray.length;t++){
                    incident = (Incident)this.Incidentarray[t];
                    if (incident.getincidentid() == tempUnit.AssignedIncidentId){
                        break;
                    }
                }
            //System.out.println(incident.Type+"    ---------------------------------------------------------------------");
            if (tempUnit != (null)){
                if (tempUnit.STATUS == UnitStatus.EN_ROUTE){
                    int X = tempUnit.xloc;  
                    int Y = tempUnit.yloc;
                    int [][] potential = new int[4][2];  // 4 coordinates each with y,x
                    int x;
                    int y;
                    if(Y+1<this.height){
                        //N is valid
                        //System.out.println("NORTH");
                        y = Y+1;
                        x = X;
                        potential[0][0] = y;
                        potential[0][1] = x;
                    }
                    if (X+1< this.width) {
                        //E is valid
                        //System.out.println("EAST");
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
                    I_xloc = incident.x;
                    I_yloc = incident.y;
                    // for (int l= 0 ; l<potential.length;l++){
                    //     System.out.println(potential[l][0]);
                    //     System.out.println(potential[l][1]);
                    // }
                    int OG_MAN = ((Math.abs((I_yloc-Y))+Math.abs((I_xloc-X))));
                    for (int z=0;z<potential.length;z++){
                        int xLOC = potential[z][1];
                        int yLOC = potential[z][0];

                        if (this.Obstaclearray[yLOC][xLOC] != "obstacle"){
                            int New_Score =  ((Math.abs((I_yloc-yLOC)))+Math.abs((I_xloc-xLOC)));
                            //System.out.println(New_Score+" <"+OG_MAN);
                            if (New_Score <OG_MAN){
                                //CHANging location
                                tempUnit.xloc = xLOC;
                                tempUnit.yloc = yLOC;
                                System.out.println("This way"+z);
                                break;
                            }
                        } 
                    }
                    //2) Mark New arrivals
                    if ((tempUnit.yloc == I_yloc)&&(tempUnit.xloc == I_xloc)){
                        //set status to 
                        tempUnit.STATUS = UnitStatus.AT_SCENE;
                        incident.status = IncidentStatus.IN_PROGRESS;
                    }
                }
                else {
                    if (tempUnit.STATUS == UnitStatus.AT_SCENE){
                        tempUnit.WORK += 1;
                        if (tempUnit.WORK == tempUnit.ticks){
                            incident.status = IncidentStatus.RESOLVED;
                            tempUnit.STATUS = UnitStatus.IDLE;
                            tempUnit.WORK = 0;

                        }
                                                
                        }
                    }
            //int count = 0 ;
            //System.out.println(++count);
            }
            }
        }
        //System.out.println(tempUnit.TYPE);
        // 3) process on scene work 
        // 4) resolve completed incidents by ascending unit id 
        
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String getStatus() {

        String StatusString = "TICK=" + this.ticks +"\n STATIONS="+ this.current_station_num +" UNITS="+ this.current_unit_num + " INCIDENTS="+ this.current_incident_num+ " OBSTACLES="+this.current_obstacle_num ;
        String IncidentString = "INCIDENTS\n ";
        for (int x = 0 ; x<Incidentarray.length;x++){
            if (this.Incidentarray[x] != null){
                String TEMP = Incidentarray[x].incidentview();
                IncidentString = IncidentString + "\n"+ TEMP;
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
                        UnitStrings = UnitStrings + "\n"+ temp;
                        break;
                    case FIRE_ENGINE:
                        Fire_engine fire_engine = (Fire_engine) this.Unitarray[length];
                        String temp2 = fire_engine.unitview();
                        UnitStrings = UnitStrings + "\n"+ temp2;
                        break; 
                    case POLICE_CAR:
                        Police_car police = (Police_car) this.Unitarray[length];
                        String temp3 = police.unitview();
                        UnitStrings = UnitStrings + "\n"+ temp3;
                        break;
                }
            }
        } 
        //throw new UnsupportedOperationException("Not implemented yet");
        String REPORT = StatusString+"\n"+ IncidentString+"\n"+UnitStrings;
        return (REPORT);
    }
    
    }

    

        