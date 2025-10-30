package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Math.Vector2d;
import frc.robot.Utils.EverKit.EverAbsEncoder;
import frc.robot.Utils.EverKit.EverEncoder;
import frc.robot.Utils.EverKit.EverMotorController;
import frc.robot.Utils.EverKit.EverPIDController;
import frc.robot.Utils.EverKit.Periodic;

public class SwerveModule extends SubsystemBase implements Periodic{
    
    private EverMotorController m_driveMotor;
    private EverPIDController m_driEverPID;
    private EverEncoder m_velocityEncoder;

    private EverMotorController m_steerMotor;
    private EverPIDController m_steerPID;
    private EverEncoder m_angleEncoder;

    private EverAbsEncoder m_absEncoder;
    
    private void set(Vector2d desireState) {
        
    }

    private double projectVectors(Vector2d currentDrive, double targetAngle) {
        Vector2d targetAngleVector = new Vector2d(currentDrive.mag(), targetAngle);
        return currentDrive.dot(targetAngleVector);
    }

    
        
    

}
