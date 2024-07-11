package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class Webshop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many randomized products do you want to add?");
        int amount = scanner.nextInt();

        for (int i = 0; i < amount; i++) {
            Product product = createProduct(generateNamesForTypesMap());
            insertProduct(product);
        }
    }

    public static Map<Type, List<List<String>>> generateNamesForTypesMap() {
        Map<Type, List<List<String>>> namesForTypes = new HashMap<>();

        List<String> namesForMemory = List.of("DDR1", "DDR2", "DDR3", "DDR4");
        List<String> prefixesForMemory = List.of("", "single-sided", "double-sided");
        List<String> suffixesForMemory = new ArrayList<>();

        for (int i = 1000; i < 4000; i += 250) {
            suffixesForMemory.add(String.valueOf(i) + " MHz");
        }
        List<List<String>> memoryLists = List.of(namesForMemory, prefixesForMemory, suffixesForMemory);
        namesForTypes.put(Type.memory, memoryLists);

        List<String> namesForHardDrive = List.of("HDD", "SDD", "SSHDD");
        List<String> prefixesForHardDrive = List.of("120 GB", "240 GB", "500 GB", "1 TB", "2 TB", "3 TB", "4 TB");
        List<String> suffixesForHardDrive = List.of("", "10 000 rpm", "7 200 rpm", "5400 rpm");
        List<List<String>> hardDriveLists = List.of(namesForHardDrive, prefixesForHardDrive, suffixesForHardDrive);
        namesForTypes.put(Type.hard_drive, hardDriveLists);

        List<String> namesForMonitor = List.of("CRT", "LCD", "LED", "OLED");
        List<String> prefixesForMonitor = List.of("CRT", "LCD", "LED", "OLED");
        List<String> suffixesForMonitor = List.of("", "TN panel", "IPS panel");
        List<List<String>> monitorLists = List.of(namesForMonitor, prefixesForMonitor, suffixesForMonitor);
        namesForTypes.put(Type.monitor, monitorLists);

        List<String> namesForKeyboard = List.of("hungarian keyboard", "english keyboard", "german keyboard");
        List<String> prefixesForKeyboard = List.of("", "Waterproof", "Gamer", "Mechanic");
        List<String> suffixesForKeyboard = List.of("PS2 plug", "USB plug", "USB / PS2 plug");
        List<List<String>> keyboardLists = List.of(namesForKeyboard, prefixesForKeyboard, suffixesForKeyboard);
        namesForTypes.put(Type.keyboard, keyboardLists);

        List<String> namesForMouse = List.of("mechanic mouse", "optical mouse", "laser mouse");
        List<String> prefixesForMouse = List.of("Normal", "5-button", "Gamer", "Double-wheeled");
        List<String> suffixesForMouse = List.of("", "10 000 DPI sensitivity", "5 000 DPI sensitivity", "5 year warranty");
        List<List<String>> mouseLists = List.of(namesForMouse, prefixesForMouse, suffixesForMouse);
        namesForTypes.put(Type.mouse, mouseLists);

        List<String> namesForProcessor = List.of("Intel CPU", "AMD CPU");
        List<String> prefixesForIntelCPU = List.of("Core i3", "Core i5", "Core i7", "Core i9");
        List<String> prefixesForAMDCPU = List.of("Ryzen", "Athlon", "A-series", "FX");
        List<String> suffixesForProcessor = List.of("2 GHz", "2.2 GHz", "2.4 GHz", "2.6 GHz", "2.8 GHz", "3 GHz");
        List<List<String>> processorLists = List.of(namesForProcessor, prefixesForIntelCPU, prefixesForAMDCPU, suffixesForProcessor);
        namesForTypes.put(Type.processor, processorLists);


        List<String> namesForGraphicCard = List.of("NVIDIA graphic card", "AMD graphic card", "Intel graphic card");
        List<String> prefixesForGraphicCard = List.of("DirectX 9", "DirectX 10", "DirectX 11", "DirectX 12");
        List<String> suffixesForGraphicCard = List.of("Gamer Edition", "including a PC game", "water-cooled", "5 year warranty");
        List<List<String>> graphicCardLists = List.of(namesForGraphicCard, prefixesForGraphicCard, suffixesForGraphicCard);
        namesForTypes.put(Type.graphic_card, graphicCardLists);

        return namesForTypes;
    }

    public static Product createProduct(Map<Type, List<List<String>>> namesForTypes) {
        Product p = new Product();
        Random random = new Random();

        int randomProductId = random.nextInt(Integer.MAX_VALUE - 11 + 1) + 11;
        p.setProduct_id(randomProductId);

        p.setDescription("");

        int randomUnitPrice = random.nextInt(220000 - 10000 + 1) + 10000;
        p.setUnit_price(randomUnitPrice);

        int randomQuantity_in_stock = random.nextInt(20 + 1);
        p.setQuantity_in_stock(randomQuantity_in_stock);

        Brand[] brands = Brand.values();
        Brand randomBrand = brands[random.nextInt(brands.length)];
        p.setBrand(randomBrand);


        Type[] types = Type.values();
        Type randomType = types[random.nextInt(types.length)];
        p.setType(randomType);


        p.setCategory(String.valueOf(p.getType()));

        //TODO: name generated by type class
        String name = createName(p.getType(), generateNamesForTypesMap());
        p.setName(name);

        return p;
    }

    public static String createName(Type type, Map<Type, List<List<String>>> namesForTypes) {
        Random r = new Random();
        List<String> nameElements = new ArrayList<>();

        if (type.equals(Type.processor)) {
            List<List<String>> listsForProcessor = namesForTypes.get(Type.processor);
            List<String> namesForProcessor = listsForProcessor.get(0);
            int randomIndex1 = r.nextInt(namesForProcessor.size());

            String processorName = namesForProcessor.get(randomIndex1);
            nameElements.add(processorName);

            if (processorName.equals("Intel CPU")) {
                List<String> prefixesForIntelCPU = listsForProcessor.get(1);
                int randomIndex2 = r.nextInt(prefixesForIntelCPU.size());
                nameElements.add(prefixesForIntelCPU.get(randomIndex2));

            } else {
                List<String> prefixesForAMDCPU = listsForProcessor.get(2);
                int randomIndex3 = r.nextInt(prefixesForAMDCPU.size());
                nameElements.add(prefixesForAMDCPU.get(randomIndex3));
            }

            List<String> suffixesForProcessor = listsForProcessor.get(3);
            int randomIndex3 = r.nextInt(suffixesForProcessor.size());
            nameElements.add(suffixesForProcessor.get(randomIndex3));


        } else {
            List<List<String>> listsForType = namesForTypes.get(type);

            for (List<String> list : listsForType) {
                int randomIndex = r.nextInt(list.size());
                nameElements.add(list.get(randomIndex));
            }
        }

        return String.join(" ", nameElements);
    }

    public static void insertProduct(Product p) {
        String url = "jdbc:mysql://localhost:3306/webshop";
        String user = "root";
        String password = "your_password";

        String query = "INSERT INTO product (id, name, description, price, in_stock, brand, product_type, category) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, user, password);

             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, p.getProduct_id());
            stmt.setString(2, p.getName());
            stmt.setString(3, p.getDescription());
            stmt.setInt(4, p.getUnit_price());
            stmt.setInt(5, p.getQuantity_in_stock());
            stmt.setString(6, String.valueOf(p.getBrand()));
            stmt.setString(7, String.valueOf(p.getType()));
            stmt.setString(8, p.getCategory());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new product was inserted.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

