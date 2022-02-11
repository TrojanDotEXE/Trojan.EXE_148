package org.firstinspires.ftc.teamcode.Autonome;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.HardwareM;

@Autonomous(name = "Test Encodere")
public class Test_Encodere extends LinearOpMode
{
    DcMotor roataStanga = null, roataDreapta = null;
    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
        roataStanga = hardwareMap.get(DcMotor.class, "motorStanga");
        roataStanga.setDirection(DcMotorSimple.Direction.FORWARD);
        roataDreapta = hardwareMap.get(DcMotor.class, "motorDreapta");
        roataDreapta.setDirection(DcMotorSimple.Direction.REVERSE);

        // Wait for start while displaying encoder values  (Note: these may not start at zero)
        // This is just for testing
        while(!isStarted()) {
            telemetry.addData("LeftPosition", roataStanga.getCurrentPosition());
            telemetry.addData("RightPosition", roataDreapta.getCurrentPosition());
            telemetry.update();
        }

        // We are now running opmode
        // Start clock at zero
        runtime.reset();

        // Reset encoders
        roataDreapta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        roataStanga.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Prepare to drive to target position
        roataDreapta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        roataStanga.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set target position and speed
        roataStanga.setTargetPosition(1440);
        roataDreapta.setTargetPosition(1440);
        roataStanga.setPower(1.0);
        roataDreapta.setPower(1.0);

        // Loop while we approach the target.  Display position as we go
        while(roataDreapta.isBusy() && roataStanga.isBusy() && opModeIsActive()) {
            telemetry.addData("LeftPosition", roataStanga.getCurrentPosition());
            telemetry.addData("LeftTarget", roataStanga.getTargetPosition());
            telemetry.addData("RightPosition", roataDreapta.getCurrentPosition());
            telemetry.addData("RightTarget", roataDreapta.getTargetPosition());
            telemetry.update();
        }

        // We are done, turn motors off and switch back to normal driving mode.
        roataStanga.setPower(0);
        roataDreapta.setPower(0);
        roataDreapta.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        roataStanga.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

