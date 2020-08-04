package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.drivetrain.Drivetrain;


public class Distance extends CommandBase {

    private final Drivetrain drivetrain;
    private double setpoint;

    private double powerL;

    private double powerR;




    public Distance(Drivetrain drivetrain , double setPoint){
        this.drivetrain = drivetrain;
        this.setpoint = setPoint;

        this.powerL = 0;
        this.powerR = 0;

    }

    private double getError(boolean side){
        if (side)//right
            return setpoint - drivetrain.getLeftPos();
        else //left
            return setpoint - drivetrain.getRightPos();
    }

    private double getIntegral(boolean side){
        if (side)//right
            return drivetrain.getRightPos()*Constants.Drivetrain.time;
        else //left
            return drivetrain.getLeftPos()*Constants.Drivetrain.time;
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

        powerL = getError(false) * Constants.Drivetrain.KP +  getIntegral(false)* Constants.Drivetrain.KI + drivetrain.getLeftVel()* Constants.Drivetrain.KD;//calc optimal power (left)

        powerR = getError(true) * Constants.Drivetrain.KP + getIntegral(true)* Constants.Drivetrain.KI + drivetrain.getRightVel()* Constants.Drivetrain.KD;// calc optimal power (right)

        drivetrain.setPowerL(powerL);//update power (left)
        drivetrain.setPowerR(powerR);//update power (right)
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.setPowerL(0);//stop left engine
        drivetrain.setPowerR(0);//stop right engine
    }

    @Override

    public boolean isFinished() {
        return (-0.1< getError(true)  && getError(true) < 0.1) && (-0.1<getError(false) || getError(false)< 0.1);//robot not in setpoint

    }
}

