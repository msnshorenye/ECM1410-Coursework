package cityrescue;

public class Station{
    public int x;
    public int y;
    public int stationId;
    public int stationmaxcapacity;
    public int currentcapacity;
    public String stationname;
    public static int NextId = 1;
    public int id;

    public Station(String stationname,int x, int y){
        this.x = x;
        this.y = y;
        this.stationname = stationname;
        this.stationmaxcapacity = 10;
        
        this.id = NextId++;
        this.currentcapacity = 0;

    }
    public int GetId(){
        return this.id;
    }
    public int Getx(){
        return this.x;
    }
    public int Gety(){
        return this.y;
    }
    public int getstationmaxcapacity(){
        return this.stationmaxcapacity;
    }
    public void setstationmaxcapacity(int NewMax){
        this.stationmaxcapacity = NewMax;
    
    }
    public int GetCurrentStationCapacity(){
        return this.currentcapacity;
    }
    public void IncreaseOrDecreaseCurrentStationCapacity(String sign){
        if (sign == "+"){
            this.currentcapacity += 1;
        }
        if (sign == "-"){
            this.currentcapacity += 1;
        }
        
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
    

