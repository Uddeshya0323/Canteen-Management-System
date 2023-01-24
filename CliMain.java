package com.Hexaware.CMS.Cli;

import java.util.Scanner;
/**
 * CliMain used as Client interface for java coding.
 * @author AmritaC
 */
import com.Hexaware.CMS.Factory.OrderFactory;
import com.Hexaware.CMS.Model.Customer;
import com.Hexaware.CMS.Model.Menu;
import com.Hexaware.CMS.Model.OrderDetails;
import com.Hexaware.CMS.Model.Vendor;

public class CliMain {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		boolean inloop = true;

		while (inloop) {
			System.out.println("----------------------------------------");
			System.out.println("CANTEEN MANAGEMENT SYSTEM");
			System.out.println(" ");
			System.out.println("1. Show Menu");
			System.out.println("2. View as Vendor");
			System.out.println("3. Customer Login");
			System.out.println("4. Change Password");
			System.out.println("5. New Customer Registration");
			System.out.println("6. Exit");
			System.out.println("---------------------------------------");
			System.out.println("Enter your choice : ");
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				menuList();
				break;
			case 2:
				vendorProfile();
				break;
			case 3:
				customerProfile();
				break;
			case 4:
				changeCustomerPassword();
				break;
			case 5:
				newCustomer();
				break;
			case 6:
				inloop = false;
				Runtime.getRuntime().halt(0);
			default:
				System.out.println("Choose option 1 , 2, 3 , 4");
				// sc.close();
			}

		}
	}

	// new customer registration
	public static void newCustomer() {
		System.out.println("Enter the customer Id:");
		String cid = sc.next();
		System.out.println("Enter your Name:");
		String cname = sc.next();
		System.out.println("Enter your login id:");
		int Lid = sc.nextInt();
		System.out.println("Enter your password:");
		String pw = sc.next();
		System.out.println("Enter your phone number:");
		String phno = sc.next();
		System.out.println("Enter the amount to credit ito your wallet:");
		int wb = sc.nextInt();
		System.out.println("Enter your Email Address:");
		String address = sc.next();
		Customer newCustomer = new Customer(cid, cname, Lid, pw, phno, wb, address);
		Customer existCs = OrderFactory.validCustomer(newCustomer);
		if (existCs == null) {
			OrderFactory.newCustomer(newCustomer);

		} else {
			System.out.println("Customer ID already Exist Try a new one !");
		}

	}

	public static void changeCustomerPassword() {
		System.out.println("Enter the  customer Login id :");
		int lid = sc.nextInt();
		System.out.println("Enter the current password :");
		String cpwd = sc.next();
		System.out.println("Enter the new password :");
		String cnpwd = sc.next();
		Customer cs = OrderFactory.validateCustomerLogin(lid, cpwd);
		if (cs == null) {
			System.out.println("Login Id / Password is invalid !");
		} else {
			OrderFactory.updatePassword(lid, cnpwd);
		}
	}

	/**
	 * this method is to place food order.
	 */
	public static void placeOrder(Customer cs) {
		Menu[] mArray = OrderFactory.showFoodMenu();
		for (Menu m : mArray)
			System.out.println(m);
		boolean mFlag = false;
		System.out.println("Enter Food id :");
		int foodId = sc.nextInt();
		Menu mObj = null;
		for (Menu m : mArray) {
			if (m.getFoodId() == foodId) {
				mFlag = true;
				mObj = m;
				break;
			}
		}
		int venId = mObj.getVendorId();
		if (mFlag == false) {
			System.out.println(" Invalid Food Id");
			return;
		}
		System.out.println("Enter Food Quantity");
		int qty = sc.nextInt();
		int totalCost = qty * mObj.getFoodPrice();
		int wB = cs.getCustWalletBalance();
		String custId = cs.getCustId();
		// System.out.println("Amount to be paid : " + totalCost);
		if (wB > totalCost) {

			System.out.println("Proceed");
			System.out.println("Sufficient Balance found in wallet !");

			OrderFactory.placeOrder(venId, custId, foodId, qty, totalCost, wB);
			// System.out.println("To be implemented by you...");

			// System.out.println(" records inserted.. " + i );
		} else {
			System.out.println("Insufficient wallet balance..");
		}

	}

	public static void menuList() {
		Menu m[] = OrderFactory.showFoodMenu();
		for (Menu menu : m)
			System.out.println(menu);
		// System.out.println("Food Id" + " " + "Food Name" + " " + "Food Price" + " " +
		// " VendorId");
		// for (int i = 0; i < m.length; i++) {
		// System.out.println(m[i].getFoodId() + " " + m[i].getFoodName() + " " +
		// m[i].getFoodPrice()
		// + " " + m[i].getVendorId());
		// }
	}

	public static void acceptRejectOrder(Vendor vendorObject) {
		OrderDetails[] ord = OrderFactory.vendorOrdersPending(vendorObject.getVenId());
		for (OrderDetails a : ord) {
			System.out.println(a);
		}
		/*
		 * int venId = vendorObject.getVenId(); OrderDetails[] ods =
		 * OrderFactory.vendorOrderHistory(vendorObject.getVenId());
		 * 
		 * // for (OrderDetails o : ods)
		 * 
		 * // System.out.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n",
		 * "ORDER NO", "VENDOR ID", "CUSTOMER ID", // "FOOD ID", "ORDER QTY",
		 * "ORDER DATE TIME", "ORDER VALUE", "ORDER STATUS"); System.out.println(" ");
		 * for (OrderDetails o : ods) System.out.println(o);
		 * //System.out.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n",
		 * o.getOrderId(), o.getVenId(), //o.getCustId(), o.getFoodId(),
		 * o.getQuantity(), o.getDateTime(), o.getOrderAmount(), //o.getOrderStatus());
		 */
		System.out.println(" ");
		System.out.println("\nEnter Order no to accept or reject order");
		int ordId = sc.nextInt();
		OrderDetails[] odsek = OrderFactory.validateOrderDetails(ordId);
		if (odsek == null)
			System.out.println("Invalid Order id");
		else {
			System.out.println("To accept or reject press any one of the options : ");
			// System.out.println("To be implemented by you... based on orderid, vendorid");
			String str = OrderFactory.acceptRejectOrder(ordId);
			System.out.println(str);
		}

	}

	/**
	 * this method is for customerProfile.
	 */
	public static void customerProfile() {
		Customer[] cArray = OrderFactory.customerProfile();
		for (Customer cs : cArray) {
			System.out.println(cs);
		}
		System.out.println(" Login - Customer  ");

		System.out.println("Enter Login Id:");
		int custLoginId = sc.nextInt();
		sc.nextLine();

		System.out.println("Enter Password:");
		String custPassword = sc.nextLine();
		Customer cs = OrderFactory.validateCustomerLogin(custLoginId, custPassword);
		if (cs != null) {
			System.out.println(" Successfully Logged in !");
			System.out.println("***********");
			System.out.println(cs);
			System.out.println("1. Place Order");
			System.out.println("2. View Order History");
			System.out.println("3. Cancel Order");
			System.out.println("4. View Wallet Balance");
			System.out.println(" ");
			System.out.println("Enter your Choice");

			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				placeOrder(cs);
				break;

			case 2:
				OrderDetails[] od = OrderFactory.customerOrderHistory(cs.getCustId());
				for (OrderDetails o : od)
					System.out.println(o);
				break;

			case 3:
				OrderDetails[] pendingOrdersArr = OrderFactory.pendingOrderDetails(cs.getCustId());
				for (OrderDetails o : pendingOrdersArr) {
					System.out.println(o);
				}
				System.out.print("Choss the Order Id of Food You want to Cancel : ");
				int orderId = sc.nextInt();
				OrderDetails ordD = OrderFactory.validatePendingOrder(orderId);
				if (ordD != null) {
					OrderFactory.cancelOrder(orderId);
				} else {
					System.out.println("Not Valid Order Id");
				}
				break;
			case 4:
				int wb = OrderFactory.customerWalletBalance(cs.getCustId());
				System.out.print("Your Wallet Balance is " + wb + " Rs");
			default:
				System.out.println(" Invalid choice ");
				break;
			}

		} else {
			System.out.println("Invalid login id or password....");
		}
	}

	/**
	 * this method is for VendorProfile.
	 */
	public static void vendorProfile() {
		// call vendorProfileDb() -> returns Vendor[] via OrderFactory

		Vendor[] venArray = OrderFactory.vendorProfile();
		// display all vendors from Vendor[]
		for (Vendor v : venArray)
			System.out.println(v);

		// prompt the user -> Enter Vendor ID:
		System.out.println(" Enter Vendor Id: ");
		int venId = sc.nextInt();

		// fetch vendorObject for this VendorId
		Vendor ven = OrderFactory.validateVendor(venId);
		// if valid vendorObject
		if (ven == null) {
			System.out.println(" Invalid Vendor Id");
		} else {

			System.out.println(" Press 1 - Accept Or Reject Order \r\n " + "Press 2 - See Vendor Order history \n"
					+ " Press any key to exit ");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				acceptRejectOrder(ven);
				break;
			case 2:
				OrderDetails[] od = OrderFactory.vendorOrderHistory(ven.getVenId());
				for (OrderDetails o : od)
					System.out.println(o);
				break;
			default:
				System.out.println(" Invalid choice ");
			}
		}
	}
}
