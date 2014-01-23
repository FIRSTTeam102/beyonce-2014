/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author Admin
 */
public class RunMotor extends CommandBase {

    int index;
    float speed;
    double initialTime;

    public RunMotor(int index, float speed) {
        this.index = index;
        this.speed = speed;
        requires(motors);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        initialTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        motors.runMotor(index, speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
