package org.firstinspires.ftc.teamcode.TeleOp

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor

@TeleOp(name = "MotorTester", group = "0000000")
class MotorTester : LinearOpMode() {
    override fun runOpMode () {
        telemetry.addData("Status", "Initialized")
        telemetry.update()

        val FL = hardwareMap.get(DcMotor::class.java, "motorFL")
        val BL = hardwareMap.get(DcMotor::class.java, "motorBL")
        val FR = hardwareMap.get(DcMotor::class.java, "motorFR")
        val BR = hardwareMap.get(DcMotor::class.java, "motorBR")
        BR.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        BR.mode = DcMotor.RunMode.RUN_USING_ENCODER
        BL.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        BL.mode = DcMotor.RunMode.RUN_USING_ENCODER
        FR.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        FR.mode = DcMotor.RunMode.RUN_USING_ENCODER
        FL.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        FL.mode = DcMotor.RunMode.RUN_USING_ENCODER

        waitForStart()
        while(opModeIsActive()) {
            if (gamepad1.a){
                BR.power = 1.0
            }
            if (gamepad1.b){
                BL.power = 1.0
            }
            if (gamepad1.x){
                FR.power = 1.0
            }
            if (gamepad1.y){
                FL.power = 1.0
            }

            telemetry.addLine("OpMode is active")
            telemetry.update()
        }
    }
}