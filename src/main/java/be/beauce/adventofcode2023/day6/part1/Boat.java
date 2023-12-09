package be.beauce.adventofcode2023.day6.part1;

public class Boat {
    private final long holdTime;
    private final RaceInstance raceInstance;

    public Boat(int holdTime, RaceInstance raceInstance) {
        this.holdTime = holdTime;
        this.raceInstance = raceInstance;
    }

    public long getTotalTime() {
        return holdTime + getTimeToRunTheRaceDistance();
    }

    private long getTotalDistance() {
        return getTimeToRunTheRaceDistance() * holdTime;
    }

    public long getTimeToRunTheRaceDistance() {
        var timeToRun = (long) Math.ceil((double) raceInstance.distance() / holdTime);
//        System.out.println("speed=" + timeToRun);
        return timeToRun;
//        return (int) ((double)raceInstance.distance() + holdTime) / holdTime;
    }

//    public int getDistance() {
//        System.out.println("distance=" + holdTime + " * " + getTimeForDistance());
//        return getTimeForDistance() * holdTime;
//    }

    public boolean canWin() {
        return (getTotalTime() < raceInstance.time() && getTotalDistance() >= raceInstance.distance())
                || (getTotalTime() == raceInstance.time() && getTotalDistance() > raceInstance.distance());
    }
}
