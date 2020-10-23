package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import harkerrobolib.commands.IndefiniteCommand;

public class SetPercentOutput extends IndefiniteCommand{

    SetPercentOutput(){
        addRequirements(Drivetrain.getInstance());
    }
    
    @Override
    public void execute(){
        Drivetrain.getInstance().setPercentOutput(x, y);
    }
}
