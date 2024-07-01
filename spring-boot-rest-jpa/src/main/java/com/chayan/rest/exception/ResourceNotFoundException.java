package com.chayan.rest.exception;

public class ResourceNotFoundException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 696410918227189228L;

  public ResourceNotFoundException(String message, Throwable cause) {
    super(message, cause);

  }

  public ResourceNotFoundException(String message) {
    super(message);

  }



}
