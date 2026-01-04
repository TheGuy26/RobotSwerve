package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Math.Vector2d;
import frc.robot.subsystems.Swerve;

public class drive extends Command {
    
    Swerve swerve;

    private final double STICKDRIFT = 0.2;
    Supplier<Double> m_leftXInput;
    Supplier<Double> m_leftYInput;
    Supplier<Double> m_rightVelocity;
    
    public drive(Supplier<Double> leftXInput, Supplier<Double> leftYInput, Supplier<Double> rightVelocity)
    {
        swerve = Swerve.getInstance();
        addRequirements(swerve);
        m_leftXInput = leftXInput;
        m_leftYInput = leftYInput;
        m_rightVelocity = rightVelocity;
    }

    @Override
    public void execute() {
        double leftStickXInput = m_leftXInput.get();
        double leftStickYInput = m_leftYInput.get();
        double angularVelocity = m_rightVelocity.get();

        if (Math.abs(leftStickXInput) < STICKDRIFT) {
            leftStickXInput = 0;
        }
        if (Math.abs(leftStickYInput) < STICKDRIFT) {
            leftStickYInput = 0;
        }
        if (Math.abs(angularVelocity) < STICKDRIFT) {
            angularVelocity = 0;
        }

        Vector2d leftStickVec = new Vector2d(leftStickXInput, -leftStickYInput); // modiffied to field axis 
        swerve.drive(leftStickVec, angularVelocity);
    }

    
}
