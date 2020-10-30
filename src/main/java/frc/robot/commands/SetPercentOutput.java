package frc.robot.commands;

import frc.robot.OI;
import frc.robot.subsystems.Drivetrain;
import harkerrobolib.commands.IndefiniteCommand;
import harkerrobolib.util.MathUtil;

public class SetPercentOutput extends IndefiniteCommand {

    public SetPercentOutput() {
        addRequirements(Drivetrain.getInstance());
    }

    @Override
    public void execute() {
        double x = MathUtil.mapJoystickOutput(OI.getInstance().getDriverGamepad().getLeftX(), OI.DEADBAND);
        double y = MathUtil.mapJoystickOutput(OI.getInstance().getDriverGamepad().getLeftY(), OI.DEADBAND);
        Drivetrain.getInstance().setPercentOutput(y, x);
    }
}