package org.httpsystemout.stream;

import java.io.IOException;
import java.io.PrintStream;

/**
 * PrintStream splitter. What a pity that it is needed and also needs several overrides.
 */
public class SplitPrintStream extends PrintStream {

    private final PrintStream first;
    private final PrintStream second;

    public SplitPrintStream(PrintStream first, PrintStream second) {
        super(first, true);

        this.first = first;
        this.second = second;
    }

    @Override
    public void write(int b) {
        first.write(b);
        second.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        first.write(b);
        second.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) {
        first.write(b, off, len);
        second.write(b, off, len);
    }



    /**
     * Terminates the current line by writing the line separator string.  The
     * line separator string is defined by the system property
     * <code>line.separator</code>, and is not necessarily a single newline
     * character (<code>'\n'</code>).
     */
    @Override
    public void println() {
        first.println();
        second.println();
    }

    /**
     * Prints a boolean and then terminate the line.  This method behaves as
     * though it invokes <code>{@link #print(boolean)}</code> and then
     * <code>{@link #println()}</code>.
     *
     * @param x  The <code>boolean</code> to be printed
     */
    @Override
    public void println(boolean x) {
        first.println(x);
        second.println(x);
    }

    /**
     * Prints a character and then terminate the line.  This method behaves as
     * though it invokes <code>{@link #print(char)}</code> and then
     * <code>{@link #println()}</code>.
     *
     * @param x  The <code>char</code> to be printed.
     */
    public void println(char x) {
        first.println(x);
        second.println(x);
    }

    /**
     * Prints an integer and then terminate the line.  This method behaves as
     * though it invokes <code>{@link #print(int)}</code> and then
     * <code>{@link #println()}</code>.
     *
     * @param x  The <code>int</code> to be printed.
     */
    @Override
    public void println(int x) {
        first.println(x);
        second.println(x);
    }

    /**
     * Prints a long and then terminate the line.  This method behaves as
     * though it invokes <code>{@link #print(long)}</code> and then
     * <code>{@link #println()}</code>.
     *
     * @param x  a The <code>long</code> to be printed.
     */
    @Override
    public void println(long x) {
        first.println(x);
        second.println(x);
    }

    /**
     * Prints a float and then terminate the line.  This method behaves as
     * though it invokes <code>{@link #print(float)}</code> and then
     * <code>{@link #println()}</code>.
     *
     * @param x  The <code>float</code> to be printed.
     */
    public void println(float x) {
        first.println(x);
        second.println(x);
    }

    /**
     * Prints a double and then terminate the line.  This method behaves as
     * though it invokes <code>{@link #print(double)}</code> and then
     * <code>{@link #println()}</code>.
     *
     * @param x  The <code>double</code> to be printed.
     */
    @Override
    public void println(double x) {
        first.println(x);
        second.println(x);
    }

    /**
     * Prints an array of characters and then terminate the line.  This method
     * behaves as though it invokes <code>{@link #print(char[])}</code> and
     * then <code>{@link #println()}</code>.
     *
     * @param x  an array of chars to print.
     */
    @Override
    public void println(char[] x) {
        first.println(x);
        second.println(x);
    }

    /**
     * Prints a String and then terminate the line.  This method behaves as
     * though it invokes <code>{@link #print(String)}</code> and then
     * <code>{@link #println()}</code>.
     *
     * @param x  The <code>String</code> to be printed.
     */
    @Override
    public void println(String x) {
        first.println(x);
        second.println(x);
    }

    /**
     * Prints an Object and then terminate the line.  This method calls
     * at first String.valueOf(x) to get the printed object's string value,
     * then behaves as
     * though it invokes <code>{@link #print(String)}</code> and then
     * <code>{@link #println()}</code>.
     *
     * @param x  The <code>Object</code> to be printed.
     */
    @Override
    public void println(Object x) {
        first.println(x);
        second.println(x);
    }


    @Override
    public void flush() {
        first.flush();
        second.flush();
    }

    @Override
    public void close() {
        try {
            first.close();
        } finally {
            second.close();
        }
    }

}

