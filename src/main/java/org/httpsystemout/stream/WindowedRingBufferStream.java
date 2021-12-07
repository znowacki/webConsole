package org.httpsystemout.stream;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;


public class WindowedRingBufferStream extends OutputStream {

    byte[] data;
    int dataCapacity;
    int window;
    int position = 0;

    public WindowedRingBufferStream(int window, int bufferRate) {
        this.window = window;
        this.dataCapacity = window * bufferRate;
        data = new byte[window * bufferRate];
    }

    @Override
    public synchronized void write(int b) {
        if (position == dataCapacity) {
            System.arraycopy(data, position -window, data, 0, window);
            position = window;
        }
        data[position++] = (byte) b;
    }

    /**
     * Writes <code>len</code> bytes from the specified byte array
     * starting at offset <code>off</code> to this byte array output stream.
     *
     * @param   b     the data.
     * @param   off   the start offset in the data.
     * @param   len   the number of bytes to write.
     */
    @Override
    public synchronized void write(byte[] b, int off, int len) {
        if (len > dataCapacity) {
            len = dataCapacity;
        }
        if (len > window) { //opportunity to start from beginning
            position = 0;
        }
        if (position + len > dataCapacity) { // move back, not enough place
            System.arraycopy(data, position - window, data, 0, window);
            position = window;
        }
        System.arraycopy(b, off, data, position, len);
        position += len;
    }


    /**
     * Reset the buffer
     */
    public synchronized void reset() {
        position = 0;
    }

    /**
     * Returns part of the buffer (window) as a String
     * @param encoding string encoding
     * @return string
     */
    public synchronized String windowToString(final String encoding) {
        int size = size();
        int start = position - size;
        try {
            return new String(data, start, size, encoding);
        } catch (UnsupportedEncodingException e) {
            return new String(data, start, size);
        }
    }

    /**
     * Returns part of the buffer (window) as a list of Strings
     * Uses line.separator to divide the buffered string
     * @param encoding string encoding
     * @return string
     */
    public synchronized List<String> windowToLines(int lines, final String encoding) {
        String[] s = windowToString(encoding).split(System.getProperty("line.separator"));
        int len = s.length;
        if (len<=lines) {
            return Arrays.asList(s);
        } else {
            return Arrays.asList(s).subList(len-lines,len);
        }
    }

    /**
     * Returns the current size of the observable window.
     */
    public synchronized int size() {
        return Math.min(position, window);
    }

}