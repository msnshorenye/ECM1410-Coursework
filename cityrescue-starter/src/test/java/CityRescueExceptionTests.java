import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import cityrescue.*;
import cityrescue.enums.*;
import cityrescue.exceptions.*;

public class CityRescueExceptionTests {

    private CityRescueImpl sim;

    @BeforeEach
    void setup() throws Exception {
        sim = new CityRescueImpl();
        sim.initialise(10, 10);
    }


    @Test
    void initialise_invalidWidth() {
        assertThrows(InvalidGridException.class, () -> {
            sim.initialise(0, 5);
        });
    }

    @Test
    void initialise_invalidHeight() {
        assertThrows(InvalidGridException.class, () -> {
            sim.initialise(5, -1);
        });
    }

 

    @Test
    void addObstacle_outOfBounds() {
        assertThrows(InvalidLocationException.class, () -> {
            sim.addObstacle(-1, 0);
        });
    }

 

    @Test
    void removeObstacle_outOfBounds() {
        assertThrows(InvalidLocationException.class, () -> {
            sim.removeObstacle(50, 50);
        });
    }

    // =========================
    // 5. addStation
    // =========================

    @Test
    void addStation_blankName() {
        assertThrows(InvalidNameException.class, () -> {
            sim.addStation("", 1, 1);
        });
    }

    @Test
    void addStation_outOfBounds() {
        assertThrows(InvalidLocationException.class, () -> {
            sim.addStation("A", 100, 1);
        });
    }



    @Test
    void removeStation_invalidId() {
        assertThrows(IDNotRecognisedException.class, () -> {
            sim.removeStation(999);
        });
    }

    @Test
    void removeStation_withUnits() throws Exception {
        int sid = sim.addStation("A", 1, 1);
        sim.setStationCapacity(sid, 5);
        sim.addUnit(sid, UnitType.AMBULANCE);

        assertThrows(IllegalStateException.class, () -> {
            sim.removeStation(sid);
        });
    }



    @Test
    void setStationCapacity_invalidId() {
        assertThrows(IDNotRecognisedException.class, () -> {
            sim.setStationCapacity(999, 5);
        });
    }

    @Test
    void setStationCapacity_invalidCapacity() throws Exception {
        int sid = sim.addStation("A", 1, 1);

        assertThrows(InvalidCapacityException.class, () -> {
            sim.setStationCapacity(sid, 0);
        });
    }



    @Test
    void addUnit_invalidStation() {
        assertThrows(IDNotRecognisedException.class, () -> {
            sim.addUnit(999, UnitType.AMBULANCE);
        });
    }

    @Test
    void addUnit_nullType() throws Exception {
        int sid = sim.addStation("A", 1, 1);
        sim.setStationCapacity(sid, 5);

        assertThrows(InvalidUnitException.class, () -> {
            sim.addUnit(sid, null);
        });
    }

    @Test
    void addUnit_stationFull() throws Exception {
        int sid = sim.addStation("A", 1, 1);
        sim.setStationCapacity(sid, 1);
        sim.addUnit(sid, UnitType.AMBULANCE);

        assertThrows(IllegalStateException.class, () -> {
            sim.addUnit(sid, UnitType.AMBULANCE);
        });
    }



    @Test
    void decommissionUnit_invalidId() {
        assertThrows(IDNotRecognisedException.class, () -> {
            sim.decommissionUnit(999);
        });
    }

    @Test
    void decommissionUnit_enRoute() throws Exception {
        int sid = sim.addStation("A", 1, 1);
        sim.setStationCapacity(sid, 5);
        int uid = sim.addUnit(sid, UnitType.AMBULANCE);
        int iid = sim.reportIncident(IncidentType.MEDICAL, 3, 5, 5);
        sim.dispatch();

        assertThrows(IllegalStateException.class, () -> {
            sim.decommissionUnit(uid);
        });
    }

   

    @Test
    void transferUnit_invalidUnit() {
        assertThrows(IDNotRecognisedException.class, () -> {
            sim.transferUnit(999, 1);
        });
    }

    @Test
    void transferUnit_notIdle() throws Exception {
        int s1 = sim.addStation("A", 1, 1);
        int s2 = sim.addStation("B", 5, 5);
        sim.setStationCapacity(s1, 5);
        sim.setStationCapacity(s2, 5);

        int uid = sim.addUnit(s1, UnitType.AMBULANCE);
        int iid = sim.reportIncident(IncidentType.MEDICAL, 3, 5, 5);
        sim.dispatch();

        assertThrows(IllegalStateException.class, () -> {
            sim.transferUnit(uid, s2);
        });
    }

    

    @Test
    void setUnitOutOfService_invalidId() {
        assertThrows(IDNotRecognisedException.class, () -> {
            sim.setUnitOutOfService(999, true);
        });
    }

    

    @Test
    void reportIncident_invalidSeverity() {
        assertThrows(InvalidSeverityException.class, () -> {
            sim.reportIncident(IncidentType.FIRE, 10, 1, 1);
        });
    }

    @Test
    void reportIncident_outOfBounds() {
        assertThrows(InvalidLocationException.class, () -> {
            sim.reportIncident(IncidentType.FIRE, 3, -1, 0);
        });
    }

  

    @Test
    void cancelIncident_invalidId() {
        assertThrows(IDNotRecognisedException.class, () -> {
            sim.cancelIncident(999);
        });
    }

   

    @Test
    void escalateIncident_invalidId() {
        assertThrows(IDNotRecognisedException.class, () -> {
            sim.escalateIncident(999, 3);
        });
    }

    @Test
    void escalateIncident_invalidSeverity() throws Exception {
        int iid = sim.reportIncident(IncidentType.FIRE, 3, 2, 2);

        assertThrows(InvalidSeverityException.class, () -> {
            sim.escalateIncident(iid, 99);
        });
    }

    

    @Test
    void viewIncident_invalidId() {
        assertThrows(IDNotRecognisedException.class, () -> {
            sim.viewIncident(999);
        });
    }


    @Test
    void viewUnit_invalidId() {
        assertThrows(IDNotRecognisedException.class, () -> {
            sim.viewUnit(999);
        });
    }
}