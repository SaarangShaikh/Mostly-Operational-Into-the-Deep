package org.firstinspires.ftc.teamcode.TeleOp

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import kotlin.math.*

@TeleOp(name = "RohanProject", group = "ZZZZZZZ")
class RohanJamesProject : LinearOpMode() {
    override fun runOpMode () {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        val leftX = gamepad1.left_stick_x
        val leftY = gamepad1.left_stick_y
        val rightX = gamepad2.left_stick_x
        val speedDiv = 2.0

        //MATH STUFF
        var x = 0.0
        var y = 0.0
        var angle = 7*PI/18
        var length = 40.0; //in
        var initAngle = 0.0;
        var horizontalEncoder = 700.0;
        var behindEncoder = -500.0;
        var topEncoder = 2000.0;
        var slideSlope = topEncoder/length;

        //TOGGLES
        var coordinator = false;

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
            if (gamepad1.a){
                sleep(1000)
                // CALCULATE VALUES
                    length = hypot(x,y)   // Find length of slide needed in inches
                    length *= slideSlope;
                    angle = atan2(x,y)  // Find Angle in Radians
                    angle = angle * 180.0/PI //Now its in Degrees
                    angle = horizontalEncoder - angle * 10   // Now its in encoders

                //CHECK IF EVERYTHING IS FINE
                    if (length > 40.0 || length < 12.0){
                        coordinator = false
                    }
                    if (angle < -300 || angle > 900) {
                        coordinator = false
                    }


                //MOVE STUFF
                if (coordinator) {
                    rotateMotor.targetPosition = angle.toInt();
                    rotateMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    rotateMotor.power = 0.1

                    slideMotor.targetPosition = length.toInt();
                    slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    slideMotor.power = 0.1
                }

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