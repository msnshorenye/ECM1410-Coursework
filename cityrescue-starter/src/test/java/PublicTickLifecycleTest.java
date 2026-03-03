import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import cityrescue.*;
import cityrescue.enums.*;
import cityrescue.exceptions.*;

public class PublicTickLifecycleTest {
    private CityRescue cr;

    @BeforeEach
    void setUp() throws Exception {
        cr = new CityRescueImpl();
        cr.initialise(5, 5);
    }

    @Test
    void tick_movesUnitTowardIncident_andEventuallyResolves() throws Exception {
        int s = cr.addStation("A", 0, 0);
        //System.out.println("1");
        int u = cr.addUnit(s, UnitType.AMBULANCE);
        String firstunitview = cr.viewUnit(u);
        //System.out.println(firstunitview);
        //System.out.println("2");
        int i = cr.reportIncident(IncidentType.MEDICAL, 1, 0, 1);
        String firstincidentview = cr.viewIncident(i);
        //System.out.println(firstincidentview);
        //System.out.println("3");
        cr.dispatch();
        //System.out.println(cr.viewUnit(u));
        //System.out.println(cr.viewIncident(i));
        //System.out.println("4");      
        cr.tick(); // should arrive at (0,1) in one tick
        //System.out.println("555555555555555555555555555555555555555555555555555555555555555");
        //System.out.println(cr.viewUnit(u));
        //System.out.println(cr.viewIncident(i));
        assertTrue(cr.viewUnit(u).contains("LOC=(0,1)"));
        System.out.println("completed assert true");
        cr.tick();
        //System.out.println(cr.viewUnit(u));
        //System.out.println();
        cr.tick();
        //System.out.println(cr.viewIncident(i));
        //System.out.println(cr.viewUnit(u));
        assertTrue(cr.viewIncident(i).contains("STATUS=RESOLVED"));
        System.out.println(cr.viewUnit(u));
        assertTrue(cr.viewUnit(u).contains("STATUS=IDLE"));
    }
}
