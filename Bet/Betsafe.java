import java.net.*;
import java.io.*;
import java.util.*;

public class Betsafe extends Sajt
{
    private final File  LOKALFIL = new File("Betsafe.txt"); 
    private URL url;
    private final int MAX = 1000;
    private final int SAJTNR = 5;
    private int antal = 0;
    private final String name = "Betsafe";
    private Betcalc betcalc;
    private String deli = "";
    
    public Betsafe(Betcalc betcalc)
    {
        this.betcalc = betcalc;
        try
        {
            url  = new URL("");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    
    public Bet [] läs(Bet [] bet,int nr)
    {
        return null;
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
