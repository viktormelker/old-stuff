import java.util.*;
public class EttKryssTvå implements Bet
{
    
    private String  hemma,borta;
    private int nr = 0;
    private Date date;
    private double [] odds1,oddsX,odds2;
    private double maxodds1,maxoddsX,maxodds2 = 0;
    private String [] names;
    private int best1,bestX,best2 = 0;
    private int antal;
    private Betcalc betcalc;
    
    
    public EttKryssTvå(Betcalc betcalc,int antal,String hemma, String borta,Date date,double odds1,double oddsX, double odds2)
    {
        this.hemma = hemma;
        this.borta = borta;
        this.date = date;
        this.antal = antal;
        this.betcalc = betcalc;
        this.odds1 = new double[antal];
        this.oddsX = new double[antal];
        this.odds2 = new double[antal];
        names = new String [antal];
        this.odds1[0]=odds1;
        this.oddsX[0]=oddsX;
        this.odds2[0]=odds2;
    }
    
    public EttKryssTvå(Betcalc betcalc,int antal,String hemma, String borta,Date date, int nr)
    {
        this.hemma = hemma;
        this.borta = borta;
        this.date = date;
        this.nr = nr;
        this.antal = antal;
        this.betcalc = betcalc;
        odds1 = new double[antal];
        oddsX = new double[antal];
        odds2 = new double[antal];
        
        names = new String [antal];
    }
    
    public String getHemma()
    {
        return hemma;
    }
    
    public String getBorta()
    {
        return borta;
    }
    
    public Date getDate()
    {
        return date;
    }
    
    public int getNr()
    {
        return nr;
    }

    public boolean equals(EttKryssTvå match)
    {
        if (date.equals(match.getDate())&&(hemma.equals(match.getHemma())||borta.equals(match.getBorta())))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public double calcWin()
    {
        maxOdds();
        return betcalc.kollaVinst(maxodds1,maxoddsX,maxodds2);
    }
    
    public void maxOdds()
    {
        for (int i = 0;i<antal;i++)
        {
            if(odds1[i]>maxodds1)
            {
                maxodds1=odds1[i];
                best1=i;
            }
            if(oddsX[i]>maxoddsX)
            {
                maxoddsX=oddsX[i];
                bestX=i;
            }
            if(odds2[i]>maxodds2)
            {
                maxodds2=odds2[i];
                best2=i;
            }   
        }
    }
    
    public void sendSuccess(double db)
    {
        System.out.println("Vinst på match: " + toString() +"\n på "+ db +" kr per 100 kr");
    }
    
    public String toString()
    {
        return (String)(hemma + " vs " + borta + "\n " +odds1[0] +"    " +oddsX[0] +"    " +odds2[0]); 
    }

}
