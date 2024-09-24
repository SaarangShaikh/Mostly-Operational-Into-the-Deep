package org.firstinspires.ftc.teamcode.Autonomous.NoSplineAuto

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.geometry.Vector2d
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.DriveMethods
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive
import java.util.Locale

// Autonomous

@TeleOp(name = "FirstAuto", group = "Linear Opmode")
@Disabled
class AutoRedBack : DriveMethods() {
    override fun runOpMode() {
        // Setup Odometry :)
        val drive = SampleMecanumDrive(hardwareMap)

        // Setup up the trajectory sequence (drive path)
        val traj1 = drive.trajectorySequenceBuilder(Pose2d())
            .strafeRight(10.0)
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