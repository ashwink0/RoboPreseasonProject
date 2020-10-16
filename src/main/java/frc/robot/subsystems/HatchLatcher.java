package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class HatchLatcher extends SubsystemBase{
    
    private static HatchLatcher hatchLatcher;

    private DoubleSolenoid extenderSolenoid =new DoubleSolenoid(RobotMap.EXTENDER_FORWARD_PORT, RobotMap.EXTENDER_REVERSE_PORT);
    private DoubleSolenoid flowerSolenoid=new DoubleSolenoid(RobotMap.FLOWER_FORWARD_PORT, RobotMap.FLOWER_REVERSE_PORT);

    HatchLatcher(){

    }


    public static HatchLatcher getInstance(){
        if(hatchLatcher==null){
            hatchLatcher=new HatchLatcher();
        }
        return hatchLatcher;
    }

    public DoubleSolenoid getExtenderSolenoid(){
        return extenderSolenoid;
    }
    public DoubleSolenoid getFlowerSolenoid(){
        return flowerSolenoid;
    }

    public void invertPiston(DoubleSolenoid piston){
        if(piston.get()==Value.kForward){
            piston.set(Value.kReverse);
            return;
        }
        piston.set(Value.kForward);


    }
}
