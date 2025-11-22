Function calculateSavings(name, monthlyIncome, savingsPercentage, years, annualIncreaseRate)
  	Set totalSavings = 0
  	Set monthlySavings = monthlyIncome * (savingsPercentage / 100)
  		For year = 1 to years
    		For month = 1 to 12
      			Add monthlySavings to totalSavings
    		Increase monthlyIncome by monthlyIncome * (annualIncreaseRate / 100)
    		Update monthlySavings = monthlyIncome * (savingsPercentage / 100)
  	Return totalSavings, initialMonthlySavings = monthlyIncome * (savingsPercentage / 100)







Main Program
  	Set annualIncreaseRate = 10 // Default 10%
  	Input "Do you want to adjust the annual income increase rate? (yes/no)"
  	If user input is "yes"
    		Input annualIncreaseRate from user
  	Set continue = true
  	While continue is true
    		Input name from user
    		Input monthlyIncome from user
    		Input savingsPercentage from user
    		Input years from user
    		Call calculateSavings(name, monthlyIncome, savingsPercentage, years, annualIncreaseRate)
      			and get totalSavings, initialMonthlySavings
    		Output "<name>, in <years> years, you can save **$<totalSavings>!**
      			If you save **<savingsPercentage>% ($<initialMonthlySavings> monthly)**"
    		Input "Do you want to restart? (yes/no)"
    		If user input is "no"
      			Set continue = false
