/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import Team102Lib.MessageLogger;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 * @author Admin
 */
public class AutonomousTimed extends CommandGroup {
    
    public AutonomousTimed(boolean autoRight, double driveTime, boolean autoDelay) {
        
        MessageLogger.LogError("AutonomousTimed - autoRight: " + autoRight + "\tdriveTime: " + driveTime);
        
        double leftFactor, rightFactor;
        if(autoRight)
        {
            leftFactor = 1.0;
            rightFactor = 0.98;
        }
        else
        {
            leftFactor = 0.98;
            rightFactor = 1.0;
        }
        if(autoDelay)
        {
            addSequential(new WaitCommand(2.0));
        }
        
        addSequential(new AutonomousLoadBall());
        addSequential(new TankDrive(1.0 * leftFactor, -1.0 * rightFactor, driveTime));
//        addSequential(new TankDrive(0.5 * leftFactor, -0.5 * rightFactor, 0.5)); 
        addSequential(new AutonomousShoot());
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.
        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
