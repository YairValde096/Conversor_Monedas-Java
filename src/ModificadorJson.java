import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModificadorJson {
    public static ArrayList<Double> extractUSDValues(String text) {
        Map<Integer, Double> values = new LinkedHashMap<>();
        Pattern pattern = Pattern.compile("\"USD[A-Z]{3}\":\\s*(\\d+\\.?\\d*)");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            try {
                Double value = Double.parseDouble(matcher.group(1));
                int position = matcher.start(1);
                values.put(position, value);
            } catch (NumberFormatException e) {
                System.out.println("Unable to parse value: " + matcher.group(1));
            }
        }

        // Insertar valor en la posición 149 y recorrer los siguientes valores
        Double valueToInsert = 1.0;
        int positionToInsert = 149;
        int i = 0;
        ArrayList<Double> sortedValues = new ArrayList<>();
        List<Integer> keys = new ArrayList<>(values.keySet());
        Collections.sort(keys);
        for (Integer key : keys) {
            if (i == positionToInsert) {
                sortedValues.add(valueToInsert);
            }
            sortedValues.add(values.get(key));
            i++;

            // Si se ha llegado al final de los valores ordenados y no se ha insertado el valor a insertar,
            // se inserta al final de la lista
            if (i == keys.size() && i == positionToInsert) {
                sortedValues.add(valueToInsert);
            }
        }

        return sortedValues;
    }


}
