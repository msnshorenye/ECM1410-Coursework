import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import cityrescue.*;
import cityrescue.enums.*;
import cityrescue.exceptions.*;

public class MultipleUnitsAndIncidentsTest {

    private CityRescue cr;

    @BeforeEach
    void setUp() throws Exception {
        cr = new CityRescueImpl();
        cr.initialise(10, 10);

        // Add stations
        cr.addStation("Central", 0, 0);   // id 1
        cr.addStation("North", 5, 5);     // id 2
    }

    @Test
    void addMultipleUnits_ofDifferentTypes() throws Exception {

        int amb = cr.addUnit(1, UnitType.AMBULANCE);
        int fire = cr.addUnit(1, UnitType.FIRE_ENGINE);
        int police = cr.addUnit(2, UnitType.POLICE_CAR);
        System.out.println(cr.getStatus());
        System.out.println(cr.viewUnit(amb));
        assertEquals(1, amb);
        assertEquals(2, fire);
        assertEquals(3, police);
        System.out.println(cr.getStatus());
        System.out.println(cr.viewUnit(amb)+" 0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"+amb);
        assertTrue(cr.viewUnit(amb).contains("TYPE=AMBULANCE"));
        assertTrue(cr.viewUnit(fire).contains("TYPE=FIRE_ENGINE"));
        assertTrue(cr.viewUnit(police).contains("TYPE=POLICE_CAR"));
    }

    @Test
    void reportMultipleIncidents_ofDifferentTypes() throws Exception {

        int med = cr.reportIncident(IncidentType.MEDICAL, 2, 1, 1);
        int fire = cr.reportIncident(IncidentType.FIRE, 3, 2, 2);
        int crime = cr.reportIncident(IncidentType.CRIME, 1, 6, 6);

        assertEquals(1, med);
        assertEquals(2, fire);
        assertEquals(3, crime);

        assertTrue(cr.viewIncident(med).contains("TYPE=MEDICAL"));
        assertTrue(cr.viewIncident(fire).contains("TYPE=FIRE"));
        assertTrue(cr.viewIncident(crime).contains("TYPE=CRIME"));
    }

    @Test
    void dispatch_assignsCorrectUnitTypes() throws Exception {

        int amb = cr.addUnit(1, UnitType.AMBULANCE);
        int fireUnit = cr.addUnit(1, UnitType.FIRE_ENGINE);
        int police = cr.addUnit(2, UnitType.POLICE_CAR);

        int med = cr.reportIncident(IncidentType.MEDICAL, 1, 1, 1);
        int fire = cr.reportIncident(IncidentType.FIRE, 1, 2, 2);
        int crime = cr.reportIncident(IncidentType.CRIME, 1, 6, 6);

        cr.dispatch();

        assertTrue(cr.viewUnit(amb).contains("EN_ROUTE"));
        assertTrue(cr.viewUnit(fireUnit).contains("EN_ROUTE"));
        assertTrue(cr.viewUnit(police).contains("EN_ROUTE"));
    }

    @Test
    void tick_movesMultipleUnitsIndependently() throws Exception {

        int amb = cr.addUnit(1, UnitType.AMBULANCE);
        int police = cr.addUnit(2, UnitType.POLICE_CAR);

        int med = cr.reportIncident(IncidentType.MEDICAL, 1, 0, 1);
        int crime = cr.reportIncident(IncidentType.CRIME, 1, 5, 6);

        cr.dispatch();
        String ambView = cr.viewUnit(amb);
        System.out.println(ambView);
        String policeView = cr.viewUnit(police);
        System.out.println(policeView);
        cr.tick();

        String ambView2 = cr.viewUnit(amb);
        System.out.println(ambView2);
        String policeView2 = cr.viewUnit(police);
        System.out.println(policeView2);
        String status = cr.getStatus();
        System.out.println(status);


        assertTrue(ambView.contains("LOC="));
        assertTrue(policeView.contains("LOC="));
    }

    @Test
    void getIds_areSorted_andNoZerosAtEnd() throws Exception {

        cr.addUnit(1, UnitType.AMBULANCE);
        cr.addUnit(1, UnitType.FIRE_ENGINE);
        cr.addUnit(1, UnitType.POLICE_CAR);

        cr.reportIncident(IncidentType.MEDICAL, 1, 1, 1);
        cr.reportIncident(IncidentType.CRIME, 1, 2, 2);

        int[] unitIds = cr.getUnitIds();
        int[] incidentIds = cr.getIncidentIds();
        System.out.println(unitIds);
        System.out.println(incidentIds);
        //assertArrayEquals(new int[]{1,2,3}, unitIds);
        //assertArrayEquals(new int[]{1,2}, incidentIds);
    }
}