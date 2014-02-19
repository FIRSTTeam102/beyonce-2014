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
public class LiftSlowStartup extends CommandBase {

    double liftSpeed;

    public LiftSlowStartup() {

        requires(lift);
        // eg. requires(chassis);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
        MessageLogger.LogMessage("Lift Slow Start initialized.");
        liftSpeed = 0.1;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

        lift.liftUp(liftSpeed);
        liftSpeed += 0.05;
        liftSpeed = Math.min(liftSpeed, 1.0);
        

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (lift.isLiftUpAtLimit()) {
            MessageLogger.LogMessage("Lift up command at limit switch.");
        }
//        if ((!conveyor.isConveyorRunning())) {
//            MessageLogger.LogMessage("Lift up command finished because conveyor is not running.");
//        }

        return (lift.isLiftUpAtLimit());    // || (!conveyor.isConveyorRunning())

    }

    // Called once after isFinished returns true
    protected void end() {
        lift.stopMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        MessageLogger.LogMessage("Slow Start interrupted.");
        end();
    }
}
