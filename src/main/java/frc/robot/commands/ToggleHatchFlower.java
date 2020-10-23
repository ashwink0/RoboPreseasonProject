package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.HatchLatcher;

public class ToggleHatchFlower extends InstantCommand{
    ToggleHatchFlower(){
        addRequirements(HatchLatcher.getInstance());
    }

    @Override
    public void initialize(){
        HatchLatcher.getInstance().invertPiston(HatchLatcher.getInstance().getFlowerSolenoid());
    }
}