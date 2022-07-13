package com.solar.exception;

public class FileStorageException extends RuntimeException {
    
	private static final long serialVersionUID = 4558451513906984970L;

	public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
