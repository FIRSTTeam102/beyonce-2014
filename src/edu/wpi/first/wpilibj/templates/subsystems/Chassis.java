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
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.DriveWithXBox;

/**
 *
 * @author Admin
 */
public class Chassis extends Subsystem {

    private final Victor frontLeftVictor;
    private final Victor rearLeftVictor;
    private final Victor rearRightVictor;
    private final Talon frontRightTalon;
    
    private final RobotDrive drive;
    // Put methods for controlling this subsystem
    // here. Call these from Commands
    private double leftJoyX;
    private double leftJoyY;
    private double rightJoyX;
    private double rightJoyY;

    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithXBox());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());

    }

    public Chassis() {
        frontLeftVictor = new Victor(RobotMap.frontLeftMotor);
        rearLeftVictor = new Victor(RobotMap.rearLeftMotor);
        rearRightVictor = new Victor(RobotMap.rearRightMotor);
        frontRightTalon = new Talon(RobotMap.frontRightMotor);
        
        // drive = new RobotDrive(RobotMap.frontLeftMotor, RobotMap.frontRightMotor, RobotMap.rearLeftMotor, RobotMap.rearRightMotor);
        drive = new RobotDrive(frontLeftVictor, frontRightTalon, rearLeftVictor, rearRightVictor);
        
        drive.setSafetyEnabled(false);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, false);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, false);
    }

    public void straight() { // sets the motor speeds to drive straight (no turn)
        drive.arcadeDrive(1.0, 0.0);
    }

    


    public void driveWithXBox(Joystick xBox) {

        leftJoyX = xBox.getRawAxis(RobotMap.xBoxLeftXAxis);
        leftJoyY = xBox.getRawAxis(RobotMap.xBoxLeftYAxis);
        rightJoyX = xBox.getRawAxis(RobotMap.xBoxRightXAxis);
        
        if(Math.abs(leftJoyX) < 0.1){
            leftJoyX = 0.0;
        }
        if(Math.abs(leftJoyY) < 0.1){
            leftJoyY = 0.0;
        }
        if(Math.abs(rightJoyX) < 0.1){
            rightJoyX = 0.0;
        }
//        rightJoyY = xBox.getRawAxis(RobotMap.xBoxRightYAxis);

        // leftJoyX = RobotMap.stickDeadBand.Deaden(leftJoyX);
        //leftJoyY = RobotMap.stickDeadBand.Deaden(leftJoyY);
        // rightJoyX = RobotMap.twistDeadBand.Deaden(rightJoyX + RobotMap.twistCorrection);

        MessageLogger.LogMessage("Joysticks LX, LY, RX, RY: \t"
                + MathLib.round(leftJoyX, 3)
                + "\t" + MathLib.round(leftJoyY, 3)
                + "\t" + MathLib.round(rightJoyX, 3)
                + "\t" + MathLib.round(rightJoyY, 3));

        drive.mecanumDrive_Cartesian(leftJoyX, leftJoyY, rightJoyX, 0);
    }

}
