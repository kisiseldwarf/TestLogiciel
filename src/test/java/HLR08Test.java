import org.junit.Test;
import com.testlogiciel.tcas.*;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class HLR08Test {
    final int AVERAGE_PLANE_SPEED = 495;
    final int AVERAGE_PLANE_SPEED_2 = 425;
    final double EPSILON = 0.0000001;

    @Test
    public void TestDistRA(){
        HLR08 hlr08 = new HLR08();
        Plane mock_plane_a = new Plane(0,0,AVERAGE_PLANE_SPEED);
        Plane mock_plane_b = new Plane(0,0,AVERAGE_PLANE_SPEED);

        assertTrue(hlr08.Alert(mock_plane_a,mock_plane_b) == "RESOLUTION ADVISORY");
    }

    @Test
    public void TestDistCT(){
        HLR08 hlr08 = new HLR08();
        Plane mock_plane_a = new Plane(0.000,0.000,AVERAGE_PLANE_SPEED);
        Plane mock_plane_b = new Plane(0.005,0.001,AVERAGE_PLANE_SPEED);

        assertTrue(hlr08.Alert(mock_plane_a,mock_plane_b) == "CLOSE TRAFFIC");
    }

    @Test
    public void TestDistTA(){
        HLR08 hlr08 = new HLR08();
        Plane mock_plane_a = new Plane(0.0000,0.000,AVERAGE_PLANE_SPEED);
        Plane mock_plane_b = new Plane(0.0010,0.001,AVERAGE_PLANE_SPEED);

        assertTrue(hlr08.Alert(mock_plane_a,mock_plane_b) == "TRAFFIC ADVISORY");
    }

    @Test
    public void TestDistNull(){
        HLR08 hlr08 = new HLR08();
        Plane mock_plane_a = new Plane(0,0,AVERAGE_PLANE_SPEED);
        Plane mock_plane_b = new Plane(3000,3000,AVERAGE_PLANE_SPEED);

        assertTrue(hlr08.Alert(mock_plane_a,mock_plane_b) == null);
    }

    @Test
    public void TestBoundries01(){
        HLR08 hlr08 = new HLR08();
        Plane mock_plane_a = new Plane(0,0,AVERAGE_PLANE_SPEED);

        List<Double> boundries = hlr08.Boundaries(mock_plane_a);

        assertTrue(boundries.get(0) >= 3.4375 - EPSILON && boundries.get(0) <= 3.4375 + EPSILON);
        assertTrue(boundries.get(1) >= 5.5 - EPSILON && boundries.get(1) <= 5.5 + EPSILON);
    }

    @Test
    public void TestBoundries02(){
        HLR08 hlr08 = new HLR08();
        Plane mock_plane_a = new Plane(0,0,AVERAGE_PLANE_SPEED_2);

        List<Double> boundries = hlr08.Boundaries(mock_plane_a);

        assertTrue(boundries.get(0) >= 2.95138888889 - EPSILON && boundries.get(0) <= 2.95138888889 + EPSILON);
        assertTrue(boundries.get(1) >= 4.72222222222 - EPSILON && boundries.get(1) <= 4.72222222222 + EPSILON);
    }

    @Test
    public void TestDistance(){
        HLR08 hlr08 = new HLR08();
        Plane mock_plane_a = new Plane(0,0,AVERAGE_PLANE_SPEED);
        Plane mock_plane_b = new Plane(0.1,0.1,AVERAGE_PLANE_SPEED);

        assertTrue(hlr08.Distance(mock_plane_a,mock_plane_b) >= 486.7017684 - EPSILON && hlr08.Distance(mock_plane_a,mock_plane_b) <= 486.7017684 + EPSILON);
    }
}
