package frc.robot.subsystems;

import java.text.Format;

import org.littletonrobotics.junction.Logger;

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
        driveVector = leftStickVector.copy();
        driveVector.mul(swerveConst.MAX_SPEED);
        driveVector.rotate(Math.toRadians(gyro.getYaw()) * swerveConst.GYRO_DIRECTION); // gyro axis is supposed to be right
        Logger.recordOutput("Swerve/gyro_yaw", gyro.getYaw());
        Logger.recordOutput("Swerve/left_stick_vector_X", leftStickVector.x);
        Logger.recordOutput("Swerve/left_stick_vector_Y", leftStickVector.y);
        Logger.recordOutput("Swerve/drive_vector", driveVector.toString());
        Logger.recordOutput("Swerve/drive_vector_mag", driveVector.mag());


        // update rotation vectors
        for (Vector2d rotationVector : swerveConst.MODULES_POSITIONS) {
            rotationVector.normalise();
            rotationVector.mul(rightStickVectorVel * convertToMPerSec(swerveConst.MAX_ANGULAR_SPEED));
        }

        // apply vector to module
        for (int i = 0; i < swerveModules.length; i++) {
            swerveModules[i].set(driveVector.copy().add(swerveConst.MODULES_POSITIONS[i]));
        }
        
        Vector2d tempDriveVector = driveVector.copy();
        tempDriveVector.add(swerveConst.MODULES_POSITIONS[0]);
        Logger.recordOutput("Swerve/desired_state_" + 0, tempDriveVector.x);
        //Logger.recordOutput("Swerve/driveVector_" + 0, driveVector.toString());
        Logger.recordOutput("Swerve/module_position_" + 0, swerveConst.MODULES_POSITIONS[0].toString());

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
