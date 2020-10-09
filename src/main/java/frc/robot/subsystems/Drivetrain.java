package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;


import frc.robot.RobotMap;
import harkerrobolib.wrappers.HSTalon;

public class Drivetrain extends SubsystemBase {
    private static  Drivetrain drivetrain;


    private static final boolean LEFT_MASTER_INVERTED = false;
    private static final boolean LEFT_FOLLOWER_INVERTED = false;
    private static final boolean RIGHT_MASTER_INVERTED = false;
    private static final boolean RIGHT_FOLLOWER_INVERTED = false;

    private static boolean leftSensorPhase = false;
    private static boolean rightSensorPhase = false;

    private static final double RIGHT_P = 1;
    private static final double RIGHT_I = 1;
    private static final double RIGHT_D = 1;

    private static final double LEFT_P = 1;
    private static final double LEFT_I = 1;
    private static final double LEFT_D = 1;


    private HSTalon leftMaster;
    private HSTalon leftFollower;
    private HSTalon rightMaster;
    private HSTalon rightFollower;

    private Drivetrain() {
        leftMaster = new HSTalon(RobotMap.DRIVE_IDS[0]);
        leftFollower = new HSTalon(RobotMap.DRIVE_IDS[1]);
        rightMaster = new HSTalon(RobotMap.DRIVE_IDS[2]);
        rightFollower = new HSTalon(RobotMap.DRIVE_IDS[3]);

        talonInit();
    }

    public static Drivetrain getInstance() {
        if (drivetrain == null) {
            drivetrain = new Drivetrain();
        }
        return drivetrain;
    }

    public HSTalon getLeftMaster() {
        return leftMaster;
    }

    public HSTalon getLeftFollower() {
        return leftFollower;
    }

    public HSTalon getRightMaster() {
        return rightMaster;
    }

    public HSTalon getRightFollower() {
        return rightFollower;
    }

    public void talonInit() {
        resetTalons();
        followMasters();
        invertTalons(LEFT_MASTER_INVERTED, LEFT_FOLLOWER_INVERTED, RIGHT_MASTER_INVERTED, RIGHT_FOLLOWER_INVERTED);

        leftMaster.setSensorPhase(leftSensorPhase);
        rightMaster.setSensorPhase(rightSensorPhase);
    }

    private void resetTalons() {
        leftMaster.configFactoryDefault();
        leftFollower.configFactoryDefault();
        rightMaster.configFactoryDefault();
        rightFollower.configFactoryDefault();
    
    }

    private void followMasters() {
        leftFollower.follow(leftMaster);
        rightFollower.follow(rightMaster);
    }

    private void invertTalons(boolean leftMasterInverted, boolean leftFollowerInverted, boolean rightMasterInverted, boolean rightFollowerInverted) {
        leftMaster.setInverted(leftMasterInverted);
        leftFollower.setInverted(leftFollowerInverted);
        rightMaster.setInverted(rightMasterInverted);
        rightFollower.setInverted(rightFollowerInverted);
    }
    
    public void configPositionPIDConstants() {
        leftMaster.config_kP(RobotMap.SLOT_INDEX, LEFT_P);
        leftMaster.config_kI(RobotMap.SLOT_INDEX, LEFT_I);
        leftMaster.config_kD(RobotMap.SLOT_INDEX, LEFT_D);

        rightMaster.config_kP(RobotMap.SLOT_INDEX, RIGHT_P);
        rightMaster.config_kI(RobotMap.SLOT_INDEX, RIGHT_I);
        rightMaster.config_kD(RobotMap.SLOT_INDEX, RIGHT_D);

        leftMaster.selectProfileSlot(RobotMap.SLOT_INDEX, RobotMap.LOOP_INDEX);
        rightMaster.selectProfileSlot(RobotMap.SLOT_INDEX, RobotMap.LOOP_INDEX);
        
        leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RobotMap.LOOP_INDEX, 0);
        rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RobotMap.LOOP_INDEX, 0);
    }


    public void setPercentOutput(double x, double y){
        leftMaster.set(ControlMode.PercentOutput, y + x);
        rightMaster.set(ControlMode.PercentOutput, y - x);
    } 
}