import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CheckTopDepartment {
  public static void main(String[] args) {

    Employee v1 = new Employee("Gena", "Development", 3000);
    Employee v2 = new Employee("Denis", "HR", 2000);
    Employee v3 = new Employee("Anastasia", "Management", 2500);
    Employee v4 = new Employee("Marya", "Management", 5700);
    Employee v5 = new Employee("Nik", "Management", 5000);
    Employee v6 = new Employee("Kostya", "HR", 2000);

    Stream<Employee> employeeStream = Stream.of(v1, v2, v3, v4, v5, v6);

    Map<String, Long> map =
        employeeStream
            .collect(
                Collectors.groupingBy(
                    Employee::getDepartment, Collectors.summingLong(Employee::getSalary)))
            .entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .limit(2)
            .collect(
                Collectors.toMap(
                    Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

    System.out.println(map);
  }
}
