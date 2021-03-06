package frc.robot.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import frc.robot.Ports;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.UnitModel;

public class Drivetrain extends SubsystemBase {
    public static final double HIGH_TICKS_PER_METER = 2048. * (2000 / 216.) / (6 * 0.0254 * Math.PI);
    public TalonFX mainR = new TalonFX(Ports.DriveTrain.mainR);//connect to main engine (right)
    public TalonFX sideR = new TalonFX(Ports.DriveTrain.sideR);//connect to side engine (right)

    public TalonFX mainL = new TalonFX(Ports.DriveTrain.mainL);//connect to main engine (left)
    public TalonFX sideL = new TalonFX(Ports.DriveTrain.sideL);//connect to side engine (left)

    UnitModel unitModel = new UnitModel(HIGH_TICKS_PER_METER);
    public Drivetrain() {
        sideR.follow(mainR);//set sideR a side engine
        mainR.setInverted(Ports.DriveTrain.invertedR);//set mainR as inverted
        sideL.follow(mainL);//set sideL a side engine
        mainL.setInverted(Ports.DriveTrain.invertedL);//set mainL as inverted
        mainL.setSelectedSensorPosition(0);//reset right encoder
        mainR.setSelectedSensorPosition(0);//reset left encoder

    }

    /**
     * @param power (%)
     * sets the right engine's power
     */
    public void setPowerR(double power) {
        mainR.set(ControlMode.PercentOutput, power);
    }

    /**
     * @param power (%)
     * sets the left engine's power
     */
    public void setPowerL(double power) {
        mainL.set(ControlMode.PercentOutput, power);
    }

    /**
     * @param ---
     * @return right wheel's position.
     */
    public double getRightPos(){
        return unitModel.toUnits(mainR.getSelectedSensorPosition());
    }

    /**
     * @param --
     * @return left wheel's position.
     */
    public double getLeftPos(){
        return unitModel.toUnits(mainL.getSelectedSensorPosition());
    }

    /**
     * @param --
     * @return left wheel's Velocity.
     */
    public double getLeftVel(){

        return unitModel.toVelocity(mainL.getSelectedSensorVelocity());
    }

    /**
     * @param --
     * @return right wheel's Velocity.
     */
    public double getRightVel(){

        return unitModel.toVelocity(mainR.getSelectedSensorVelocity());
    }
    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}