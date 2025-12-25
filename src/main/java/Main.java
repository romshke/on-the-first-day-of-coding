import java.io.File;
import java.time.LocalTime;
import java.util.*;

public class Main {

    static String mostCommonErrorBetween15_00And15_30(File file) {
        Map<String, Integer> errorCountsDuringADay = new HashMap<>();
        Map<String, Integer> errorCountsBetween15_00And15_30 = new LinkedHashMap<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String[] parts = scanner.nextLine().split(" ");

                errorCountsDuringADay.compute(parts[1], (k, v) -> v == null ? 1 : v + 1);

                if (parts[0].startsWith("15")) {
                    LocalTime time = LocalTime.parse(parts[0]);

                    if (time.isAfter(LocalTime.of(14, 59)) && time.isBefore(LocalTime.of(15, 31))) {
                        errorCountsBetween15_00And15_30.compute(parts[1], (k, v) -> v == null ? 1 : v + 1);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String mostCommonError = errorCountsDuringADay.entrySet().stream()
                .max(Map.Entry.comparingByValue()).get().getKey();

        return errorCountsBetween15_00And15_30.entrySet().stream()
                .filter(entry -> !entry.getKey().equals(mostCommonError))
                .max(Map.Entry.comparingByValue()).get().getKey();
    }

    public static void main(String[] args) {
        File file = new File("src/main/resources/hyperskill-dataset-118975037.txt");
        System.out.println(mostCommonErrorBetween15_00And15_30(file));
    }
}
