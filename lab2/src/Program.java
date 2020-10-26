import com.google.gson.Gson;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<Product>(Arrays
                .asList(readProductsFromFile("F:/dev/cpp/resources/Products.json")));

        ArrayList<Product> products2 = new ArrayList<>(Arrays
                .asList(readProductsFromFile("F:/dev/cpp/resources/Products2.json")));

        Scanner scanner = new Scanner(System.in);

        while(true){
            printMenu();

            try{
                int choice  = scanner.nextInt();

                switch (choice){
                    case 1:
                        scanner.nextLine();
                        repricing(scanner, products);

                        break;
                    case 2:
                        scanner.nextLine();
                        removeByName(scanner, products);

                        break;
                    case 3:
                        manipulateTwoProductLists(scanner, products, products2);

                        break;
                }
            }
            catch (InputMismatchException exception){
                break;
            }
        }
    }

    private static void manipulateTwoProductLists(Scanner scanner, List<Product> firstProductList,
                                                  List<Product> secondProductList){
        System.out.println("Write number what you want to do with lists:");
        System.out.println("1 - intersection.");
        System.out.println("2 - unique from first list.");
        System.out.println("3 - unique from second list.");

        Calendar calendar = new GregorianCalendar();

        try{
            int choice = scanner.nextInt();
            System.out.println();

            Calendar calendarForProducts = new GregorianCalendar();

            switch (choice){
                case 1:
                    var intersection = firstProductList.stream()
                            .filter(p -> secondProductList.stream().anyMatch(x -> x.getName()
                                    .compareTo(p.getName()) == 0))
                            .filter(distinctByKey(Product::getName))
                            .collect(Collectors.toList());

                    intersection = intersection.stream().filter(p -> {
                        calendarForProducts.setTime(p.getDateOfManufacture());
                        return calendarForProducts.get(Calendar.YEAR) == calendar.get(Calendar.YEAR);
                    }).collect(Collectors.toList());

                    printProducts(intersection);

                    break;
                case 2:
                    var uniqueFromFirst = firstProductList.stream()
                            .filter(p -> !secondProductList.stream().anyMatch(x -> x.getName()
                                    .compareTo(p.getName()) == 0))
                            .filter(distinctByKey(Product::getName))
                            .collect(Collectors.toList());

                    uniqueFromFirst = uniqueFromFirst.stream().filter(p -> {
                        calendarForProducts.setTime(p.getDateOfManufacture());
                        return calendarForProducts.get(Calendar.YEAR) == calendar.get(Calendar.YEAR);
                    }).collect(Collectors.toList());

                    printProducts(uniqueFromFirst);

                    break;
                case 3:
                    var uniqueFromSecond = secondProductList.stream()
                            .filter(p -> !firstProductList.stream().anyMatch(x -> x.getName()
                                    .compareTo(p.getName()) == 0))
                            .filter(distinctByKey(Product::getName))
                            .collect(Collectors.toList());

                    uniqueFromSecond = uniqueFromSecond.stream().filter(p -> {
                        calendarForProducts.setTime(p.getDateOfManufacture());
                        return calendarForProducts.get(Calendar.YEAR) == calendar.get(Calendar.YEAR);
                    }).collect(Collectors.toList());

                    printProducts(uniqueFromSecond);

                    break;
            }
        }
        catch (InputMismatchException exception){
            System.out.println("You entered incorrect symbol.");
        }
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    private static void removeByName(Scanner scanner, List<Product> products){
        System.out.println("Write name of product:");
        String name = scanner.nextLine();
        var productsToRemove = products
                .stream().filter(p -> p.getName().compareTo(name) == 0).collect(Collectors.toList());
        products.removeAll(productsToRemove);

        printProducts(products);
    }

    private static void repricing(Scanner scanner, List<Product> products){
        try {
            System.out.println("Write today's date:");
            String dateOfTodayToParse = scanner.nextLine();
            Date dateOfToday = new SimpleDateFormat("dd/MM/yyyy").parse(dateOfTodayToParse);

            products.forEach((product) -> {
                long difference = getDateDifference(dateOfToday, product.getDateOfConsumption());

                if(difference <= 3 && difference > 0) {
                    double price = product.getPrice();
                    price *= 0.9;
                    price = Math.round(price);
                    product.setPrice(price);
                }
            });

            printProducts(products);
        }
        catch (ParseException exception) {
            System.out.println(exception.toString());
        }
    }

    private static void printMenu(){
        System.out.println("Write number what you want to do:");
        System.out.println("1 - make a repricing of first product list.");
        System.out.println("2 - remove product by name from first list.");
        System.out.println("3 - make operations with two product lists.");
        System.out.println("Any symbol else to end.");
    }

    private static void printProducts(List<Product> products){
        var dates = products.stream()
                .map(x -> x.getDateOfConsumption())
                .distinct()
                .collect(Collectors.toList());

        for(Date date: dates){
            var productsWithDate = products
                    .stream()
                    .filter(p -> p.getDateOfConsumption().compareTo(date) == 0).collect(Collectors.toList());
            System.out.print(date + ":");
            for(Product product: productsWithDate){
                System.out.print(product.getName() + " " + product.getPrice() + "$, ");
            }
            System.out.println();
        }
    }

    private static Product[] readProductsFromFile(String fullPath){
        Product[] products = null;

        try{
            String text = Files.readString(Paths.get(fullPath));

            Gson gson = new Gson();

            products = gson.fromJson(text, Product[].class);

            for(Product product : products) {
                System.out.println(product);
            }
        }
        catch (Exception exception) {
            System.out.println(exception.toString());
        }

        return products;
    }

    private static long getDateDifference(Date firstDate, Date secondDate){
        long diffInMillies = secondDate.getTime() - firstDate.getTime();
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        return diff;
    }
}
