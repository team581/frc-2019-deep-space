/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.OperatorInput;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.structures.Config;
import frc.robot.structures.DriveJoystick;
import frc.robot.structures.JoystickPorts;
import frc.robot.structures.Logger;

/**
 * Driving subsystem that controls motors.
 */
public class Drive extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public MecanumDrive robotDrive;

  private final Logger logger = new Logger();

  // Motor controllers
  public final WPI_TalonSRX talonRearRight = new WPI_TalonSRX(RobotMap.talonRearRight);
  public final WPI_TalonSRX talonRearLeft = new WPI_TalonSRX(RobotMap.talonRearLeft);
  public final WPI_TalonSRX talonFrontRight = new WPI_TalonSRX(RobotMap.talonFrontRight);
  public final WPI_VictorSPX victorFrontLeft = new WPI_VictorSPX(RobotMap.victorFrontLeft);

  /**
   * Set up motors and robot drive.
   */
  public Drive() {
    victorFrontLeft.set(ControlMode.PercentOutput, 0);
    talonFrontRight.set(ControlMode.PercentOutput, 0);
    talonRearLeft.set(ControlMode.PercentOutput, 0);
    talonRearRight.set(ControlMode.PercentOutput, 0);

    // Invert all the motors
    victorFrontLeft.setInverted(true);
    talonRearLeft.setInverted(true);
    victorFrontLeft.setInverted(true);
    talonFrontRight.setInverted(true);

    robotDrive = new MecanumDrive(victorFrontLeft, talonRearLeft, talonFrontRight, talonRearRight);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new ExampleCommand());
  }

  @Override
  public void periodic() {
    double angle = 0;
    boolean gyroDrive = false;

    if (Config.gyroSubsytemEnabled) {
      final Gyro gyro = Robot.gyroSubsytem;
      angle = gyro.ahrs.getAngle();
      gyroDrive = gyro.gyroDrive;
    }

    final DriveJoystick joystick = OperatorInput.driveJoystick;

    final double x = joystick.getScaledAxis(JoystickPorts.rightXAxis);
    final double y = joystick.getScaledAxis(JoystickPorts.rightYAxis);
    final double z = OperatorInput.scale(OperatorInput.triggerTurn());

    logger.logJoystick(joystick);

    robotDrive.driveCartesian(x, y, z, gyroDrive ? angle : 0);

  }

  public void stop() {
    robotDrive.driveCartesian(0, 0, 0);
  }
}
