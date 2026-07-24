class UndergroundSystem {

    class CheckIn {
        String station;
        int time;

        CheckIn(String station, int time) {
            this.station = station;
            this.time = time;
        }
    }

    class Trip {
        int totalTime;
        int count;

        Trip() {
            totalTime = 0;
            count = 0;
        }
    }

    HashMap<Integer, CheckIn> checkInMap;
    HashMap<String, Trip> routeMap;

    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        routeMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new CheckIn(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {

        CheckIn in = checkInMap.get(id);

        String key = in.station + "->" + stationName;

        int travelTime = t - in.time;

        Trip trip = routeMap.getOrDefault(key, new Trip());

        trip.totalTime += travelTime;
        trip.count++;

        routeMap.put(key, trip);

        checkInMap.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {

        String key = startStation + "->" + endStation;

        Trip trip = routeMap.get(key);

        return (double) trip.totalTime / trip.count;
    }
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */
