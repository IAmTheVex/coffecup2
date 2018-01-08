package labs.vex.coffeework.core.service;

import java.util.ArrayList;
import java.util.List;

public class ServiceManager {
    public static List<Job> jobs = new ArrayList<>();

    public static void boot(int id) {
        Job job = jobs.get(id);
        job.getService().setup(job.getHandler());
    }

    public static void kill(int id) {
        Job job = jobs.get(id);
        job.getService().kill();
    }

    public static void start() {
        for(Job j: jobs) {
            j.getService().setup(j.getHandler());
        }
    }

    public static void killAll() {
        for(Job j: jobs) {
            j.getService().kill();
        }
    }

    public static int mount(IService service, ServiceHandler handler) {
        jobs.add(new Job(service, handler));
        return jobs.size() - 1;
    }

}
