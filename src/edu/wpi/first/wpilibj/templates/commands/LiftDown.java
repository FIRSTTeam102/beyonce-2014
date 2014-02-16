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
public class LiftDown extends CommandBase {
    
    public LiftDown() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        MessageLogger.LogMessage("Lift down command began");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        lift.liftDown();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return lift.isLiftDownAtLimit();
    }

    // Called once after isFinished returns true
    protected void end() {
        lift.stopMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
                MessageLogger.LogMessage("Lift down command interrupted");
        end();
    }
}

