package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

class TestCVOpMode : LinearOpMode() {
    override fun runOpMode() {
        telemetry.addLine("Initialized")
        telemetry.update()

        waitForStart()

        while (opModeIsActive()) {

        }
    }
}