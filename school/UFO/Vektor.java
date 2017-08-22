
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

    public Vektor skalning(double skalär)
    {
        return (new Vektor(x*skalär,y*skalär));
    }

    public double skalär(Vektor v)
    {
        return (x*v.x+y*v.y);
    }

    public double längd()
    {
        return Math.sqrt(x*x+y*y);
    }

    public void print()
    {
        System.out.println("Värdet på x är " + x);
        System.out.println("Värdet på y är " + y);
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
        if(add(h).sub(v2.add(h)).längd()<size)
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
    
    //ger ett true värde tillbaks om partiklarna är på väg bort från varandra
    // den här metoden har som syfte att se till att partiklar som har "fastnat"
    // i varandra ska kunna ta sig loss och inte bara krocka och byta riktning 
    // varje tidshopp (händer om 2 partiklar råkar få samma position).
    
    public boolean bort(Vektor v2,Vektor h,Vektor h2)
    {
        double tal,tal2;
        tal = v2.sub(this).längd();
        tal2 = v2.add(h2).sub(this.add(h)).längd();
        if(tal2>tal)
        {
            return true;
        }
        return false;
    }
}


