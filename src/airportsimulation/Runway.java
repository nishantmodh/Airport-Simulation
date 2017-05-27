
package airportsimulation;

/**
 *
 * @author Nishant
 */
public class Runway {
    private int takeOffTime;
    private int LandingTime;
    private int TimeLeft;
    
    
    public Runway(int s1, int s2)
    {
        takeOffTime = s1;
        LandingTime = s2;
        TimeLeft = 0;
    }
    public boolean isBusy()
    {
        return (TimeLeft > 0);
    }
    public void reduceTime()
    {
        if(TimeLeft > 0)
            TimeLeft--;
    }
    public void startLanding()
    {
       if(TimeLeft > 0)
           throw new IllegalStateException("Runway is busy.");
       TimeLeft = LandingTime;
    }
    public void startTakeOff()
    {
        if(TimeLeft > 0)
            throw new IllegalStateException("Runway is busy.");
        TimeLeft = takeOffTime;
    }
    
}
