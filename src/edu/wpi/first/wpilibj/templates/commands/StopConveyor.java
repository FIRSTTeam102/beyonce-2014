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
public class StopConveyor extends CommandBase {
    
    public StopConveyor() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(conveyor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
                   MessageLogger.LogMessage("Stop conveyor command initialize");

        conveyor.moveConveyor(0.0, 0.0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        
    }
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
           MessageLogger.LogMessage("Stop conveyor command interrupted");
    }
}
