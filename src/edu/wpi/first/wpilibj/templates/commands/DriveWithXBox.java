/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import Team102Lib.MessageLogger;


/**
 *
 * @author Admin
 */
public class DriveWithXBox extends CommandBase
{
    
    public DriveWithXBox()
    {
        requires(chassis); // reserve the chassis subsystem
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
        MessageLogger.LogMessage("DriveWithXBox Initalized");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
        chassis.driveWithXBox(oi.getDriverXBox());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        return false;
    }

    // Called once after isFinished returns true
    protected void end()
    {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {
         MessageLogger.LogMessage("DriveWithXBox interrupted");
    }
}
