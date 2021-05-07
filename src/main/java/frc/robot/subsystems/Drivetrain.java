package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.RobotMap;
import frc.robot.commands.SetPercentOutput;
import harkerrobolib.wrappers.HSTalon;

public class Drivetrain extends SubsystemBase {
    private static  Drivetrain drivetrain;


    private static final boolean LEFT_MASTER_INVERTED = false;
    private static final boolean LEFT_FOLLOWER_INVERTED = false;
    private static final boolean RIGHT_MASTER_INVERTED = false;
    private static final boolean RIGHT_FOLLOWER_INVERTED = false;

    private static final boolean LEFT_SENSOR_PHASE = false;
    private static final boolean RIGHT_SENSOR_PHASE = false;

    private static final double RIGHT_P = 0;
    private static final double RIGHT_I = 0;
    private static final double RIGHT_D = 0;

    private static final double LEFT_P = 0;
    private static final double LEFT_I = 0;
    private static final double LEFT_D = 0;

    private static final double PEAK_CURRENT_LIMIT = 20;
    private static final double CONTINUOUS_CURRENT_LIMIT = 15;

    private static final double PEAK_TIME = 0.750;




    private HSTalon leftMaster;
    private VictorSPX leftFollower;
    private HSTalon rightMaster;
    private VictorSPX rightFollower;

    private Drivetrain() {
        leftMaster = new HSTalon(RobotMap.DRIVE_IDS[0]);
        leftFollower = new VictorSPX(RobotMap.DRIVE_IDS[1]);
        rightMaster = new HSTalon(RobotMap.DRIVE_IDS[2]);
        rightFollower = new VictorSPX(RobotMap.DRIVE_IDS[3]);

        talonInit();

        setDefaultCommand(new SetPercentOutput());
    }

    public static Drivetrain getInstance() {
        if (drivetrain == null) {
            drivetrain = new Drivetrain();
        }
        return drivetrain;
    }

    public void talonInit() {
        resetTalons();
        followMasters();
        invertTalons(LEFT_MASTER_INVERTED, LEFT_FOLLOWER_INVERTED, RIGHT_MASTER_INVERTED, RIGHT_FOLLOWER_INVERTED);

        applyCurrentLimits();

        leftMaster.setSensorPhase(LEFT_SENSOR_PHASE);
        rightMaster.setSensorPhase(RIGHT_SENSOR_PHASE);
    }

    private void resetTalons() {
        leftMaster.configFactoryDefault();
        leftFollower.configFactoryDefault();
        rightMaster.configFactoryDefault();
        rightFollower.configFactoryDefault();
    
    }

    public void applyCurrentLimits(){
        leftMaster.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, CONTINUOUS_CURRENT_LIMIT, PEAK_CURRENT_LIMIT, PEAK_TIME));
        rightMaster.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, CONTINUOUS_CURRENT_LIMIT, PEAK_CURRENT_LIMIT, PEAK_TIME));
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
        
        leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RobotMap.LOOP_INDEX);
        rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RobotMap.LOOP_INDEX);
    }


    public void setPercentOutput(double speed, double turn){
        leftMaster.set(ControlMode.PercentOutput, speed + turn);
        rightMaster.set(ControlMode.PercentOutput, speed - turn);
        System.out.println(speed +  " " + turn);
    } 

    public HSTalon getLeftMaster() {
        return leftMaster;
    }

    public VictorSPX getLeftFollower() {
        return leftFollower;
    }

    public HSTalon getRightMaster() {
        return rightMaster;
    }

    public VictorSPX getRightFollower() {
        return rightFollower;
    }
}