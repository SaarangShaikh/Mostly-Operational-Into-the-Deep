package org.firstinspires.ftc.teamcode.TeleOp

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor

@TeleOp(name = "SampleTeleOp", group = "Among Us")
class SampleTeleOp : LinearOpMode() {
    override fun runOpMode () {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        val leftX = gamepad1.left_stick_x
        val leftY = gamepad1.left_stick_y
        val rightX = gamepad2.left_stick_x
        val speedDiv = 2.0

        var target = 100.0

        val motorFL = hardwareMap.get(DcMotor::class.java, "motorFL")
        val motorBL = hardwareMap.get(DcMotor::class.java, "motorBL")
        val motorFR = hardwareMap.get(DcMotor::class.java, "motorFR")
        val motorBR = hardwareMap.get(DcMotor::class.java, "motorBR")
        val rotateMotor = hardwareMap.get(DcMotor::class.java, "motorRotate")
        val slideMotor = hardwareMap.get(DcMotor::class.java, "motorSlide")
        slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rotateMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart()
        while(opModeIsActive()) {
            var joystickY= -gamepad1.left_stick_y;

            if(target+joystickY<850&&target+joystickY>25) {
                var increase = if (joystickY<0) {
                    joystickY*50;
                }else{
                    joystickY * 100;
                }

                target += increase;
                sleep(70)
            }else if(target+joystickY>=850){
                target=850.0;
                sleep(70);
            }else if(target+joystickY<=25){
                target=25.0;
                sleep(70);
            }

            motorFL?.power = (leftY - leftX - rightX) / speedDiv
            motorBL?.power = (leftY + leftX - rightX) / speedDiv
            motorFR?.power = (leftY + leftX + rightX) / speedDiv
            motorBR?.power = (leftY - leftX + rightX) / speedDiv

            telemetry.addLine("OpMode is active")
            telemetry.update()
        }
    }
}