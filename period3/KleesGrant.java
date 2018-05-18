package period3;
import robocode.*;
import robocode.util.*;
//import Point2D.*;
//import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * KleesGrant - a robot by (your name here)
 */
public class KleesGrant extends Robot
{
	double enemyOldHeading = 0;
	double bulletVelocity = 11;
	Bullet bullet;
	/**
	 * run: KleesGrant's default behavior
	 */
	public void run() {
		// Initialization of the robot should be put here
		//setAdjustGunForRobotTurn(true);
		//setAdjustRadarForGunTurn(true);
		//setAdjustGunForRobotTurn(true);
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
		double enemyDist = e.getDistance();
		double enemyVel = e.getVelocity();
		double enemyHeading = e.getHeadingRadians();
		double enemyHeadingChange = enemyHeading - enemyOldHeading;
		enemyOldHeading = enemyHeading;
		double absoluteBearing = e.getBearingRadians() + Math.toRadians(getHeading());
		double enemyX = getX() + enemyDist * Math.sin(absoluteBearing);
		double enemyY = getY() + enemyDist * Math.cos(absoluteBearing);
		double estBulletTravelTime = enemyDist/bulletVelocity;
		
		double deltaTime=0;
		double predictedX = enemyX;
		double predictedY = enemyY;
		while (((++deltaTime)*(20-3.0*3)) < Point2D.Double.distance(getX(),getY(),predictedX,predictedY))
		{
			predictedX += Math.sin(enemyHeading) * enemyVel;
			predictedY += Math.cos(enemyHeading) * enemyVel;
		}
		double theta = Utils.normalAbsoluteAngle(Math.atan2(predictedX-getX(), predictedY-getY()));
		double turnInRadians = Utils.normalRelativeAngle(theta-Math.toRadians(getGunHeading()));
		System.out.println("Gun Bearing Calc: " + (e.getBearing() + (getHeading() - getGunHeading())));
		System.out.println("Amount to turn: " + e.getBearing());
		turnGunRight(Utils.normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getGunHeading())));
		bullet = fireBullet(3);
		bulletVelocity = bullet.getVelocity();
		
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
