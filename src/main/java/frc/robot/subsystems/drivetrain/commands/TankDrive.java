package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class TankDrive extends CommandBase {
    private final Drivetrain drivetrain;
    private double rightP;
    private double leftP;

    public TankDrive(Drivetrain drivetrain , double rightP, double leftP) {
        this.drivetrain = drivetrain;
        this.leftP = leftP;
        this.rightP = rightP;

    }

    @Override
    public void initialize() {
        drivetrain.setPowerL(leftP);
        drivetrain.setPowerR(rightP);
    }

    @Override
    public void execute() {

    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.setPowerL(0);
        drivetrain.setPowerR(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
