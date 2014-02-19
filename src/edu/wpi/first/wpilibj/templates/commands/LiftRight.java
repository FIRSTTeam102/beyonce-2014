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
public class LiftRight extends CommandBase {

    boolean up;

    public LiftRight(boolean up) {
        // Use requires() here to declare subsystem dependencies
        requires(lift);
        this.up = up;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
                MessageLogger.LogMessage("Lift right command began");

        if (up && lift.isLiftRightAtLimit(up)) {
            lift.stopMotors();
        } else {
            lift.liftRight(up);
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (up && lift.isLiftRightAtLimit(up)) {
            MessageLogger.LogMessage("LiftRight reached up limit switch");
            return true;
        } else {
            return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
        lift.stopMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        MessageLogger.LogMessage("LiftRight interrupted");
        end();
    }
}
