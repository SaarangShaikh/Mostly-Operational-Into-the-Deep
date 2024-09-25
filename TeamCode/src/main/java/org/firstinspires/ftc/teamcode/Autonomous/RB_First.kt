package org.firstinspires.ftc.teamcode.Autonomous.NoSplineAuto

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.geometry.Vector2d
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.DriveMethods
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence
import java.util.Locale


// Autonomous

@TeleOp(name = "RB", group = "B")
@Disabled
class RB_First : DriveMethods() {
    override fun runOpMode() {
        // Setup Odometry :)
        val drive = SampleMecanumDrive(hardwareMap)

        // Setup up the trajectory sequence (drive path)
        val traj1: TrajectorySequence? =
            drive.trajectorySequenceBuilder(Pose2d(-34.09, -63.19, Math.toRadians(90.00)))
                .splineTo(Vector2d(-39.64, -47.68), Math.toRadians(144.20))
                .splineTo(Vector2d(-57.26, -57.64), Math.toRadians(225.00))
                .build()

        // Tell the User the Robot has been initialized
        telemetry.addData("Status", "Initialized")
        telemetry.update()


        // Wait for the game to start (driver presses PLAY)
        waitForStart()
        telemetry.addLine("started")
        telemetry.update()
        if (isStopRequested) return
        telemetry.addLine("not stopped")
        telemetry.update()
        drive.followTrajectorySequenceAsync(traj1)
        while (opModeIsActive() && !isStopRequested) {
            drive.update()
        }
        telemetry.addLine("tried to run code")
        telemetry.update()
        sleep(1000)
        val (x, y, heading) = drive.poseEstimate
        telemetry.addData(
            "Current Position",
            String.format(Locale.ENGLISH, "X: %f, Y: %f, and Rotation: %f", x, y, heading)
        )
        telemetry.update()
    }
}