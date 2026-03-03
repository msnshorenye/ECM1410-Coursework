import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import cityrescue.*;
import cityrescue.enums.*;
import cityrescue.exceptions.*;

public class PublicDispatchTest {
    private CityRescue cr;

    @BeforeEach
    void setUp() throws Exception {
        cr = new CityRescueImpl();
        cr.initialise(6, 6);
    }

    @Test
    void dispatch_assignsClosestEligibleUnit_thenLowestUnitId() throws Exception {
        int s = cr.addStation("A", 0, 0);
        int u1 = cr.addUnit(s, UnitType.POLICE_CAR);
        String unitstring = cr.viewUnit(u1);
        System.out.println(unitstring);
        System.out.println("This is u1: "+ u1);
    
        int u2 = cr.addUnit(s, UnitType.POLICE_CAR);
        int u3 = cr.addUnit(s, UnitType.FIRE_ENGINE);

        String unitstring2 = cr.viewUnit(u2);
        System.out.println(unitstring2);
    
        System.out.println("This is u2: "+ u2);
        String unitstring3 = cr.viewUnit(u3);
        System.out.println(unitstring3);

        int i1 = cr.reportIncident(IncidentType.CRIME, 2, 2, 2);

        cr.dispatch();
        
        String inc = cr.viewIncident(i1);
        System.out.println(inc);
        assertTrue(inc.contains("UNIT=" + u1));
        assertFalse(inc.contains("UNIT=" + u2));
    }
}
