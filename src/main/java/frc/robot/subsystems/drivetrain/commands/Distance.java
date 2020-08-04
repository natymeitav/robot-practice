package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.Constants.PID;

public class Distance extends CommandBase {

    private final Drivetrain drivetrain;
    private double setPoint;

    private double distanceL;
    private double integL;
    private double errorL;
    private double velL;
    private double powerL;

    private double distanceR;
    private double integR;
    private double errorR;
    private double velR;
    private double powerR;

    public Distance(Drivetrain drivetrain , double setPoint){
        this.drivetrain = drivetrain;
        this.setPoint = setPoint;

        this.distanceL = drivetrain.getLeftPos();
        this.errorL = setPoint - distanceL;
        this.integL = 0;
        this.velL = 0;
        this.powerL = 0;

        this.distanceR = drivetrain.getLeftPos();
        this.errorR = setPoint - distanceL;
        this.integR = 0;
        this.velR = 0;
        this.powerR = 0;

    }

    @Override
    public void initialize() {
        drivetrain.setPowerL(0.3);
        drivetrain.setPowerR(0.3);
    }

    @Override
    public void execute() {
        integL += drivetrain.getLeftPos()*0.02;
        velL = drivetrain.getLeftVel();
        powerL = errorL * PID.Kp + integL*PID.Ki + velL* PID.Kd;

        integR += drivetrain.getRightPos()*0.02;
        velR = drivetrain.getRightVel();
        powerR = errorR * PID.Kp + integR*PID.Ki + velR* PID.Kd;

        drivetrain.setPowerL(powerL);
        drivetrain.setPowerR(powerR);

        distanceL = drivetrain.getLeftPos();
        errorL = setPoint - distanceL;

        distanceR = drivetrain.getLeftPos();
        errorR = setPoint - distanceR;
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.setPowerL(0);
        drivetrain.setPowerR(0);
    }

    @Override
    public boolean isFinished() {
        return (-10<errorR && errorR< 10)  && (-10<errorL && errorL< 10);
    }
}

