package coffecup.concretes.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import coffecup.concretes.opmodes.Groups;
import coffecup.concretes.opmodes.OpModes;
import labs.vex.coffeework.CoffeeWork;
import labs.vex.coffeework.core.Core;
import labs.vex.coffeework.core.application.App;

@TeleOp(name = OpModes.RUNTIME, group = Groups.RUNTIME)
public class RuntimeOpMode extends LinearOpMode{
    public static RuntimeOpMode opMode;

    public RuntimeOpMode() {
        opMode = this;
    }

    @Override
    public void runOpMode() throws InterruptedException {
        try {
            CoffeeWork coffee = new CoffeeWork(); Core core = coffee.getCore();

            core.register("coffecup.concretes.configurations", "coffecup.concretes.resources", "coffecup.concretes.resources");
            core.init();
            if(opModeIsActive()) {
                core.execute(App.AUTONOMOUS_SCOPE);
                core.end(App.AUTONOMOUS_SCOPE);
                core.execute(App.MANUAL_SCOPE);
                while (opModeIsActive()) {
                    core.pulse(App.MANUAL_SCOPE);
                }
                core.end(App.MANUAL_SCOPE);
                core.stop();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
