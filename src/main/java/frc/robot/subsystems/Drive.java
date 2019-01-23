/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.OperatorInput;
import frc.robot.RobotMap;
import frc.robot.structures.DriveJoystick;
import frc.robot.structures.JoystickPorts;

/**
 * Driving subsystem that controls motors.
 */
public class Drive extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public MecanumDrive robotDrive;

  // Motor controllers
  public final WPI_TalonSRX frontLeft = new WPI_TalonSRX(RobotMap.talonFrontLeft);
  public final WPI_TalonSRX rearLeft = new WPI_TalonSRX(RobotMap.talonRearLeft);
  public final WPI_TalonSRX frontRight = new WPI_TalonSRX(RobotMap.talonFrontRight);
  public final WPI_TalonSRX rearRight = new WPI_TalonSRX(RobotMap.talonRearRight);

  public final AnalogGyro gyro = new AnalogGyro(RobotMap.gyro);

  /**
   * Set up motors and robot drive.
   */
  public Drive() {
    frontLeft.set(ControlMode.PercentOutput, 0);
    rearLeft.set(ControlMode.PercentOutput, 0);
    frontRight.set(ControlMode.PercentOutput, 0);
    rearRight.set(ControlMode.PercentOutput, 0);

    // Invert all the motors
    frontLeft.setInverted(true);
    rearLeft.setInverted(true);
    frontRight.setInverted(true);
    rearRight.setInverted(true);

    robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  public void periodic() {
    final OperatorInput oi = new OperatorInput();
    final DriveJoystick joystick = OperatorInput.driveJoystick;

    final double x = joystick.getScaledAxis(JoystickPorts.rightXAxis);
    final double y = joystick.getScaledAxis(JoystickPorts.rightYAxis);
    final double z = OperatorInput.scale(OperatorInput.triggerTurn());

    oi.logJoystick(joystick);

    robotDrive.driveCartesian(x, y, z, gyro.getAngle());
  }
}