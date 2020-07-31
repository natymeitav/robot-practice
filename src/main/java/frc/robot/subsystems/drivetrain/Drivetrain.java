package frc.robot.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import frc.robot.Ports;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
    public TalonFX mainR = new TalonFX(Ports.DriveTrain.mainR);
    public TalonFX sideR = new TalonFX(Ports.DriveTrain.sideR);

    public TalonFX mainL = new TalonFX(Ports.DriveTrain.mainL);
    public TalonFX sideL = new TalonFX(Ports.DriveTrain.sideL);

    public Drivetrain() {
        sideR.follow(mainR);
        mainR.setInverted(Ports.DriveTrain.invertedR);
        sideL.follow(mainL);
        mainL.setInverted(Ports.DriveTrain.invertedL);
    }


    public void setPowerR(double power) {
        mainR.set(ControlMode.PercentOutput, power);
    }

    public void setPowerL(double power) {
        mainL.set(ControlMode.PercentOutput, power);
    }

    public double getRightPos(){
        /*
        * param: ---
        * return: right wheel's position.
        */
        return unitModel.toUnits(mainR.getSelectedSensorPosition());
    }


    public double getLeftPos(){
        /*
         * param: --
         * return: left wheel's position.
         */
        return unitModel.toUnits(mainL.getSelectedSensorPosition());
    }


    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}