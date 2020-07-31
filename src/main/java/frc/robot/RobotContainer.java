/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.Intake.Intake;
import frc.robot.subsystems.Intake.commands.Chop;
import frc.robot.subsystems.Intake.commands.EatCommand;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  public Intake intake = new Intake();
  public Drivetrain driveTrain = new Drivetrain();

  //not sure these are the actual ports
  public Joystick rightS = new Joystick(2);
  public Joystick leftS = new Joystick(3);

  public XboxController Xbox = new XboxController(1);
  public JoystickButton a = new JoystickButton(Xbox, XboxController.Button.kA.value);
  public JoystickButton b = new JoystickButton(Xbox, XboxController.Button.kB.value);
  public JoystickButton x = new JoystickButton(Xbox, XboxController.Button.kX.value);
  // The robot's subsystems and commands are defined here...


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

  }

  /**
   * Use this method to define your button->commands mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    a.whileHeld(new EatCommand(intake, 0.5));
    b.whenPressed(new Chop(intake, Intake.state.OPEN));
    
  }


  /**
   * Use this to pass the autonomous commands to the main {@link Robot} class.
   *
   * @return the commands to run in autonomous
   */
  public Command getAutonomousCommand() {

    // An ExampleCommand will run in autonomous
    return null;
  }
}
