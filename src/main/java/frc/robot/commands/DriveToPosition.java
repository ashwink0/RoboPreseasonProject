package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveToPosition extends CommandBase {
    private int encoderTicks;

    public DriveToPosition(int encoderTicks) {
        this.encoderTicks = encoderTicks;
        addRequirements(Drivetrain.getInstance());
    }

    @Override
    public void initialize() {
        Drivetrain.getInstance().configPositionPIDConstants();

        Drivetrain.getInstance().getLeftMaster().setSelectedSensorPosition(0);
        Drivetrain.getInstance().getRightMaster().setSelectedSensorPosition(0);
    }

    @Override
    public void execute() {
        Drivetrain.getInstance().getLeftMaster().set(ControlMode.Position, encoderTicks);
        Drivetrain.getInstance().getRightMaster().set(ControlMode.Position, encoderTicks);
    }

    @Override
    public boolean isFinished() {
        return isAcceptableError();
    }

    private boolean isAcceptableError() {
        if (Math.abs(Drivetrain.getInstance().getLeftMaster().getSelectedSensorPosition() - encoderTicks) < 100 && Math
                .abs(Drivetrain.getInstance().getRightMaster().getSelectedSensorPosition() - encoderTicks) < 100) {

            Drivetrain.getInstance().getLeftMaster().set(ControlMode.Disabled, 0);
            Drivetrain.getInstance().getRightMaster().set(ControlMode.Disabled, 0);
            return true;
        }
        return false;
    }
}
