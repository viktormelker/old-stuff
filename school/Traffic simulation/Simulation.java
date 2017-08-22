public class Simulation {


    public static void main(String [] args) {
	// Skapar ett TrafikSystem
	// Utför stegningen, anropar utskriftsmetoder
        TraficSystem system = new TraficSystem();
        for(int i = 0;i<10000;i++)
        {
            system.step();
        }
        system.print();
        system.printStatistics();

    }
}