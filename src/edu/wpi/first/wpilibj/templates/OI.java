package edu.wpi.first.wpilibj.templates;

import Team102Lib.MessageLogger;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.templates.commands.ExampleCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    public static final int JOYSTICK_PORT1 = 1;
    public static final int JOYSTICK_PORT2 = 2;
    public static final int JOYSTICK_PORT3 = 3;
    private Joystick leftstick;
    private Joystick rightstick;
    private Joystick xBox;
    private JoystickButton trigger;
    private JoystickButton leftStickButton8;
    private JoystickButton xBoxA;
    private JoystickButton xBoxB;
    private JoystickButton xBoxY;
    private JoystickButton xBoxX;
    private JoystickButton xBoxRightBumper;
    private JoystickButton xBoxLeftBumper;

    public OI() {
        try {
            xBox = new Joystick(JOYSTICK_PORT1);

            xBoxA = new JoystickButton(xBox, RobotMap.xBoxAIndex);
            xBoxX = new JoystickButton(xBox, RobotMap.xBoxXIndex);
            xBoxB = new JoystickButton(xBox, RobotMap.xBoxBIndex);
            xBoxY = new JoystickButton(xBox, RobotMap.xBoxYIndex);
            xBoxRightBumper = new JoystickButton(xBox, RobotMap.xBoxRightBumperIndex);
            xBoxLeftBumper = new JoystickButton(xBox, RobotMap.xBoxLeftBumperIndex);
        } catch (Exception ex1) {
            MessageLogger.LogError("Unhandled exception in OI constructor.");
            MessageLogger.LogError(ex1.toString());
        }

    }

    public Joystick getXBox() {
        return xBox;
    }
}
