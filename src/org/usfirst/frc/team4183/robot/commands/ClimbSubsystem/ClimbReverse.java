package org.usfirst.frc.team4183.robot.commands.ClimbSubsystem;

import org.usfirst.frc.team4183.robot.LightingControl;
import org.usfirst.frc.team4183.robot.Robot;
import org.usfirst.frc.team4183.robot.LightingControl.LightingObjects;
import org.usfirst.frc.team4183.utils.CommandUtils;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimbReverse extends Command {

    public ClimbReverse() {
    	requires(Robot.climbSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.lightingControl.set(LightingObjects.CLIMB_SUBSYSTEM, 
					              LightingControl.FUNCTION_REVERSE, 
					              LightingControl.COLOR_ORANGE,
					              3,
					              100,
					              300);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.climbSubsystem.onReverse();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if ( (timeSinceInitialized() > 0.200) && 
    		( (Robot.climbSubsystem.isPastCurrentLimit()) || Robot.climbSubsystem.bumperSwitch() )
    	) {
    		return CommandUtils.stateChange( this, new ClimbFinish() ); 
    	}
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
