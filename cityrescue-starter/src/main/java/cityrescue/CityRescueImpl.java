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
 * This is the main class that implements the CityRescue interface
 * with all the methods and exception throws stated in the contract.
 * Arrays for all objects (and obstacles) stored in arrays mirroring the grid
 * 
 * 
 */
public class CityRescueImpl implements CityRescue {

    public int ticks;
    public CityMap TheMap;
    public Station [] Stationarray;
    public String [][] Obstaclearray;
    public Unit [] Unitarray;
    public Incident [] Incidentarray;
    public final int MAX_STATIONS = 20;
    public final int MAX_UNITS = 50;
    public final int MAX_INCIDENTS = 200;
    public int current_station_num;
    public int current_station_id;
    public int current_unit_num;
    public int current_unit_id;
    public int current_incident_num;
    public int current_incident_id;
    public int current_obstacle_num;
    public int width;
    public int height;

    


/**
 * This is the constructor and will create the arrays of size intended 
 * using Constants set up as default values before the constructor 
 * e.g. of max capacity stations, incidents, units.
 * 
 */

    public CityRescueImpl(){
        
        this.Stationarray = new Station[MAX_STATIONS];
        this.Incidentarray = new Incident[MAX_INCIDENTS];
        this.Unitarray = new Unit[MAX_UNITS]; 
    }

    @Override
    public void initialise(int width, int height) throws InvalidGridException {
        /**
         * This is used to create / reset the grid with all values being reset to 0 and all arrays to be set to contain only null
         * It will also create the grid by calling CityMap constructor and passing in the parameters that initialise took to create grid of given size
         * it will
         * @params width , height 
         * 
         */
        if (width <= 0 || height <= 0){
            throw new InvalidGridException("Invalid Grid size");
        }
        this.Stationarray = new Station[MAX_STATIONS];
        this.Incidentarray = new Incident[MAX_INCIDENTS];
        this.Unitarray = new Unit[MAX_UNITS]; 
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
        


        //throw new UnsupportedOperationException("Not implemented yet");
    }
    /**
     * Get method for the grid size attributes
     * instantiated with the width and height parameters
     * 
     * @return returns an integer array that is structured 
     * with the grids width then height attribute.
    */
    @Override
    public int[] getGridSize() {
        
        int[] Gridsize = this.TheMap.getGridSize();
        return Gridsize;
    }
    /**
     * addObstacle is a method that sets an obstacle with coordinates (within the grid)
     *on the 2d obstacle array
     * given parameter as coordinates to mirror the exact positions on the grid
     * used to simulate city rescue 
     * @param x This parameter is the integer x coordinate for the 2d obstacle array 
     * that the obstacle string is placed in.
     * @param y This parameter is the integer y coordinate for the 2d obstacle array 
     * that the obstacle string is placed in.
     * @throws InvalidLocationException if coordinates given are out of bound
     * 
     */
    @Override
    public void addObstacle(int x, int y) throws InvalidLocationException {
        if (x<0 || y<0 || y>this.height || x>this.width){
            throw new InvalidLocationException("Out of bounds");
        }
        this.Obstaclearray[y][x] = "obstacle";
        this.current_obstacle_num += 1;
    }
    /**
     * removeObstacle takes x and y values (if in the size of the grid) 
     * and sets the string empty this works as 
     * @throws InvalidLocationException if the obstacle coordinates are out of the grid.
     */
    @Override
    public void removeObstacle(int x, int y) throws InvalidLocationException {
        if (x<0||y<0||x>this.height||y>this.width){
            throw new InvalidLocationException("Out of Bounds");
        }        
        this.Obstaclearray[y][x] = " ";
        this.current_obstacle_num -= 1;
    }
    /**
     * Station object is created at a grid position which is valid 
     * its instantiated and a station id is set using an enumarating id number
     * and set station id method. 
     * @param name  given name of the station to be set as the name attribute.
     * @param x x position to be set for station to instantiated at 
     * @param y y position to be set for station to instantiated at
     * @return It returns the Id given to the instantiated station method or just an int 
     * if it cannot be created for any reason
     * @throws InvalidNameException throws if name string is empty
     * @throws InvalidLocationException throws if station coordinates are invalid and outside of grid coordinates
     * 
     * 
     */
    @Override
    public int addStation(String name, int x, int y) throws InvalidNameException, InvalidLocationException {
        if (name == ""){
            throw new InvalidNameException("Must enter a name");
        }
        if (x<0 || y<0 || y>this.height || x>this.width){
            throw new InvalidLocationException("Out of bounds");
        }
        if (current_station_num == MAX_STATIONS){
            throw new CapacityExceededException("There are already max amount of stations");
        }
         for(int i=0;i<this.Stationarray.length;i++){
            if (this.Stationarray[i]==(null)){
                int length = i;
                Station Newstation = new Station(name, x, y);
                this.Stationarray[length] = Newstation;
                this.current_station_num += 1;
                this.current_station_id += 1;
                Newstation.SetStationId(this.current_station_id);
                return  Newstation.GetId();
            }
        }
        return 1;
    }
    /**
     * remove station takes an id of a created station 
     * searches through the station id list in afor loop. 
     * If the id given as a parameter is equal to one of the station in the list id 
     * then that position is set to null in the station list.
     * If any unit is found linked to the Station an exception is thrown.
     * @param stationId integer which represents the unique id of the station object which needs to be removed.
     * @throws IDNotRecognisedException throws when the Station Id given does not exist with an available station object.
     * @throws IllegalStateException throws when there is a unit in the station being removed.
     * 
     */
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
                if (c_unit.get_station() == stationId){
                    HasUnit = true;
                }

            }
        }
        if (HasUnit == true){
            throw new IllegalStateException("Unit in the station trying to be removed");
        }
        for (int x = 0; x<this.Stationarray.length; x++){
            if (this.Stationarray[x].GetId() == stationId){
                this.Stationarray[x] = null;
                this.current_station_num -= 1;
            }
        }
    }

    /**
     * 
     * given a valid chosen station and valid maxUnits 
     * (by calculating the current number of stations in a for loop 
     * through the unit)
     * it uses a for loop and ids like others to identify the station
     * then gets its attributes and sets the parameter maxUnits equal
     * to the Station chosen max capacity.
     * 
     * @param stationId the station id integer given for the station 
     * that needs to have its max capacity attribute set to maxUnits
     * @param maxUnits the amount of units the station maxium 
     * is to be set to.
     * @throws IDNotRecognisedException throws when station id given does not exist.
     * @throws InvalidCapacityException  throws when Station currently has more units than  max capacity given
     * 
     */
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
                if (c_unit.get_station() == stationId){
                    CurrentUnitNum += 1;
                }

            }
        }
        if (maxUnits<=0 || CurrentUnitNum > maxUnits){
            throw new InvalidCapacityException("Too full");
        }
        for (int x = 0; x<this.Stationarray.length; x++){
            if( this.Stationarray[x] != (null)){
            if (this.Stationarray[x].GetId() == stationId){
                this.Stationarray[x].setstationmaxcapacity(maxUnits);
            }
        }
    }
}
/**
 * Get station ids method loops through the station array
 * and if it is not a null value it uses the get method get id 
 * to get the integer value of the id and set it equal to the index
 * of the for loop value of the new station id
 * Then sort this list once it is filled with all station ids
 * from all the stations in the station array.
 * @return returns the sorted list of station ids.
 */
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
        
        return stationIdlist;
    }

    /**
     * Add unit takes a station id and type of unit to create
     * if the station exists and its a valid type
     * then a for loop is run through the unit array and if an empty null
     * space is found then a switch statment is run to create an 
     * object at that empty index of the specific Enum Unitype
     * specified. Once the unit has been created. The station array
     * is iterated  until the station seleteced is found (for loop get id methods)
     * then it uses a set station method to give the unit a home station id.
     * 
     * 
     * @param type the Unit type Enum which is passed through which decides what type of unit
     * will be instantiated.
     * @param stationId the station unique id integer given which station to set the units station id
     * and station its on give 
     * 
     * 
     * @return returns the Id of the newly made Unit (or an int if unit was not made)
     * @throws IDNotRecognisedException throws if Station id given is not a station that exists
     * @throws InvalidUnitException throws if Unit type given is a null value
     * @throws IllegalStateException throws if Station added is already full
     * 
     */
    @Override
    public int addUnit(int stationId, UnitType type) throws IDNotRecognisedException, InvalidUnitException, IllegalStateException {
        if (MAX_UNITS == current_unit_num){
            throw new CapacityExceededException("Already reach max capacity of units");
        }
        boolean StationExists =  false;
        for (int x= 0; x < this.Stationarray.length; x++){
            if (this.Stationarray[x] != (null)){
                Station Stat = this.Stationarray[x];
                if (Stat.GetId() == stationId){
                    StationExists = true;
                    //System.out.println(Stat.GetCurrentStationCapacity()+" "+Stat.getstationmaxcapacity()+"--------------------------------");
                    if (Stat.GetCurrentStationCapacity() == Stat.getstationmaxcapacity()){
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
                        //System.out.println(ambulance.get_unit_type());
                        this.Unitarray[length] = ambulance;
                        break;
                    case FIRE_ENGINE:
                        //System.out.println("Fire enginge");
                        Fire_engine fire_engine = new Fire_engine(stationId);
                        this.Unitarray[length] = fire_engine;
                        break;
                    case POLICE_CAR:
                        //System.out.println("Police car");
                        Police_car police = new Police_car(stationId);
                        this.Unitarray[length] = police;
                        break;
                }
                for (int x =0; x< this.Stationarray.length; x++){
                            Station CurrentStation = this.Stationarray[x];
                            if (CurrentStation != null) {
                                
                            int CurrentStationid = CurrentStation.GetId();
                            if (CurrentStationid == stationId) {
                                this.Unitarray[length].set_xloc(CurrentStation.Getx()); 
                                this.Unitarray[length].set_yloc(CurrentStation.Gety()); 
                                this.current_unit_id += 1;
                                this.current_unit_num += 1;
                                this.Unitarray[length].SetUnitID(this.current_unit_id); 
                                CurrentStation.IncreaseOrDecreaseCurrentStationCapacity("+");;
                                //System.out.println(this.Unitarray[length].get_unit_type());
                                return(this.Unitarray[length].get_unit_id());
                            }
                            }
                }
            break;
            }
        }
       
        return(1);
    }

    /**
     * The decommission unit method throws error if a non existent id is given 
     * or if the Unit type enum is not equal to idle and is equal to en routeor at the scene.
     * This is because you should not be able to decommision a unit that is currently
     * resolving an incident. The index and Unit in the Unit array 
     * found from looping through then checking Unit id is now set to null
     * if the Enum conditions are correct. But first, a Get station id is used on the 
     * Unit in a for loop through station array; 
     * the station with the unit that is going to be removed's 
     * current capacity is decrement by one
     * 
     *  The integer variable stored to count the current num of units is decremented.
     * 
     * 
     * 
     * 
     * 
     * @param unitId Unique id int parameter given to select the unit to decomission
     */
    @Override
    public void decommissionUnit(int unitId) throws IDNotRecognisedException, IllegalStateException {
        boolean UnitExist = false;
        for (int y =0; y<this.Unitarray.length; y++){
            if (this.Unitarray[y] != null){
                Unit CheckUnit = this.Unitarray[y];
                if (CheckUnit.get_unit_id() == unitId){
                    UnitExist = true;
                }
            }
        }
        if (UnitExist == false){
            throw new IDNotRecognisedException("Unit does not exist");
        }

        
        for (int x = 0; x<this.Unitarray.length; x++){
            if (this.Unitarray[x] != null){
                Unit DecomUnit = this.Unitarray[x];
                if (DecomUnit.get_unit_id() == unitId){
                    if (DecomUnit.get_status() == UnitStatus.EN_ROUTE || DecomUnit.get_status() == UnitStatus.AT_SCENE){
                        throw new IllegalStateException();

                    }

                    if (DecomUnit.get_status() != UnitStatus.EN_ROUTE || DecomUnit.get_status() != UnitStatus.AT_SCENE){
                        for (int e = 0; e< this.Stationarray.length; e++){
                            if (this.Stationarray[e] != (null)){
                             Station Stat = this.Stationarray[e];
                            if (Stat.GetId() == DecomUnit.get_station()){
                                Stat.IncreaseOrDecreaseCurrentStationCapacity("-");
                            
                        }
                    }

                }  
                        this.Unitarray[x] =null;
                        this.current_unit_num -= 1;
                        
                    
                }
            }
        }
    }
}

        
    /**
     * The Transfer Unit method throws an exception if the unitId or station id cannot be found
     * or if Units status enums are en route or at the scene. If the unit is dealing with an incident
     * it should not be transfered between units. 
     * If unit id is found and is an object then
     * the  set station id method is used to set the station id attribute
     * to the newStationid parameter given transferring where a Unit object of a given type
     * is stationed.
     * 
     * @param unitId integer unique identifier for a specific unitId
     * @param newStationId integer unique that should if valid 
     * be set to give the selected unit a new station at that id.
     * @throws IllegalStateException - to transfer status has to be idle if not errors
     * @throws IDNotRecognisedException - Cannot find unit with unit id passed therefore must not exist
     */

    @Override
    public void transferUnit(int unitId, int newStationId) throws IDNotRecognisedException, IllegalStateException {
        boolean UnitExist = false;
        for (int y =0; y<this.Unitarray.length; y++){
            if (this.Unitarray[y] != null){
                Unit CheckUnit = this.Unitarray[y];
                if (CheckUnit.get_unit_id() == unitId){
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
                if (TransferUnit.get_unit_id() == unitId){
                    if (TransferUnit.get_status() != UnitStatus.IDLE ){
                        throw new IllegalStateException("Unit is not IDLE therefore cannot be transferred to another station");
                    }
                }
            }
        }
        for (int x = 0; x<this.Unitarray.length; x++){
            if (this.Unitarray[x] != null){
                if (this.Unitarray[x].get_unit_id() == unitId){
                    this.Unitarray[x].set_station(newStationId);
                }
            }
        }
        

    }
    /**
     * setUnitOutOfService is used to change the status of a selected unit to and from OUT_OF_SERVICE and IDLE based on boolean @param outOfService which will
     * specify whether unit needs to be set to I LE or Set to out of service @param unitId is also passed this specifies which unit needs to be changed
     * Unitid passed is checked if it exists else throws exception. 
     * After checking unit id and ensuring that status is not either EN_route or At_scene as those arent valid
     * THen it checks boolean of input if true it will find Unit object then check if status is IDLE if is it willchange status to out of service
     * if not it will through exception as is already of type trying to be set to
     * and vice versa for false
     * @throws IDNotRecognisedException - Cannot find id of unit given therefore must throw exception
     * @throws IllegalStateException - State given is not of expected type to convert from out of service to idle and v
ce versa    */
    @Override
    public void setUnitOutOfService(int unitId, boolean outOfService) throws IDNotRecognisedException, IllegalStateException {
        boolean isin = false;
        for (int x = 0; x<this.Unitarray.length; x++){
            if (this.Unitarray[x]!= null){
            if (this.Unitarray[x].get_unit_id()== unitId){
                isin = true;
                if(this.Unitarray[x].get_status() == UnitStatus.EN_ROUTE || this.Unitarray[x].get_status() == UnitStatus.AT_SCENE ){
                    throw new IllegalStateException("Not allowed units of types that arent OUt of service or idle");
                }
            }
        }
        }
        if (isin==false){
            throw new IDNotRecognisedException("this id is not an existing unit");
        }
        boolean used = false;
        if (outOfService == true){
            for (int x = 0; x<this.Unitarray.length; x++){
                if (this.Unitarray[x].get_unit_id() == unitId ){
                    if (this.Unitarray[x].get_status() == UnitStatus.IDLE){
                        this.Unitarray[x].set_status(UnitStatus.OUT_OF_SERVICE);
                    }
                    else{
                        throw new IllegalStateException("Unit passed already of type OUT_OF_SERVICE");
                    }
                }
            }
        }
        else{
            for (int x = 0; x< this.Unitarray.length; x++){
                if (this.Unitarray[x].get_unit_id() == unitId){
                    if (this.Unitarray[x].get_status() == UnitStatus.OUT_OF_SERVICE){
                        this.Unitarray[x].set_status(UnitStatus.IDLE); 
                    }
                    else{
                        throw new IllegalStateException("Unit passed already of type IDLE");
                    }
                }
            }
        }

    }

    /** 
    This method takes no parameters and is used to obtain the sorted list of used ids it works by checking the unit array 
    and if not null it will add the id of the unit to a new list of equal length to previous then it will use Array.sort on new list
    @return UNitIdlist which will return the sorted array of unit ids
    */
    @Override
    public int[] getUnitIds() {
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
        
    }
    /**
     * viewUnit is used to view all the attributes of a specific unit
     * @param unitId is used to locate incident in UNitarray
     * Method will check if unitid is valid else throwing error
     * If it is valid unit method unitview is used to obtain the string of all attributes 
     * @return UnitStrings will return this string      
     * @throws IDNotRecognisedException - cannot find id passed must not exist therefore error
     * 
     * */
    
    @Override
    public String viewUnit(int unitId) throws IDNotRecognisedException {
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
    }
    /**
     * report incident  is used to create an object of incident class it takes in the parameters  and passes them into constructor after checking all data passed is valid
     * @param type
     * This takes in the IncidentType of the new incident
     * @param severity
     * This is used to set the severity of the incident it has to be checked that it is within bounds (1-5) else exception thrown
     * @param x @param y 
     * These are used to set the coordinate of the new incident it has to be checked it is within bounds and that location is not already in location
     * Max_Incidents is used to compare with current incident number if they are same then limit has been reached and new incident is not allowed to be made and error is thrown
     * 
     * if it passes all the throws then it will find an empty space on Incidentarray and willcreate a new object in location it will then increment incident_num and incident_id and pass incident id to location
     * @throws InvalidSeverityException - severity passed is 
utside of bounds 1-5 must ensure valid data entered
     * @throws InvalidLocationException - means location selected is out of bounds or already occupied 
     * @throws CapacityExceededException - cant add more than max capacity of incidents so error thrown
     *      */
    @Override
    public int reportIncident(IncidentType type, int severity, int x, int y) throws InvalidSeverityException, InvalidLocationException {
        if (MAX_INCIDENTS == current_incident_num){
            throw new CapacityExceededException("Already reach max capacity of incidents");
        }
        
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
    /**
     * Cancel incident is used to cancel incidents that are either dispatched or reported
     * it works by first checking incident id passed exists else throwing exception
     *  Then it will check if incident selected status is reported 
     * IF IT IS it will change incidents status to CANCELLED and reduce total number of incedents by 1 
     * else if it will check if status is DISPATCHED in which case it will do the same but also find the attached units id and will change its status to idle and remove its attached Incident ID  
     *   else it will throw an error as an incorrect incident type is passed
     * @param incidentId
     * is usedd to find incednt to be cancelled
     * 
     * Has no returns as all updates made to incidentarray and targeted object on there     
     * @throws IDNotRecognisedException - id was not found in array so valid id not entered
     * @throws IllegalStateException - Was an incorrect type as cancel incedent epects either REPORTED or DISPATCHED
     */

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
            throw new IDNotRecognisedException("Incident with this id is not found");
        }
        // TODO: implement
        for (int x = 0; x<this.Incidentarray.length; x++){
            if (this.Incidentarray[x] != (null)){ 
                if ((this.Incidentarray[x].getincidentid()) == incidentId){
                    if (this.Incidentarray[x].get_IncidentStatus()== IncidentStatus.REPORTED){
                        this.Incidentarray[x].CancelIncidentstatus(IncidentStatus.CANCELLED);
                        this.current_incident_num -= 1;
                         for (int b=0;b<this.Unitarray.length;b++){
                            if (this.Unitarray[b] != (null)){
                                if (this.Unitarray[b].GetAssignedIncidentId() == incidentId){
                                    this.Unitarray[b].set_status(UnitStatus.IDLE);
                                    this.Unitarray[b].SetAssignedIncidentId(-1);// sets assigned id to null value again
                                }
                            }
                        }
                    }
                    else if (this.Incidentarray[x].get_IncidentStatus() == IncidentStatus.DISPATCHED){
                        this.Incidentarray[x].CancelIncidentstatus(IncidentStatus.CANCELLED);
                        this.current_incident_num -= 1;
                        for (int t=0;t<this.Unitarray.length;t++){
                            if (this.Unitarray[t] != (null)){
                                if (this.Unitarray[t].GetAssignedIncidentId() == incidentId){
                                    this.Unitarray[t].set_status(UnitStatus.IDLE);
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


    /**
     * This method is used to update the severity of a specific incident to a new value
     * It works by first checking if both incidentid and newSeverity are valid if not it will through the respective exception     
     * If it they are valid it will check if the incident selected is of correct status (REPORTED or DISPATCHED)     if it isnt it will throw corresponding exception
     * if it is valid it will run the method SetIncidentseverity passing new severity in to update incident object. 
     * @param incidentId
     * Used to locate correct incident to update
     * @param newSeverity 
     * Is the new value used to update severity
     * @throws IDNotRecognisedException  - incident is not found in unitarray a
d des not exist of id     * @throws InvalidSeverityException - seveertiy is out of bounds given 1-5
     * @throws IllegalStateException - incident is already resolved / cancelled so not valid to be escalated 

     *    */
    @Override
    public void escalateIncident(int incidentId, int newSeverity) throws IDNotRecognisedException, InvalidSeverityException, IllegalStateException {
        boolean Isin = false;
        for (int x=0;x<Incidentarray.length;x++){
            if (Incidentarray[x] != null){
            if (incidentId == Incidentarray[x].getincidentid()){
                Isin = true;
                if (Incidentarray[x].get_IncidentStatus() == IncidentStatus.RESOLVED || Incidentarray[x].get_IncidentStatus() == IncidentStatus.CANCELLED ){
                    throw new IllegalStateException("Incident is Resolved/Cancelled");
                }
             } }
        }
        if (Isin == false){
            throw new IDNotRecognisedException("Incident with this id is not found");
        }
        if (newSeverity<1 || newSeverity>5){
            throw new InvalidSeverityException("New severerity is not valid");
        }
        for(int i=0;i<this.Incidentarray.length;i++){
            int needid = this.Incidentarray[i].getincidentid();
            if (needid == incidentId){
                this.Incidentarray[i].SetIncidentseverity(newSeverity);
                break;
            }
        }
    }

    /**
     * This method is used to go through all Incidents in Incidentarray and will obtain all ids and then sort them to then be returned
     * @return Incidentidlist
     * this will return the sorted id's from Incidentarray
    */
    @Override
    public int[] getIncidentIds() {
        
        int[] Incidentidlist = new int[this.Incidentarray.length];
        for (int i = 0; i < this.Incidentarray.length; i++){
            if (Incidentarray[i] != null){
            Incident current_Incident = Incidentarray[i];
            Incidentidlist[i] =  current_Incident.getincidentid();
            }      
        }
        Arrays.sort(Incidentidlist);
        
        
        return Incidentidlist;
        
        //throw new UnsupportedOperationException("Not implemented yet");
    }
    /**
     * This method will take in an incident id and will then find try to find the incident 
     * 
     * If it is found it will call the incident method incidentview which will return the string off all key attributes of objct which the method then returns
     * If the incident with matching id is not found it will throw an except on    
     * @param incidentId
     * This parameter is required to allow method to identify specific variable searching for 
     * @return (currentIncidentViewed.incidentview())
     * This will return a string about the requested id
    */
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
            throw new IDNotRecognisedException("Incident with this id is not found");
        }
             
        for (int i=0;i<this.Incidentarray.length;i++){
            if (this.Incidentarray[i] != null){
                Incident currentIncidentViewed = this.Incidentarray[i];
                int current_Incident_id_viewed = currentIncidentViewed.getincidentid();
                //System.out.println(current_Incident_id_viewed);
                if (current_Incident_id_viewed == incidentId){
                    return(currentIncidentViewed.incidentview());
                }
           
            }
        }
        

            return("incident not found");}
        
    
    /**
     *  
     *  Dispatch is a method that gets the list of Incident Ids 
     * then does a loop through the ids 
     * getting the incident Ids objects. 
     * If the incident has just been reported and has the corresponding 
     * enum then  it can look to assign 
     * the closest Idle Unit (in manhatten distance).
     * It sets a max value for the smallest distance intially 
     * before the first iteration of a loop checking through every Unit 
     * (checking its in the Unit array then accessing its methods)  and then
     * checks whether each unit has a shorter manhatten  distance
     * than the current shortest distance then sets the coordinatest the id and station id of 
     * the unit closest as the chosen id and stationid (for the tie breaker).
     * If the manhatten distance from the last closest unit 
     * is equal to the current unit being checked in the for loops distance
     * from then the lowest id decides which Unit is dispatched. 
     * if hypothetically the unit ids are equal the unit id selected to 
     * be dispatched to the current incident is the unit with the lowest station id.
     * 
     * 
     * 
     */
    @Override
    public void dispatch() {
        
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
                
                if (Current_Incident.get_IncidentStatus()== IncidentStatus.REPORTED){
                    
                
                
                if (Current_Incident.getincidentid() == current_incident){

                    int Incident_x = Current_Incident.GetIncidentX();
                    int Incident_y = Current_Incident.GetIncidentY();
                    int ChosenUnitId = -1;
                    int SmallestDistance = Integer.MAX_VALUE;
                    int ChosenHomeStationId = -1;
                    for (int y = 0; y< this.Unitarray.length; y++){
                        Unit CurrentUnit = this.Unitarray[y];
                        if  (CurrentUnit == null){
                            break;

                        }
                        else{
                            if (CurrentUnit.get_status() == UnitStatus.IDLE){
                                if ((CurrentUnit.get_unit_type()== UnitType.AMBULANCE && Current_Incident.GetIncidentType() == IncidentType.MEDICAL)|| (CurrentUnit.get_unit_type() == UnitType.FIRE_ENGINE && Current_Incident.GetIncidentType() == IncidentType.FIRE) || (CurrentUnit.get_unit_type() == UnitType.POLICE_CAR && Current_Incident.GetIncidentType() == IncidentType.CRIME)){
                                int CurrentUnitx = CurrentUnit.get_xloc();
                                int CurrentUnity = CurrentUnit.get_yloc();
                                int Currentdistance = Math.abs(Incident_x - CurrentUnitx) + (Math.abs(Incident_y- CurrentUnity));
                                if (Currentdistance < SmallestDistance){
                                    SmallestDistance = Currentdistance;
                                    ChosenUnitId = CurrentUnit.get_unit_id();  
                                }
                                else if(Currentdistance == SmallestDistance){
                                    int CurrentChosenUnitId = CurrentUnit.get_unit_id();
                                    if (CurrentChosenUnitId < ChosenUnitId){
                                        ChosenUnitId = CurrentChosenUnitId;
                                    }
                                    else if (CurrentChosenUnitId == ChosenUnitId){
                                        int CurrentChosenHomeStationId = CurrentUnit.get_station();
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
                                if (ChosenUnitId == Curr_unit.get_unit_id()){
                                    Curr_unit.set_status(UnitStatus.EN_ROUTE);
                                    Curr_unit.SetAssignedIncidentId(Current_Incident.getincidentid());
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
    }

    /**
     * The method tick is used to advance each unit either moving toward assigned incident/working onassigned incident
     * It works by checking each unit within unitarray andfinding its corresponding incident and then based on whether it is en_route or at scene 
     * it will either move towards unit based off of movement rules otherwise if the unit status is at scene it will increment the units WORK 
     * by calling get and set work if WORK is equal to units tick it will set itslf to idle and incident to RESOLVED
     * Movement rules works by:
     * a) checking all directions around and if valid (form check if status is en_route to if change == falsen) move and reduces the totalmanhattan distance it will ta e move N,E,S,W
     * b) find the first move as none reduced the manhattan distance
     * c) otherwise no move will be made and for loop will move onto next unit
     * d) if it arrives to the scene it will change status to at_scene and set incident to in_progress 
     * 
    */ 
    @Override
    public void tick() {
        // 1) Move En_route units first by ascending unit id
        //System.out.println("RAWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWR 1");
        this.ticks += 1;
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
            //System.out.println(tempUnit.get_unit_type()+"...............................................");
            if (tempUnit != (null)){
            Incident incident = null ;
            for (int t = 0; t<this.Incidentarray.length;t++){
                    incident = (Incident)this.Incidentarray[t];
                    if (incident != null){
                    if (incident.getincidentid() == tempUnit.GetAssignedIncidentId()){
                        break;
                    }
                    }
                }
            //System.out.println(incident.get_unit_type()+"    ---------------------------------------------------------------------");
                if (tempUnit.get_status() == UnitStatus.EN_ROUTE){
                    int X = tempUnit.get_xloc();  
                    int Y = tempUnit.get_yloc();
                    int [][] potential = new int[4][2];  // 4 coordinates each with y,x
                    boolean [] move_valid = new boolean[4]; // makes array of length 4 with default value false
                    int x;
                    int y;
                    if(Y+1<this.height){
                        //N is valid
                        //System.out.println("NORTH");
                        y = Y+1;
                        x = X;
                        potential[0][0] = y;
                        potential[0][1] = x;
                        move_valid[0] = true;

                        
                    }
                    if (X+1< this.width) {
                        //E is valid
                        //System.out.println("EAST");
                        y = Y;
                        x = X+1;
                        potential[1][0] = y;
                        potential[1][1] = x;
                        move_valid[1] = true;
                    }
                    if(Y-1>=0){
                        //S is valid
                        y = Y-1;
                        x = X;
                        potential[2][0] = y;
                        potential[2][1] = x;
                        move_valid[2] = true;
                    }  
                    if(X-1>=0){
                        //W is valid
                        y = Y;
                        x = X-1;
                        potential[3][0] = y;
                        potential[3][1] = x;
                        move_valid[3] = true;
                    } 
                    int I_xloc = incident.GetIncidentX();
                    int I_yloc = incident.GetIncidentY();

                    // for (int l= 0 ; l<potential.length;l++){
                    //     System.out.println(potential[l][0]);
                    //     System.out.println(potential[l][1]);
                    // }
                    int OG_MAN = ((Math.abs((I_yloc-Y))+Math.abs((I_xloc-X))));
                    boolean Change = false;
                    for (int z=0;z<potential.length;z++){
                        if (move_valid[z] == true){
                            int xLOC = potential[z][1];
                            int yLOC = potential[z][0];

                            if (this.Obstaclearray[yLOC][xLOC] != "obstacle"){
                                int New_Score =  ((Math.abs((I_yloc-yLOC)))+Math.abs((I_xloc-xLOC)));
                                //System.out.println(New_Score+" <"+OG_MAN);
                                if (New_Score <OG_MAN){
                                    //CHANging location
                                    Change = true;
                                    tempUnit.set_xloc(xLOC);
                                    tempUnit.set_yloc(yLOC);
                                    System.out.println("This way"+z);
                                    break;
                                }
                            }
                        }
                    }    
                    if (Change == false){
                        for (int a=0; a<potential.length; a++){
                            int newxLOC = potential[a][1];
                            int newyLOC = potential[a][0];
                            if (this.Obstaclearray[newyLOC][newxLOC] != "obstacle"){
                                tempUnit.set_xloc(newxLOC);
                                tempUnit.set_yloc(newyLOC);
                                break;
                            }
                                
                        }
                                
                    }
                         
                    
                    //2) Mark New arrivals
                    if ((tempUnit.get_yloc() == I_yloc)&&(tempUnit.get_xloc() == I_xloc)){
                        //set status to 
                        tempUnit.set_status(UnitStatus.AT_SCENE);
                        incident.SetIncidentStatus(IncidentStatus.IN_PROGRESS);
                    }
                }
                else {
                    if (tempUnit.get_status() == UnitStatus.AT_SCENE){
                        tempUnit.set_WORK(tempUnit.get_WORK()+1); 
                        if (tempUnit.get_WORK() == tempUnit.get_unit_ticks()){
                            incident.SetIncidentStatus(IncidentStatus.RESOLVED);
                            tempUnit.set_status( UnitStatus.IDLE);
                            tempUnit.set_WORK(0);

                        }
                                                
                        }
                    }
            //int count = 0 ;
            //System.out.println(++count);
            
            }
        }
        //System.out.println(tempUnit.get_unit_type());
    }
    /**
     * This method is used to make a string of the current state of dispatch screen outputting the required amount of:
     * Ticks, STations, Units, Incidents and Obstacles
     * It will then output all the different objects of class Incident in ascending ID order
     * Then it will do the same for units
     * @return REPORT
     *  the string created is then returned 
     */
    @Override
    public String getStatus() {
        

        String StatusString = "TICK=" + this.ticks +"\n STATIONS="+ this.current_station_num +" UNITS="+ this.current_unit_num + " INCIDENTS="+ this.current_incident_num+ " OBSTACLES="+this.current_obstacle_num ;
        String IncidentString = "INCIDENTS";
        int [] INCIDENTLIST = getIncidentIds();
        for (int c = 0; c<INCIDENTLIST.length;c++){
            for (int x = 0 ; x<Incidentarray.length;x++){
                if (this.Incidentarray[x] != null && this.Incidentarray[x].getincidentid() ==INCIDENTLIST[c]){
                    String TEMP = Incidentarray[x].incidentview();
                    IncidentString = IncidentString + "\n"+ TEMP;
                }
            }
        }
        String UnitStrings = "UNITS";
        int [] UNITLIST = getUnitIds();
        for (int c = 0; c<UNITLIST.length;c++){
            for (int i = 0; i<this.Unitarray.length;i++) {
                if (this.Unitarray[i] !=(null) && this.Unitarray[i].get_unit_id() ==UNITLIST[c]) {
                    UnitStrings = UnitStrings + "\n" + Unitarray[i].unitview();
                }
            } 
        }
        //throw new UnsupportedOperationException("Not implemented yet");
        String REPORT = StatusString+"\n"+ IncidentString+"\n"+UnitStrings;
        return (REPORT);
    }
    
    }

    

        