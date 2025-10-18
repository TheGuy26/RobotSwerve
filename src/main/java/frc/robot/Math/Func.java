package frc.robot.Math;

public class Func {
    
    public static double modulo(double a, double b) {
        return ((a % b) + b) % b;
    }    
    
    private static double shortestPath(double a, double b) {
        double deltaAngle = modulo(a, 360) - modulo(b, 360);
        if (Math.abs(deltaAngle) > 180) {
            deltaAngle = -1 * Math.signum(deltaAngle) * 360 + deltaAngle;
        }
        return deltaAngle;
    }

    

    
}
