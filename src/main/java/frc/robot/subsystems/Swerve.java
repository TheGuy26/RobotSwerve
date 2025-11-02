package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Math.Vector2d;
import frc.robot.Utils.EverKit.EverGyro;

public class Swerve extends SubsystemBase{
    private static Swerve m_swerve;
    
    private EverGyro gyro;

    private Vector2d driveVector;

    private SwerveModule[] swerveModules;

    public Swerve()
    {
        
    }

    public void drive(Vector2d leftStickVector, double rightStickVectorVel)
    {
        // set drive vector
        driveVector = leftStickVector.copy().mul(swerveConst.MAX_SPEED);
        driveVector.rotate(Math.toRadians(gyro.getYaw()) * swerveConst.GYRO_DIRECTION); // gyro axis is false

        // update rotation vectors
        for (Vector2d rotationVector : swerveConst.MODULES_POSITIONS) {
            rotationVector.mul(rightStickVectorVel * convertToMPerSec(swerveConst.MAX_ANGULAR_SPEED));
        }

        // apply vector to module
        for (int i = 0; i < swerveModules.length; i++) {
            swerveModules[i].set(driveVector.copy().add(swerveConst.MODULES_POSITIONS[i]));
        }
    }

    public static void setModuleVectores(Vector2d moduleVectors[]) {
        for (Vector2d moduleVector : moduleVectors) {
            moduleVector.normalise();
            moduleVector.rotate(Math.toRadians(90));
        }
    }

    public static double convertToMPerSec(double degPerSec) {
        double ciclePerSec = degPerSec / 360;
        return ciclePerSec * swerveConst.ROBOT_ROUNDING_CICLE_PERIMETER;
    }
}
