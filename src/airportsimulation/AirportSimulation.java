package airportsimulation;

import java.util.LinkedList;
import java.util.Queue;


public class AirportSimulation {

    public static void main(String[] args) {
        final int landTime = 4;
        final int takeOffTime = 2;
        final double arrivalProb = 0.05;
        final double PlanesToLand = 20.0;
        final double departureProb = 0.05;
        final double PlanesToTakeOff = 20.0;
        final int inAirBeforeCrash = 2;
        final int TotalSimulationTime = 6000;
        
        AirportSimulate(landTime, takeOffTime, arrivalProb, PlanesToLand, departureProb, PlanesToTakeOff, inAirBeforeCrash, TotalSimulationTime);
    }
    public static void AirportSimulate(int landTime, int takeOffTime, double arrivalProb, double PlanesToLand, double departureProb, double PlanesToTakeOff, int inAirBeforeCrash, int TotalSimulationTime)
    {
        Queue<Integer> LandingTime = new LinkedList<Integer>();
        Queue<Integer> TakingOffTime = new LinkedList<Integer>();
        BooleanSource arrival = new BooleanSource(arrivalProb);
        BooleanSource departure = new BooleanSource(departureProb);
        
        Runway airplane = new Runway(takeOffTime, landTime);
        
        Averager avg = new Averager();
        int currentSecond;
        int next, crash = 0;
        
        System.out.println("Amount of minutes to land: "+landTime);
        System.out.println("Amount of minutes to take off: "+takeOffTime);
        System.out.println("Probability of arrival during a minute: "+arrivalProb);
        System.out.println("Average amount of time between planes to land: "+PlanesToLand);
        System.out.println("Probability of departure during a minute: "+departureProb);
        System.out.println("Average amount of time between planes to take off: "+PlanesToTakeOff);
        System.out.println("Maximum amount of time in the air before crashing: "+inAirBeforeCrash);
        System.out.println("Total simulation minutes: "+TotalSimulationTime);
        System.out.println("");
        
        
        if(landTime <= 0 || takeOffTime <= 0 || arrivalProb < 0 || departureProb < 0 || departureProb > 1 || arrivalProb > 1 || TotalSimulationTime < 0)
            throw new IllegalStateException("Values are out of range.");
        
        for(currentSecond = 0; currentSecond < TotalSimulationTime; currentSecond++)
        {
            if(arrival.query())
            {
                LandingTime.add(currentSecond);
                
            }
            if(departure.query())
            {
                TakingOffTime.add(currentSecond);
            }
            if(!airplane.isBusy())
            {
                if(!LandingTime.isEmpty())
                {
                    next = LandingTime.remove();
                    if((currentSecond - next) > inAirBeforeCrash)
                    {
                        crash++;
                    }
                    else
                    {
                        avg.addLandingNumber(currentSecond - next);
                        airplane.startLanding();
                    }
                    
                }
                else if(!TakingOffTime.isEmpty())
                {
                    next = TakingOffTime.remove();
                    avg.addtakeOffNumber(currentSecond - next);
                    airplane.startTakeOff();
                }
            }
            airplane.reduceTime();
        }
        System.out.println("Number of planes taken off: "+avg.howmanyTakeOff());
        System.out.println("Number of planes landed: "+avg.howManyLanding());
        System.out.println("Number of planes crashed: "+crash);
        if(avg.howmanyTakeOff()>0)
        System.out.printf("Average waiting time for taking off: %4.2f",avg.takeOffAverage());
        if(avg.howManyLanding()>0)
        System.out.printf("\nAverage waiting time for landing: %4.2f \n",avg.LandingAverage());
        
    }
    
}
