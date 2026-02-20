package cityrescue;

public class Station{
    public int x;
    public int y;
    public int stationId;
    public int stationmaxcapacity;
    public int currentcapacity;
    public String stationname;
    private static int NextId = 1;
    private final int id;

    public Station(int x, int y, String stationname){
        this.x = x;
        this.y = y;
        this.stationname = stationname;
        this.stationmaxcapacity = 10;
        this.id = NextId++;
        this.currentcapacity = 0;

    }
    public int getId(){
        return this.id;
    
    public int [] addunit(){
        
    }
    }

}