import java.util.*;

public class Senior 
{
    public static void main (String[] args)
    {
        for (int i=0; i<5; i++)
        {
            char loc1, loc2;
            double hour1, hour2;
            String firstTimeOfDay, secondTimeOfDay;
            double speed1, speed2;
            boolean time1, time2; //true if AM, false if PM
            
            //read in the input 
            Scanner input=new Scanner (System.in);
            loc1=input.next().charAt(0);
            input.nextLine ();
            loc2=input.next().charAt(0);
            input.nextLine();
            hour1=Double.parseDouble (input.next());
            input.nextLine ();
            firstTimeOfDay=input.nextLine();
            hour2=Double.parseDouble (input.next());
            input.nextLine ();
            secondTimeOfDay=input.nextLine();
            speed1=Double.parseDouble (input.next());
            speed2=Double.parseDouble (input.next());
            
            //determine if time's refer to AM or PM values
            if (firstTimeOfDay.charAt (0)=='A')
            {
                time1=true;
            }
            else
            {
                time1=false;
            }
            if (secondTimeOfDay.charAt (0)=='A')
            {
                time2=true;
            }
            else
            {
                time2=false;
            }
            
            double totalTime=meetInHours (timeBetween (hour1, time1, hour2, time2), earlier (hour1, time1, speed1, hour2, time2, speed2), getDistance (loc1, loc2), later (hour1, time1, speed1, hour2, time2, speed2), speed1, speed2);
            double hours=getHours (totalTime);
            double minutes=getMinutes (totalTime);
            if (minutes <10)
            {
                System.out.print((int)hours+":0"+(int)minutes);
            }
            else
            {
                System.out.print((int)hours+":"+(int)minutes);
            }
        }
    }
    
    /*
     * Determines the speed of the traveler that left earlier
     * @param hour1 is the start time of the first traveler
     * @param time1 is a string, either AM or PM, referring to the start time of the first traveler
     * @param speed1 is the speed of the first traveler
     * @param hour2 is the start time of the second traveler
     * @param time2 is a string, either AM or PM, referring to the start time of the second traveler
     * @param speed2 is the speed of the second traveler
     * @return a double value that represents the speed of the traveler that left earlier 
     */
    public static double earlier (double hour1, boolean time1, double speed1, double hour2, boolean time2, double speed2)
    {
        if (speed1==later(hour1, time1, speed1, hour2, time2, speed2))//speed1 is later
        {
            return speed2;
        }
        return speed1;
    }
    
    /*
     * Determines the speed of the traveler that left earlier
     * @param hour1 is the start time of the first traveler
     * @param time1 is a string, either AM or PM, referring to the start time of the first traveler
     * @param speed1 is the speed of the first traveler
     * @param hour2 is the start time of the second traveler
     * @param time2 is a string, either AM or PM, referring to the start time of the second traveler
     * @param speed2 is the speed of the second traveler
     * @return a double value that represents the speed of the traveler that left later
     */
    public static double later (double hour1, boolean time1, double speed1, double hour2, boolean time2, double speed2)
    { 
        if (hour1!=12 && hour2!=12)
        {
            if (time1==time2)//both AM or PM
            {
                if (hour1>hour2)//hour1 is later
                    return speed1;
            }
            else if (time1!=time2)//not both AM/PM
            {
                if (time2)//hour2 in AM therefore hour1 is PM and later
                    return speed1;
            }
            return speed2;
        }
        if (hour1==12&&time1)//12 AM (midnight)
        {
            if (time2)//also AM
            {
                return speed2;
            }
            return speed1;
        }
        if (hour1==12&& !time1)//12 PM (noon)
        {
            if (!time2)//also PM
            {
                return speed2;
            }
            return speed1;
        }
        if (hour2==12&&time2)//12 AM (midnight)
        {
            if (time1)//also AM
            {
                return speed1;
            }
            return speed2;
        }
        if (hour2==12&& !time2)//12 PM (noon)
        {
            if (!time1)//also PM
            {
                return speed1;
            }
            return speed2;
        }
        return 0.0;
    }
    
    /*
     * Determines the number of hours between the different start times
     * @param hour1 is the start time of the first traveler
     * @param time1 is a string, either AM or PM, referring to the start time of the first traveler
     * @param hour2 is the start time of the second traveler
     * @param time2 is a string, either AM or PM, referring to the start time of the second traveler
     * @return a double value that represents the speed of the traveler that left later
     */
    public static double timeBetween (double hour1, boolean time1, double hour2, boolean time2)
    {
        if (hour1!=12 && hour2 != 12)
        {
            if (time1==time2)//both AM or PM
            {
                return Math.abs (hour1-hour2);
            }
            else
            {
                if (time1)//hour1 is AM
                {
                    return 12-hour1+hour2;
                }
                else//hour2 is AM
                {
                    return 12-hour2+hour1;
                }
            }
        }
        else
        {
            if (hour1==12&&time1)//12 AM (midnight)
            {
                if (time1)//also AM
                {
                    return hour2;
                }
                return 12-hour2;
            }
            if (hour1==12&& !time1)//12 PM (noon)
            {
                if (!time2)//also PM
                {
                    return hour2;
                }
                return 12-hour2;
            }
            if (hour2==12&&time2)//12 AM (midnight)
            {
                if (time1)//also AM
                {
                    return hour1;
                }
                return 12-hour1;
            }
            if (hour2==12&& !time2)//12 PM (noon)
            {
                if (!time1)//also PM
                {
                    return hour1;
                }
                return 12-hour1;
            }
        }
        return 0.0;
    }
    
    /*
     * Determines the number of hours between the different start times
     * @param timeDifference is the number of hours between start times
     * @param earlierSpeed is the speed of the traveler that left earlier
     * @param distance is the distance between the travelers
     * @param laterSpeed is the speed of the traveler that left later
     * @param speed1 is the speed of the first traveler
     * @param speed2 is the speed of the second traveler
     * @return a double value that represents amount of time that would pass until the two travelers meet
     */
    public static double meetInHours (double timeDifference, double earlierSpeed, double distance, double laterSpeed, double speed1, double speed2)
    {
        if (timeDifference>12.0) //switches between AM/PM
        {
            timeDifference=24-timeDifference;
            double InitialearlierSpeed=earlierSpeed;
            earlierSpeed=laterSpeed;
            laterSpeed=InitialearlierSpeed;
        }
        
        if (speed1==earlierSpeed)
            return (distance-timeDifference*earlierSpeed)/(earlierSpeed+laterSpeed)+timeDifference;
        
        return (distance-timeDifference*earlierSpeed)/(earlierSpeed+laterSpeed); //speed1 is the later speed
    }
    
 
    public static int getHours (double hours)
    {
        return (int)hours;
    }
    
    /*
     * Determine the minutes value of a certain fraction of an hour
     * @param hours is a double value representing a time
     * @return an int value representing the number of minutes 
     */
    public static int getMinutes (double hours)
    {
        double remainder=hours%(getHours (hours))*60+.5;
        return (int)remainder;
    }
    
    /*
     * Determine the distance between two locations
     * @param first is the location of the first traveler
     * @param second is the location of the second traveler
     * @return the distance between the locations
     */
    public static double getDistance (char first, char second)
    {
        double totalDistance=0;
        int loc1=assignNum (first);
        int loc2=assignNum (second);
        double [] distances=new double [9];
        distances[0]=450; //distance between location A and location B
        distances[1]=140; //distance between location B and location C
        distances[2]=125; //distance between location C and location D
        distances[3]=365;
        distances[4]=250;
        distances[5]=160;
        distances[6]=380;
        distances[7]=235;
        distances[8]=320;
        
        for (int i=loc1-1; i<loc2-1; i++)
        {
            totalDistance+=distances[i];
        }
        return totalDistance;
    }
    
    /*
     * Determine the distance between two locations
     * @param first is the location of the first traveler
     * @param second is the location of the second traveler
     * @return the distance between the locations
     */
    public static int assignNum (char one)
    {
        if (one=='A')
            return 1;
        if (one=='B')
            return 2;
        if (one=='C')
            return 3;
        if (one=='D')
            return 4;
        if (one=='E')
            return 5;
        if (one=='F')
            return 6;
        if (one=='G')
            return 7;
        if (one=='H')
            return 8;
        if (one=='J')
            return 9;
        if (one=='K')
            return 10;
        return 0;
    }
}