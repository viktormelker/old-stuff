

public class Partikel
{
    //beskriver Ufots storlek
    final private int SIZE = 45;
   
    //beskriver beh�llarens storlek om den inte anges som parameter i konstruktorn
    private final int MAXHEIGHT = 600;
    private final int MAXWIDTH = 800;
    
    //anger den h�gsta till�tna hastigheten
    private final int MAXSPEED = 4;
    
    //de vektorer som beskriver position och hastighet p� bollen
    private Vektor flygPosition,flygHastighet;
    
    // skapar en ny partikel med en slumpm�ssig position inom en given gr�ns
    // som anges av MAXHEIGHT och MAXWIDTH samt SIZE
    public Partikel()
    {
        flygPosition = new Vektor(Math.random()*MAXWIDTH-SIZE,Math.random()*MAXHEIGHT-SIZE);
        flygHastighet = new Vektor(Math.random()*MAXSPEED,Math.random()*MAXSPEED);
    }
    
    // skapar en ny partikel med en slumpm�ssig position inom en given gr�ns
    // som anges av parametrarna height och width och size
    public Partikel(int width,int height,int size)
    {
        flygPosition = new Vektor(Math.random()*width-size,Math.random()*height-size);
        flygHastighet = new Vektor(Math.random()*MAXSPEED,Math.random()*MAXSPEED);
    }
    
    // skapar en ny partikel med den givna positionen och hastigheten
    public Partikel(Vektor flygPosition, Vektor flygHastighet)
    {
        this.flygPosition = flygPosition;
        this.flygHastighet = flygHastighet;
    }
        
    // ger en vektor som innehar positionen
    public Vektor getPosition()
    {
        return flygPosition;
    }
    
    // �ndrar partikelns position till den som anges i parametern
    public void setPosition(Vektor position)
    {
        flygPosition = position;
    }
    
    // ger en vektor som innehar hastigheten
    public Vektor getSpeed()
    {
        return flygHastighet;
    }
    
    // �ndrar partikelns hastighet till den som anges i parametern
    public void setSpeed(Vektor hastighet)
    {
        flygHastighet = hastighet;
    }
    
}
