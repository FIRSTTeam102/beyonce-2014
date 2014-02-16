/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import Team102Lib.MessageLogger;
import edu.wpi.first.wpilibj.templates.subsystems.ChassisWithEncoder;

/**
 *
 * @author Admin
 */
public class DriveADistance extends CommandBase {
    
    public DriveADistance(double distanceToTravelInInches) {
        // Use requires() here to declare subsystem dependencies
        requires(chassis);
        chassis.setSetpoint(distanceToTravelInInches);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        chassis.encoder.reset();
        chassis.encoder.start();
        chassis.enable();
        MessageLogger.LogMessage("DriveADistance enabled");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Math.abs(chassis.getSetpoint() - chassis.getPosition()) < 0.5);
    }

    // Called once after isFinished returns true
    protected void end() {
           MessageLogger.LogMessage("DriveADistance end");
        chassis.disable();
        chassis.tankDrive(0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
         MessageLogger.LogMessage("DriveADistance interrupted");
    }
}
