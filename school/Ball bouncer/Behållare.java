import java.awt.*;
import javax.swing.*;

public class Behållare extends JPanel
{
    //den här variabeln bestämmer hur många partiklar som det finns
    private final int MAXUFO = 5;
    
    // Bestämmer storleken på partikeln
    private final int UFOSIZE = 45;
    
    // De här variablerna bestämmer storleken på rutan som partiklarna studsar runt i.
    private final int WIDTH = 400;
    private final int HEIGHT = 400;
    private Dimension dim;
    
    // Den här arrayen innehåller referenser till alla partiklar.
    private Partikel [] ufo = new Partikel [MAXUFO];
    
    // en textsträng som visar hur många rutor per sekund som datorn ritar.
    private String FPS = "FPS : ";
    
    public Behållare()
    {
        setDoubleBuffered(true);
        
        dim = new Dimension(WIDTH,HEIGHT);
        //dim.setSize(new Dimension(WIDTH,HEIGHT));
        setPreferredSize(dim);
        
        //skapar så många nya partiklar som det anges i MAXUFO
        for(int i = 0; i<MAXUFO;i++)
        {
            ufo[i] = new Partikel((int)dim.getWidth(),(int)dim.getHeight(),UFOSIZE);
        }
    }
   
    //den här metoden visar vad som händer för varje tidssteg och i vilken ordning det sker
    public void tick()
    {
        kollaKollision();
        move();
        repaint();
    }
    
    /* Den här metoden kollar först om någon partikel kommer krocka med väggen,
     * om så är fallet så ändras den partikelns hastighetsvektor.
     * Efter det kollar den om någon partikel kommer krocka med någon annan partikel och ändrar
     * då deras respektive hastighetsvektorer.
     */
    public void kollaKollision()
    {
        for (int i = 0;i<MAXUFO;i++)
        {
            väggKollision(ufo[i]);
            ufoKollision();
        }
    }
    
    // den här metoden kollar om partikeln i parametern är på väg att träffa en vägg och ändrar 
    // då dess hastighetsvektor.
    public void väggKollision(Partikel ufo)
    {
        Vektor speed = ufo.getSpeed();
        Vektor pos = ufo.getPosition();
        int nyX =(int) (pos.getX()+speed.getX());
        int nyY =(int) (pos.getY()+speed.getY());
        
        if(nyX<0)
        {
            ufo.setSpeed(new Vektor(Math.abs(speed.getX()),speed.getY()));
        }
        else if((nyX+UFOSIZE)>dim.getWidth())
        {
            ufo.setSpeed(new Vektor(-Math.abs(speed.getX()),speed.getY()));
        }
        else if((nyY+UFOSIZE)>dim.getHeight())
        {
            ufo.setSpeed(new Vektor(speed.getX(),-Math.abs(speed.getY())));
        }
        else if(nyY<0)
        {
            ufo.setSpeed(new Vektor(speed.getX(),Math.abs(speed.getY())));
        }
    }
    
    // den här metoden kollar om det finns några partiklar som är på väg att krocka med varandra.
    // om så är fallet ändras deras hastighetsvektorer enligt den givna formeln
    public void ufoKollision()
    {
        Vektor [] vektor = new Vektor[MAXUFO];
        Vektor [] hast = new Vektor[MAXUFO];
        
        for (int i = 0;i<MAXUFO;i++)
        {
            hast[i] = ufo[i].getSpeed();
            vektor [i] = ufo[i].getPosition();//.add(ufo[i].getSpeed());
        }
        
        for (int j = 0;j<MAXUFO-1;j++)
        {
            for (int k = j+1;k<MAXUFO;k++)
            {
                if(vektor[j].krockar(vektor[k],UFOSIZE,hast[j],hast[k]))
                {
                    double tal,nämnare;
                    tal = hast[k].sub(hast[j]).skalär(vektor[k].sub(vektor[j]));
                    nämnare = (vektor[k].sub(vektor[j]).längd()*vektor[k].sub(vektor[j]).längd());
                    ufo[j].setSpeed(vektor[k].sub(vektor[j]).skalning(tal/nämnare).add(hast[j]));
                    ufo[k].setSpeed(hast[k].sub(vektor[k].sub(vektor[j]).skalning(tal/nämnare)));
                }
            }
        }
    }
    
    // Den här metoden uppdaterar alla partiklars positioner
    public void move()
    {
        for (int i = 0;i<MAXUFO;i++)
        {
            ufo[i].setPosition(ufo[i].getPosition().add(ufo[i].getSpeed()));
        }
    }
    
    // den här metoden ritar ut allting på skärmen
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for (int i = 0;i<MAXUFO;i++)
        {
            g.fillOval((int)ufo[i].getPosition().getX(),(int)
            ufo[i].getPosition().getY(),UFOSIZE,UFOSIZE);
            g.drawString(FPS,20,20);
        }
    }
    
    // den här metoden används för att ändra på textsträngen som visas i övre vänstra hörnet.
    public void setFPS(String fps)
    {
        FPS = fps;
    }
        
}
