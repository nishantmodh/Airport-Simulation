
package airportsimulation;

public class Averager {
    private int LandingCount;
    private double LandingSum;
    private int takeOffCount;
    private double takeOffSum;
    
    public Averager(){
        LandingCount = 0;
        LandingSum = 0;
        takeOffCount = 0;
        takeOffSum = 0;
    }
    public void addLandingNumber(double value1)
    {
        if(LandingCount == Integer.MAX_VALUE)
            throw new IllegalStateException("Too many Landing.");
        LandingCount++;
        LandingSum += value1;
    }
    public void addtakeOffNumber(double value2)
    {
       if(takeOffCount == Integer.MAX_VALUE)
           throw new IllegalStateException("Too many takeOff");
       takeOffCount++;
       takeOffSum += value2;
    }
    public double LandingAverage()
    {
        if(LandingCount == 0)
            return Double.NaN;
        else
            return (LandingSum / LandingCount);
    }
    public double takeOffAverage()
    {
        if(takeOffCount == 0)
            return Double.NaN;
        else
            return takeOffSum / takeOffCount;
    }
    public int howManyLanding()
    {
        return LandingCount;
    }
    public int howmanyTakeOff()
    {
        return takeOffCount;
    }
}
