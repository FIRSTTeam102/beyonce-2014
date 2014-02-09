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
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.MoveConveyorWithXBox;

/**
 *
 * @author Admin
 */
public class Conveyor extends Subsystem {

    public Talon frontMotor;
    public Talon rearMotor;
    public DigitalInput loadLimitSwitch;

    boolean isConstantSpeedMode;
    double constantSpeedValue;

    public Conveyor() {
        frontMotor = new Talon(RobotMap.frontConveyorMotor);
        rearMotor = new Talon(RobotMap.rearConveyorMotor);
        loadLimitSwitch = new DigitalInput(RobotMap.loadLimitSwitchPort);
        constantSpeedValue = 0.0;
        isConstantSpeedMode = false;

    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new MoveConveyorWithXBox());
    }

    public void moveConveyorWithXBox(Joystick xBox) {

        double triggerValue = xBox.getRawAxis(RobotMap.xBoxTriggerAxis);

        // 29 Jan 2014 MJP: tested that these are correct for the actual conveyor.
        if (triggerValue > 0) {
            if (loadLimitSwitch.get()) {
                triggerValue = triggerValue * RobotMap.percentSpeedToLoadBall;
                frontMotor.set(-triggerValue);
                rearMotor.set(triggerValue);
            } else {
                frontMotor.set(0.0);
                rearMotor.set(0.0);
            }
        } else {
            frontMotor.set(-triggerValue);
            rearMotor.set(triggerValue);
        }

//        MessageLogger.LogMessage(
//                "Joysticks Trigger tv: \t"
//                + MathLib.round(triggerValue, 3) + "\t" + loadLimitSwitch.get());
    }

    public void moveConveyor(double frontMotorSpeed, double rearMotorSpeed) {
        frontMotor.set(frontMotorSpeed);
        rearMotor.set(rearMotorSpeed);
    }

    public void setConstantSpeedValue(Joystick xBox) {

        double triggerValue = xBox.getRawAxis(RobotMap.xBoxTriggerAxis);

        if (triggerValue < 0) {

            isConstantSpeedMode = true;
            constantSpeedValue = triggerValue;

        } else {

            isConstantSpeedMode = false;
            constantSpeedValue = 0.0;
        }
    }

    public void moveConveyorAtConstantSpeed() {
        if (isConstantSpeedMode == false) {
            return;

        } else {
            moveConveyor(-constantSpeedValue, constantSpeedValue);

        }
    }

    public boolean isConveyorRunning()
    {
        return (frontMotor.get() > RobotMap.minConveyorSpeedToShoot);
    }
    public void leaveConstantSpeedMode(){
        isConstantSpeedMode = false;
        constantSpeedValue = 0.0;
        moveConveyor(0.0,0.0);
    }
       public void updateStatus()
    {
        String status = "Cvy: ";
        status += MathLib.round(rearMotor.get(), 2) + " ";
        status += MathLib.round(frontMotor.get(), 2) + " ";
        
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser3, 1, "                     ");
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser3,1, status);

        status = "Cvy Sw: ";
        status += (loadLimitSwitch.get() ? 1 : 0) + " ";
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser4, 1, "                     ");
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser4,1, status);
    }
}
