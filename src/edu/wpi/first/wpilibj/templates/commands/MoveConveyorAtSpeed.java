/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import Team102Lib.MessageLogger;
import edu.wpi.first.wpilibj.templates.subsystems.Conveyor;

/**
 *
 * @author Admin
 */
public class MoveConveyorAtSpeed extends CommandBase {
   double frontMotorSpeed;
   double rearMotorSpeed;

    public MoveConveyorAtSpeed(   double frontMotorSpeed, double rearMotorSpeed, double timeout) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(conveyor);
        this.frontMotorSpeed = frontMotorSpeed;
        this.rearMotorSpeed = rearMotorSpeed;
        this.setTimeout(timeout);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
                MessageLogger.LogMessage("Move conveyor at speed initialize");

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

        conveyor.moveConveyor(frontMotorSpeed, rearMotorSpeed);
        MessageLogger.LogMessage("Shooting Conveyor");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
        conveyor.frontMotor.set(0.0);
        conveyor.rearMotor.set(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
                MessageLogger.LogMessage("Move Coveyor at speed command interrupted");

        end();
    }
}
