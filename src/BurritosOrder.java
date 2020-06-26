import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.text.NumberFormat;

public class BurritosOrder {
    public static void main(String[] args) {

        Random random = new Random();

        final int NUMBER_OF_NEDDED_ORDER = 25;
        final int MIN_NUMBER_OF_INGREDIENTS = 5;
        final int MAX_NUMBER_OF_INGREDIENTS = 9;

 /*       Rice: white, brown, none, all
        Meat: chicken, steak, carnidas, chorizo, sofritas, veggie meat, none, all
        Beans: pinto, black, none, all
        Salsa: mild, medium, hot, none, all
        Veggies: lettuce, fajita veggies, none, all
        Cheese: yes/no
        Guac: yes/no
        Queso: yes/no
        Sour cream: yes/no */

        String[] ingredients = {"Rice", "Meat", "Beans", "Salsa", "Veggies",
                "Cheese", "Guac", "Queso", "Sour cream"};

        String[] riceOption = {"white", "brown", "none", "all"},
                meatOption = {"chicken", "steak", "carnidas", "chorizo",
                        "sofritas", "veggie meat", "none", "all"},
                beansOption = {"pinto", "black", "none", "all"},
                salsaOption = {"mild", "medium", "hot", "none", "all"},
                veggiesOption = {"lettuce", "fajita veggies", "none", "all"},
                cheeseOption = {"yes", "no"},
                guacOption = {"yes", "no"},
                quesoOption = {"yes", "no"},
                sourCreamOption = {"yes", "no"};

        HashMap<String, String[]> ingredientsWithOption = new HashMap<String, String[]>();
        // Storing them into hash map
        ingredientsWithOption.put("Rice", riceOption);
        ingredientsWithOption.put("Meat", meatOption);
        ingredientsWithOption.put("Beans", beansOption);
        ingredientsWithOption.put("Salsa", salsaOption);
        ingredientsWithOption.put("Veggies", veggiesOption);
        ingredientsWithOption.put("Cheese", cheeseOption);
        ingredientsWithOption.put("Guac", guacOption);
        ingredientsWithOption.put("Queso", quesoOption);
        ingredientsWithOption.put("Sour cream", sourCreamOption);

        // Generate an order
        ArrayList<HashMap<Integer, String>> orders = generateRandomOrder(
                random, ingredientsWithOption, ingredients, NUMBER_OF_NEDDED_ORDER,
                MIN_NUMBER_OF_INGREDIENTS, MAX_NUMBER_OF_INGREDIENTS);

        // Calculate each order prices
        double [] eachOrderPrices = calculatePrice(orders, NUMBER_OF_NEDDED_ORDER);

        // Display order with price
        displayOrderWithPrice(orders, eachOrderPrices, ingredients);
    }


    /* Randomly generate a number of ingredients per burrito.
         Each burrito should have a minimum of 5 ingredients and a maximum of 9
         ingredients. Save the finished burritos and display the contents. */

    public static ArrayList<HashMap<Integer, String>> generateRandomOrder
            (Random random, HashMap<String, String[]> ingredWithOpt, String[] ingredients,
             int NUMBER_OF_NEDDED_ORDER, int MIN_NUMBER_OF_INGREDIENTS, int MAX_NUMBER_OF_INGREDIENTS) {

        //ArrayList<ArrayList<String>> orders = new ArrayList<>();
        ArrayList<HashMap<Integer, String>> orders;
        HashMap<Integer, String> ingredOptionHash;
        ArrayList<Integer> alreadyUsedIngredients;
        HashMap<Integer, String> sortedOrderIngredOpt;
        int numberOfOrder = 0;
        String ingredOrKey = "";
        int randomOptionIdx;
        String ingredOption;
        int randomNumberOfIngredients;
        int randomIngredientsOptionIdx;

        orders = new ArrayList<HashMap<Integer, String>>();

        while (numberOfOrder != NUMBER_OF_NEDDED_ORDER) {

            randomNumberOfIngredients = MIN_NUMBER_OF_INGREDIENTS +
                    random.nextInt(5);

            alreadyUsedIngredients = new ArrayList<Integer>();
            ingredOptionHash = new HashMap<Integer, String>();

            for (int ingredIdx = 0; ingredIdx < randomNumberOfIngredients; ingredIdx=alreadyUsedIngredients.size()) {
                randomIngredientsOptionIdx = random.nextInt(ingredients.length);

                if (!alreadyUsedIngredients.contains(randomIngredientsOptionIdx)) {

                    alreadyUsedIngredients.add(randomIngredientsOptionIdx);
                    // Like Rice
                    ingredOrKey = ingredients[randomIngredientsOptionIdx];
                    randomOptionIdx = random.nextInt(ingredWithOpt.get(ingredOrKey).length);

                    // 0Brown
                    ingredOption = ingredWithOpt.get(ingredOrKey)[randomOptionIdx];

                    ingredOptionHash.put(randomIngredientsOptionIdx, ingredOption);
                }
            }

            sortedOrderIngredOpt = sortOrder(ingredOptionHash);

            orders.add(sortedOrderIngredOpt);
            numberOfOrder++;
        }

        return orders;
    }

    public static HashMap<Integer, String> sortOrder(HashMap<Integer, String> currentOrder) {
        HashMap<Integer, String> sorted = new HashMap<>();

        Object[] keys = currentOrder.keySet().toArray();
        Arrays.sort(keys);

        for(Object key : keys) {
            sorted.put((int)key, currentOrder.get(key));
        }

        return sorted;
    }
    /* Calculate the price for burrito. Pricing will be $3.00 plus 0.50 for each
            additional ingredient. So a burrito without any ingredients will be $3.00.
             Also, add $0.50 for "all" but for "none" or "no" add $0 .  */


    public static double[] calculatePrice(ArrayList<HashMap<Integer, String>> orders, int NUMBER_OF_NEDDED_ORDER) {
        double[] eachOrderPrices = new double[NUMBER_OF_NEDDED_ORDER];

        double BASE_PRICE = 3.0;
        double currentOrderPrice = BASE_PRICE;
        String ingredientOption = "";
        for (int orderIdx=0; orderIdx < orders.size(); orderIdx++) {
            HashMap<Integer, String> order = orders.get(orderIdx);

            currentOrderPrice = BASE_PRICE;


            for (int ingredOptIdx : order.keySet()) {
                ingredientOption = order.get(ingredOptIdx);

                if (!ingredientOption.equals("none") && !ingredientOption.equals("no")){
                    currentOrderPrice += 0.50;
                }
            }
            eachOrderPrices[orderIdx] = currentOrderPrice;
        }
        return eachOrderPrices;
    }

    // Display order with price
    public static void displayOrderWithPrice(ArrayList<HashMap<Integer, String>> orders,
                                      double[] eachOrderPrices, String[] ingredients) {
        HashMap<Integer, String> order;
        int orderNumber;
        String orderString;

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();

        for (int eachOrderIdx = 0; eachOrderIdx < orders.size(); eachOrderIdx++) {

            order = orders.get(eachOrderIdx);
            orderNumber = eachOrderIdx + 1;
            orderString = formatOrderString(order, ingredients);
            String orderPrice = numberFormat.format(eachOrderPrices[eachOrderIdx]);

            System.out.print("Burrito " +orderNumber + ": ");
            System.out.print(orderString + "\n");
            System.out.println(orderPrice);
        }
    }

    // Untility Method: It format the order string
    public static String formatOrderString(HashMap<Integer, String> order, String[] ingredients) {
        String orderString = "";
        String option = "";
        int counter = 0;

        for (Integer optionKey : order.keySet()) {
            option = order.get(optionKey);

            if (option.equals("none") || option.equals("no")){
                option = "no " + (ingredients[optionKey]).toLowerCase();
            } else if (option.equals("yes")) {
                option = (ingredients[optionKey]).toLowerCase();
            } else if (option.equals("all")){
                option = "all " + (ingredients[optionKey]).toLowerCase();
            } else if (optionKey == 1 || optionKey == 4){
                option = order.get(optionKey);
            } else {
                option = order.get(optionKey) + " " + (ingredients[optionKey]).toLowerCase();
            }

            orderString += option;
            if (counter++ != order.size()-1)  orderString +=", ";
        }
        return orderString;
    }



}


