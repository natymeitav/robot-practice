package frc.robot.subsystems.Intake.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake.Intake;


public class Chop extends CommandBase{
    private final Intake.state state;
    private final Intake intake;

    public Chop(Intake intake, Intake.state state){
        this.intake = intake;
        this.state = state;
        addRequirements(intake);
    }

    @Override
    public void initialize(){
        intake.movePiston(state);
    }

    @Override
    public void execute() {
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
