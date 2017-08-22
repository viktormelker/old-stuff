public class Light {
    int period; //period är antalet sekunder som signalen är röd
    int green;  // antalet sekunder som signalen är grön
    int time;
    int splitTime = 0;  // är tiden sedan signalen bytte färg förra gången
    
    boolean drive = false; // är false när signalen är röd och true annars

    public Light(int p, int g) 
    {
        period = p;
        green = g;
        
    }
    
    public void step()      
    {
        time++;
        splitTime++;
        // ljuset är grönt
        if(drive) 
        {
            if (splitTime>green)
            {
                splitTime = 0;
                drive = !drive;
             }
        }
        else
        {
            if (splitTime>period)
            {
                splitTime = 0;
                drive = !drive;
             }
        }
    }
    
    public boolean isGreen()   
    {
        return drive;
    }
    
    public String  toString()  
    {
        return "Light is green? " +drive;
    }
	
}