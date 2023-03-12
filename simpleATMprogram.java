package Prog;

/*
 	Name: John Paul Albania
 	Section: 1C
 	Activity Name: ATM Program
*/



import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class simpleATMprogram {

	public static void main(String[] args) {
		
		ArrayList<Double> atm = new ArrayList<Double>();
		Scanner input = new Scanner(System.in); 
		
		double withdraw, deposit, total, balance = 10000;
		int choose = 0;
		
		Random transactionNo = new Random();
		
		System.out.println("ATM Machine");
		System.out.println();
		System.out.println("\t1.[Deposit] 2.[Withdraw]");
		System.out.println("\t3.[Balance] 4.[Receipt]");
		System.out.println();
		
		while (choose < 4) 
		{
			System.out.println();
			System.out.print("Please select in the menu: ");
			choose = input.nextInt();
			
			
			
			if (choose == 1) {
				System.out.print("\n");
				double dep = input.nextDouble();
				
				if (dep != 100 && dep != 500 && dep != 1000) {
					System.out.println("Invalid deposit amount. Please enter 100, 500, or 1000.");
					continue;
				}
				
				if (balance + dep > 50000) {
					System.out.println("Deposit amount exceeds the maximum limit of 50,000.");
					continue;
				}
					
				atm.add((double) dep);
				balance = balance + dep;
			}
			if (choose == 2) {
				System.out.print("\n");
				double with = input.nextDouble();
				
				if (with > balance) {
					System.out.println("Invalid withdrawal amount. Please enter an amount not exceeding to your current balance.");
					continue;
					}
				
				atm.add((double) -with);
				balance = balance - with;
			}
			if (choose == 3) {
				System.out.println("\nYour balance is "+ balance);				
			}
			
			
			if (choose == 4) {
				
				try {
					LocalDateTime currentDateTime = LocalDateTime.now();
					DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
					String formattedDate = currentDateTime.format(date);
					String formattedTime = currentDateTime.format(time);
					FileWriter writer = new FileWriter("Receipt.txt");
					writer.write("-------------------------------------------------------------------");
					writer.write("\n\t\t   CENTRAL BICOL STATE OF AGRICULTURE");
					writer.write("\n\t\t\tImpig, Sipocot Camarines Sur");
					writer.write("\n\t\t\t   NOTICE OF TRANSACTION");
					writer.write("\n-------------------------------------------------------------------");
					writer.write("\nDate " + "\t\t\t\t\t" + "  : " + date.format(currentDateTime));
					writer.write("\nTime " + "\t\t\t\t\t" + "  : " + time.format(currentDateTime));
					int transactionNumber = transactionNo.nextInt(1000000); // Generate random transaction number
					writer.write("\nTransaction no." + "\t\t\t" + "  : " + transactionNumber);
					writer.write("\n--------------------------------------------------------------------");					
					writer.write("\nTransaction History: ");

					for (Double transaction : atm) {
						if (transaction > 0) {
							writer.write("\n\nTransaction " +  "\t\t\t " +" : Deposit ");	     
							writer.write("\nAmount" + "\t\t\t\t  " +": " + transaction);
							
						} else {
							writer.write("\n\nTransaction " +  "\t\t\t " +" : Withdrawal ");	     
							writer.write("\nAmount" + "\t\t\t\t  " +": " + transaction);
							
						}
					}					
					writer.write("\n\n\nAvailable Balance " + "\t\t  " + ": " + balance);
					writer.write("\n\n-------------------------------------------------------------------");
					writer.write("\nTHANK YOU FOR USING OUR ATM");
					writer.write("\nFor Customer Service, call: 2-001-456-5438");
					writer.close();
					System.out.println("Receipt file written successfully");
				} catch (IOException e) {
					System.out.println("Error writing receipt file.");
				}
                	System.exit(0);
            }
        }
    }
}
