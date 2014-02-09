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
public class LiftUp extends CommandBase {

    public LiftUp() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(lift);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
        // This prevents the LiftDown command from interrupting the LiftUp command before it is complete.
        this.setInterruptible(false);
        MessageLogger.LogMessage("Lift up command began");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (conveyor.isConveyorRunning()) {
            lift.liftUp();
        } else {
            MessageLogger.LogMessage("Lift up command cancelled because conveyor is not running.");
        }

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (lift.isLiftUpAtLimit()) {
            MessageLogger.LogMessage("Lift up command at limit switch.");
        }
        if ((!conveyor.isConveyorRunning())) {
            MessageLogger.LogMessage("Lift up command finished because conveyor is not running.");
        }

        return (lift.isLiftUpAtLimit() || (!conveyor.isConveyorRunning()));
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        MessageLogger.LogMessage("Lift up command interrupted.");
    }
}
