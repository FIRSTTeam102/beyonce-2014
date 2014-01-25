/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import Team102Lib.MathLib;
import Team102Lib.MessageLogger;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.MoveConveyor;

/**
 *
 * @author Admin
 */
public class Conveyor extends Subsystem {

    Jaguar frontMotor = new Jaguar(RobotMap.frontConveyorMotor);
    Victor rearMotor = new Victor(RobotMap.rearConveyorMotor);
    //TODO: limit switch, create command moveConveyor()
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new MoveConveyor());
    }

    public void moveConveyorWithXBox(Joystick xBox) {

        double triggerValue = xBox.getRawAxis(RobotMap.xBoxTriggerAxis);
        
        if (triggerValue > 0) {
            triggerValue = triggerValue * .25;
        }
        
        frontMotor.set(triggerValue);
        rearMotor.set(-triggerValue);

        MessageLogger.LogMessage("Joysticks Trigger tv: \t"
                + MathLib.round(triggerValue, 3) + "\t");

        
    }
}
