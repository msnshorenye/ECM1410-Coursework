import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import cityrescue.*;
import cityrescue.enums.*;
import cityrescue.exceptions.*;

public class EdgeCaseTest {
    private CityRescue cr;

    @BeforeEach
    void setUp() throws Exception {
        cr = new CityRescueImpl();
        cr.initialise(6, 6);
    }

    @Test
    void wills_testicle() throws Exception {
        int s = cr.addStation("A", 0, 0);
        int u1 = cr.addUnit(s, UnitType.POLICE_CAR);
        String unitstring = cr.viewUnit(u1);
        //System.out.println(unitstring);
        //System.out.println("This is u1: "+ u1);
        cr.addObstacle(1,0);
        cr.addObstacle(1,1);
        //cr.addObstacle(0,1);// use to see if remain stationary if no available move
        int u2 = cr.addUnit(s, UnitType.POLICE_CAR);
        int u3 = cr.addUnit(s, UnitType.FIRE_ENGINE);

        String unitstring2 = cr.viewUnit(u2);
        //System.out.println(unitstring2);
    
        //System.out.println("This is u2: "+ u2);
        String unitstring3 = cr.viewUnit(u3);
        //System.out.println(unitstring3);

        int i1 = cr.reportIncident(IncidentType.CRIME, 2, 2, 2);
        int i2 = cr.reportIncident(IncidentType.FIRE,4,4,4);
        cr.dispatch();
        
        String inc = cr.viewIncident(i1);
        String inc2 = cr.viewIncident(i2);
        //System.out.println(inc);
        assertTrue(inc.contains("UNIT=" + u1));
        assertFalse(inc.contains("UNIT=" + u2));
        cr.tick();
        System.out.println(cr.viewUnit(u1));
        System.out.println(cr.viewUnit(u3));
        cr.tick();
        System.out.println(cr.viewUnit(u1));
        System.out.println(cr.viewUnit(u3));
        cr.tick();
        System.out.println(cr.viewUnit(u1));
        System.out.println(cr.viewUnit(u3));
        cr.tick();
        System.out.println(cr.viewUnit(u1));
        System.out.println(cr.viewUnit(u3));
        cr.tick();
        System.out.println(cr.viewUnit(u1));
        System.out.println(cr.viewUnit(u3));
        cr.tick();
        cr.tick();
    }
    @Test
    void test_unit_max_capacity() throws Exception {
        System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEELLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
        int s = cr.addStation("A", 0, 0);
        cr.addStation("B", 1, 1);
        for (int i=0 ;i<50;i++){
            cr.addUnit(s, UnitType.POLICE_CAR);
        }
        assertThrows(CapacityExceededException.class, () -> cr.addUnit(s, UnitType.POLICE_CAR));
        System.out.println(cr.getStatus());
        System.out.println(cr.viewUnit(3));
        cr.decommissionUnit(3);
        
        cr.addUnit(s,UnitType.AMBULANCE);
        System.out.println(cr.viewUnit(51));
        //assertEquals(UnitType.AMBULANCE, ());
        int a = cr.reportIncident(IncidentType.CRIME, 2, 2, 2);
        System.out.println(cr.getStatus());
        cr.transferUnit(51,2);//test transfer unit
        System.out.println(cr.viewUnit(51));
        cr.cancelIncident(a)
        System.out.println(cr.getStatus());
    }
    @Test
    void test_last_first_legal_move()throws Exception{
        // will always go back and forth as first move will bring it back to original location therefore stuck in loop - it follows the guidelines given
        //System.out.println("ALLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
       int s = cr.addStation("A", 2, 2);
        int u1 = cr.addUnit(s, UnitType.POLICE_CAR);
        String unitstring = cr.viewUnit(u1);
        //System.out.println(unitstring);
        //System.out.println("This is u1: "+ u1);
        cr.addObstacle(3,2);
        cr.addObstacle(2,3);


        int i1 = cr.reportIncident(IncidentType.CRIME,3, 4, 3);
        cr.dispatch();
        
        String inc = cr.viewIncident(i1);
        //System.out.println(inc);
        assertTrue(inc.contains("UNIT=" + u1));
        cr.tick();
        System.out.println(cr.viewUnit(u1));
        cr.tick();
        System.out.println(cr.viewUnit(u1));
        cr.tick();
        System.out.println(cr.viewUnit(u1));

        cr.tick();
        System.out.println(cr.viewUnit(u1));
        cr.tick();
        System.out.println(cr.viewUnit(u1));
        cr.tick();
        cr.tick(); 
        System.out.println(cr.getStatus());
    }   
}
