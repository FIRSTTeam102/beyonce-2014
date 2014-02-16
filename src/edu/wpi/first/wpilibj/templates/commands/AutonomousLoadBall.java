/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author Admin
 */
public class AutonomousLoadBall extends CommandBase {
    
    public AutonomousLoadBall() {
        // Use requires() here to declare subsystem
        requires(lift);
        requires(conveyor);
        this.setTimeout(5.0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        conveyor.moveConveyor(-0.5, 0.5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return   !conveyor.loadLimitSwitch.get() || isTimedOut() ;
    }

    // Called once after isFinished returns true
    protected void end() {
        conveyor.moveConveyor(0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
