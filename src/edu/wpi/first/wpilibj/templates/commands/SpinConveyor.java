/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import Team102Lib.MessageLogger;

/**
 *
 * @author Admin
 */
public class SpinConveyor extends CommandBase {
   double frontMotorSpeed;
   double rearMotorSpeed;

    public SpinConveyor(double frontMotorSpeed, double rearMotorSpeed ) {
        // Use requires() here to declare subsystem dependencies
        requires(conveyor);
        this.frontMotorSpeed = frontMotorSpeed;
        this.rearMotorSpeed = rearMotorSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        
        conveyor.moveConveyor(frontMotorSpeed, rearMotorSpeed);
           MessageLogger.LogMessage("Spin conveyor command initialize");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
                MessageLogger.LogMessage("Spin conveyor command interrupted");

    }
}
