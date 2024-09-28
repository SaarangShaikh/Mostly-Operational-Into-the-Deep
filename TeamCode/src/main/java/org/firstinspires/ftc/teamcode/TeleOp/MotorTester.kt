package org.firstinspires.ftc.teamcode.TeleOp

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple

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
        BR.direction = DcMotorSimple.Direction.REVERSE
        BL.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        BL.mode = DcMotor.RunMode.RUN_USING_ENCODER
        FR.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        FR.mode = DcMotor.RunMode.RUN_USING_ENCODER
        FL.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        FL.mode = DcMotor.RunMode.RUN_USING_ENCODER

        waitForStart()
        while(opModeIsActive()) {
            var leftX = gamepad1.left_stick_x.toDouble()
            var leftY = -gamepad1.left_stick_y.toDouble()
            var rightX = gamepad1.right_stick_x.toDouble()

            if (gamepad1.a){
                BR.power = 1.0
                telemetry.addLine("BR")
            }
            if (gamepad1.b){
                BL.power = 1.0
                telemetry.addLine("BL")
            }
            if (gamepad1.x){
                FR.power = 1.0
                telemetry.addLine("FR")
            }
            if (gamepad1.y){
                FL.power = 1.0
                telemetry.addLine("FL")
            }

            FL?.power = (leftY + leftX + rightX)
            BL?.power = (leftY - leftX + rightX)
            FR?.power = (leftY - leftX - rightX)
            BR?.power = (leftY + leftX - rightX)


            telemetry.addData("BL Power: ",  BL.power)
            telemetry.addData("BR Power:  ",  BR.power)
            telemetry.addData("FL Power: ",  FL.power)
            telemetry.addData("FR Power:  ",  FR.power)
            telemetry.addData("Left Stick Y:  ",  gamepad1.left_stick_y)
            telemetry.addData("Left Stick X:  ",  gamepad1.left_stick_x)
            telemetry.addData("Right Stick X:  ",  gamepad1.right_stick_x)
            telemetry.addLine("OpMode is active")
            telemetry.update()
        }
    }
}