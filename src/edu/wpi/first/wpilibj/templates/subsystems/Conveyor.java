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
import edu.wpi.first.wpilibj.templates.commands.MoveConveyorWithXBox;

/**
 *
 * @author Admin
 */
public class Conveyor extends Subsystem {

   public Jaguar frontMotor;
   public Victor rearMotor;
    //TODO: limit switch, create command moveConveyor()
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

   public Conveyor(){
       frontMotor = new Jaguar(RobotMap.frontConveyorMotor);
       rearMotor = new Victor(RobotMap.rearConveyorMotor);
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
            triggerValue = triggerValue * .50;
        }
        
        frontMotor.set(-triggerValue);
        rearMotor.set(triggerValue);

        MessageLogger.LogMessage("Joysticks Trigger tv: \t"
                + MathLib.round(triggerValue, 3) + "\t");      
    }
    public void moveConveyor(double frontMotorSpeed, double rearMotorSpeed) {
        frontMotor.set(frontMotorSpeed);
        rearMotor.set(rearMotorSpeed);        
    }
}
