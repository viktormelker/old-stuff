import javax.swing.*;
import java.awt.*;
public class Projekt extends JFrame
{
    // borde best�mma min FPS (men misslyckas...)
    private final long WAITTIME = 50;
    
    //best�mmer hur ofta jag uppdaterar FPS
    private final int VILOTID = 20;
    
    private Beh�llare cont;
    private boolean finished = false;
    private long tiden;
    private long f�rraTiden;
    
    
    public Projekt() 
    {
        cont = new Beh�llare();
        getContentPane().add(cont);
        pack();
        setVisible(true);
    }
    
    public void driv()
    {
        int nr = 1;
        long fpsTime;
        tiden = System.currentTimeMillis();
        fpsTime = tiden;
        while(!finished)
        {
            
            cont.tick();
            nr++;
            if(nr%VILOTID == 0)
            {
                int fps =(int) (VILOTID*1000/((tiden-fpsTime)));
                fpsTime = tiden;
                cont.setFPS("FPS: " +fps);
            }
            f�rraTiden = tiden;
            tiden = System.currentTimeMillis();
            if(tiden-f�rraTiden<WAITTIME)
            {
                try
                {
                    Thread.sleep(WAITTIME-(tiden-f�rraTiden));
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void main(String [] args) 
    {
        Projekt p = new Projekt();
        p.driv();
        //cont.start();
        
    }
        
}
