/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import Team102Lib.MathLib;
import Team102Lib.MessageLogger;

/**
 *
 * @author Admin
 */
public class LiftLeft extends CommandBase {

    boolean up;

    public LiftLeft(boolean up) {
        // Use requires() here to declare subsystem dependencies
        requires(lift);
        this.up = up;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
                MessageLogger.LogMessage("Lift left command began");

        if (up && lift.isLiftLeftAtLimit(up)) {
            lift.stopMotors();
        } else {
            lift.liftLeft(up);
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (up && lift.isLiftLeftAtLimit(up)) {
            MessageLogger.LogMessage("LiftLeft reached up limit switch");
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
        MessageLogger.LogMessage("LiftLeft interrupted");
        end();
    }
}
