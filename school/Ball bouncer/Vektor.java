
public class Vektor
{
    double x,y;

    public Vektor(double X, double Y)
    {
        x=X;
        y=Y;
    }

    public Vektor add(Vektor v)
    {
        return (new Vektor(x+v.x,y+v.y));
    }

    public Vektor sub(Vektor v)
    {
        return (new Vektor(x-v.x,y-v.y));
    }

    public Vektor skalning(double skal�r)
    {
        return (new Vektor(x*skal�r,y*skal�r));
    }

    public double skal�r(Vektor v)
    {
        return (x*v.x+y*v.y);
    }

    public double l�ngd()
    {
        return Math.sqrt(x*x+y*y);
    }

    public void print()
    {
        System.out.println("V�rdet p� x �r " + x);
        System.out.println("V�rdet p� y �r " + y);
    }
    
    public double getX()
    {
        return x;
    }
    
    public void setX(double x)
    {
        this.x = x;
    }
    
    public double getY()
    {
        return y;
    }
    
    public void setY(double y)
    {
        this.y = y;
    }

    public boolean krockar(Vektor v2,int size,Vektor h,Vektor h2)
    {
        if(add(h).sub(v2.add(h)).l�ngd()<size)
        {
            if(bort(v2,h,h2))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }
    }
    
    //ger ett true v�rde tillbaks om partiklarna �r p� v�g bort fr�n varandra
    // den h�r metoden har som syfte att se till att partiklar som har "fastnat"
    // i varandra ska kunna ta sig loss och inte bara krocka och byta riktning 
    // varje tidshopp (h�nder om 2 partiklar r�kar f� samma position).
    
    public boolean bort(Vektor v2,Vektor h,Vektor h2)
    {
        double tal,tal2;
        tal = v2.sub(this).l�ngd();
        tal2 = v2.add(h2).sub(this.add(h)).l�ngd();
        if(tal2>tal)
        {
            return true;
        }
        return false;
    }
}


