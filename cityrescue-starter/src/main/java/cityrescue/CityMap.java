package cityrescue;

public class CityMap {
    public int width;
    public int height;
    public String[][] grid;
    
    
    CityMap(int width, int height){
     this.width = width;
     this.height = height;
     String[][] grid = grid;
    }

    public new int[] getGridSize(){

    return new int [] {this.width, this.height};
    }
    public newcreategrid(){
        this.grid = new String[this.height][this.width];
        system.out.println(this.grid);
        return this.grid;
        // for (int i = 0; i<= this.height; i++){
        //     for (int y = 0; y<= this.width; y++){
        //         this.grid[i][y] = " "
        //     }
        //     system.out.println(this.grid)
        }
    
    public static void main(String[] args) {
          the_city_map = new CityMap(args[0],args[1]);
          int[] size = the_city_map.getGridSize();
          system.out.println(size);
    }
          

    }