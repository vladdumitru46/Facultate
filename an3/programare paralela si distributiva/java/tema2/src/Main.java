import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int k = 0;
        int n = 0;
        int m = 0;
        int p = 3;
        p = Integer.parseInt(args[0]);

        int[][] v;
        int[][] c = null;
        int[][] f = null;

        String fileName = "C:\\Users\\vladb\\Desktop\\Facultate\\an3\\programare paralela si distributiva\\java\\tema1java\\resources\\date1.txt";
        String fileOutputName = "C:\\Users\\vladb\\Desktop\\Facultate\\an3\\programare paralela si distributiva\\java\\tema1java\\resources\\output";
        String fileOutputName2 = "C:\\Users\\vladb\\Desktop\\Facultate\\an3\\programare paralela si distributiva\\java\\tema1java\\resources\\output2";
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                if (line.startsWith("k:3")) {
                    List<String> l = List.of(line.split(":"));
                    k = Integer.parseInt(l.get(1));
                    line = scanner.nextLine().trim();
                    List<String> list = List.of(line.split(" "));
                    c = new int[k][k];
                    for (int i = 0; i < k; i++) {
                        for (int j = 0; j < k; j++) {
                            c[i][j] = Integer.parseInt(list.get(j));
                        }
                        line = scanner.nextLine().trim();
                        list = List.of(line.split(" "));
                    }
                } else if (line.equals("n: 1000 m: 1000")) {
                    List<String> l = List.of(line.split(" "));
                    String nStr = l.get(1);
                    String mStr = l.get(3);

                    n = Integer.parseInt(nStr);
                    m = Integer.parseInt(mStr);
                    line = scanner.nextLine().trim();
                    List<String> list = List.of(line.split(" "));
                    f = new int[n][m];
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < m; j++) {
                            f[i][j] = Integer.parseInt(list.get(j));
                        }
                        line = scanner.nextLine().trim();
                        list = List.of(line.split(" "));
                    }
                }
            }
            v = new int[n][m];
            scanner.close();
            long startTime = System.nanoTime();
            sequential(n, m, k, v, c, f);
            long endTime = System.nanoTime();
            saveMatrixToFile(v, fileOutputName);
            System.out.println((double) (endTime - startTime) / 1E6);//ms
            startTime = System.nanoTime();
            sequentialParallelOnColumns(n, m, k, v, c, f, p);
            endTime = System.nanoTime();
            saveMatrixToFile(v, fileOutputName2);
            System.out.println((double) (endTime - startTime) / 1E6);//ms
            startTime = System.nanoTime();
            sequentialParallelOnLines(n, m, k, v, c, f, p);
            endTime = System.nanoTime();
            System.out.println((double) (endTime - startTime) / 1E6);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[][] generateRandomMatrix(int n, int m) {
        int[][] matrix = new int[n][m];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = random.nextInt(100);
            }
        }

        return matrix;
    }

    public static void saveMatrixToFile(int[][] matrix, int[][] f, String fileName) {
        List<Integer> list = new ArrayList<>();

        list.add(3);
        list.add(10);
        list.add(10);
        list.add(5);
        list.add(1000);
        list.add(1000);
        list.add(5);
        list.add(10);
        list.add(1000);
        list.add(5);
        list.add(1000);
        list.add(10);
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (int l = 0; l < list.size() - 2; l += 3) {
                System.out.println(list.get(l) + " " + list.get(l + 1) + " " + list.get(l + 2));
                int k = list.get(l);
                writer.println("k:" + k);
                matrix = generateRandomMatrix(k, k);
                for (int i = 0; i < k; i++) {
                    for (int j = 0; j < k; j++) {
                        writer.print(matrix[i][j] + " ");
                    }
                    writer.println();
                }
                writer.println();
                int n = list.get(l + 1);
                int m = list.get(l + 2);
                writer.println("n: " + n + " m: " + m);
                f = generateRandomMatrix(n, m);
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        writer.print(f[i][j] + " ");
                    }
                    writer.println();
                }
                writer.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sequential(int n, int m, int k, int[][] v, int[][] c, int[][] f) {
        Threads threads = new Threads(k, n, m, 0, v, c, f);
        threads.broadMatrix();
        if (k == 3) {
            for (int i = 1; i < n - 1; i++) {
                for (int j = 1; j < m - 1; j++) {
                    if (k == 3) {
                        v[i][j] = f[i][j] * c[1][1] + f[i - 1][j] * c[0][1] + f[i][j - 1] * c[1][0] + f[i - 1][j - 1] * c[0][0] + f[i + 1][j] * c[2][1] + f[i][j + 1] * c[1][2] + f[i + 1][j + 1] * c[2][2];
                    }
                }
            }
        }else if (k == 5) {
            for (int i = 2; i < n - 2; i++) {
                for (int j = 2; j < m - 2; j++) {
                    v[i][j] = f[i][j] * c[2][2] + f[i - 2][j] * c[0][2] + f[i - 1][j] * c[1][2] +
                            f[i + 1][j] * c[3][2] + f[i + 2][j] * c[4][2] + f[i][j - 2] * c[2][0] +
                            f[i][j - 1] * c[2][1] + f[i][j + 1] * c[2][3] + f[i][j + 2] * c[2][4] +
                            f[i - 2][j - 2] * c[0][0] + f[i - 2][j - 1] * c[0][1] + f[i - 1][j - 2] * c[1][0] +
                            f[i - 1][j - 1] * c[1][1] + f[i - 1][j + 1] * c[1][3] + f[i - 1][j + 2] * c[1][4] +
                            f[i + 1][j - 2] * c[3][0] + f[i + 1][j - 1] * c[3][1] + f[i + 1][j + 1] * c[3][3] +
                            f[i + 1][j + 2] * c[3][4] + f[i + 2][j - 2] * c[4][0] + f[i + 2][j - 1] * c[4][1] +
                            f[i + 2][j + 1] * c[4][3] + f[i + 2][j + 2] * c[4][4];
                }
            }
        }
    }

    private static void sequentialParallelOnLines(int n, int m, int k, int[][] v, int[][] c, int[][] f, int p) {
        int start = 0;
        int cat = n / p;
        int rest = n % p;

        Threads[] threads = new Threads[p];
        for (int i = 0; i < p; i++) {
            int finish = start + cat - 1;
            if (rest > 0) {
                finish++;
                rest--;
            }
            threads[i] = new Threads(k, n, m, p, v, c, f);
            threads[i].start();
            start = finish;
        }
        for (int i = 0; i < p; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void sequentialParallelOnColumns(int n, int m, int k, int[][] v, int[][] c, int[][] f, int p) {
        int start = 0;
        int cat = m / p;
        int rest = m % p;

        Threads[] threads = new Threads[p];
        for (int i = 0; i < p; i++) {
            int finish = start + cat - 1;
            if (rest > 0) {
                finish++;
                rest--;
            }
            threads[i] = new Threads(k, n, m, p, v, c, f);
            threads[i].start();
            start = finish;
        }
        for (int i = 0; i < p; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void saveMatrixToFile(int[][] matrix, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            int rows = matrix.length;
            int cols = matrix[0].length;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    writer.write(Integer.toString(matrix[i][j]));
                    if (j < cols - 1) {
                        writer.write(",");
                    }
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static class Threads extends Thread {
        private final int k;
        private final int n;
        private final int m;
        private final int p;
        private final int[][] v;
        private final int[][] c;
        private final int[][] f;

        public Threads(int k, int n, int m, int p, int[][] v, int[][] c, int[][] f) {
            this.k = k;
            this.n = n;
            this.m = m;
            this.p = p;
            this.v = v;
            this.c = c;
            this.f = f;
        }

        @Override
        public void run() {
            broadMatrix();
            if (k == 3) {
                for (int i = 1; i < n - 1; i++) {
                    for (int j = 1; j < m - 1; j++) {
                        if (k == 3) {
                            v[i][j] = f[i][j] * c[1][1] + f[i - 1][j] * c[0][1] + f[i][j - 1] * c[1][0] + f[i - 1][j - 1] * c[0][0] + f[i + 1][j] * c[2][1] + f[i][j + 1] * c[1][2] + f[i + 1][j + 1] * c[2][2];
                        }
                    }
                }
            }else if (k == 5) {
                for (int i = 2; i < n - 2; i++) {
                    for (int j = 2; j < m - 2; j++) {
                        v[i][j] = f[i][j] * c[2][2] + f[i - 2][j] * c[0][2] + f[i - 1][j] * c[1][2] +
                                f[i + 1][j] * c[3][2] + f[i + 2][j] * c[4][2] + f[i][j - 2] * c[2][0] +
                                f[i][j - 1] * c[2][1] + f[i][j + 1] * c[2][3] + f[i][j + 2] * c[2][4] +
                                f[i - 2][j - 2] * c[0][0] + f[i - 2][j - 1] * c[0][1] + f[i - 1][j - 2] * c[1][0] +
                                f[i - 1][j - 1] * c[1][1] + f[i - 1][j + 1] * c[1][3] + f[i - 1][j + 2] * c[1][4] +
                                f[i + 1][j - 2] * c[3][0] + f[i + 1][j - 1] * c[3][1] + f[i + 1][j + 1] * c[3][3] +
                                f[i + 1][j + 2] * c[3][4] + f[i + 2][j - 2] * c[4][0] + f[i + 2][j - 1] * c[4][1] +
                                f[i + 2][j + 1] * c[4][3] + f[i + 2][j + 2] * c[4][4];
                    }
                }
            }
        }

        private void broadMatrix() {
            int kernelSize = c.length;
            int offset = kernelSize / 2;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int sum = 0;

                    for (int u = -offset; u <= offset; u++) {
                        for (int v = -offset; v <= offset; v++) {
                            int ni = i + u;
                            int nj = j + v;

                            if (ni < 0) {
                                ni = 0;
                            }
                            if (ni >= n) {
                                ni = n - 1;
                            }
                            if (nj < 0) {
                                nj = 0;
                            }
                            if (nj >= m) {
                                nj = m - 1;
                            }

                            sum += f[ni][nj] * c[u + offset][v + offset];
                        }
                    }

                    v[i][j] = sum;
                }
            }
        }


    }
}