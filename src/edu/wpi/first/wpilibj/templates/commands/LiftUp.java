/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import Team102Lib.MessageLogger;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Admin
 */
public class LiftUp extends CommandBase {

    boolean shouldCheckConveyor = true;
    double startTime;

    public LiftUp() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(lift);
        this.setTimeout(1.2);
    }

    public LiftUp(boolean shouldCheckConveyor) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(lift);
        this.shouldCheckConveyor = shouldCheckConveyor;
        this.setTimeout(1.2);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        // This prevents the LiftDown command from interrupting the LiftUp command before it is complete.
        this.setInterruptible(false);
        startTime = Timer.getFPGATimestamp();
        MessageLogger.LogMessage("Lift up command began");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (!shouldCheckConveyor || (conveyor.isConveyorRunning())) {
            // This is here to slow the motors 
            if ((Timer.getFPGATimestamp() - startTime) > 0.8) {
                lift.liftUp(0.2);
            } else {
                lift.liftUp();
            }
        } else {
            MessageLogger.LogMessage("Lift up command cancelled because conveyor is not running.");
        }

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        boolean finished = false;
        if (lift.isLiftUpAtLimit()) {
            MessageLogger.LogMessage("Lift up command at limit switch.");
            finished = true;
        } else if (shouldCheckConveyor && (!conveyor.isConveyorRunning())) {
            MessageLogger.LogMessage("Lift up command finished because conveyor is not running.");
            finished = true;
        } else if (this.isTimedOut()) {
            MessageLogger.LogMessage("Lift up command timed out.");
            finished = true;
        }
        return finished;
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
