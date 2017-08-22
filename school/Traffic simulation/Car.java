public class Car {

    private int bornTime;
    private int dest; 

    // konstruktor och get-metoder
    public Car (int bornTime, int dest)
    {
        this.bornTime = bornTime;
        this.dest = dest;
    }
    
    public int getDest()
    {
        return dest;
    }
    
    public int getBornTime()
    {
        return bornTime;
    }

    public String toString() 
    {
        return "Car created " + bornTime + " with destination " + dest;
    }
	
}
