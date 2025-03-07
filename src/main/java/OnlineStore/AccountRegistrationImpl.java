package OnlineStore;

import org.omg.CORBA.ORB;

public class AccountRegistrationImpl extends AccountRegistrationPOA {
    private ORB orb;

    public void setORB(ORB orb_val) {
        orb = orb_val;
    }

    @Override
    public boolean registerAccount(String username, String password, String email) {
        try {
            AccountRegistrationServer.registerAccount(username, password, email);
            return true;
        } catch (Exception e) {
            System.err.println("❌ Error registering account: " + e.getMessage());
            return false;
        }
    }
}
