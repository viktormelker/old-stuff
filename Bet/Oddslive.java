import java.net.*;
import java.io.*;
import java.util.*;

public class Oddslive extends Sajt
{
    //filen där källkoden sparas
    private final File  LOKALFIL = new File("Oddslive.txt"); 
    
    //bestämmer om matcher som bara kan bli 1 eller 2 ska visas
    private final boolean TVÅ = false;
    
    // ett antal delimiter som behövs för att läsa information från källkoden
    private final String DELIMITER = "<a class=\"wide\">";
    private final String DELIMITER2 = " - ";
    private final String DELIMITER3 = "</a>";
    private final String TEXT = "layer_sportbook";
    
    // variabeln deli ändras av getDate funktionen och innehåller ett datum
    private String deli = "";
    
    // bestämmer hur många bets man kan ha totalt
    private final int MAX = 1000;
    
    // en temporär fil som eventuellt inte används längre
    //private final File SKRÄPFIL = new File("temp.txt");
    //private final int MAXLEN = 1700;
    
    // url en till sidan
    private URL url;
    
    private final int SAJTNR = 4;
    
    private int antal = 1;
    private final String name = "Oddslive";
    private Betcalc betcalc;
    
    
    //fulkod
    //ett nummer som säger hur många textdokument som har skapats.
    static int utskrift = 0;
    
    FileOutputStream [] outer = new FileOutputStream[150];
    
    public Oddslive(Betcalc betcalc)
    {
        this.betcalc = betcalc;
        try
        {
            url = new URL("http://www.oddslive.se/?a=odds_hours&hours=24");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public Bet [] läs()throws IOException
    {
        Bet [] bet = new Bet[MAX];
        return(läs(bet,0));
    }
    
    
    public Bet getInfo(String st,Date date)throws FileNotFoundException,IOException
    {
        // den här första delen är bara för testsyften.
        int b;/*
        outer[utskrift]= new FileOutputStream(new File("odds" + utskrift +".txt"));
        outer[utskrift].write(st.getBytes());
        outer[utskrift].close();
        utskrift++;*/
        //System.out.println(utskrift);
        
        
        boolean etttvå = false;
        Scanner scan = new Scanner(st);
        
        
        scan.useDelimiter(DELIMITER);
        String temp =scan.next();
        date.setHours(Integer.parseInt(temp.substring(1,3)));
        date.setMinutes(Integer.parseInt(temp.substring(4,6)));
        temp = scan.next();

        Scanner scan2 = new Scanner(temp);
        scan2.useDelimiter(DELIMITER2);
        String hemma = scan2.next();

        scan2.useDelimiter(DELIMITER3);
        String borta = scan2.next().substring(3);

        try
        {
            temp = scan.next();
            scan2 = new Scanner(temp);
            scan2.useDelimiter(DELIMITER3);
            double odds1 = 0;
            
            if(!(temp.substring(0,1).equals("-")))
            {
               odds1 =Double.parseDouble(scan2.next());
            }
            temp = scan.next();
            //System.out.println(temp);
            scan2 = new Scanner(temp);
            scan2.useDelimiter(DELIMITER3);
            double oddsX = 0;
            
            if(!(temp.substring(0,1).equals("-")))
            {
               oddsX =Double.parseDouble(scan2.next());
               //System.out.println(oddsX); 
            }
            else
            {
                etttvå=true;
            }
            temp = scan.next();
            //System.out.println(temp);
            scan2 = new Scanner(temp);
            scan2.useDelimiter(DELIMITER3);
            double odds2 = 0;
            
            if(!(temp.substring(0,1).equals("-")))
            {
               odds2 =Double.parseDouble(scan2.next());
               //System.out.println(odds2); 
            }
            Bet ett = null;
            if(etttvå&&TVÅ)
            {
                ett = new EttTvå(betcalc,antal,hemma,borta,date,odds1,odds2);
            }
            else
            {
                ett = new EttKryssTvå(betcalc,antal,hemma,borta,date,odds1,oddsX,odds2);
            }
            return ett;
        }
        catch(NoSuchElementException e )
        {
            return null;
        }
    }
    
    public Bet [] läs(Bet [] bet, int nr) throws FileNotFoundException,IOException
    {
        FileInputStream is = new FileInputStream(LOKALFIL);
        int b = 0;
        
        // här lagras den inlästa texten
        char [] st =new char[999999];
        //anger vilken som är nästa position i st
        int stnr=0;
        
        //används 
        String bajs = "";
        
        String test;
        
        
        super.download(this);
        Date date = super.getDate(0,this);   
        String delimit1 = deli;
        Date date2 = super.getDate(1,this);   
        String delimit2 = deli;
        
        // innehåller de senaste inlästa tecknen
        char [] temp= new char[delimit1.length()];
       // char [] temp2 = new char[delimit2.length()];
        
        System.out.println(delimit1);
        System.out.println(delimit2);
        
        boolean first = true;
        byte [] bit =  delimit1.getBytes();
        //byte [] bit2 = delimit2.getBytes();
        
        for(int i = 0;i<deli.length();i++)
        {
            temp[i] = (char) bit[i];
           // temp2[i] = (char) bit2[i];
        }
        
        while((b=is.read())!=(-1))
        {
            for (int i =0;i<delimit1.length()-1;i++)
            {
                temp[i] = temp[i+1];
              //  temp2[i] = temp[i+1];
            }
            temp [delimit1.length()-1]  =(char) b;
            //temp2 [delimit2.length()-1]  =(char) b;
            st [stnr] =(char) b;
            stnr++;
            
            test = "";
            for(int j = 0;j<deli.length();j++)
            {
                test += temp[j];
            }
            
            if((test.equals(delimit1)||test.equals(delimit2))&&(!first))
            {
                if(!test.equals(delimit1))
                {
                    date = date2;
                }
                bajs = "";
                for(int j = 0;j<stnr;j++)
                {
                    bajs += st[j];
                }
                bet[nr]=getInfo(bajs,(Date) date.clone());
                if(bet[nr] != null)
                {
                    nr++;
                }
                stnr = 0;
            }
            else if(test.equals(deli))
            {
                first =  false;
                stnr = 0;
            }        
        
        }

        betcalc.setMax(nr-1);
        return bet;
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
