package com.tagtrade.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class EUException extends RuntimeException {

	private static final long serialVersionUID = -3345727793910715824L;
	
	private String messageCode;
	private String internalMessage;

	public EUException() {
		super();
	}

	public EUException(String messageCode, String internalMessage) {
		super(internalMessage);
		this.messageCode = messageCode;
		this.internalMessage = internalMessage;
	}
	
	  @Override
	  public void printStackTrace(PrintStream printstream) {
	    printstream.println("Exception: "+getClass().getName());
	    printstream.println("  MessageData: ");
	    printstream.println("    code: " + messageCode);
	    printstream.println("    title: " + internalMessage);
	    printstream.println("Stack Trace:");
	    super.printStackTrace(printstream);
	  }

	  @Override
	  public void printStackTrace(PrintWriter printwriter) {
	    printwriter.println("Exception: "+getClass().getName());
	    printwriter.println("  MessageData: ");
	    printwriter.println("    code: " + messageCode);
	    printwriter.println("    title: " + internalMessage);
	    printwriter.println("Stack Trace:");
	    super.printStackTrace(printwriter);
	  }

}
