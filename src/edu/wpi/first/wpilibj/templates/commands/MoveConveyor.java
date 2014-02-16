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
public class MoveConveyor extends CommandBase {
    
    public MoveConveyor(double timeOut) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(conveyor);
        this.setTimeout(timeOut);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        conveyor.setConstantSpeedValue(oi.getOperatorXBox());        
                MessageLogger.LogMessage("Move Conveyor command began");

    }
    
    

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        conveyor.moveConveyorAtConstantSpeed();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
        conveyor.leaveConstantSpeedMode();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
                MessageLogger.LogMessage("Move Conveyor command interrupted");
        end();
    }
}
