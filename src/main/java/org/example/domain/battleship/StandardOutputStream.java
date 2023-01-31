package org.example.domain.battleship;

import java.io.*;

public class StandardOutputStream extends PrintStream {
    private BufferedReader br = new BufferedReader(new StringReader(""));

    public StandardOutputStream(){
        super(new ByteArrayOutputStream());
    }

    public String readLine() {
        String line = "";
        try {
            if((line = br.readLine()) != null) return line;
            br = new BufferedReader(new StringReader(out.toString()));
            ((ByteArrayOutputStream) out).reset();
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
