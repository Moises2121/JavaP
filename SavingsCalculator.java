//Moises Sanchez. Sophia Java Project

// I import the Scanner class to read user input
import java.util.Scanner;
import javax.print.attribute.standard.PrinterInfo;
import javax.swing.event.HyperlinkEvent;

public class SavingsCalculator {
    // This is where my program starts
    public static void main(String[] args) {
        //  create a Scanner to get input from the user
        Scanner scanner = new Scanner(System.in);
        // I'm using a default annual income increase of 10%
        double annualIncreaseRate = 10;

        //  ask if the user wants to adjust the income increase rate
        System.out.println("Do you want to adjust the annual income increase rate? (yes/no)");
        String adjustRate = scanner.next();
        if (adjustRate.equalsIgnoreCase("yes")) {
            // make sure the input is a valid number
            while (true) {
                try {
                    System.out.print("Enter annual income increase rate (%): ");
                    annualIncreaseRate = scanner.nextDouble();
                    if (annualIncreaseRate < 0) {
                        System.out.println("Please enter a non-negative percentage.");
                    } else {
                        break; 
                    }
                } catch (Exception e) {
                    System.out.println("Invalid. Please enter a number.");
                    scanner.next(); //  clear the invalid input
                }
            }
        }

        // loop to calculate savings until the user stops
        boolean continueCalculating = true;
        while (continueCalculating) {
            System.out.print("Enter your name: ");
            String name = scanner.next();

            //  get and validate the monthly income
            while (true) {
                try {
                    System.out.print("Enter your monthly income: $");
                    double monthlyIncome = scanner.nextDouble();
                    if (monthlyIncome <= 0) {
                        System.out.println("Please enter a positive income.");
                    } else {
                        // handle the rest in processSavings
                        processSavings(scanner, name, monthlyIncome, annualIncreaseRate);
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid. Please enter a number.");
                    scanner.next(); // Clear the invalid input
                }
            }

            //  ask if the user wants to restart
            continueCalculating = askToRestart(scanner);
        }
        //  close the scanner and say thanks!
        scanner.close();
        System.out.println("Thanks for using the Savings Calculator!");
    }

    // I handle the savings calculation and input validation here
    public static void processSavings(Scanner scanner, String name, double monthlyIncome, double annualIncreaseRate) {
        while (true) {
            try {
                System.out.print("Enter monthly savings percentage: ");
                double savingsPercentage = scanner.nextDouble();
                if (savingsPercentage < 0 || savingsPercentage > 100) {
                    System.out.println("Please enter a percentage between 0 and 100.");
                } else {
                    System.out.print("Enter time period (years): ");
                    int years = scanner.nextInt();
                    if (years <= 0) {
                        System.out.println("Please enter a positive number of years.");
                    } else {
                        //  calculate and show the results
                       
                        double[] result = calculateSavings(monthlyIncome, savingsPercentage, years, annualIncreaseRate);
                       
                        System.out.printf("%s, in %d years, you can save **$%,.2f!** If you save **%.0f%% ($%,.2f monthly)**\n",
                                name, years, result[0], savingsPercentage, result[1]);
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear the invalid input
            }
        }
    }
    
    // I ask the user if they want to restart, and validate yes/no
    public static boolean askToRestart(Scanner scanner) {
        while (true) {
            System.out.print("Do you want to restart? (yes/no): ");
            String restart = scanner.next();
            if (restart.equalsIgnoreCase("yes")) {
                return true;
            } else if (restart.equalsIgnoreCase("no")) {
                return false;
            } else {
                System.out.println("Please enter 'yes' or 'no'.");
            }
        }
    }

    // I do the actual savings calculation here
    public static double[] calculateSavings(double monthlyIncome, double savingsPercentage, int years, double annualIncreaseRate) {
        double totalSavings = 0;
        double monthlySavings = monthlyIncome * (savingsPercentage / 100);
        double initialMonthlySavings = monthlySavings;

        //  loop through each year and month to calculate savings
        for (int year = 0; year < years; year++) {
            for (int month = 0; month < 12; month++) {
                totalSavings += monthlySavings;
            }
            monthlyIncome *= (1 + annualIncreaseRate / 100);
            
            monthlySavings = monthlyIncome * (savingsPercentage / 100);
        }
        return new double[] { totalSavings, initialMonthlySavings };
    }
}