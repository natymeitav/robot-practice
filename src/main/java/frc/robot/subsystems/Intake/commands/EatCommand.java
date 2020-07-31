package frc.robot.subsystems.Intake.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake.Intake;

public class EatCommand extends CommandBase {
    private final Intake intake;
    private final double power;

    public EatCommand(Intake intake , double power){
        this.intake = intake;
        this.power = power;
    }

    @Override
    public void initialize() {
        this.intake.setPower(this.power);
    }

    @Override
    public void execute() {

    }

    @Override
    public void end(boolean interrupted) {
        this.intake.setPower(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
