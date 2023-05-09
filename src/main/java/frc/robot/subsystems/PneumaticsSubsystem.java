package frc.robot.subsystems;

import java.time.LocalDateTime;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;

public class PneumaticsSubsystem extends SubsystemBase {
    
    DoubleSolenoid solenoid1;
    DoubleSolenoid solenoid2;
    Compressor mainCompressor;
    public static boolean isRunning;

    long extensionWaitTime = 100000000;
    long toggleIntervalTime = 2;
    LocalDateTime extensionStartTime;
    LocalDateTime toggleStartTime;

    boolean isExtending;
    boolean isToggledForward;
    boolean extended;
    boolean firstExtension;

    public PneumaticsSubsystem() {
        mainCompressor = new Compressor(Ports.PNEUMATICS_HUB, PneumaticsModuleType.REVPH);
        
        solenoid1 = new DoubleSolenoid(Ports.PNEUMATICS_HUB, PneumaticsModuleType.REVPH, Ports.SOLENOID1_EXTEND, Ports.SOLENOID1_RETRACT);
        solenoid2 = new DoubleSolenoid(Ports.PNEUMATICS_HUB, PneumaticsModuleType.REVPH, Ports.SOLENOID2_EXTEND, Ports.SOLENOID2_RETRACT);

        solenoid1.set(Value.kOff);
        solenoid2.set(Value.kOff);

        mainCompressor.enableDigital();
        firstExtension = true;
    }

    void setSolenoids(Value mode) {
        solenoid1.set(mode);
        solenoid2.set(mode);
    }

    boolean pastToggleTime() {
        return toggleStartTime != null && LocalDateTime.now().minusSeconds(toggleIntervalTime).compareTo(toggleStartTime) == 1;
    }

    public void periodic() {
        if (isRunning) {

        
            if (extensionStartTime == null && extended == false && isExtending == false && firstExtension == true) {
                extensionStartTime = LocalDateTime.now();
                setSolenoids(Value.kForward);
                isExtending = true;
                firstExtension = false;
            }

            if (isExtending == false && extended == false && pastToggleTime()) {
                setSolenoids(Value.kForward);
                isExtending = true;
                extensionStartTime = LocalDateTime.now();
            }

            if (isExtending == false && extended == true && pastToggleTime()) {
                setSolenoids(Value.kReverse);
                isExtending = true;
                extensionStartTime = LocalDateTime.now();
            }
        


            // stop extending(both directions)
            if (extensionStartTime != null && LocalDateTime.now().minusNanos(extensionWaitTime).compareTo(extensionStartTime) > 0 && isExtending == true) {
                setSolenoids(Value.kOff);
                extensionStartTime = null;
                isExtending = false;
                toggleStartTime = LocalDateTime.now();
            }
        }
    }
}
