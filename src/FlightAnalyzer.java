import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FlightAnalyzer {
    public static void main(String[] args) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(new FileReader("/home/mr_smile/Desktop/TestTask/src/tickets.json"));
            JSONArray tickets = (JSONArray) jsonData.get("tickets");

            List<Flight> flights = new ArrayList<>();
            for (Object obj : tickets) {
                JSONObject ticket = (JSONObject) obj;
                String origin = (String) ticket.get("origin");
                String destination = (String) ticket.get("destination");
                String carrier = (String) ticket.get("carrier");
                String departureTime = (String) ticket.get("departure_time");
                String arrivalTime = (String) ticket.get("arrival_time");
                Long price = (Long) ticket.get("price");

                if (origin.equals("VVO") && destination.equals("TLV")) {
                    Flight flight = new Flight(carrier, departureTime, arrivalTime, price);
                    flights.add(flight);
                }
            }

            System.out.println("Минимальное время полета между Владивостоком и Тель-Авивом:");
            Map<String, Integer> flightTimeMinimum = new HashMap<>();
            for (Flight flight : flights) {
                String flightCarrier = flight.getCarrier();
                int flightTime = flight.getFlightTime();
                if (!flightTimeMinimum.containsKey(flightCarrier) || flightTimeMinimum.get(flightCarrier) > flightTime) {
                    flightTimeMinimum.put(flightCarrier, flightTime);
                }
            }

            for (Map.Entry<String, Integer> entry : flightTimeMinimum.entrySet()) {
                System.out.println("Авиаперевозчик: " + entry.getKey());
                System.out.println("Время полета: " + entry.getValue() + " мин");
                System.out.println();
            }

            System.out.println("Разница между средней ценой и медианой для полета между Владивостоком и Тель-Авивом:");
            List<Long> prices = new ArrayList<>();
            for (Flight flight : flights) {
                Long price = flight.getPrice();
                prices.add(price);
            }
            double averagePrice = calculateAverage(prices);
            double medianPrice = calculateMedian(prices);
            double difference = averagePrice - medianPrice;
            System.out.println("Средняя цена: " + averagePrice);
            System.out.println("Медиана: " + medianPrice);
            System.out.println("Разница: " + difference);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static double calculateAverage(List<Long> prices) {
        long sum = 0;
        for (Long price : prices) {
            sum += price;
        }
        return (double) sum / prices.size();
    }

    public static double calculateMedian(List<Long> prices) {
        Collections.sort(prices);
        int size = prices.size();
        if (size % 2 == 0) {
            int middleIndex1 = size / 2 - 1;
            int middleIndex2 = size / 2;
            Long price1 = prices.get(middleIndex1);
            Long price2 = prices.get(middleIndex2);
            return (double) (price1 + price2) / 2;
        } else {
            int middleIndex = size / 2;
            return prices.get(middleIndex);
        }
    }
}
