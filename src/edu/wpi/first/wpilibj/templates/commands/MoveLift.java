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
public class MoveLift extends CommandBase {

    double speed;

    public MoveLift(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(lift);
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        MessageLogger.LogMessage("MoveLift command began");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        lift.lift(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        boolean done = false;
        if (speed > 0.0) {
            if (lift.isLiftUpAtLimit()) {
                done = true;
                MessageLogger.LogMessage("Move Lift up command at limit switch.");
            }
        } else if (speed < 0.0) {
            if (lift.isLiftDownAtLimit()) {
                done = true;
                MessageLogger.LogMessage("Move Lift down command at limit switch.");
            }
        }
        return done;
    }

// Called once after isFinished returns true
    protected void end() {
        lift.stopMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
        MessageLogger.LogMessage("Move Lift command interrupted.");
    }
}
