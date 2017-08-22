public class Road {

    Car[] theRoad;      //har referenser till alla bilar på vägen
    int maxCar;         // antal fordon som får plats på vägen
    Light light = null; // ett ev. trafikljus i slutet av vägen
    Road [] cont;       // en ev. fortsättningsväg

    public Road(int n) {
	// Konstruerar ett Road-objekt med plats för n fordon
        maxCar = n;
        theRoad = new Car [n];
    }
    
    public Road(int n, Road [] cont) {
	/* Konstruerar ett Road-objekt med plats för n 
         fordon och en väg i slutet*/
        maxCar = n;
        theRoad = new Car [n];
        this.cont = cont;
    }
    
    public Road(int n, Light light)
    {
        /* Konstruerar ett Road-objekt med plats 
         för n fordon och ett trafikljus i slutet*/
        maxCar = n;
        theRoad = new Car [n];
        this.light = light;
        
    }

    public Car step() {
	// Stega fram alla fordon (utom det på plats 0) ett steg 
        // (om det går). (Fordonet på plats 0 tas bort utifrån 
	// mm h a metoden nedan.)
        
        
        //Köra på nästa väg
        if((light==null)&&(theRoad[0]!=null))
        {
            /* tittar om den första bilen kan köra på nästa väg*/
            if(cont[theRoad[0].getDest()-1].lastFree())
            {
                return getFirst();
            }
        }
        // första bilen kör om det är grönt
        else if((light!=null)&&(light.isGreen()))
        {
            return getFirst();
        }
        else
        {
            //kör fram alla bilar som har en lucka framför
            for (int i = maxCar-1;i>0;i--)
            {
                if(theRoad[i-1]==null)
                {
                   theRoad[i-1]=theRoad[i];
                   theRoad[i]=null;
                }
            }
        }   
        
        return null;
        
    }

    public Car getFirst() {
	// Returnera och tag bort bilen som står först
        Car ret = theRoad[0];
        for (int i = 0; i<maxCar-2; i++)
        {
            theRoad[i]= theRoad[i+1];
        }
        return ret;
    }

    public Car firstCar() {
	// Returnera bilen som står först utan att ta bort den
        return theRoad[0];
    }


    public boolean lastFree() {
	// Returnera true om sista platsen ledig, annars false
        return (theRoad[maxCar-1]==null);
    }

    public void putLast(Car c) {
	// Ställ en bil på sista platsen på vägen
	// (om det går).
        if (lastFree())
        {
            theRoad[maxCar-1] = c;
        }
    }

    public String toString() 
    {
        String ret ="Road \n ";
        for (int i = 0;i<maxCar;i++)
        {
            if(theRoad[i]==null)
            {
                ret += "no Car \n ";
            }
            else
            {
                ret += " " +theRoad[i].toString() + "\n";
            }
        }
        ret += "\n";
        return ret;
    }


}