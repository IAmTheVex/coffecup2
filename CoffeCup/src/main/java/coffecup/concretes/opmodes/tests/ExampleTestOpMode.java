package coffecup.concretes.opmodes.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import coffecup.concretes.opmodes.Groups;
import coffecup.concretes.opmodes.OpModes;

@TeleOp(name = OpModes.FIRST_TEST, group = Groups.TESTS)
//@Disabled
public class ExampleTestOpMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor motor1 = hardwareMap.get(DcMotor.class, "motor1");
        DcMotor motor2 = hardwareMap.get(DcMotor.class, "motor2");

        DcMotor lift = hardwareMap.get(DcMotor.class, "lift");

        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motor1.setDirection(DcMotorSimple.Direction.REVERSE);
        motor2.setDirection(DcMotorSimple.Direction.FORWARD);

        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Servo arm_right = hardwareMap.get(Servo.class, "arm_right");
        Servo arm_left = hardwareMap.get(Servo.class, "arm_left");

        int counter = 0;
        waitForStart();
        while (opModeIsActive()) {
            if(gamepad1.b) {
                counter++;
                while(gamepad1.b);
            }

            double left = gamepad1.left_stick_y;
            double right = gamepad1.right_stick_y;

            motor1.setPower(right * (((counter % 4) + 1) * 0.25));
            motor2.setPower(left * (((counter % 4) + 1) * 0.25));

            if (gamepad1.y) {
                arm_left.setPosition(1);
                arm_right.setPosition(0);
            } else if (gamepad1.a) {
                arm_left.setPosition(0.7);
                arm_right.setPosition(0.3);
            }

            if(gamepad1.dpad_up) {
                lift.setPower(1);
            } else if(gamepad1.dpad_down) {
                lift.setPower(-1);
            } else {
                lift.setPower(0);
            }

            telemetry.addData("Servos Position", arm_right.getPosition());
            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}
