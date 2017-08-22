import java.awt.*;
import javax.swing.*;

public class Beh�llare extends JPanel
{
    //den h�r variabeln best�mmer hur m�nga partiklar som det finns
    private final int MAXUFO = 5;
    
    // Best�mmer storleken p� partikeln
    private final int UFOSIZE = 45;
    
    // De h�r variablerna best�mmer storleken p� rutan som partiklarna studsar runt i.
    private final int WIDTH = 400;
    private final int HEIGHT = 400;
    private Dimension dim;
    
    // Den h�r arrayen inneh�ller referenser till alla partiklar.
    private Partikel [] ufo = new Partikel [MAXUFO];
    
    // en textstr�ng som visar hur m�nga rutor per sekund som datorn ritar.
    private String FPS = "FPS : ";
    
    public Beh�llare()
    {
        setDoubleBuffered(true);
        
        dim = new Dimension(WIDTH,HEIGHT);
        //dim.setSize(new Dimension(WIDTH,HEIGHT));
        setPreferredSize(dim);
        
        //skapar s� m�nga nya partiklar som det anges i MAXUFO
        for(int i = 0; i<MAXUFO;i++)
        {
            ufo[i] = new Partikel((int)dim.getWidth(),(int)dim.getHeight(),UFOSIZE);
        }
    }
   
    //den h�r metoden visar vad som h�nder f�r varje tidssteg och i vilken ordning det sker
    public void tick()
    {
        kollaKollision();
        move();
        repaint();
    }
    
    /* Den h�r metoden kollar f�rst om n�gon partikel kommer krocka med v�ggen,
     * om s� �r fallet s� �ndras den partikelns hastighetsvektor.
     * Efter det kollar den om n�gon partikel kommer krocka med n�gon annan partikel och �ndrar
     * d� deras respektive hastighetsvektorer.
     */
    public void kollaKollision()
    {
        for (int i = 0;i<MAXUFO;i++)
        {
            v�ggKollision(ufo[i]);
            ufoKollision();
        }
    }
    
    // den h�r metoden kollar om partikeln i parametern �r p� v�g att tr�ffa en v�gg och �ndrar 
    // d� dess hastighetsvektor.
    public void v�ggKollision(Partikel ufo)
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
    
    // den h�r metoden kollar om det finns n�gra partiklar som �r p� v�g att krocka med varandra.
    // om s� �r fallet �ndras deras hastighetsvektorer enligt den givna formeln
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
                    double tal,n�mnare;
                    tal = hast[k].sub(hast[j]).skal�r(vektor[k].sub(vektor[j]));
                    n�mnare = (vektor[k].sub(vektor[j]).l�ngd()*vektor[k].sub(vektor[j]).l�ngd());
                    ufo[j].setSpeed(vektor[k].sub(vektor[j]).skalning(tal/n�mnare).add(hast[j]));
                    ufo[k].setSpeed(hast[k].sub(vektor[k].sub(vektor[j]).skalning(tal/n�mnare)));
                }
            }
        }
    }
    
    // Den h�r metoden uppdaterar alla partiklars positioner
    public void move()
    {
        for (int i = 0;i<MAXUFO;i++)
        {
            ufo[i].setPosition(ufo[i].getPosition().add(ufo[i].getSpeed()));
        }
    }
    
    // den h�r metoden ritar ut allting p� sk�rmen
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
    
    // den h�r metoden anv�nds f�r att �ndra p� textstr�ngen som visas i �vre v�nstra h�rnet.
    public void setFPS(String fps)
    {
        FPS = fps;
    }
        
}
