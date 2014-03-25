/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import Team102Lib.MathLib;
import Team102Lib.MessageLogger;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
//import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.DriveWithXBox;

/**
 *
 * @author Admin
 */
public class ChassisWithEncoder extends PIDSubsystem {

    private static final double Kp = 0.01;
    private static final double Ki = 0.0;
    private static final double Kd = 0.01;

    private final Talon frontLeftMotor;
    private final Talon rearLeftMotor;
    private final Talon rearRightMotor;
    private final Talon frontRightMotor;

    private final RobotDrive drive;
    // Put methods for controlling this subsystem
    // here. Call these from Commands
    private double leftJoyX;
    private double leftJoyY;
    private double rightJoyX;
    private double rightJoyY;

    public double speedScale;
    public boolean driveBackwards;

    public Encoder encoder;
    
    public boolean driveMecanum = true;

    public void initDefaultCommand() {
       setDefaultCommand(new DriveWithXBox());
    }

    public ChassisWithEncoder() {
        super("ChassisWithEncoder", Kp, Ki, Kd);
        
        frontLeftMotor = new Talon(RobotMap.frontLeftMotor);
        rearLeftMotor = new Talon(RobotMap.rearLeftMotor);
        rearRightMotor = new Talon(RobotMap.rearRightMotor);
        frontRightMotor = new Talon(RobotMap.frontRightMotor);

        // drive = new RobotDrive(RobotMap.frontLeftMotor, RobotMap.frontRightMotor, RobotMap.rearLeftMotor, RobotMap.rearRightMotor);
        drive = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);

        drive.setSafetyEnabled(false);
        this.setInvertedMotors();

        speedScale = 1.0;
        
        encoder = new Encoder(RobotMap.encoderA, RobotMap.encoderB);
        encoder.setDistancePerPulse(RobotMap.encoderInchesPerPulse);
    }

    public void straight() { // sets the motor speeds to drive straight (no turn)
        drive.arcadeDrive(1.0, 0.0);
    }

    public void driveWithXBox(Joystick xBox) {

        leftJoyX = xBox.getRawAxis(RobotMap.xBoxLeftXAxis);
        leftJoyY = xBox.getRawAxis(RobotMap.xBoxLeftYAxis);
        rightJoyX = xBox.getRawAxis(RobotMap.xBoxRightXAxis);
        rightJoyY = xBox.getRawAxis(RobotMap.xBoxRightYAxis);

        // Scale the speed.
        leftJoyX *= speedScale;
        leftJoyY *= speedScale;
        rightJoyX *= speedScale;
        rightJoyY *= speedScale;
        // Simple deadband
        if (Math.abs(leftJoyX) < 0.1) {
            leftJoyX = 0.0;
        }
        if (Math.abs(leftJoyY) < 0.1) {
            leftJoyY = 0.0;
        }
        if (Math.abs(rightJoyX) < 0.1) {
            rightJoyX = 0.0;
        }
        if (Math.abs(rightJoyY) < 0.1) {
            rightJoyY = 0.0;
        }
        
        if(driveBackwards)
        {
            leftJoyX = -leftJoyX;
            leftJoyY = -leftJoyY;
            rightJoyX = rightJoyX;
        }
        if (driveMecanum){
            drive.mecanumDrive_Cartesian(leftJoyX, leftJoyY, rightJoyX, 0);
        }else{
            drive.tankDrive(leftJoyY, rightJoyY);
        }
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        drive.tankDrive(leftSpeed, rightSpeed);
    }

    public void updateStatus() {
        String status = "Frnt(" + (driveMecanum ? "M" : "T" )+"): ";
        status += MathLib.round(frontLeftMotor.get(), 2) + " ";
        status += MathLib.round(frontRightMotor.get(), 2) + " ";

        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser1, 1, "                     ");
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser1, 1, status);

        status = "Rear: ";
        status += MathLib.round(rearLeftMotor.get(), 2) + " ";
        status += MathLib.round(rearRightMotor.get(), 2) + " ";
        status += MathLib.round(encoder.getDistance(), 2);
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser2, 1, "                     ");
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser2, 1, status);
    }

    protected double returnPIDInput() {
        return encoder.getDistance();
    }

    protected void usePIDOutput(double output) {
        if(output > 1.0)
            output  = 1.0;
        else if(output < -1.0)
            output = -1.0;
        
        frontLeftMotor.set(output);
        rearLeftMotor.set(output);
        frontRightMotor.set(-output);
        rearRightMotor.set(-output);
    }
    public void setInvertedMotors(){
        if(driveMecanum){
            drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
            drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
            drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, false);
            drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, false);            
        }else{            
            drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
            drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
            drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
            drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        }
    }
}

