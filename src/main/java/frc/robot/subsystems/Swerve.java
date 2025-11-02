package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Math.Vector2d;
import frc.robot.Utils.EverKit.EverGyro;

public class Swerve extends SubsystemBase{
    private EverGyro gyro;


    private Vector2d driveVector;
    private Vector2d steerVector;

    public void drive(Vector2d leftStickVector)
    {
        driveVector = leftStickVector.copy().mul(swerveConst.MAX_SPEED);
        driveVector.rotate(Math.toRadians(gyro.getYaw()) * swerveConst.GYRO_DIRECTION); // gyro axis is false
    }

    public static void setModuleVectores(Vector2d moduleVectors[]) {
        for (Vector2d moduleVector : moduleVectors) {
            moduleVector.normalise();
            moduleVector.rotate(Math.toRadians(90));
        }
    }
}
