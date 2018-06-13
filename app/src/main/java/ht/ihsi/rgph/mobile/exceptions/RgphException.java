/**
 * 
 */
package ht.ihsi.rgph.mobile.exceptions;

/**
 * @author Jordany
 *
 */
@SuppressWarnings("serial")
public class RgphException extends Exception {
	
	private Throwable cause = null;
	
	  public RgphException() {
	    super();
	  }
	  
	  public RgphException(String message) { 
	    super(message);
	  }
	  
	  public RgphException(String message, Throwable cause) {
	    super(message);
	    this.cause = cause;
	  }
	  
	  public Throwable getCause() {
	    return cause;
	  }
	  
	  public void printStackTrace() {
	    super.printStackTrace();
	    if (cause != null) {
	      System.err.println("Caused by:");
	      cause.printStackTrace();
	    }
	  }
	  
	  public void printStackTrace(java.io.PrintStream ps) {
	    super.printStackTrace(ps);
	    if (cause != null) {
	      ps.println("Caused by:");
	      cause.printStackTrace(ps);
	    }
	  }
	  
	  public void printStackTrace(java.io.PrintWriter pw) {
	    super.printStackTrace(pw);
	    if (cause != null) {
	      pw.println("Caused by:");
	      cause.printStackTrace(pw);
	    }
	  }

}
