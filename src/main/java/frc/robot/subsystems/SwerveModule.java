package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Math.Func;
import frc.robot.Math.Vector2d;
import frc.robot.Utils.EverKit.EverAbsEncoder;
import frc.robot.Utils.EverKit.EverEncoder;
import frc.robot.Utils.EverKit.EverMotorController;
import frc.robot.Utils.EverKit.EverPIDController;
import frc.robot.Utils.EverKit.EverPIDController.ControlType;

public class SwerveModule extends SubsystemBase{
    
    private EverMotorController m_driveMotor;
    private EverPIDController m_driveEverPID;
    private EverEncoder m_velocityEncoder;

    private EverMotorController m_steerMotor;
    private EverPIDController m_steerPID;
    private EverEncoder m_angleEncoder;

    private EverAbsEncoder m_absEncoder;
    
    public SwerveModule(EverMotorController driveMotor, EverPIDController driveEverPID, EverEncoder velocityEncoder, EverMotorController steerMotor, EverPIDController steerPID, EverEncoder angleEncoder, EverAbsEncoder absEncoder) {
        m_driveMotor = driveMotor;
        m_driveEverPID = driveEverPID;
        m_velocityEncoder = velocityEncoder;

        m_steerMotor = steerMotor;
        m_steerPID = steerPID;
        m_angleEncoder = angleEncoder;

        m_absEncoder = absEncoder;
        angleEncoder.setPos(m_absEncoder.getAbsPos());
    }

    public void set(Vector2d desireState) {
        double currentAng = m_angleEncoder.getPos();
        double desiredAng = desireState.theta();
        
        double normalDeltaAngle = Func.shortestPath(currentAng, desiredAng);
        double flippedDeltaAngle = Func.shortestPath(currentAng, Func.modulo(desiredAng + 180, 360));

        if (flippedDeltaAngle < normalDeltaAngle)
        {
            m_driveMotor.set(-m_driveMotor.get());
        }
        double deltaAngle = Math.min(Math.abs(normalDeltaAngle), Math.abs(flippedDeltaAngle));

        m_steerPID.activate(currentAng + deltaAngle, ControlType.kPos);

        double currentSpeed = m_driveMotor.get();

        m_driveEverPID.activate(currentSpeed * Math.cos(deltaAngle), ControlType.kVel);
    }


    
        
    

}
