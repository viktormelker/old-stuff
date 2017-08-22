import java.net.*;
import java.io.*;
import java.util.*;

public class H24Poker extends Sajt
{
    private final File  LOKALFIL = new File("24hPoker.txt"); 
    private final int SAJTNR = 6;
    private final String name = "24hPoker";
    private final int MAX = 1000;
    private URL url;
    private int antal = 0;
    private Betcalc betcalc;
    private String deli = "";
    
    public H24Poker(Betcalc betcalc)
    {
        this.betcalc = betcalc;
        try
        {
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public Bet [] läs() throws IOException
    {
        Bet [] bet = new Bet[MAX];
        int nr = 0;
        FileInputStream is = new FileInputStream(LOKALFIL);
        int b = 0;
        Scanner scan = new Scanner(LOKALFIL);
        Scanner scan2 = new Scanner(LOKALFIL);
        
        super.download(this);
        Date date = super.getDate(0,this);  
        
        
        return null;
    }
    
    public Bet [] läs(Bet [] bet,int nr)
    {
        return null;
    }
    
    public void setAntal(int antal)
    {
        this.antal = antal;
    }
    
    public String getName()
    {
        return name;
    }
    
    public URL getUrl()
    {
        return url;
    }
    
    public void setDeli(String deli)
    {
        this.deli = deli;
    }
}
