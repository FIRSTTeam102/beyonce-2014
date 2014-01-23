/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;


/**
 *
 * @author Admin
 */
public class Motors extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    

        SpeedController[] speedcontrollers;
    
      
    public Motors(){
        speedcontrollers = new SpeedController[6];
        speedcontrollers[0] = new Victor(1);
        speedcontrollers[1] = new Talon(2);
        speedcontrollers[2] = new Victor(3);
        speedcontrollers[3] = new Jaguar(4);
        speedcontrollers[4] = new Victor(5);
        speedcontrollers[5] = new Victor(6);
    }
    
    

    public void initDefaultCommand() {
        

    }
   
    
    public void runMotor(int index, float speed){
        speedcontrollers[index].set(speed);
    }
}
