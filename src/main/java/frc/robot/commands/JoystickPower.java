/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.OperatorInput;

public class JoystickPower extends InstantCommand {
  boolean powerChange;

  /**
   * Toggle the power value of the joystick.
   */
  public JoystickPower(boolean toSlow) {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    toSlow = powerChange;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    OperatorInput.power = powerChange ? 1 : 3;
  }
}
