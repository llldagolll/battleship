package org.example.domain.battleship;

import java.io.InputStream;

public class StandardInputStream extends InputStream {
    private StringBuilder sb = new StringBuilder();
    private String lf = System.getProperty("line.separator");

    public void inputln(String str) {
        sb.append(str).append(lf);
    }

    @Override
    public int read() {
        if (sb.length() == 0) return -1;
        int result = sb.charAt(0);
        sb.deleteCharAt(0);
        return result;
    }

}
