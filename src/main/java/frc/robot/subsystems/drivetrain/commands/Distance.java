package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.Constants.PID;

public class Distance extends CommandBase {

    private final Drivetrain drivetrain;
    private double setpoint;

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
        this.setpoint = setPoint;

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
        //start in low speed
        drivetrain.setPowerL(0.3);
        drivetrain.setPowerR(0.3);
    }

    //the actual PID
    @Override
    public void execute() {

        integL += drivetrain.getLeftPos()*0.02;// update integral (left)
        velL = drivetrain.getLeftVel();//update velocity (left)
        powerL = errorL * PID.Kp + integL*PID.Ki + velL* PID.Kd;//calc optimal power (left)

        integR += drivetrain.getRightPos()*0.02;// update integral (right)
        velR = drivetrain.getRightVel();//update velocity (right)
        powerR = errorR * PID.Kp + integR*PID.Ki + velR* PID.Kd;// calc optimal power (right)

        drivetrain.setPowerL(powerL);//update power (left)
        drivetrain.setPowerR(powerR);//update power (right)

        distanceL = drivetrain.getLeftPos();///update distance (left)
        errorL = setpoint - distanceL;//update error (left)

        distanceR = drivetrain.getLeftPos();//update distance (right)
        errorR = setpoint - distanceR;//update error (right)
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.setPowerL(0);//stop left engine
        drivetrain.setPowerR(0);//stop right engine
    }

    @Override

    public boolean isFinished() {
        return (-10>errorR || errorR> 10) || (-10>errorL || errorL> 10);//robot not in setpoint
    }
}

