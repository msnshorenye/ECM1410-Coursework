
package cityrescue;
import java.util.Arrays;
public class CityMap {
    private int width;
    private int height;
    private String[][] grid;
    
    
    public CityMap(int width, int height){
     this.width = width;
     this.height = height;
     this.grid = new String[height][width];
    }

    public int [] getGridSize(){

    return new int [] {this.width, this.height};
    }
    public String[][] newcreategrid(){
        this.grid = new String[this.height][this.width];
        System.out.println(Arrays.deepToString(this.grid));
        return this.grid;
        // for (int i = 0; i<= this.height; i++){
        //     for (int y = 0; y<= this.width; y++){
        //         this.grid[i][y] = " "
        //     }
        //     system.out.println(this.grid)
        }
    
    public String [][] AddObstacle(int x, int y){
        if (x>= 0 && x<= this.height && y>=0 && y<= this.width){
            this.grid[y][x] = "obstacle";
        }
        return this.grid.clone();

    }
    
    public String[][] RemoveObstacle(int x, int y){
        String ob = "obstacle";
        if (ob.equals(this.grid[y][x])){
            this.grid[y][x] = " ";
        }
        return this.grid.clone();
    }
    public static void main(String[] args) {
          int width = Integer.parseInt(args[0]);
          int height = Integer.parseInt(args[1]);
        
          CityMap the_city_map = new CityMap(width, height);
          int[] size = the_city_map.getGridSize();
          String[][] map = the_city_map.newcreategrid();
          System.out.println(Arrays.toString(size));
          

    }
    public int getwidth(){
        return this.width;
    }
    public int getheight(){
        return this.height;
    }
          
    public String[][]getgrid(){
        return this.grid.clone();
    }

    }