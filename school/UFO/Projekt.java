import javax.swing.*;
import java.awt.*;
public class Projekt extends JFrame
{
    // borde bestämma min FPS (men misslyckas...)
    private final long WAITTIME = 50;
    
    //bestämmer hur ofta jag uppdaterar FPS
    private final int VILOTID = 20;
    
    private Behållare cont;
    private boolean finished = false;
    private long tiden;
    private long förraTiden;
    
    
    public Projekt() 
    {
        cont = new Behållare();
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
            förraTiden = tiden;
            tiden = System.currentTimeMillis();
            if(tiden-förraTiden<WAITTIME)
            {
                try
                {
                    Thread.sleep(WAITTIME-(tiden-förraTiden));
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
