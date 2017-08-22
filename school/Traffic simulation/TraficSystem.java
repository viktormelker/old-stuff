
import java.util.Scanner;
import java.util.Random;
public class TraficSystem {
    // Definierar de vägar och signaler som ingår i det 
    // system som skall studeras.
    // Samlar statistik
    
    // Attribut som beskriver beståndsdelarna i systemet
    private Road  r0;       // de 3 vägarna från problembeskrivningen
    private Road  r1;
    private Road  r2;
    
    /*de 2 sista vägarna samlade på ett ställe*/
    private Road [] cont = new Road [2];  
    
    private Light s1;   //De 2 trafikljusen
    private Light s2;

    // Diverse attribut för simuleringsparametrar (ankomstintensiteter,
    // destinationer...)
    int intensity;
    
    /* p1 = period för 1:A ljussignalen, p2 för 2:a, g1 resp. g2 är gröntiderna
     för signalerna, max0-max2 är längderna på de olika vägarna*/
    int p1,p2,max0,max1,max2,g1,g2;  
    Scanner scan = new Scanner(System.in);
    Random rand = new Random();
    
    

    // Diverse attribut för statistiksamling
    private int maxTime1,maxTime2,total1,total2,num1,num2 = 0;
            
    private int time = 0;

    public TraficSystem() 
    {
        readParameters();
        s1 = new Light(p1,g1);
        s2 = new Light(p2,g2);
        r0 = new Road(max0,cont);
        r1 = new Road(max1,s1);
        r2 = new Road(max2,s2);
        cont [0] = r1; 
        cont [1] = r2;
        
    }

    public void readParameters() {
	// Läser in parametrar för simuleringen
	// Metoden kan läsa från terminalfönster, dialogrutor
	// eller från en parameterfil. Det sista alternativet
	// är att föredra vid uttestning av programmet eftersom
	// man inte då behöver mata in värdena vid varje körning. 
        System.out.println("Ange ankomstintensiteten (#bilar/100 sekunder) ");
        intensity = scan.nextInt();
        System.out.println("Ange väglängd 0 ");
        max0 = scan.nextInt(); 
        System.out.println("Ange väglängd 1 ");
        max1 = scan.nextInt(); 
        System.out.println("Ange väglängd 2 ");
        max2 = scan.nextInt(); 
        System.out.println("Ange period 1 ");
        p1 = scan.nextInt(); 
        System.out.println("Ange period 2 ");
        p2 = scan.nextInt(); 
        System.out.println("Ange gröntid 1");
        g1 = scan.nextInt(); 
        System.out.println("Ange gröntid 2 ");
        g2 = scan.nextInt(); 
    }

    public void step() {
	// Stega systemet ett tidssteg m h a komponenternas step-metoder
	// Skapa bilar, lägg in och ta ur på de olika Road-kompenenterna
        time++;
        
        Car temp;
        temp = r0.step();
        
        //om en bil från första vägen körde fram sätter vi in den i nästa väg
        if(temp!=null)
        {
            cont[temp.getDest()-1].putLast(temp);
        }
        
        temp = r1.step();
        finish(temp);
        temp = r2.step();
        finish(temp);
        
        s1.step();
        s2.step();
        
        //Det kommer en ny bil till första vägen
        // bilen skapas slumpmässigt i tiden.
        int num = rand.nextInt(100) ;
        if(num<=intensity)
        {
            r0.putLast(new Car(time,rand.nextInt(2)+1));
        }
        
        
    }
    
    public void finish(Car car)
    {
        if(car!=null)
        {
            //räknar ut passagetiden
            int passed = time-car.getBornTime();
            
            //och lägger in den i statistiken
            if(car.getDest()==1)
            {
                
                total1 += passed;
                if(passed>maxTime1)
                {
                    maxTime1 = passed;
                }
                
                num1++;
            }
            else
            {
                total2 += passed;
                if(passed>maxTime2)
                    maxTime2 = passed;
                
                num2++;
            }
            
        }
    }

    public void printStatistics() {
        
	// Skriv statisteken samlad så här långt
        System.out.println("Totalt antal passerade bilar: " + (num1+num2));
        System.out.println("Total genomsnittlig passagetid: " + ((total1+total2)/(num1+num2)));
        System.out.println();
        System.out.println("Antal bilar till destination 1: " + num1);
        System.out.println("Genomsnittlig passagetid till destination 1: " + (total1/num1));
        System.out.println("Längsta passagetid till destination 1: " + maxTime1);
        System.out.println();
        System.out.println("Antal bilar till destination 2: " + num2);
        System.out.println("Genomsnittlig passagetid till destination 2: " + (total2/num2));
        System.out.println("Längsta passagetid till destination 2: " + maxTime2);
    }

    public void print() {
	// Skriv ut en grafisk representation av kösituationen
	// med hjälp av klassernas toString-metoder
        
        
        
        System.out.println("Trafic situation at " + time);
        System.out.println(r0.toString());
        System.out.println(r1.toString());
        System.out.println(s1.toString());
        System.out.println(r2.toString());
        System.out.println(s2.toString());
    }

}