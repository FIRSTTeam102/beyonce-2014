package edu.wpi.first.wpilibj.templates;

import Team102Lib.Deadband;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static final int leftMotor = 1;
    // public static final int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
     // PWM Ports
    public static final int frontLeftMotor = 1;
    public static final int frontRightMotor = 2;
    public static final int rearLeftMotor = 5;
    public static final int rearRightMotor = 6;

    // Analog Input Ports
   // public static final int gyroPort = 1;
    //public static final int ultrasonic = 2;

    // Joystick Setup
    public static final double joystickRange = 1.0d; // the range of the joystick around 0.0
    public static final double flatDeadband = 0.02d;        // The amount of flat space in the deadband (around 0.0)
    public static Deadband stickDeadBand = null;    // Used to create a smooth deadband for the stick.
   // public static final double twistCorrection = +0.0;

    //Digital Outputs
   // public static final int compressorSwitchChannel = 1;

    //Digital Inputs
   
    
    // Solenoid Modules and Ports
  
    
     // XBox Controller Button Indexes
    public static final int xBoxAIndex = 1;
    public static final int xBoxBIndex = 2;
    public static final int xBoxXIndex = 3;
    public static final int xBoxYIndex = 4;
    public static final int xBoxLeftBumperIndex = 5;
    public static final int xBoxRightBumperIndex = 6;
    public static final int xBoxBackButtonIndex = 7;
    public static final int xBoxStartButtonIndex = 8;
    
    
    // XBox Controller Joystick Axis
    public static final int xBoxLeftXAxis = 1;
    public static final int xBoxLeftYAxis = 2;
    public static final int xBoxTriggerAxis = 3;  // Left trigger 0.0-0.5, right trigger 0.5-1.0
    public static final int xBoxRightXAxis = 4;    
    public static final int xBoxRightYAxis = 5;
    public static final int xBoxDPadHorizontalAxis = 6;
}

