//record Flight(String carrier, String departureTime, String arrivalTime, Long price) {
//    // Класс полёта реализованный в качестве record (Java 14 и выше)
//    public int getFlightTime() {
//        // Расчет времени полета в минутах
//        int departureHour = Integer.parseInt(departureTime.split(":")[0]);
//        int departureMinute = Integer.parseInt(departureTime.split(":")[1]);
//        int arrivalHour = Integer.parseInt(arrivalTime.split(":")[0]);
//        int arrivalMinute = Integer.parseInt(arrivalTime.split(":")[1]);
//
//        int departureTotalMinutes = departureHour * 60 + departureMinute;
//        int arrivalTotalMinutes = arrivalHour * 60 + arrivalMinute;
//
//        return arrivalTotalMinutes - departureTotalMinutes;
//    }
//
//}

public class Flight {
    private String carrier;
    private String departureTime;
    private String arrivalTime;
    private Long price;

    public Flight(String carrier, String departureTime, String arrivalTime, Long price) {
        this.carrier = carrier;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
    }

    public String getCarrier() {
        return carrier;
    }

    public int getFlightTime() {
        int departureHour = Integer.parseInt(departureTime.split(":")[0]);
        int departureMinute = Integer.parseInt(departureTime.split(":")[1]);
        int arrivalHour = Integer.parseInt(arrivalTime.split(":")[0]);
        int arrivalMinute = Integer.parseInt(arrivalTime.split(":")[1]);

        int departureTotalMinutes = departureHour * 60 + departureMinute;
        int arrivalTotalMinutes = arrivalHour * 60 + arrivalMinute;

        return arrivalTotalMinutes - departureTotalMinutes;
    }

    public Long getPrice() {
        return price;
    }
}