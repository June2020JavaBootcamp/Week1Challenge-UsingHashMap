# Burritos Order

Write a program that will allow a robot to assemble 25 burritos. Use a random generator for each burrito option and build a list of 25 burrito customization:

Rice: white, brown, none, all
Meat: chicken, steak, carnidas, chorizo, sofritas, veggie meat, none, all
Beans: pinto, black, none, all
Salsa: mild, medium, hot, none, all
Veggies: lettuce, fajita veggies, none, all
Cheese: yes/no
Guac: yes/no
Queso: yes/no
Sour cream: yes/no

Randomly generate a number of ingredients per burrito. Each burrito should have a minimum of 5 ingredients and a maximum of 9 ingredients. Save the finished burritos and display the contents.

Calculate and display a price for each burrito. Pricing will be $3.00 plus 0.50 for each additional ingredient. So a burrito without any ingredients will be $3.00. Also, add $0.50 for "all" but for "none" or "no" add $0 .  

 

Don't forget to include project description and pseudocode in the README file!

Identify required items and check your pseudocode satisfies all of requirements.

If you're finished with above before 5pm, modify your program to use modules (methods). For example, you can call a module (method) from main whose sole purpose is to calculate the price of the burrito.


Pseudocode:
    import Random;
    import NumberFormat;
    
    BEGIN
        Random random = new Random();
        final int NUMBER_OF_NEDDED_ORDER = 25
        
        Rice: white, brown, none, all
        Meat: chicken, steak, carnidas, chorizo, sofritas, veggie meat, none, all
        Beans: pinto, black, none, all
        Salsa: mild, medium, hot, none, all
        Veggies: lettuce, fajita veggies, none, all
        Cheese: yes/no
        Guac: yes/no
        Queso: yes/no
        Sour cream: yes/no
        
        String [] ingredients = {"Rice", "Meat", "Beans", "Salsa", "Veggies", 
                        "Cheese", "Guac", "Queso", "Sour cream"}
        
        String[] riceOption = {"white", "brown", "none", "all"}
        String[] meatOption = {"chicken", "steak", "carnidas", "chorizo", "sofritas", "veggie meat", "none", "all"}
        String[] beansOption = {"pinto", "black", "none", "all"}
        String[] salsaOption = {"mild", "medium", "hot", "none", "all"}
        String[] veggiesOption = {"lettuce", "fajita veggies", "none", "all"}
        String[] cheeseOption = {"yes","no"}
        String[] guacOption = {"yes","no"}
        String[] quesoOption = {"yes","no"}
        String[] sourCreamOption = {"yes","no"}
        
        
        
        // Storing them into hash map
        ingredientsWithOption.put("Rice", riceOption)
        ingredientsWithOption.put("Meat", meatOption)
        ingredientsWithOption.put("Beans", beansOption)
        ingredientsWithOption.put("Salsa", salsaOption)
        ingredientsWithOption.put("Veggies", veggiesOption)
        ingredientsWithOption.put("Cheese", cheeseOption)
        ingredientsWithOption.put("Guac", guacOption)
        ingredientsWithOption.put("Queso", quesoOption)
        ingredientsWithOption.put("Sour cream", sourCreamOption)
        
        // Generate an order
        Arraylist<Arraylist<>String> order = generateRandomOrder(ingredientsWithOption)
        
        // Calculate each order prices
        int[] eachOrderPrices = calculatePrice(order)
        
        // Display order with price
        displayOrderWithPrice(order, eachOrderPrices)
        
        
        
        /* Randomly generate a number of ingredients per burrito.
         Each burrito should have a minimum of 5 ingredients and a maximum of 9 i
         ngredients. Save the finished burritos and display the contents. */
     
        ArrayList<ArrayList<String>> generateRandomOrder(ingredientsWithOption) {
            int numberOfOrder = 0
            int numberOfIngredients = 0
             
            ArrayList<ArrayList<String>> orders = new ArrayList<>()
            
            WHILE (numberOfOrder != NUMBER_OF_NEDDED_ORDER) DO
                
               ArrayList<String> currentOrder = ArrayList<>()
               numberOfIngredients = 0
                
               FOR ingredientIndex = 0 TO ingredientsWithOption.keyset().size() - 1  DO
                   randomIngredientOptionIndex = random.nextInt(ingredientsWithOption.get(i).size())
                    
                   randomIngredientOption = ingredientsWithOption.get(i).get(randomIngredientOptionIndex)
                   currentOrder.add(randomIngredientOption)
                    
                   IF (!randomIngredientOption.equals("none") && 
                               !randomIngredientOption.equals("no")) THEN
                       numberOfIngredients++
                   ENDIF
                ENDFOR
                
               IF (numberOfIngredients >= 5 && numberOfIngredients <= 9) THEN
                   // currentOrder.add(""+numberOfIngredients)
                   orders.add(currentOrder)
                   numberOfOrder++
               ENDIF
             
            ENDWHILE
            
            return orders
        }
     
        /* Calculate the price for burrito. Pricing will be $3.00 plus 0.50 for each 
            additional ingredient. So a burrito without any ingredients will be $3.00.
             Also, add $0.50 for "all" but for "none" or "no" add $0 .  */
        
        int[] calculatePrice(orders) {
            int[] eachOrderPrices = new int[NUMBER_OF_NEDDED_ORDER]
            
            double currentOrderPrice = 3.0
            String ingredientOption = ""
            FOR orderIdx=0 TO orders.size() - 1 DO
                ArrayList<String> order = orders.get(orderIdx)
                
                currentOrderPrice = 3.0;
                FOR ingredientOptionIdx = 0; TO order.size() - 1 DO
                    ingredientOption = order.get(ingredientOptionIdx)
                    IF (!ingredientOption.equals("none") && 
                                                   !ingredientOption.equals("no")) THEN
                        currentOrderPrice += 0.50
                    ENDIF
                ENDFOR
                
                eachOrderPrices[orderIdx] = currentOrderPrice
                
            ENDFOR
       
            return eachOrderPrices
        }
        
        
        
        // Display order with price
        void displayOrderWithPrice(order, eachOrderPrices) {
        
            Access the order and prices and display them together.

        }
        
        
        
    END
