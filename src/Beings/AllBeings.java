package Beings;

public class AllBeings {
    /*
     //public static List<AllBeings> allBeings = Collections.synchronizedList(new ArrayList<>());
     public static List<AllBeings> allBeings = new CopyOnWriteArrayList<>();

     protected int iY;  //y location
     protected int jX;  //x location

     public AllBeings(int iY, int jX) {
         this.iY = iY;
         this.jX = jX;
     }
     */
    public int iY;  //y location
    public int jX;  //x location
    public BeingsNames beingName;
    public double weight;

    public AllBeings(int iY, int jX) {
        this.iY = iY;
        this.jX = jX;
        this.beingName = BeingsNames.valueOf(this.getClass().getSimpleName());
    }
}
