package org.firstinspires.ftc.teamcode.TeleOp

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.Servo
import kotlin.math.pow

@TeleOp(name = "DB", group = "Among Us")

class DB : LinearOpMode() {
    override fun runOpMode() {

        val speedDiv = 2.0

        val rightWheel = hardwareMap.get(DcMotor::class.java, "rightwheel")
        val leftWheel = hardwareMap.get(DcMotor::class.java, "leftwheel")
        val rotation = hardwareMap.get(DcMotor::class.java, "motorRotate")
        val launchServo = hardwareMap.get(Servo::class.java, "launchservo")

        val clawRotation = hardwareMap.get(Servo::class.java, "clawrotation")
        val claw = hardwareMap.get(Servo::class.java, "claw")

        val startPos = 0.63
        val endPos = 0.9
        var clawPos = 0.63

        rotation.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE)
        rotation.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER)

        launchServo.position = startPos

        waitForStart()
        while (opModeIsActive()) {
            val leftY = gamepad1.left_stick_y.toDouble()
            val rightX = gamepad1.right_stick_x.toDouble()
            val rotateUp = gamepad1.dpad_up
            val rotateDown = gamepad1.dpad_down
            val clawUp = gamepad1.right_bumper
            val clawDown = gamepad1.left_bumper


            rightWheel.power = (leftY - rightX) / speedDiv
            leftWheel.power = (leftY + rightX) / speedDiv


            if (rotateUp) {rotation.power = -0.2}
            else if (rotateDown) {rotation.power = 0.2}
            else {rotation.power = 0.0}
            if (clawUp) {clawRotation.pow(.02)}
            else if (clawDown) {rotation.power = 0.2}
            else {rotation.power = 0.0}

            clawRotation.position = clawPos
            if (gamepad1.a) {launchServo.position = startPos}
            if (gamepad1.y) {launchServo.position = endPos}


        }


    }
}