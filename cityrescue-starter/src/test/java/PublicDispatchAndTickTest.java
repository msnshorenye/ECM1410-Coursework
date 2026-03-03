import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import cityrescue.*;
import cityrescue.enums.*;
import cityrescue.exceptions.*;

public class PublicDispatchAndTickTest {

    private CityRescue cr;

    @BeforeEach
    void setUp() throws Exception {
        cr = new CityRescueImpl();
        cr.initialise(5, 5);
        cr.addStation("Central", 0, 0);
    }

    @Test
    void dispatch_setsUnitToEnRoute() throws Exception {
        int u = cr.addUnit(1, UnitType.AMBULANCE);
        int i = cr.reportIncident(IncidentType.MEDICAL, 1, 0, 1);

        cr.dispatch();

        String unitView = cr.viewUnit(u);
        assertTrue(unitView.contains("STATUS=EN_ROUTE"));
    }

    @Test
    void tick_movesUnitTowardIncident() throws Exception {
        int u = cr.addUnit(1, UnitType.AMBULANCE);
        int i = cr.reportIncident(IncidentType.MEDICAL, 1, 0, 1);

        cr.dispatch();
        cr.tick();

        String view = cr.viewUnit(u);
        assertTrue(view.contains("LOC=(0,1)"));
    }

    @Test
    void incidentEventuallyResolves() throws Exception {
        int u = cr.addUnit(1, UnitType.AMBULANCE);
        int i = cr.reportIncident(IncidentType.MEDICAL, 1, 0, 1);

        cr.dispatch();
        cr.tick();
        cr.tick();
        cr.tick();

        assertTrue(cr.viewIncident(i).contains("STATUS=RESOLVED"));
        assertTrue(cr.viewUnit(u).contains("STATUS=IDLE"));
    }
}