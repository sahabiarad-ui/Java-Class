import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("--- TESTING CONSTRUCTORS ---");

        Plane p1 = new Plane();
        boolean p1Pass = p1.getMake().equals("Boeing") && p1.getCurrentAltitude() == 0 && p1.getLandingGear();
        System.out.println("Default Constructor Test: " + (p1Pass ? "PASS" : "FAIL"));

        Plane p2 = new Plane("Airbus", "A320", "N123AB");
        boolean p2Pass = p2.getMake().equals("Airbus") && p2.getMaxAltitude() == 13746.0;
        System.out.println("3-Arg Constructor Test: " + (p2Pass ? "PASS" : "FAIL"));

        Plane p3 = new Plane("Cessna", "172", "N987", 4000.0, 150.0);
        boolean p3Pass = p3.getCurrentAltitude() == 150.0 && !p3.getLandingGear();
        System.out.println("Full Constructor (High Altitude) Test: " + (p3Pass ? "PASS" : "FAIL"));

        Plane p4 = new Plane("Cessna", "172", "N987", 4000.0, 5000.0);
        boolean p4Pass = p4.getCurrentAltitude() == 0.0 && p4.getLandingGear();
        System.out.println("Full Constructor (Exceeds Max Alt) Test: " + (p4Pass ? "PASS" : "FAIL"));

        System.out.println("\n--- TESTING LANDING GEAR LOGIC ---");

        Plane gearTest1 = new Plane();
        gearTest1.changeLandingGear(false);
        System.out.println("Turn off gear at low altitude Test: " + (gearTest1.getLandingGear() ? "PASS" : "FAIL"));

        Plane gearTest2 = new Plane("Boeing", "737", "N111", 10000.0, 200.0);
        gearTest2.changeLandingGear(true);
        System.out.println("Turn on gear at high altitude Test: " + (!gearTest2.getLandingGear() ? "PASS" : "FAIL"));

        System.out.println("\n--- TESTING ALTITUDE LOGIC ---");

        Plane altPlane = new Plane();

        boolean asc1 = altPlane.changeAltitude(50.0);
        boolean asc1Pass = asc1 && altPlane.getCurrentAltitude() == 50.0 && altPlane.getLandingGear();
        System.out.println("Ascend (Low to Low) Test: " + (asc1Pass ? "PASS" : "FAIL"));

        boolean asc2 = altPlane.changeAltitude(100.0);
        boolean asc2Pass = asc2 && altPlane.getCurrentAltitude() == 150.0 && !altPlane.getLandingGear();
        System.out.println("Ascend (Low to High) Test: " + (asc2Pass ? "PASS" : "FAIL"));

        boolean desc1 = altPlane.changeAltitude(-100.0);
        boolean desc1Pass = desc1 && altPlane.getCurrentAltitude() == 50.0 && altPlane.getLandingGear();
        System.out.println("Descend (High to Low) Test: " + (desc1Pass ? "PASS" : "FAIL"));

        boolean crash = altPlane.changeAltitude(-100.0);
        boolean crashPass = !crash && altPlane.getCurrentAltitude() == 50.0;
        System.out.println("Negative Altitude (Crash) Test: " + (crashPass ? "PASS" : "FAIL"));

        boolean overMax = altPlane.changeAltitude(20000.0);
        boolean overMaxPass = !overMax && altPlane.getCurrentAltitude() == 50.0;
        System.out.println("Exceed Max Altitude Test: " + (overMaxPass ? "PASS" : "FAIL"));
    }
}