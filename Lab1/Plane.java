public class Plane {

    private String make, model, registrationNumber;
    private double maxAltitude, currentAltitude;
    private boolean landingGear;

    Plane()
    {
        make = "Boeing";
        model = "747";
        registrationNumber = "N747PA";
        maxAltitude = 13746;
        currentAltitude = 0;
        landingGear = true;
    }

    Plane(String ma, String mo, String reg, double altitude, double current) {
        make = ma;
        model = mo;
        registrationNumber = reg;
        maxAltitude = altitude;

        if (maxAltitude > current && current > 0)
        {
            currentAltitude = current;
        }

        else
        {
            currentAltitude = 0;
            System.out.println("You entered a current altitude larger than the maximum altitude (or less than 0), altitude set to 0 m");
        }

        if(currentAltitude < 100)
        {
            landingGear = true;
        }

        else if(currentAltitude > 100)
        {
            landingGear = false;
        }

    }


    Plane(String ma, String mo, String reg)
    {
        make = ma;
        model = mo;
        registrationNumber = reg;
        maxAltitude = 13746;
        currentAltitude = 0;
        landingGear = true;
    }

    String getMake()
    {
        return make;
    }

    String getModel()
    {
        return model;
    }

    String getRegistrationNumber()
    {
        return registrationNumber;
    }

    double getMaxAltitude()
    {
        return maxAltitude;
    }

    double getCurrentAltitude()
    {
        return currentAltitude;
    }

    boolean getLandingGear()
    {
        return landingGear;
    }

    void changeLandingGear(boolean change)
    {
        if(!change)
        {
            if(currentAltitude < 100)
            {
                landingGear = true;
                System.out.println("Current altitude is less than 100 m, cannot turn off landing gear");
            }
            else
            {
                landingGear = change;
            }
        }
        else
        {
            if(currentAltitude > 100)
            {
                landingGear = false;
                System.out.println("Current altitude is greate than 100 m, cannot turn on landing gear");
            }
            else
            {
                landingGear = change;
            }
        }
    }

    boolean changeAltitude(double change) {
        double newAltitude = currentAltitude + change;
        if ((newAltitude) < 0) {
            System.out.println("Cannot perform operation, causes negative altitude (crash)");
            return false;
        }
        else {
            if (newAltitude > maxAltitude) {
                System.out.println("Cannot exceed max altitude, returning");
                return false;
            }
            else
            {
                if(newAltitude > 100)
                {
                    currentAltitude = newAltitude;
                    landingGear = false;
                    return true;
                }
                else
                {
                    currentAltitude = newAltitude;
                    landingGear = true;
                    return true;
                }
            }
        }
    }

}
