import java.io.*;
import java.net.*;
public class Betcalc
{
    
    private final double VINST = 100;
    private final double MAXWIN = 20;
    private int max = 0;
    private Sajt [] sajter = {new Oddslive(this)};//new Unibet(this),new Ladbrokes(this), new Bet24(this)};
    private Bet [] matcher;
    
    public Betcalc() throws IOException
    {
        // förbereder sajterna(eller snarare ettkrysstvå-klassen
        for(int j = 0;j<sajter.length;j++)
        {
            sajter[j].setAntal(sajter.length);
        }
        
        // läser in alla matcher
        matcher = sajter[0].läs();
        for(int i = 1; i<sajter.length;i++)
        {
            matcher = sajter[i].läs(matcher,max+1);
        }
        
        //kollar om det finns någon vinst i matcherna
        boolean vunnit = false;
        for(int k = 0; k<max;k++)
        {
            if((matcher[k].calcWin())>0&&(matcher[k].calcWin()<MAXWIN))
            {
                matcher[k].sendSuccess(matcher[k].calcWin());
                vunnit = true;
            }
            //matcher[k].sendSuccess(matcher[k].calcWin());
        }
        if(!vunnit)
        {
            System.out.println("Inga matcher har garanterad vinst idag");
        }       
    }

    public double kollaVinst(double odds1,double oddsX, double odds2)
    {
        double betalat = 0;
        betalat += VINST/odds1;
        betalat += (VINST/oddsX);
        betalat += VINST/odds2;
        return VINST-betalat;        
    }
    
    public double kollaVinst(double odds1, double odds2)
    {
        double betalat = 0;
        betalat += VINST/odds1;
        betalat += VINST/odds2;
        return VINST-betalat;        
    }
    
    
    public static void main(String [] args) throws IOException
    {
        new Betcalc();        
    }
    
    public void setMax(int nr)
    {
        max = nr;
    }
    
}
