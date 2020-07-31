package frc.robot.subsystems.Intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Ports;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    public TalonSRX talon = new TalonSRX(Ports.Intake.Motor);
    public Solenoid piston = new Solenoid(Ports.Intake.Piston);

    public Intake() {
        talon.setInverted(Ports.Intake.inverted);
    }

    public void setPower(double power){
        talon.set(ControlMode.PercentOutput, power);
    }

    public void movePiston(state direction){
        switch (direction){
            case OPEN:
                piston.set(true);break;
            case CLOSE:
                piston.set(false);break;
            case TOGGLE:
                piston.set(!piston.get());break;
        }
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public enum state{
        OPEN, CLOSE, TOGGLE
    }
}