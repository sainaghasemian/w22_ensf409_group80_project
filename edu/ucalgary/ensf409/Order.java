package edu.ucalgary.ensf409;

import java.util.*;

class Order
{
    private ArrayList<Hamper> hampers;

    //Order constructor
    public Order()
    {
        this.hampers = new ArrayList<Hamper>();
    }

    //Adds a hamper to the ArrayList of Hampers in this Order
    public void addToOrder(Hamper hamper)
    {
        this.hampers.add(hamper);
    }

    //Calculates the total nutrition requirements for each Hamper in this Order and stores 
    //the results in an array of type Nutrition
    public Nutrition[] calculateNutrition()
    {
        Nutrition[] totalNutrition = new Nutrition[this.hampers.length];

        int i = 0;
        for(Hamper hamper : this.hampers)
        {
            float totalCals = 0;
            float grainsCals = 0;
            float fVCals = 0;
            float proteinCals = 0;
            float otherCals = 0;
            
            for(Food food : hamper.getFood())
            {
                int currentNutrition = food.getNutrition();
                int currentCals = currentNutrition.getTotalCals();
                totalCals += currentCals;
                grainsCals += currentCals * currentNutrition.getPercentGrains() * 0.01;
                fVCals += currentCals * currentNutrition.getPercentFV() * 0.01;
                proteinCals += currentCals * currentNutrition.getPercentProtein() * 0.01;
                otherCals += currentCals * currentNutrition.getPercentOther() * 0.01;
            }

            float percentGrains = grainsCals / totalCals;
            float percentFV = fVCals / totalCals;
            float percentProtein = proteinCals / totalCals;
            float percentOther = otherCals / totalCals;

            totalNutrition[i] = new Nutrition(totalCals, percentGrains, percentFV, percentProtein, percentOther);
            i++;
        }

        return totalNutrition;
    }

    //Returns ArrayList of Hampers from this Order
    public ArrayList<Hamper> getHampers()
    {
        return this.hampers;
    }
}