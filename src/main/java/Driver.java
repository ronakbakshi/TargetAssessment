import com.target.barrenanalysis.BarrenLand;

public class Driver {
    public static void main(String[] args) throws Exception {
        BarrenLand barrenLand = new BarrenLand();
        //Sample stdin
        String output = barrenLand.getFertileLandArea(new String[]{"48 192 351 207", "48 392 351 407", "120 52 135 547", "260 52 275 547"});
        System.out.println("Areas of fertile land starting from smallest in square meters are: " + output);
    }
}