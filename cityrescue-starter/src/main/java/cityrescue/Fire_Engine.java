package cityrescue;
import cityrescue.Unit.*;
import cityrescue.enums.UnitType;
import cityrescue.enums.UnitStatus;
class Fire_engine extends Unit{
    void FIRE_ENGINE(int Stationid){
        this.UnitID = NextID++;
        this.ticks = 4;
        this.TYPE = UnitType.FIRE_ENGINE; 
        this.Stationid = Stationid;
        this.STATUS = UnitStatus.IDLE;
    }
    public void main(String[] args) {
        Fire_engine fire = new Fire_engine();
        fire.get_data();
    }
    public void get_data(){
        System.out.println(this.TYPE+ " " +this.ticks);
    }
    public void set_station(int StatID){
        this.Stationid = StatID;
    }
    public int get_unit_id(){
        return this.UnitID;
    }

    
}