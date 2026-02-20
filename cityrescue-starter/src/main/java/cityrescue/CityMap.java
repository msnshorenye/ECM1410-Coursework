import java.util.Arrays;
package cityrescue;

public class CityMap {
    public int width;
    public int height;
    public String[][] grid;
    
    
    CityMap(int width, int height){
     this.width = width;
     this.height = height;
     this.grid = new String[height][width];
    }

    public int [] getGridSize(){

    return new int [] {this.width, this.height};
    }
    public String[][] newcreategrid(){
        this.grid = new String[this.height][this.width];
        System.out.println(this.grid);
        return this.grid;
        // for (int i = 0; i<= this.height; i++){
        //     for (int y = 0; y<= this.width; y++){
        //         this.grid[i][y] = " "
        //     }
        //     system.out.println(this.grid)
        }

    
    public static void main(String[] args) {
          int width = Integer.parseInt(args[0]);
          int height = Integer.parseInt(args[1]);
        
          CityMap the_city_map = new CityMap(width, height);
          int[] size = the_city_map.getGridSize();
          
          System.out.println(size);
    }
          

    }