package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class TankOpMode extends LinearOpMode {

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

    @Override
    public void runOpMode() throws InterruptedException {
        InstantiateHardware();

        flMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        blMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        while(opModeIsActive()){
            UpdateInputs();

            SetDriveTrainMotors(leftX, leftY);
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
        leftX = gamepad1.left_stick_x;
        //Put any other cached inputs in here.
    }

    /**
     * Updates drivetrain motors to move in the direction provided
     *
     *
     *
     * @param x x input
     * @param y y input
     */
    public void SetDriveTrainMotors(double x, double y){

        double s = Constants.DriveConstants.kMaxSpeed;

        flMotor.setPower((y + x) * s);
        blMotor.setPower((y + x) * s);
        frMotor.setPower((y - x) * s);
        brMotor.setPower((y - x) * s);

    }
}
