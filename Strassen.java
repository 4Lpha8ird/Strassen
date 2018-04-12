package strassen;

import java.io.*;

public class Strassen {

    public static int q = 2;

    public static void main(String[] args) {

        Strassen s = new Strassen();

        int o = 40;
        int u = 0;
        while (o > 0) {
            q = 2;
            int a = 100 + u;
            int k = 100 + u;
            int n = 100 + u;
            int[][] A = new int[a][k];
            int[][] B = new int[k][n];

            for (int i = 0; i < a; i++) {
                for (int j = 0; j < k; j++) {
                    A[i][j] = (int) Math.round(Math.random() * 10);

                }

            }

            for (int i = 0; i < k; i++) {
                for (int j = 0; j < n; j++) {
                    B[i][j] = (int) Math.round(Math.random() * 10);

                }

            }
            try (final FileWriter writer = new FileWriter("D:/Strass!!!.txt", true)) {
                final String z = Integer.toString(a);
                writer.write(z + "\t ");

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            while (q <= 40) {

                long startTime = System.currentTimeMillis();
                int[][] C = s.multiply(A, B);

                if (q == 40) {
                    long timeSpent = System.currentTimeMillis() - startTime;
                    System.out.println("программа выполнялась " + timeSpent + " миллисекунд");
                    try (final FileWriter writer = new FileWriter("D:/Strass!!!.txt", true)) {

                        final String t = Long.toString(timeSpent);
                        writer.write(t + "\t ");
                        writer.write(System.lineSeparator());

                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    long timeSpent = System.currentTimeMillis() - startTime;
                    System.out.println("программа выполнялась " + timeSpent + " миллисекунд");
                    try (final FileWriter writer = new FileWriter("D:/Strass!!!.txt", true)) {

                        final String t = Long.toString(timeSpent);
                        writer.write(t + "\t ");

                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
                q = q + 1;
            }

            o = o - 1;
            u = u + 100;
        }
    }

    public int[][] multiply(int[][] A, int[][] B) {

        int[][] R = new int[A.length][B[0].length];
        int f = 2;
        double g = 0;

        if (A.length <= A[0].length && A.length <= B.length && A.length <= B[0].length) {
            g = Math.log(A.length) / Math.log(f);
        } else if (A[0].length <= A.length && A[0].length <= B.length && A[0].length <= B[0].length) {
            g = Math.log(A[0].length) / Math.log(f);
        } else if (B.length <= A[0].length && B.length <= A.length && B.length <= B[0].length) {
            g = Math.log(B.length) / Math.log(f);
        } else if (B[0].length <= A[0].length && B[0].length <= B.length && B[0].length <= A.length) {
            g = Math.log(B[0].length) / Math.log(f);
        }

        double u = (int) Math.ceil(g);
        int m = (int) Math.pow(2, u) / 2;

        if (u <= q) {
            R = mul(A, B);
        } else {

            int c = A.length - m;
            int p = A[0].length - m;
            int r = B.length - m;
            int z = B[0].length - m;

            int[][] A11 = new int[m][m];

            int[][] A12 = new int[m][p];

            int[][] A21 = new int[c][m];

            int[][] A22 = new int[c][p];

            int[][] B11 = new int[m][m];

            int[][] B12 = new int[m][z];

            int[][] B21 = new int[r][m];

            int[][] B22 = new int[r][z];

            split(A, A11, 0, 0);

            split(A, A12, 0, m);

            split(A, A21, m, 0);

            split(A, A22, m, m);

            split(B, B11, 0, 0);

            split(B, B12, 0, m);

            split(B, B21, m, 0);

            split(B, B22, m, m);

            int[][] M1 = multiply(add(A11, A22), add(B11, B22));

            int[][] M2 = multiply(add(A21, A22), B11);

            int[][] M3 = multiply(A11, sub(B12, B22));

            int[][] M4 = multiply(A22, sub(B21, B11));

            int[][] M5 = multiply(add(A11, A12), B22);

            int[][] M6 = multiply(sub(A21, A11), add(B11, B12));

            int[][] M7 = multiply(sub(A12, A22), add(B21, B22));

            int[][] C11 = add(sub(add(M1, M4), M5), M7);

            int[][] C12 = add(M3, M5);

            int[][] C21 = add(M2, M4);

            int l = R.length - C11.length;
            int y = R[0].length - C11[0].length;

            int[][] C22 = addC22(subC22(addC22(M1, M3, l, y), M2, l, y), M6, l, y);

            join(C11, R, 0, 0);

            join(C12, R, 0, m);

            join(C21, R, m, 0);

            joinC22(C22, R, m, m, l, y);

        }

        return R;

    }

    public int[][] add(int[][] A, int[][] B) {

        int a = A.length;
        int k = A[0].length;
        int v = B.length;
        int n = B[0].length;

        if (A.length >= B.length && A[0].length >= B[0].length) {
            a = a;
            k = k;
        } else if (A.length <= B.length && A[0].length <= B[0].length) {
            a = v;
            k = n;
        } else if (A.length > B.length && A[0].length < B[0].length) {
            a = a;
            k = n;
        } else if (A.length < B.length && A[0].length > B[0].length) {
            a = v;
            k = k;
        }

        int[][] C = new int[a][k];

        if (A.length == B.length && A[0].length == B[0].length) {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[0].length; j++) {
                    C[i][j] = A[i][j] + B[i][j];
                }
            }
        } else if (A.length > B.length && A[0].length > B[0].length) {
            for (int i = 0; i < B.length; i++) {
                for (int j = 0; j < B[0].length; j++) {
                    C[i][j] = A[i][j] + B[i][j];
                }
            }
            for (int i = B.length; i < A.length; i++) {
                for (int j = B[0].length; j < A[0].length; j++) {
                    C[i][j] = A[i][j];
                }
            }
            for (int i = B.length; i < A.length; i++) {
                for (int j = 0; j < A[0].length; j++) {
                    C[i][j] = A[i][j];
                }
            }
            for (int i = 0; i < A.length; i++) {
                for (int j = B[0].length; j < A[0].length; j++) {
                    C[i][j] = A[i][j];
                }
            }
        } else if (A.length > B.length && A[0].length == B[0].length) {
            for (int i = 0; i < B.length; i++) {
                for (int j = 0; j < B[0].length; j++) {
                    C[i][j] = A[i][j] + B[i][j];
                }
            }
            for (int i = B.length; i < A.length; i++) {
                for (int j = 0; j < A[0].length; j++) {
                    C[i][j] = A[i][j];
                }
            }
        }
        if (A.length == B.length && A[0].length > B[0].length) {
            for (int i = 0; i < B.length; i++) {
                for (int j = 0; j < B[0].length; j++) {
                    C[i][j] = A[i][j] + B[i][j];
                }
            }
            for (int i = 0; i < A.length; i++) {
                for (int j = B[0].length; j < A[0].length; j++) {
                    C[i][j] = A[i][j];
                }
            }
        } else if (A.length < B.length && A[0].length < B[0].length) {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[0].length; j++) {
                    C[i][j] = A[i][j] + B[i][j];
                }
            }
            for (int i = A.length; i < B.length; i++) {
                for (int j = 0; j < B[0].length; j++) {
                    C[i][j] = B[i][j];
                }
            }
            for (int i = 0; i < B.length; i++) {
                for (int j = A[0].length; j < B[0].length; j++) {
                    C[i][j] = B[i][j];
                }
            }
            for (int i = A.length; i < B.length; i++) {
                for (int j = A[0].length; j < B[0].length; j++) {
                    C[i][j] = B[i][j];
                }
            }
        } else if (A.length < B.length && A[0].length == B[0].length) {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[0].length; j++) {
                    C[i][j] = A[i][j] + B[i][j];
                }
            }
            for (int i = A.length; i < B.length; i++) {
                for (int j = 0; j < B[0].length; j++) {
                    C[i][j] = B[i][j];
                }
            }
        } else if (A.length == B.length && A[0].length < B[0].length) {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[0].length; j++) {
                    C[i][j] = A[i][j] + B[i][j];
                }
            }
            for (int i = 0; i < B.length; i++) {
                for (int j = A[0].length; j < B[0].length; j++) {
                    C[i][j] = B[i][j];
                }
            }
        } else if (A.length > B.length && A[0].length < B[0].length) {
            for (int i = 0; i < B.length; i++) {
                for (int j = 0; j < A[0].length; j++) {
                    C[i][j] = A[i][j] + B[i][j];
                }
            }
            for (int i = B.length; i < A.length; i++) {
                for (int j = 0; j < A[0].length; j++) {
                    C[i][j] = A[i][j];
                }
            }
            for (int i = 0; i < B.length; i++) {
                for (int j = A[0].length; j < B[0].length; j++) {
                    C[i][j] = B[i][j];
                }
            }
        } else if (A.length < B.length && A[0].length > B[0].length) {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < B[0].length; j++) {
                    C[i][j] = A[i][j] + B[i][j];
                }
            }
            for (int i = A.length; i < B.length; i++) {
                for (int j = 0; j < B[0].length; j++) {
                    C[i][j] = B[i][j];
                }
            }
            for (int i = 0; i < A.length; i++) {
                for (int j = B[0].length; j < A[0].length; j++) {
                    C[i][j] = A[i][j];
                }
            }
        }

        return C;

    }

    public int[][] sub(int[][] A, int[][] B) {

        int a = A.length;
        int k = A[0].length;
        int v = B.length;
        int n = B[0].length;

        if (A.length >= B.length && A[0].length >= B[0].length) {
            a = a;
            k = k;
        } else if (A.length <= B.length && A[0].length <= B[0].length) {
            a = v;
            k = n;
        } else if (A.length > B.length && A[0].length < B[0].length) {
            a = a;
            k = n;
        } else if (A.length < B.length && A[0].length > B[0].length) {
            a = v;
            k = k;
        }

        int[][] C = new int[a][k];

        if (A.length == B.length && A[0].length == B[0].length) {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[0].length; j++) {
                    C[i][j] = A[i][j] - B[i][j];
                }
            }
        } else if (A.length > B.length && A[0].length > B[0].length) {
            for (int i = 0; i < B.length; i++) {
                for (int j = 0; j < B[0].length; j++) {
                    C[i][j] = A[i][j] - B[i][j];
                }
            }
            for (int i = B.length; i < A.length; i++) {
                for (int j = B[0].length; j < A[0].length; j++) {
                    C[i][j] = A[i][j];
                }
            }
            for (int i = B.length; i < A.length; i++) {
                for (int j = 0; j < A[0].length; j++) {
                    C[i][j] = A[i][j];
                }
            }
            for (int i = 0; i < A.length; i++) {
                for (int j = B[0].length; j < A[0].length; j++) {
                    C[i][j] = A[i][j];
                }
            }
        } else if (A.length > B.length && A[0].length == B[0].length) {
            for (int i = 0; i < B.length; i++) {
                for (int j = 0; j < B[0].length; j++) {
                    C[i][j] = A[i][j] - B[i][j];
                }
            }
            for (int i = B.length; i < A.length; i++) {
                for (int j = 0; j < A[0].length; j++) {
                    C[i][j] = A[i][j];
                }
            }
        } else if (A.length == B.length && A[0].length > B[0].length) {
            for (int i = 0; i < B.length; i++) {
                for (int j = 0; j < B[0].length; j++) {
                    C[i][j] = A[i][j] - B[i][j];
                }
            }
            for (int i = 0; i < A.length; i++) {
                for (int j = B[0].length; j < A[0].length; j++) {
                    C[i][j] = A[i][j];
                }
            }
        } else if (A.length < B.length && A[0].length < B[0].length) {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[0].length; j++) {
                    C[i][j] = A[i][j] - B[i][j];
                }
            }
            for (int i = A.length; i < B.length; i++) {
                for (int j = A[0].length; j < B[0].length; j++) {
                    C[i][j] = (-1) * B[i][j];
                }
            }
            for (int i = 0; i < B.length; i++) {
                for (int j = A[0].length; j < B[0].length; j++) {
                    C[i][j] = (-1) * B[i][j];
                }
            }
            for (int i = A.length; i < B.length; i++) {
                for (int j = 0; j < B[0].length; j++) {
                    C[i][j] = (-1) * B[i][j];
                }
            }
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[0].length; j++) {
                    C[i][j] = C[i][j];
                }
            }
        } else if (A.length < B.length && A[0].length == B[0].length) {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[0].length; j++) {
                    C[i][j] = A[i][j] - B[i][j];
                }
            }
            for (int i = A.length; i < B.length; i++) {
                for (int j = 0; j < A[0].length; j++) {
                    C[i][j] = (-1) * B[i][j];
                }
            }
        } else if (A.length == B.length && A[0].length < B[0].length) {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[0].length; j++) {
                    C[i][j] = A[i][j] - B[i][j];
                }
            }
            for (int i = 0; i < B.length; i++) {
                for (int j = A[0].length; j < B[0].length; j++) {
                    C[i][j] = (-1) * B[i][j];
                }
            }
        } else if (A.length > B.length && A[0].length < B[0].length) {
            for (int i = 0; i < B.length; i++) {
                for (int j = 0; j < A[0].length; j++) {
                    C[i][j] = A[i][j] - B[i][j];
                }
            }
            for (int i = B.length; i < A.length; i++) {
                for (int j = 0; j < A[0].length; j++) {
                    C[i][j] = A[i][j];
                }
            }
            for (int i = 0; i < B.length; i++) {
                for (int j = B[0].length; j < A[0].length; j++) {
                    C[i][j] = (-1) * B[i][j];
                }
            }
            for (int i = 0; i < B.length; i++) {
                for (int j = A[0].length; j < B[0].length; j++) {
                    C[i][j] = (-1) * B[i][j];
                }
            }
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[0].length; j++) {
                    C[i][j] = C[i][j];
                }
            }
        } else if (A.length < B.length && A[0].length > B[0].length) {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < B[0].length; j++) {
                    C[i][j] = A[i][j] - B[i][j];
                }
            }
            for (int i = A.length; i < B.length; i++) {
                for (int j = 0; j < B[0].length; j++) {
                    C[i][j] = (-1) * B[i][j];
                }
            }
            for (int i = 0; i < A.length; i++) {
                for (int j = B[0].length; j < A[0].length; j++) {
                    C[i][j] = A[i][j];
                }
            }
        }

        return C;

    }

    public int[][] addC22(int[][] A, int[][] B, int c, int z) {

        int[][] C = new int[c][z];
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < z; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }

        return C;

    }

    public int[][] subC22(int[][] A, int[][] B, int c, int z) {

        int[][] C = new int[c][z];
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < z; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }

        return C;

    }

    public int[][] mul(int[][] A, int[][] B) {

        int[][] w = new int[B[0].length][B.length];
        for (int i = 0; i < B[0].length; i++) {
            for (int j = 0; j < B.length; j++) {
                w[i][j] = B[j][i];
            }
        }
        int[][] C = new int[A.length][B[0].length];
        if (A[0].length == B.length && A.length == B[0].length) {
            for (int i = 0; i < B[0].length; i++) {
                for (int j = 0; j < B[0].length; j++) {
                    for (int l = 0; l < B.length; l++) {
                        C[i][j] += A[i][l] * w[j][l];
                    }
                }
            }
        } else if (A[0].length == B.length && A.length < B[0].length) {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < B[0].length; j++) {
                    for (int l = 0; l < B.length; l++) {
                        C[i][j] += A[i][l] * w[j][l];
                    }
                }
            }
        } else if (A[0].length == B.length && A.length > B[0].length) {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < B[0].length; j++) {
                    for (int l = 0; l < B.length; l++) {
                        C[i][j] += A[i][l] * w[j][l];
                    }
                }
            }
        } else if (A[0].length > B.length && A.length == B[0].length) {
            for (int i = 0; i < B[0].length; i++) {
                for (int j = 0; j < B[0].length; j++) {
                    for (int l = 0; l < B.length; l++) {
                        C[i][j] += A[i][l] * w[j][l];
                    }
                }
            }
        } else if (A[0].length > B.length && A.length > B[0].length) {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < B[0].length; j++) {
                    for (int l = 0; l < B.length; l++) {
                        C[i][j] += A[i][l] * w[j][l];
                    }
                }
            }
        } else if (A[0].length > B.length && A.length < B[0].length) {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < B[0].length; j++) {
                    for (int l = 0; l < B.length; l++) {
                        C[i][j] += A[i][l] * w[j][l];
                    }
                }
            }
        } else if (A[0].length < B.length && A.length == B[0].length) {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A.length; j++) {
                    for (int l = 0; l < A[0].length; l++) {
                        C[i][j] += A[i][l] * w[j][l];
                    }
                }
            }
        } else if (A[0].length < B.length && A.length > B[0].length) {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < B[0].length; j++) {
                    for (int l = 0; l < A[0].length; l++) {
                        C[i][j] += A[i][l] * w[j][l];
                    }
                }
            }
        } else if (A[0].length < B.length && A.length < B[0].length) {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < B[0].length; j++) {
                    for (int l = 0; l < A[0].length; l++) {
                        C[i][j] += A[i][l] * w[j][l];
                    }
                }
            }
        }

        return C;

    }

    public void split(int[][] P, int[][] C, int iB, int jB) {

        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++) {
            for (int j1 = 0, j2 = jB; j1 < C[0].length; j1++, j2++) {
                C[i1][j1] = P[i2][j2];
            }
        }

    }

    public void join(int[][] C, int[][] P, int iB, int jB) {

        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++) {
            for (int j1 = 0, j2 = jB; j1 < C[0].length; j1++, j2++) {
                P[i2][j2] = C[i1][j1];
            }
        }

    }

    public void joinC22(int[][] C, int[][] P, int iB, int jB, int a, int n) {

        for (int i2 = 0; i2 < a; i2++) {
            for (int j2 = 0; j2 < n; j2++) {
                P[i2 + iB][j2 + jB] = C[i2][j2];
            }
        }

    }

}
