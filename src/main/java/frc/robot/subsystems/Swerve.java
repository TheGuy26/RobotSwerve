package frc.robot.subsystems;

import com.studica.frc.AHRS;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Math.Vector2d;

public class Swerve extends SubsystemBase{
    private static Swerve m_swerve = new Swerve();
    
    private AHRS gyro;

    private Vector2d driveVector;

    private SwerveModule[] swerveModules;

    public Swerve()
    {
        swerveConst.config();
        setModuleVectors(swerveConst.MODULES_POSITIONS);
        swerveModules = swerveConst.MODULES;
        gyro = swerveConst.GYRO;
    }

    public static Swerve getInstance() {
        return m_swerve;
    }
    
    public void drive(Vector2d leftStickVector, double rightStickVectorVel)
    {
        // set drive vector
        driveVector = leftStickVector.copy().mul(swerveConst.MAX_SPEED);
        driveVector.rotate(Math.toRadians(gyro.getYaw()) * swerveConst.GYRO_DIRECTION); // gyro axis is supposed to be right

        // update rotation vectors
        for (Vector2d rotationVector : swerveConst.MODULES_POSITIONS) {
            rotationVector.mul(rightStickVectorVel * swerveConst.MAX_ANGULAR_SPEED);
        }

        // apply vector to module
        for (int i = 0; i < swerveModules.length; i++) {
            swerveModules[i].set(driveVector.copy().add(swerveConst.MODULES_POSITIONS[i]));
        }
    }

    public static void setModuleVectors(Vector2d moduleVectors[]) {
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
