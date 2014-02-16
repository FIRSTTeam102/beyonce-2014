package edu.wpi.first.wpilibj.templates.commands;

import Team102Lib.MessageLogger;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.subsystems.Chassis;
import edu.wpi.first.wpilibj.templates.subsystems.ChassisWithEncoder;
import edu.wpi.first.wpilibj.templates.subsystems.Conveyor;
import edu.wpi.first.wpilibj.templates.subsystems.Lift;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use
 * CommandBase.exampleSubsystem
 *
 * @author Author
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    // Create a single static instance of all of your subsystems
 //   public static Chassis chassis;
    public static ChassisWithEncoder chassis;
    public static Conveyor conveyor;
    public static Lift lift;

    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        try {
//            chassis = new Chassis();
           chassis = new ChassisWithEncoder();
            conveyor = new Conveyor();
            lift = new Lift();
            // ALL SUBSYSTEMS MUST BE CREATED BEFORE THE OI
            oi = new OI();

        } catch (Exception e) {
            MessageLogger.LogError("Unhandled Exception in CommandBase.init()");
            MessageLogger.LogError(e.toString());
            e.printStackTrace();
        }

        // Show what command your subsystem is running on the SmartDashboard
        //SmartDashboard.putData(chassis);
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
