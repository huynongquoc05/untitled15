package OnlineStore;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

public class AccountRegistrationServer {
    // Cấu hình DataSource
    private static final SQLServerDataSource ds = new SQLServerDataSource();

    static {
        ds.setServerName("localhost"); // Đổi thành địa chỉ server của bạn
        ds.setPortNumber(1433);
        ds.setDatabaseName("OnlineStoreDB");
        ds.setUser("sa");  // Thay bằng user SQL Server của bạn
        ds.setPassword("051120"); // Thay bằng mật khẩu SQL Server
        ds.setEncrypt(true);
        ds.setTrustServerCertificate(true);
    }

    public static void registerAccount(String username, String password, String email) {
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.executeUpdate();

            System.out.println("Account registered successfully in database!");

        } catch (SQLException e) {
            System.err.println("Error registering account: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        try {
            // Khởi tạo ORB
            ORB orb = ORB.init(args, null);

            // Lấy tham chiếu RootPOA và kích hoạt POAManager
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            // Tạo đối tượng triển khai dịch vụ
            AccountRegistrationImpl accountRegImpl = new AccountRegistrationImpl();
            accountRegImpl.setORB(orb);

            // Lấy tham chiếu CORBA từ đối tượng triển khai
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(accountRegImpl);
            AccountRegistration href = AccountRegistrationHelper.narrow(ref);

            // Lấy Naming Service
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Đăng ký dịch vụ vào Naming Service
            NameComponent path[] = ncRef.to_name("AccountRegistration");
            ncRef.rebind(path, href);

            System.out.println("AccountRegistration Service is running...");

            // Chạy ORB để lắng nghe request
            orb.run();
        } catch (Exception e) {
            System.err.println("Error: " + e);
            e.printStackTrace(System.out);
        }
    }
}
