package org.firstinspires.ftc.teamcode.TeleOp

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple

@TeleOp(name = "SampleTeleOp", group = "Among Us")
class SampleTeleOp : LinearOpMode() {
    override fun runOpMode () {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        val speedDiv = 3.0

        var target = 100.0

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
        FR.direction = DcMotorSimple.Direction.REVERSE
        FL.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        FL.mode = DcMotor.RunMode.RUN_USING_ENCODER

        val rotateMotor = hardwareMap.get(DcMotor::class.java, "motorRotate")
        rotateMotor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        rotateMotor.mode = DcMotor.RunMode.RUN_USING_ENCODER
        val slideMotor = hardwareMap.get(DcMotor::class.java, "motorSlide")
        slideMotor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        slideMotor.mode = DcMotor.RunMode.RUN_USING_ENCODER
//        slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        rotateMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart()
        while(opModeIsActive()) {
            var leftX = -gamepad1.left_stick_x.toDouble()
            var leftY = -gamepad1.left_stick_y.toDouble()
            var rightX = -gamepad1.right_stick_x.toDouble()

//            if (gamepad1.a){
//                rotateMotor.power = 0.5;
//            }
//            else {
//                rotateMotor.power = 0.0;
//            }
//            var Pos = slideMotor?.let { -(it.currentPosition) }
//            var joystickY= -gamepad1.left_stick_y;
//
//            if(target+joystickY<850&&target+joystickY>25) {
//                var increase = if (joystickY<0) {
//                    joystickY*50;
//                }else{
//                    joystickY * 100;
//                }
//
//                target += increase;
//                sleep(70)
//            }else if(target+joystickY>=850){
//                target=850.0;
//                sleep(70);
//            }else if(target+joystickY<=25){
//                target=25.0;
//                sleep(70);
//            }
//
//            slideMotor.setTargetPosition(target.toInt());
//            slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            slideMotor.setPower(0.5);
            FL?.power = (leftY - leftX - rightX) / speedDiv
            BL?.power = (leftY + leftX - rightX) / speedDiv
            FR?.power = (leftY + leftX + rightX) / speedDiv
            BR?.power = (leftY - leftX + rightX) / speedDiv

            telemetry.addData("BL Power: ",  BL.power)
            telemetry.addData("BR Power:  ",  BR.power)
            telemetry.addData("FL Power: ",  FL.power)
            telemetry.addData("FR Power:  ",  FR.power)
            telemetry.addData("Slide Encoder Position: ",  slideMotor?.let { -(it.currentPosition)})
            telemetry.addData("Rotate Encoder Position: ",  rotateMotor?.let { -(it.currentPosition)})
            telemetry.addLine("OpMode is active")
            telemetry.update()
        }
    }
}