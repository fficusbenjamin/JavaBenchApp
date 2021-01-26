package com.example.javapj.Tests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class BrainTest {

    public enum OpT {INC, MOVE, PRINT, LOOP}

    public static final class Op {
        private static final Op[] NO_LOOP_OPS = new Op[0];

        public final OpT op;
        public final int v;
        public final Op[] loop;

        public Op(final OpT _op, final int _v) { op = _op; v = _v; loop = NO_LOOP_OPS; }
        public Op(final OpT _op, final Op[] _l) { op = _op; loop = _l; v = 0; }
    }

    public static final class CharacterIterator {
        private final String str;
        private final int strLen;
        private int pos = 0;

        public CharacterIterator(final String str) {
            this.str = str;
            strLen = str.length();
        }

        public boolean hasNext() {
            return pos < strLen;
        }

        public char next() {
            return str.charAt(pos++);
        }
    }

    public static final class Tape {
        private int[] tape;
        private int pos;

        public Tape() {
            tape = new int[1];
        }

        public int get() {
            return tape[pos];
        }

        public void inc(final int x) {
            tape[pos] += x;
        }

        public void move(final int x) {
            pos += x;
            while ( pos >= tape.length ) {
                this.tape = Arrays.copyOf(this.tape, this.tape.length * 2);
            }
        }
    }

    static final class Printer {
        private int sum1 = 0;
        private int sum2 = 0;
        final boolean quiet;

        Printer(boolean quiet) {
            this.quiet = quiet;
        }

        void print(int n) {
            if (quiet) {
                sum1 = (sum1 + n) % 255;
                sum2 = (sum2 + sum1) % 255;
            } else {
                System.out.print((char)n);
            }
        }

        int getChecksum() {
            return (sum2 << 8) | sum1;
        }
    }

    public static final class Program {
        private final Op[] ops;
        private final Printer p;

        public Program(final String code, final Printer p) {
            CharacterIterator it = new CharacterIterator(code);
            ops = parse(it);
            this.p = p;
        }

        private Op[] parse(final CharacterIterator it) {
            final List<Op> res = new ArrayList<>();
            while (it.hasNext()) {
                switch( it.next() ) {
                    case '+':
                        res.add(new Op(OpT.INC, 1));
                        break;
                    case '-':
                        res.add(new Op(OpT.INC, -1));
                        break;
                    case '>':
                        res.add(new Op(OpT.MOVE, 1));
                        break;
                    case '<':
                        res.add(new Op(OpT.MOVE, -1));
                        break;
                    case '.':
                        res.add(new Op(OpT.PRINT, 0));
                        break;
                    case '[':
                        res.add(new Op(OpT.LOOP, parse(it)));
                        break;
                    case ']':
                        return res.toArray(new Op[res.size()]);
                }
            }
            return res.toArray(new Op[res.size()]);
        }

        public void run() {
            _run(ops, new Tape());
        }

        private void _run(final Op[] program, final Tape tape) {
            for (final Op op : program)
                switch (op.op) {
                    case INC: tape.inc(op.v); break;
                    case MOVE: tape.move(op.v); break;
                    case LOOP: while (tape.get() > 0) _run(op.loop, tape); break;
                    case PRINT: p.print(tape.get()); break;
                }
        }
    }


    private static void verify() {
        final String text = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>" +
                "---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.";
        final Printer pLeft = new Printer(true);
        new Program(text, pLeft).run();
        final int left = pLeft.getChecksum();

        final Printer pRight = new Printer(true);
        for (final char c : "Hello World!\n".toCharArray()) {
            pRight.print(c);
        }
        final int right = pRight.getChecksum();
        if (left != right) {
            System.err.printf("%d != %d\n", left, right);
            System.exit(1);
        }
    }

    public static void main() throws IOException {
        String[] args = new String[0];

        verify();
        final String code = new String(Files.readAllBytes( Paths.get( args[0] ) ));
        final Printer p = new Printer(System.getenv("QUIET") != null);

        final double startTime = System.currentTimeMillis();
        new Program(code, p).run();
        final double elapsed = (System.currentTimeMillis()-startTime) / 1e3;

        System.err.println("time: " + elapsed +"s");
        if (p.quiet) {
            System.out.println("Output checksum: " + p.getChecksum());
        }
    }
}
