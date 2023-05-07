package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PneumaticsSubsystem;

public class PneumaticsMovingCommand extends CommandBase {

    PneumaticsSubsystem pneumatics;

    public PneumaticsMovingCommand(PneumaticsSubsystem pneumatics) {
        this.pneumatics = pneumatics;
        addRequirements(pneumatics);
    }
    
    public void initialize() {

    }
    
}
