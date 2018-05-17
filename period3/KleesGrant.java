package period3;
import robocode.*;
import robocode.util.*;
//import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * KleesGrant - a robot by (your name here)
 */
public class KleesGrant extends Robot
{
	double tOldHeading;
	/**
	 * run: KleesGrant's default behavior
	 */
	public void run() {
		// Initialization of the robot should be put here
		//setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		// Robot main loop
		while(true) {
			turnRadarRight(40);
			// Replace the next 4 lines with any behavior you would like
			//ahead(100);
			//turnGunRight(360);
			//back(100);
			//turnGunRight(360);
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		double myX = getX();
		double myY = getY();
		double myHeading = getHeading();
		double enemyHeading = getHeading();
		double absBearing = Math.toRadians(enemyHeading) + Math.toRadians(myHeading);
		double enemyX = myX + e.getDistance() * Math.sin(absBearing);
		double enemyY = myY + e.getDistance() * Math.cos(absBearing);
		double newEnemyX = enemyX + e.getVelocity() * Math.sin(enemyHeading);
		double newEnemyY = enemyY + e.getVelocity() * Math.cos(enemyHeading);
		tOldHeading = enemyHeading;
		System.out.println("enemyY: " + enemyY + "\nenemyX: " + enemyX);
		System.out.println("newEnemyY: " + newEnemyY + "\nnewEnemyX: " + newEnemyX + "\nmyY: " + myY + "\nmyX: " + myX);
		System.out.println("Enemy Relative Y Position: " + (newEnemyY - myY) + "Enemy Relative X Position: " + (newEnemyX - myX)); 
		double theta = Utils.normalAbsoluteAngleDegrees(Math.toDegrees((Math.atan2(newEnemyX - myX, newEnemyY - myY))));
		System.out.println("atan2: " + (Math.atan2(newEnemyX - myX, newEnemyY - myY)));
		System.out.println("Theta: " + theta);
		System.out.println("Gun heading: " + getGunHeading());
		System.out.println("Amount to turn: " + (theta - getGunHeading()));
		turnGunRight(Utils.normalRelativeAngleDegrees(theta - getGunHeading()));
		fire(3);
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		back(10);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		back(20);
	}	
}
