package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@TeleOp(name = "Mecanum")
public class MecanumOpMode extends LinearOpMode {

    //***************************
    //          Motors
    //***************************
    DcMotor flMotor;
    DcMotor frMotor;
    DcMotor blMotor;
    DcMotor brMotor;

    //***************************
    //     Joystick Inputs
    //***************************
    double leftY;
    double leftX;
    double rightX;

    @Override
    public void runOpMode() throws InterruptedException {


        InstantiateHardware(); //Instantiates all motors and hardware components from the hardware map.


        //Reverses direction of right motors so that positive spins them forward
        frMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        brMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()){
            UpdateInputs();

            SetDriveTrainMotors(leftX, leftY, rightX);
        }
    }


    public void InstantiateHardware(){
        flMotor = hardwareMap.get(DcMotor.class, "flMotor");
        frMotor = hardwareMap.get(DcMotor.class, "frMotor");
        blMotor = hardwareMap.get(DcMotor.class, "blMotor");
        brMotor = hardwareMap.get(DcMotor.class, "brMotor");

        //Put other hardware instantiation in here.
    }

    public void UpdateInputs(){
        leftY = -gamepad1.left_stick_y;
        leftX = gamepad1.left_stick_x * Constants.DriveConstants.kXScale;
        rightX = gamepad1.right_stick_x;
        //Put any other cached inputs in here.
    }

    /**
     * Updates drivetrain motors to move in the direction provided, scaling it down to a unit vector if too big
     *
     *
     *
     * @param x x input
     * @param y y input
     * @param rx rotation input
     */
    public void SetDriveTrainMotors(double x, double y, double rx){

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double s = Constants.DriveConstants.kMaxSpeed;

        flMotor.setPower((y + x + rx) * s / denominator);
        blMotor.setPower((y - x + rx) * s / denominator);
        frMotor.setPower((y - x - rx) * s / denominator);
        brMotor.setPower((y + x - rx) * s / denominator);
    }
}
