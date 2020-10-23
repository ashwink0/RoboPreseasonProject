package frc.robot;

public class OI {
    private static OI oi;


    public static OI getInstance(){
        if(oi==null)
            oi=new OI();
        return oi;

    }
}
