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
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.DriveWithXBox;

/**
 *
 * @author Admin
 */
public class Chassis extends Subsystem {

    private final RobotDrive drive;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private double x;
    private double y;
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
        drive = new RobotDrive(RobotMap.frontLeftMotor, 4, RobotMap.frontRightMotor, RobotMap.rearRightMotor);
        drive.setSafetyEnabled(false);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
    }

    public void straight() { // sets the motor speeds to drive straight (no turn)
        drive.arcadeDrive(1.0, 0.0);
    }

    public void turnLeft() { // sets the motor speeds to start a left turn
        System.out.println(":) Turning Left.");
        drive.arcadeDrive(0.0, 0.5);
    }

    public void turnRight() {
        System.out.println(":) Turning Right.");
        drive.arcadeDrive(0.0, -0.5);
    }

    public void driveWithJoystick(Joystick stick) {
//        drive.arcadeDrive(stick);

        DriverStation ds = DriverStation.getInstance();
        DriverStationLCD lcd = DriverStationLCD.getInstance();

        x = stick.getX();
        y = stick.getY();
        double twist = stick.getTwist();

        x = RobotMap.stickDeadBand.Deaden(x);
        y = RobotMap.stickDeadBand.Deaden(y);
    }

    public void driveWithXBox(Joystick xBox) {

        leftJoyX = xBox.getRawAxis(RobotMap.xBoxLeftXAxis);
        leftJoyY = xBox.getRawAxis(RobotMap.xBoxLeftYAxis);
        rightJoyX = xBox.getRawAxis(RobotMap.xBoxRightXAxis);
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
