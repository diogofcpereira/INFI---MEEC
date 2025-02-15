public class Time_And_Day {
    private volatile long currentTime;
    private final long dayDuration;
    private volatile int currentSecond;
    private volatile boolean running;

    private DB_Interaction db = new DB_Interaction();

    public Time_And_Day(long dayDurationInSeconds) {
        this.currentTime = 0;
        this.currentSecond = 0;
        this.dayDuration = dayDurationInSeconds;
        this.running = false;

        db.updateDay(0);
        db.updateSecond(0);
    }

    public void start() {
        if(!running) {
            running = true;
            Thread t = new Thread(() -> {
                while(running) {
                    try {
                        for(int i = 0; i < dayDuration; i++) {
                            Thread.sleep(1000);
                            this.currentSecond = i;
                            db.updateSecond(getCurrentSecond());
                        }
                        currentTime += dayDuration;
                        db.updateDay(getCurrentDay());
                        //System.out.println("Day " + (currentTime/dayDuration));
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
        }
    }

    public void stop() {
        running = false;
    }

    public long getCurrentDay() {
        return currentTime / dayDuration;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public int getCurrentSecond() {
        return currentSecond;
    }
}
