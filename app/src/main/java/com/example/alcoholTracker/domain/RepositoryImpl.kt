package com.example.alcoholTracker.domain

import com.example.alcoholTracker.data.Drink
import com.example.alcoholTracker.data.HealthImprovement
import com.example.alcoholTracker.data.Milestone
import com.example.alcoholTracker.data.User
import com.example.alcoholTracker.database.AlcoholTrackerDatabase
import com.example.alcoholTracker.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val database: AlcoholTrackerDatabase
): Repository {

    override suspend fun getUser(): User {
        return database.userDao().getUser()
    }

    override suspend fun insertUser(user: User) {
        database.userDao().insertUser(user)
    }

    override suspend fun getDrinks(): List<Drink> {
        return listOf(
            Drink(id = 0, name = "Lager", type = "beer"),
            Drink(id = 1, name = "Ale", type = "beer"),
            Drink(id = 2, name = "Stout", type = "beer"),
            Drink(id = 3, name = "Pilsner", type = "beer"),
            Drink(id = 4, name = "Porter", type = "beer"),
            Drink(id = 5, name = "Wheat Beer", type = "beer"),
            Drink(id = 6, name = "IPA (India Pale Ale)", type = "beer"),
            Drink(id = 7, name = "Cabernet Sauvignon", type = "wine"),
            Drink(id = 8, name = "Merlot", type = "wine"),
            Drink(id = 9, name = "Pinot Noir", type = "wine"),
            Drink(id = 10, name = "Chardonnay", type = "wine"),
            Drink(id = 11, name = "Sauvignon Blanc", type = "wine"),
            Drink(id = 12, name = "Riesling", type = "wine"),
            Drink(id = 13, name = "Rosé Wine", type = "wine"),
            Drink(id = 14, name = "Champagne", type = "wine"),
            Drink(id = 15, name = "Prosecco", type = "wine"),
            Drink(id = 16, name = "Port", type = "wine"),
            Drink(id = 17, name = "Sherry", type = "wine"),
            Drink(id = 20, name = "Scotch", type = "whiskey"),
            Drink(id = 21, name = "Bourbon", type = "whiskey"),
            Drink(id = 22, name = "Rye", type = "whiskey"),
            Drink(id = 23, name = "Vodka", type = "spirit"),
            Drink(id = 24, name = "White Rum", type = "spirit"),
            Drink(id = 25, name = "Dark Rum", type = "spirit"),
            Drink(id = 26, name = "Spiced Rum", type = "spirit"),
            Drink(id = 27, name = "Tequila", type = "spirit"),
            Drink(id = 28, name = "Gin", type = "spirit"),
            Drink(id = 29, name = "Brandy", type = "spirit"),
            Drink(id = 30, name = "Cognac", type = "spirit"),
            Drink(id = 31, name = "Absinthe", type = "spirit"),
            Drink(id = 32, name = "Amaretto", type = "liqueur"),
            Drink(id = 33, name = "Baileys Irish Cream", type = "liqueur"),
            Drink(id = 34, name = "Cointreau", type = "liqueur"),
            Drink(id = 35, name = "Grand Marnier", type = "liqueur"),
            Drink(id = 36, name = "Kahlúa", type = "liqueur"),
            Drink(id = 37, name = "Sambuca", type = "liqueur"),
            Drink(id = 38, name = "Triple Sec", type = "liqueur"),
            Drink(id = 39, name = "Margarita", type = "cocktail"),
            Drink(id = 40, name = "Mojito", type = "cocktail"),
            Drink(id = 41, name = "Martini", type = "cocktail"),
            Drink(id = 42, name = "Old Fashioned", type = "cocktail"),
            Drink(id = 43, name = "Mimosa", type = "cocktail"),
            Drink(id = 44, name = "Daiquiri", type = "cocktail"),
            Drink(id = 45, name = "Manhattan", type = "cocktail")
        )
    }

    override suspend fun getHealthImprovements(): List<HealthImprovement> {
        return database.healthImprovementsDao().getAllHealthImprovements()
    }

    override suspend fun getHealthImprovementById(id: Int): HealthImprovement {
        return database.healthImprovementsDao().getHealthImprovementById(id)
    }

    override suspend fun insertAllHealthImprovements() {
        database.healthImprovementsDao().insertAllHealthImprovements(
            listOf(
                HealthImprovement(
                    name = "Immediate Hydration and Sleep Improvement",
                    description = "Alcohol is a diuretic, so stopping its consumption helps the body rehydrate. Initial withdrawal might cause sleep disturbances, but overall, sleep quality starts to improve.",
                    happensAfterGivenDays = 1
                ),
                HealthImprovement(
                    name = "Initial Stabilization and Symptom Relief",
                    description = "Without alcohol, blood sugar levels begin to stabilize. Symptoms associated with alcohol withdrawal start to diminish.",
                    happensAfterGivenDays = 3
                ),
                HealthImprovement(
                    name = "Notable Health Enhancements",
                    description = "Noticeable improvement in sleep quality and feeling more rested. Better skin hydration, which may result in a healthier complexion. The digestive system starts functioning more efficiently without the irritant effect of alcohol.",
                    happensAfterGivenDays = 7
                ),
                HealthImprovement(
                    name = "Significant Physical Benefits",
                    description = "The liver begins to repair itself, and liver fat can decrease by up to 15%. Blood pressure may decrease, and the risk of heart disease begins to drop. Reduction in calorie intake from alcohol can lead to weight loss.",
                    happensAfterGivenDays = 30
                ),
                HealthImprovement(
                    name = "Mental and Immune System Boost",
                    description = "Improved cognitive functions, better memory, and increased concentration. Significant reduction in symptoms of depression and anxiety. Immune system starts functioning more effectively.",
                    happensAfterGivenDays = 90
                ),
                HealthImprovement(
                    name = "Major Health Milestones",
                    description = "The liver can regenerate significantly, with many cells repaired. Lowered risk of mouth, liver, breast, and other cancers associated with alcohol consumption. General physical health is likely much improved, with better energy levels and physical performance.",
                    happensAfterGivenDays = 365
                ),
                HealthImprovement(
                    name = "Long-Term Health Improvements",
                    description = "The risk of alcohol-related cancers continues to decline. Continued lower risk of cardiovascular diseases, stroke, and heart attacks.",
                    happensAfterGivenDays = 1825
                ),
                HealthImprovement(
                    name = "Near-Normal Liver Function and Longevity",
                    description = "Liver function may be close to that of someone who has never consumed alcohol, assuming no other liver diseases. Increased life expectancy due to reduced risks of various chronic illnesses.",
                    happensAfterGivenDays = 3650
                ),
                HealthImprovement(
                    name = "Greatly Reduced Disease Risk and Healthy Aging",
                    description = "The risks of diseases associated with long-term alcohol consumption, such as certain cancers and liver diseases, are significantly reduced. Better quality of life and healthier aging process due to long-term abstinence.",
                    happensAfterGivenDays = 7300
                )
            )
        )
    }

    override suspend fun getMilestones(): List<Milestone> {
        return database.milestonesDao().getAllMilestones()
    }

    override suspend fun getMilestoneById(id: Int): Milestone {
        return database.milestonesDao().getMilestoneById(id)
    }

    override suspend fun insertAllMilestones() {
        database.milestonesDao().insertAllMilestones(
            listOf(
                Milestone(
                    name = "Lift Off",
                    description = "Lift Off",
                    icon = "money_24px"
                ),
                Milestone(
                    name = "10 skipped drinks",
                    description = "10 skipped drinks",
                    icon = "mail_24px"
                ),
                Milestone(
                    name = "7 sober days",
                    description = "7 sober days",
                    icon = "liquor_24px"
                )
            )
        )
    }
}