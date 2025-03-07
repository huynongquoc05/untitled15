package OnlineStore;

import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextExtHelper;

import java.util.Scanner;

public class AccountRegistrationClient {
    public static void main(String[] args) {
        try {
            // Khởi tạo ORB
            ORB orb = ORB.init(args, null);

            // Lấy Naming Service
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Tìm dịch vụ trong Naming Service
            AccountRegistration accountReg = AccountRegistrationHelper.narrow(ncRef.resolve_str("AccountRegistration"));

            // Giao diện nhập liệu cho người dùng
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            System.out.print("Enter email: ");
            String email = scanner.nextLine();

            // Gọi dịch vụ từ xa
            boolean success = accountReg.registerAccount(username, password, email);
            if (success) {
                System.out.println("Account registered successfully for user: " + username);
            } else {
                System.out.println("Registration failed: Username '" + username + "' already exists.");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e);
            e.printStackTrace();
        }
    }
}
