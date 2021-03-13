package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Solution2251 {
    static class Capacity {
        int a;
        int b;
        int c;

        public Capacity(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Capacity capacity = (Capacity) o;
            return a == capacity.a && b == capacity.b && c == capacity.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b, c);
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int A,B,C;
    static HashSet<Integer> remains = new HashSet<>();
    static HashSet<Capacity> visited = new HashSet<>();
    public static void main(String[] args) throws IOException {
        String[] line = br.readLine().split(" ");
        A = Integer.parseInt(line[0]);
        B = Integer.parseInt(line[1]);
        C = Integer.parseInt(line[2]);

        moveWater(new Capacity(0, 0, C));
        remains.stream().sorted().forEach(e -> System.out.print(e + " " ));
    }

    private static void moveWater(Capacity start) {
        Queue<Capacity> q = new LinkedList<Capacity>();
        q.add(start);
        while(!q.isEmpty()) {
            Capacity cur = q.poll();
            if (visited.contains(cur)) continue;
            visited.add(cur);
            if (cur.a == 0)
                remains.add(cur.c);

            //A => B
            if (cur.a + cur.b >= B)
                q.add(new Capacity(cur.a - B + cur.b, B, cur.c));
            if (cur.a + cur.b < B)
                q.add(new Capacity(0, cur.b + cur.a, cur.c));

            // A => C
            if (cur.a + cur.c < C)
                q.add(new Capacity(0 , cur.b, cur.c + cur.a));
            if (cur.a + cur.c >= C)
                q.add(new Capacity(cur.a - C + cur.c, cur.b, C));

            // B => A
            if (cur.b + cur.a >= A)
                q.add(new Capacity(A, cur.b - A + cur.a, cur.c));
            if (cur.b + cur.a < A)
                q.add(new Capacity(cur.b + cur.a, 0, cur.c));

            // B => C
            if (cur.b + cur.c < C)
                q.add(new Capacity(cur.a, 0, cur.c + cur.b));
            if (cur.b + cur.c >= C)
                q.add(new Capacity(cur.a, cur.b - C + cur.c, C));

            // C => A
            if (cur.c + cur.a >= A)
                q.add(new Capacity(A, cur.b, cur.c - A + cur.a));
            if (cur.c + cur.a < A)
                q.add(new Capacity(cur.a + cur.c, cur.b, 0));

            // C => B
            if (cur.c + cur.b >= B)
                q.add(new Capacity(cur.a, B, cur.c - B + cur.b));
            if (cur.c + cur.b < B)
                q.add(new Capacity(cur.a, cur.b + cur.c, 0));
        }
    }
}