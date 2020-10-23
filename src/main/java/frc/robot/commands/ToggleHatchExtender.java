package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.HatchLatcher;

public class ToggleHatchExtender extends InstantCommand{
    ToggleHatchExtender(){
        addRequirements(HatchLatcher.getInstance());
    }

    @Override
    public void initialize(){
        HatchLatcher.getInstance().invertPiston(HatchLatcher.getInstance().getExtenderSolenoid());
    }
}