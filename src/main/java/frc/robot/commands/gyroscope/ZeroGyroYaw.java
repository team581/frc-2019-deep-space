/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.gyroscope;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ZeroGyroYaw extends InstantCommand {

  /**
   * Sets the gyroscopes yaw to 0.
   */
  public ZeroGyroYaw() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.gyroSubsystem.ahrs.zeroYaw();
  }
}
