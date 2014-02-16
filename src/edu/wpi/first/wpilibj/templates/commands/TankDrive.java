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
public class TankDrive extends CommandBase {
    double leftSpeed;
    double rightSpeed;

    public TankDrive(double leftSpeed, double rightSpeed, double timeout) {
        requires(chassis);
        this.leftSpeed = leftSpeed;
        this.rightSpeed = rightSpeed;
        this.setTimeout(timeout);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        chassis.tankDrive(leftSpeed, rightSpeed);
                   MessageLogger.LogMessage("Stop conveyor command interrupted");

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
        chassis.tankDrive(0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
                   MessageLogger.LogMessage("Tank Drive command interrupted");

        end();
    }
}
