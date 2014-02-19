/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import Team102Lib.MathLib;
import Team102Lib.MessageLogger;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.DriveWithXBox;

/**
 *
 * @author Admin
 */
public class Lift extends Subsystem {

    private final DigitalInput liftDownLeft;
    private final DigitalInput liftDownRight;
    private final DigitalInput liftUpLeft;
    private final DigitalInput liftUpRight;

    public final Talon leftMotor;
    public final Talon rightMotor;

    public Lift() {
        liftDownLeft = new DigitalInput(RobotMap.liftDownLeft);
        liftDownRight = new DigitalInput(RobotMap.liftDownRight);
        liftUpLeft = new DigitalInput(RobotMap.liftUpLeft);
        liftUpRight = new DigitalInput(RobotMap.liftUpRight);

        leftMotor = new Talon(RobotMap.leftLiftMotor);
        rightMotor = new Talon(RobotMap.rightLiftMotor);

    }

    public void initDefaultCommand() {

    }

    public void lift(double speed) {
        leftMotor.set(speed);
        if (speed > 0.0) {
            rightMotor.set(speed * RobotMap.liftMotorRightSpeedAdjustment);
        } else {
            rightMotor.set(speed * RobotMap.liftMotorRightSpeedAdjustment);
            

        }
    }

    public void liftLeft(boolean up) {
        if (up) {
            if (liftUpLeft.get() == true) {
                leftMotor.set(RobotMap.liftUpSpeed * 0.25);
                MessageLogger.LogMessage("LiftLeftUp - left motor up.");
            } else {
                leftMotor.set(0.0);
                MessageLogger.LogMessage("LiftLeftUp - stopping left motor.");
            }
        } else {
            if (liftDownLeft.get() == false) {
                leftMotor.set(0.0);
                MessageLogger.LogMessage("LiftLeftDown - stopping left motor.");
            } else {
                leftMotor.set(-RobotMap.liftUpSpeed * 0.25);
                MessageLogger.LogMessage("LiftLeftDown - left motor down.");
            }
        }
    }

    public void liftRight(boolean up) {
        if (up) {
            if (liftUpRight.get() == true) {
                rightMotor.set(RobotMap.liftUpSpeed * 0.25);
                MessageLogger.LogMessage("LiftRightUp - right motor up.");
            } else {
                rightMotor.set(0.0);
                MessageLogger.LogMessage("LiftRightUp - stopping right motor.");
            }
        } else {
            if (liftDownRight.get() == false) {
                rightMotor.set(0.0);
                MessageLogger.LogMessage("LiftRightDown - stopping right motor.");
            } else {
                rightMotor.set(-RobotMap.liftUpSpeed * 0.25);
                MessageLogger.LogMessage("LiftRightDown - right motor down.");
            }
        }
    }

    public void liftUp() {
        if ((liftUpLeft.get() == true) && (liftUpRight.get() == true)) {

            leftMotor.set(RobotMap.liftUpSpeed * RobotMap.liftMotorLeftSpeedAdjustment);
            rightMotor.set(RobotMap.liftUpSpeed * RobotMap.liftMotorRightSpeedAdjustment);
            
            MessageLogger.LogMessage("LiftUp - left and right motor up.");
        } else {
            leftMotor.set(0.0);
            rightMotor.set(0.0);
            MessageLogger.LogMessage("LiftUp - stopping left and right motor.");
        }
    }
    public void liftUp(double liftSpeed) {
        if ((liftUpLeft.get() == true) && (liftUpRight.get() == true)) {
//            
            leftMotor.set(liftSpeed * RobotMap.liftMotorLeftSpeedAdjustment);
            rightMotor.set(liftSpeed * RobotMap.liftMotorRightSpeedAdjustment);
            
            MessageLogger.LogMessage("LiftUp(" + liftSpeed + ") - left and right motor up.");
        } else {
            leftMotor.set(0.0);
            rightMotor.set(0.0);
            MessageLogger.LogMessage("LiftUp(" + liftSpeed + ") - stopping left and right motor.");
        }
    }

    public void liftDown(double liftSpeed) {
        if (liftDownLeft.get() == false) {
            leftMotor.set(0.0);
            MessageLogger.LogMessage("LiftDown - stopping left motor.");
        } else {
            leftMotor.set(-liftSpeed * RobotMap.liftMotorLeftSpeedAdjustment);
            MessageLogger.LogMessage("LiftDown - left motor down.");
        }
        if (liftDownRight.get() == false) {
            rightMotor.set(0.0);
            MessageLogger.LogMessage("LiftDown - stopping right motor.");
        } else {
            rightMotor.set(-liftSpeed * RobotMap.liftMotorRightSpeedAdjustment);
            MessageLogger.LogMessage("LiftDown - right motor down.");
        }        
    }
    public void liftDown() {
        liftDown(RobotMap.liftDownSpeed);
    }

    public boolean isLiftUpAtLimit() {
        if ((liftUpLeft.get() == true) && (liftUpRight.get() == true)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isLiftDownAtLimit() {
        if ((liftDownLeft.get() == false) && (liftDownRight.get() == false)) {
            return true;
        } else {
            return false;
        }
    }

    public void stopMotors() {
        leftMotor.set(0.0);
        rightMotor.set(0.0);
    }

    public void updateStatus() {
        String status = "Lft: ";
        status += MathLib.round(leftMotor.get(), 2) + " ";
        status += MathLib.round(rightMotor.get(), 2);

        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser5, 1, "                     ");
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser5, 1, status);

        status = "Lft Sw: ";
        status += (liftUpLeft.get() ? 1 : 0) + " ";
        status += (liftUpRight.get() ? 1 : 0) + " ";
        status += (liftDownLeft.get() ? 1 : 0) + " ";
        status += (liftDownRight.get() ? 1 : 0);

        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser6, 1, "                     ");
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser6, 1, status);
    }

    public boolean isLiftLeftAtLimit(boolean up) {
        if (up) {
            if (liftUpLeft.get() == true) {
                return false;
            } else {
                return true;
            }
        } else {
            if (liftDownLeft.get() == false) {
                return true;
            } else {
                return false;
            }
        }

    }

    public boolean isLiftRightAtLimit(boolean up) {
        if (up) {
            if (liftUpRight.get() == true) {
                return false;
            } else {
                return true;
            }
        } else {
            if (liftDownRight.get() == false) {
                return true;
            } else {
                return false;
            }
        }

    }
}
