package org.example;


public class Main {
    public static void main(String[] args) {
        int n = 5000000;
        int p = 16;
        int[] v1 = new int[n];
        int[] v2 = new int[n];
        int[] v3 = new int[n];

        for (int i = 0; i < n; i++) {
            v1[i] = i + 1;
            v2[i] = -i;
        }
        long startTime = System.nanoTime();
        sequentialPSum(v1, v2, v3, n, p);
        long endTime = System.nanoTime();
        System.out.println("paralel:    " + (double) (endTime - startTime));
        startTime = System.nanoTime();
        sequentialSum(v1, v2, v3, n);
        endTime = System.nanoTime();
        System.out.println("sequential: " + (double) (endTime - startTime));
        startTime = System.nanoTime();
        try {
            cyclic(v1, v2, v3, n, p);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        endTime = System.nanoTime();
        System.out.println("cyclic: " + (double) (endTime - startTime));
//        for (int i = 0; i < n; i++) {
//            System.out.println(v3[i] + " ");
//        }

    }

    private static void sequentialSum(int[] v1, int[] v2, int[] v3, int n) {
        for (int i = 0; i < n; i++) {
            v3[i] = (int) (Math.pow(v1[i], 3) + Math.pow(v2[i], 3));;
        }
    }

    private static void sequentialPSum(int[] v1, int[] v2, int[] v3, int n, int p) {
        int start = 0;
        int cat = n / p;
        int rest = n % p;
        ThreadSum[] threadSums = new ThreadSum[p];
        for (int i = 0; i < p; i++) {
            int finish = start + cat - 1;
            if (rest > 0) {
                finish++;
                rest--;
            }
            threadSums[i] = new ThreadSum(v1, v2, v3, start, finish);

            threadSums[i].start();
            start = finish;
        }
        for (int i = 0; i < p; i++) {
            try {
                threadSums[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void cyclic(int[] v1, int[] v2, int[] v3, int n, int p) throws InterruptedException {
        Thread[] threads = new Thread[p];
        for (int i = 0; i < p; i++) {
            var th = new Thread(new ThreadSum2(v1, v2, v3, p, i, n));
            th.start();
            threads[i] = th;
        }
        for (int i = 0; i < p; i++) {
            threads[i].join();
        }
    }

    public static class ThreadSum extends Thread {
        private final int[] a;
        private final int[] b;
        private final int[] c;
        private final int n;
        private final int p;

        public ThreadSum(int[] a, int[] b, int[] c, int n, int p) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.n = n;
            this.p = p;
        }

        @Override
        public void run() {
            for (int i = n; i < p; i++) {
                c[i] = (int) (Math.pow(a[i], 3) + Math.pow(b[i], 3));
            }
        }
    }

    public static class ThreadSum2 implements Runnable {
        private final int[] a;
        private final int[] b;
        private final int[] c;
        private final int threadsNumber;
        private final int startPos;

        private final int n;

        public ThreadSum2(int[] a, int[] b, int[] c, int threadsNumber, int startPos, int n) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.threadsNumber = threadsNumber;
            this.startPos = startPos;
            this.n = n;
        }

        @Override
        public void run() {
            for (int i = startPos; i < n; i++) {
                c[i] = (int) (Math.pow(a[i], 3) + Math.pow(b[i], 3));
//                c[i] = a[i] + b[i];
            }
        }
    }
}
