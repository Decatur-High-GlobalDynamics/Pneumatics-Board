package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;

public class PneumaticsSubsystem extends SubsystemBase {
    
    public DoubleSolenoid solenoid1;
    public DoubleSolenoid solenoid2;
    public Compressor mainCompressor;
    public boolean isRunning;

    public PneumaticsSubsystem() {
        mainCompressor = new Compressor(Ports.PNEUMATICS_HUB, PneumaticsModuleType.REVPH);
        
        solenoid1 = new DoubleSolenoid(Ports.PNEUMATICS_HUB, PneumaticsModuleType.REVPH, Ports.SOLENOID1_EXTEND, Ports.SOLENOID1_RETRACT);
        solenoid2 = new DoubleSolenoid(Ports.PNEUMATICS_HUB, PneumaticsModuleType.REVPH, Ports.SOLENOID2_EXTEND, Ports.SOLENOID2_RETRACT);

        solenoid1.set(Value.kOff);
        solenoid2.set(Value.kOff);

        mainCompressor.enableDigital();
    }

    public void periodic() {
        if (isRunning) {
            
        }
    }
}
