package org.firstinspires.ftc.teamcode.TeleOp

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.Gamepad
import kotlin.math.*

@TeleOp(name = "RohanProject", group = "Z")
class RohanJamesProject : LinearOpMode() {
    override fun runOpMode () {

        telemetry.addData("Status", "Initialized")
        telemetry.update()

        val speedDiv = 2.0

        //MATH STUFF
        var x = 0.0
        var y = 0.0
        var angle = 7*PI/18
        var length = 40.0 //in
        val horizontalEncoder = 700.0
        val behindEncoder = -500.0
        val topEncoder = 2000.0
        val slideSlope = topEncoder/length

        //TOGGLES
        var coordinator = false

        //GAMEPADS
        val currentGamepad1: Gamepad = Gamepad()
        val currentGamepad2: Gamepad = Gamepad()
        val previousGamepad1: Gamepad = Gamepad()
        val previousGamepad2: Gamepad = Gamepad()

        //MOTORS
        val motorFL = hardwareMap.get(DcMotor::class.java, "motorFL")
        val motorBL = hardwareMap.get(DcMotor::class.java, "motorBL")
        val motorFR = hardwareMap.get(DcMotor::class.java, "motorFR")
        val motorBR = hardwareMap.get(DcMotor::class.java, "motorBR")
        val rotateMotor = hardwareMap.get(DcMotor::class.java, "motorRotate")
        val slideMotor = hardwareMap.get(DcMotor::class.java, "motorSlide")
        slideMotor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        rotateMotor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE

        waitForStart()

        while(opModeIsActive()) {
            previousGamepad1.copy(currentGamepad1);
            previousGamepad2.copy(currentGamepad2);
            currentGamepad1.copy(gamepad1);
            currentGamepad2.copy(gamepad2);

            val y = -gamepad1.left_stick_y.toDouble() // Remember, Y stick is reversed!
            val x = gamepad1.left_stick_x.toDouble()
            val rx = gamepad1.right_stick_x.toDouble()

            motorFL.power = y + x + rx
            motorBL.power = y - x + rx
            motorFR.power = y - x - rx
            motorBR.power = y + x - rx

            if (currentGamepad1.a && !previousGamepad1.a){
                // CALCULATE VALUES
                    length = hypot(x,y)   // Find length of slide needed in inches
                    length *= slideSlope
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
                    rotateMotor.targetPosition = angle.toInt()
                    rotateMotor.mode = DcMotor.RunMode.RUN_TO_POSITION
                    rotateMotor.power = 0.1

                    slideMotor.targetPosition = length.toInt()
                    slideMotor.mode = DcMotor.RunMode.RUN_TO_POSITION
                    slideMotor.power = 0.1
                }
                else {
                    rotateMotor.power = 0.0
                    slideMotor.power = 0.0
                }
            }

            telemetry.addLine("OpMode is active")
            telemetry.update()
        }
    }
}