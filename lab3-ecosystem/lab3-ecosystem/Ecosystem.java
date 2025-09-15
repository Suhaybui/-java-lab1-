import java.util.*;

abstract class Animal {
    public abstract String toString();
}

class Bear extends Animal {
    @Override public String toString() { return "B"; }
}

class Fish extends Animal {
    @Override public String toString() { return "F"; }
}

public class Ecosystem {
    private Animal[] river;
    private final Random rnd = new Random();

    public Ecosystem(int size, int bears, int fish) {
        river = new Animal[size];
        List<Integer> spots = new ArrayList<>();
        for (int i = 0; i < size; i++) spots.add(i);
        Collections.shuffle(spots, rnd);
        int idx = 0;
        for (int i = 0; i < bears && idx < spots.size(); i++) river[spots.get(idx++)] = new Bear();
        for (int i = 0; i < fish && idx < spots.size(); i++) river[spots.get(idx++)] = new Fish();
    }

    public void runStep() {
        Animal[] next = new Animal[river.length];
        for (int i = 0; i < river.length; i++) {
            Animal a = river[i];
            if (a == null) continue;
            int move = rnd.nextInt(3) - 1;
            int dest = i + move;
            if (dest < 0 || dest >= river.length) dest = i;
            if (next[dest] == null) {
                next[dest] = a;
            } else {
                Animal b = next[dest];
                if (a.getClass() == b.getClass()) {
                    int birth = randomEmptyIndex(next);
                    if (birth != -1) next[birth] = newInstance(a);
                } else {
                    if (a instanceof Bear || b instanceof Bear) {
                        next[dest] = new Bear();
                    }
                }
            }
        }
        river = next;
    }

    private int randomEmptyIndex(Animal[] arr) {
        List<Integer> empties = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) if (arr[i] == null) empties.add(i);
        if (empties.isEmpty()) return -1;
        return empties.get(rnd.nextInt(empties.size()));
    }

    private Animal newInstance(Animal a) {
        return (a instanceof Bear) ? new Bear() : new Fish();
    }

    public void visualize() {
        StringBuilder sb = new StringBuilder();
        for (Animal x : river) sb.append(x == null ? "-" : x.toString()).append(' ');
        System.out.println(sb.toString().trim());
    }

    public static void main(String[] args) {
        Ecosystem eco = new Ecosystem(30, 6, 10);
        System.out.println("Initial:");
        eco.visualize();
        for (int step = 1; step <= 15; step++) {
            eco.runStep();
            System.out.println("Step " + step + ":");
            eco.visualize();
        }
    }
}
