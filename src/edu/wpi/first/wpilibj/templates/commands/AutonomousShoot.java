/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author Admin
 */
public class AutonomousShoot extends CommandGroup {

    public AutonomousShoot() {
        addSequential(new LiftUp(false));
        addSequential(new WaitCommand(1.5));
        addSequential(new MoveConveyorAtSpeed(1.0, -1.0, 1.5)); //if high goal, may need to get up to speed
        addSequential(new LiftDown());

//        addParallel(new MoveConveyorAtSpeed(0.75, -0.75, 1.5)); //if high goal, may need to get up to speed
//        addSequential(new LiftUp(false));
//        addSequential(new WaitCommand(0.5));
//        addSequential(new LiftDown());

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
