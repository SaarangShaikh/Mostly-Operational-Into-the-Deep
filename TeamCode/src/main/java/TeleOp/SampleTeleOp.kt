package TeleOp

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
        val motorFL = hardwareMap.get(DcMotor::class.java, "motor1")
        val motorBL = hardwareMap.get(DcMotor::class.java, "motor2")
        val motorFR = hardwareMap.get(DcMotor::class.java, "motor3")
        val motorBR = hardwareMap.get(DcMotor::class.java, "motor1")

        waitForStart()
        while(opModeIsActive()) {
            telemetry.addLine("OpMode is active")
            telemetry.update()

            motorFL.power = (leftX + leftY + rightX)/speedDiv
            motorBL.power = (leftX - leftY + rightX)/speedDiv
            motorFR.power = (leftX - leftY - rightX)/speedDiv
            motorFL.power = (leftX + leftY - rightX)/speedDiv
        }
    }
}