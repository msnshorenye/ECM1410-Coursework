package cityrescue;
/**
 * The Station Class is used to create Station Objects 
 * that allow Unit objects to have a specified start point when they are
 * created. It has capacity that can limit the number of Units created.
 * It has get and set methods for all its attributes
 * except station name which allow units
 * to be transfered around Stations 
 * and for the capacity of the station object.  
 */
public class Station{
    
    private int x;
    private int y;
    private int stationId;
    private int stationmaxcapacity;
    private int currentcapacity;
    private String stationname;
    /**
     * The constructor intialises these parameters 
     * as attributes as well as the stations max capacity 
     * set to the station object as a default value 
     * as well as the current capacity attribute
     *  which is set to 0 as when instantiated a station
     * will have 0 units stored in it. 
     * 
     * @param stationname parameter given to be set 
     * as the name of the station.
     * @param x x value given as a parameter 
     * to be set as the station objects x coordinate.
     * @param y y value is the parameter that is
     *  set as the y coordinate for the station object instantiated.
     *
     */

    public Station(String stationname,int x, int y){
        this.x = x;
        this.y = y;
        this.stationname = stationname;
        this.stationmaxcapacity = 100;
        this.currentcapacity = 0;

    }
    /**
     * Get method for Stations Id attribute.
     * @return  Station's Object unique id.
     */
    public int GetId(){
        return this.stationId;
    }
    /**
     * Get method for Stations x coordinate attribute.
     * @return Station's x coordinate.
     */
    public int Getx(){
        return this.x;
    }
    /**
     * Get method for Stations y coordinate attribute.
     * @return Station's y coordinate.
     */
    public int Gety(){
        return this.y;
    }
    /**
     * Get methods for Stations max possible compacity
     * @return Stations object's max capacity
     */
    public int getstationmaxcapacity(){
        return this.stationmaxcapacity;
    }
    /**
     * Set method for Stations max capacity integer.
     * @param NewMax The new max capacity integer for the station
     */
    public void setstationmaxcapacity(int NewMax){
        this.stationmaxcapacity = NewMax;
    
    }
    
    /**
     * Get method for the current capacity/ amount of units currently in the station.
     * @return returns the current capacity of the station (How many units in the station)
     */
    public int GetCurrentStationCapacity(){
        return this.currentcapacity;
    }
    /**
     * Method increases or decreases the current capacity attribute 
     * depending on the parameter given.
     * @param sign string sign is passed in either + or - lead to the 
     * counter tracking the current capacity of the station being increased 
     * or decreased by one respectively.
     */
    public void IncreaseOrDecreaseCurrentStationCapacity(String sign){
        if (sign == "+"){
            this.currentcapacity += 1;
        }
        if (sign == "-"){
            this.currentcapacity += 1;
        }
        
    }
    /**
     * Set method for the Station Unique Id 
     * @param newid entered integer that the Stations Id attribute will be set to.
     */
    public void SetStationId(int newid){
        this.stationId = newid;
    }


    // public boolean AddUnit(){
    //     if (this.currentcapacity >= this
    //         .stationId){
    //         return false;
    //     }
    //     else{
    //         this.currentcapacity += 1;
    //         return true;
    //     }
    // }
    
    // public int RemoveUnit(){
    //     this.currentcapacity -= 1;
    //     return this.currentcapacity;
    // }
    
    // public boolean setCapacity(int capacity){
    //     if (capacity >= 0){
    //         this.stationmaxcapacity = capacity;
    //         return true;
    //     }
    //     else{
    //         return false;
    //     }
    
    }
    

