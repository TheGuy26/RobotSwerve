package frc.robot.subsystems;

import frc.robot.Math.Vector2d;

public interface swerveConst {
    public static final double MAX_SPEED = 2;

    // motor gear ratios
    public static final double
                DRIVE_MOTOR_RAIO = 6.75,
                STEER_MOTOR_RATIO = 12.8;
    
    public static final double WHEEL_PERIMETER = Math.PI * 0.09;

    public int GYRO_DIRECTION = -1; // -1 is clockwise

    //chassis dimentions
    public static final double CHASSIS_LENTGH = 0.75, CHASSIS_WIDTH = 0.75;

    public static final Vector2d
                        TR_VEC = new Vector2d(CHASSIS_WIDTH / 2, CHASSIS_LENTGH / 2),
                        TL_VEC = new Vector2d(-CHASSIS_WIDTH / 2, CHASSIS_LENTGH / 2),
                        DR_VEC = new Vector2d(CHASSIS_WIDTH / 2, -CHASSIS_LENTGH / 2),
                        DL_VEC = new Vector2d(-CHASSIS_WIDTH / 2, -CHASSIS_LENTGH / 2);
    
    public static final Vector2d[] SWERVE_MODULES = { TR_VEC, TL_VEC, DR_VEC, DL_VEC };
}
