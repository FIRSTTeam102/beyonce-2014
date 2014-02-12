package edu.wpi.first.wpilibj.templates;

import Team102Lib.MessageLogger;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.templates.commands.LiftDown;
import edu.wpi.first.wpilibj.templates.commands.LiftLeft;
import edu.wpi.first.wpilibj.templates.commands.LiftRight;
import edu.wpi.first.wpilibj.templates.commands.LiftUp;
import edu.wpi.first.wpilibj.templates.commands.Shoot;
import edu.wpi.first.wpilibj.templates.commands.MoveConveyorAtSpeed;
import edu.wpi.first.wpilibj.templates.commands.MoveLift;
import edu.wpi.first.wpilibj.templates.commands.SpinConveyor;

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
    private Joystick xBoxDriver;
    private Joystick xBoxOperator;
    private Joystick xBoxTester;
    private JoystickButton trigger;
    private JoystickButton leftStickButton8;
    private JoystickButton xBoxA;
    private JoystickButton xBoxB;
    private JoystickButton xBoxY;
    private JoystickButton xBoxX;
    private JoystickButton xBoxRightBumper;
    private JoystickButton xBoxLeftBumper;
    
  //Tester Buttons
    private JoystickButton xBoxTesterA;
    private JoystickButton xBoxTesterB;
    private JoystickButton xBoxTesterY;
    private JoystickButton xBoxTesterX;
    private JoystickButton xBoxTesterRightBumper;
    private JoystickButton xBoxTesterLeftBumper;
    private boolean twoDriverMode = false;
    
    public OI() {
        try {
            xBoxDriver = new Joystick(JOYSTICK_PORT1);
            xBoxTester = new Joystick(JOYSTICK_PORT3);

            xBoxX = new JoystickButton(xBoxDriver, RobotMap.xBoxXIndex);
            xBoxY = new JoystickButton(xBoxDriver, RobotMap.xBoxYIndex);
            xBoxRightBumper = new JoystickButton(xBoxDriver, RobotMap.xBoxRightBumperIndex);

            DriverStation ds = DriverStation.getInstance();
            // ATTENTION: getAnalog does not work in robotInit()!!  (except in debug mode :()
            twoDriverMode = ds.getDigitalIn(RobotMap.twoDriverModeDI);
            if(twoDriverMode)
            {
                xBoxOperator = new Joystick(JOYSTICK_PORT2);
                xBoxA = new JoystickButton(xBoxOperator, RobotMap.xBoxAIndex);
                xBoxB = new JoystickButton(xBoxOperator, RobotMap.xBoxBIndex);
                xBoxLeftBumper = new JoystickButton(xBoxOperator, RobotMap.xBoxLeftBumperIndex);
            }
            else
            {
                xBoxA = new JoystickButton(xBoxDriver, RobotMap.xBoxAIndex);
                xBoxB = new JoystickButton(xBoxDriver, RobotMap.xBoxBIndex);
                xBoxLeftBumper = new JoystickButton(xBoxDriver, RobotMap.xBoxLeftBumperIndex);
            }
            xBoxLeftBumper.toggleWhenPressed(new Shoot());
            xBoxA.whileHeld(new MoveLift(RobotMap.liftUpSpeed));
            xBoxB.whileHeld(new MoveLift(-RobotMap.liftDownSpeed));
            
            //Tester Controller
            

/*            xBoxTesterA.whileHeld(new LiftRight(true));
            xBoxTesterB.whileHeld(new LiftRight(false));
            xBoxTesterX.whileHeld(new LiftLeft(true));
            xBoxTesterY.whileHeld(new LiftLeft(false));
 */                    
        } catch (Exception ex1) {
            MessageLogger.LogError("Unhandled exception in OI constructor.");
            MessageLogger.LogError(ex1.toString());
        }
    }

    public Joystick getOperatorXBox() {
        if(twoDriverMode)
            return xBoxOperator;
        else
            return xBoxDriver;
    }
    public Joystick getDriverXBox() {
        return xBoxDriver;
    }
}
