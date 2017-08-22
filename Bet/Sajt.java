import java.io.*;
import java.net.*;
import java.util.*;

public abstract class Sajt
{
    public abstract Bet [] läs() throws IOException;
    public abstract Bet [] läs(Bet [] bet, int nr) throws IOException;
    public abstract void setAntal(int antal);
    public abstract URL getUrl();
    public abstract String getName();
    public abstract void setDeli(String deli);
    
    
    public void download(Sajt sajt) throws IOException
    {
        int bytesRead=0;
        URLConnection connection = sajt.getUrl().openConnection();
        InputStream is = connection.getInputStream();
        FileOutputStream out = new FileOutputStream(new File(sajt.getName()+".txt"));
        int b = 0;
        
        while ((b = is.read()) != -1)
        {
            out.write(b);
            bytesRead++;
        }
        is.close();
        out.close();
    }
    
    public Date getDate(int plus,Sajt sajt)
    {
        Calendar cal = new GregorianCalendar();
        String year = "";
        String month = "";
        String day = "";
        year =""+ cal.get(Calendar.YEAR);
        if(cal.get(Calendar.MONTH)+1<10)
        {
            month = "0";
        }
        month += cal.get(Calendar.MONTH)+1;
        if((cal.get(Calendar.DAY_OF_MONTH)+plus)<10)
        {
            day = "0";
        }
        day += (cal.get(Calendar.DAY_OF_MONTH)+plus);
        String total = year+"-"+month+"-"+(day);
        Date date = new Date(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
        sajt.setDeli(total);
        return date;
    }
}
