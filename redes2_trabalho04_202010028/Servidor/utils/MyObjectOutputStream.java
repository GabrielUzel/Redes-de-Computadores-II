package Servidor.utils;

import java.io.*;
 
public class MyObjectOutputStream extends ObjectOutputStream {
    // Constructors
    public MyObjectOutputStream() throws IOException { 
        super();
    }
    public MyObjectOutputStream(OutputStream object) throws IOException {
        super(object);
    }
 
    public void writeStreamHeader() throws IOException {
        return;
    }
}
