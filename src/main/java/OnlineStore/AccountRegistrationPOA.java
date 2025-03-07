package OnlineStore;


/**
* OnlineStore/AccountRegistrationPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from C:/Users/VICTUS/IdeaProjects/untitled14/src/main/resources/account_registration.idl
* Friday, March 7, 2025 1:59:13 PM ICT
*/

public abstract class AccountRegistrationPOA extends org.omg.PortableServer.Servant
 implements AccountRegistrationOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("registerAccount", new Integer (0));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    Integer __method = (Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // OnlineStore/AccountRegistration/registerAccount
       {
         String username = in.read_string ();
         String password = in.read_string ();
         String email = in.read_string ();
         boolean $result = false;
         $result = this.registerAccount (username, password, email);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:OnlineStore/AccountRegistration:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public AccountRegistration _this() 
  {
    return AccountRegistrationHelper.narrow(
    super._this_object());
  }

  public AccountRegistration _this(org.omg.CORBA.ORB orb) 
  {
    return AccountRegistrationHelper.narrow(
    super._this_object(orb));
  }


} // class AccountRegistrationPOA
