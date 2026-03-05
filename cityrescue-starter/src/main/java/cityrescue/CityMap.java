
package cityrescue;
import java.util.Arrays;
/**
 * City map creates a grid using constructor and parameters width and height
 */
public class CityMap {
    private int width;
    private int height;
    private String[][] grid;
    
    /**
     * Constructor instantiates the two given parameters 
     * of width and height.
     * The grid is an attribute created in the constructor 
     * using the two parameters given. 
     * The grid is a 2d array size of width and height.
     * @param width integer given which will represent
     *  the number of columns in the grid attribute.
     * @param height
     * The height as a parameter is the given number of rows
     * within the grid atttribute which is used for checking 
     * boundary values for coordinates for any object that has 
     * a position in the simulation.
     */
    public CityMap(int width, int height){
     this.width = width;
     this.height = height;
     this.grid = new String[height][width];
    }
/**
 * getGridsize 
 * returns the width and height attribute as an integer array
 * containg both. This gives the main class information about the grid created.
 * 
 * @return returns the width and height attributes as an integer array
 */
    public int [] getGridSize(){

    return new int [] {this.width, this.height};
    }
    public String[][] newcreategrid(){
        this.grid = new String[this.height][this.width];
        //System.out.println(Arrays.deepToString(this.grid));
        return this.grid;
        }
    

  
    /**
     * Get method for the City map width so it can be used
     * @return returns the width attribute of the instantiated City map object.
     * 
     */
    public int getwidth(){
        return this.width;
    }
    /**
     * Get method for City maps grids height attribute 
     * @return the integer value of the grids height 
     * which represents the rows of the grid.
     */
    public int getheight(){
        return this.height;
    }
          

    }