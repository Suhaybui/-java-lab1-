import java.util.Scanner;

interface Polygon {
    double area();
    double perimeter();
}

class Triangle implements Polygon {
    protected double a, b, c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double area() {
        double s = (a + b + c) / 2.0;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    public double perimeter() {
        return a + b + c;
    }
}

class IsoscelesTriangle extends Triangle {
    public IsoscelesTriangle(double equalSide, double base) {
        super(equalSide, equalSide, base);
    }
}

class EquilateralTriangle extends Triangle {
    public EquilateralTriangle(double side) {
        super(side, side, side);
    }
}

class Quadrilateral implements Polygon {
    protected double length, width;

    public Quadrilateral(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double area() {
        return length * width;
    }

    @Override
    public double perimeter() {
        return 2 * (length + width);
    }
}

class Rectangle extends Quadrilateral {
    public Rectangle(double length, double width) {
        super(length, width);
    }
}

class Square extends Rectangle {
    public Square(double side) {
        super(side, side);
    }
}

abstract class RegularPolygon implements Polygon {
    protected int n;
    protected double side;

    public RegularPolygon(int n, double side) {
        this.n = n;
        this.side = side;
    }

    @Override
    public double perimeter() {
        return n * side;
    }

    @Override
    public double area() {
        return (n * side * side) / (4 * Math.tan(Math.PI / n));
    }
}

class Pentagon extends RegularPolygon {
    public Pentagon(double side) { super(5, side); }
}

class Hexagon extends RegularPolygon {
    public Hexagon(double side) { super(6, side); }
}

class Octagon extends RegularPolygon {
    public Octagon(double side) { super(8, side); }
}

public class PolygonCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose a polygon type:");
        System.out.println("1. Triangle");
        System.out.println("2. Isosceles Triangle");
        System.out.println("3. Equilateral Triangle");
        System.out.println("4. Rectangle");
        System.out.println("5. Square");
        System.out.println("6. Pentagon");
        System.out.println("7. Hexagon");
        System.out.println("8. Octagon");

        int choice = sc.nextInt();
        Polygon poly = null;

        switch (choice) {
            case 1:
                System.out.print("Enter 3 sides: ");
                poly = new Triangle(sc.nextDouble(), sc.nextDouble(), sc.nextDouble());
                break;
            case 2:
                System.out.print("Enter equal side and base: ");
                poly = new IsoscelesTriangle(sc.nextDouble(), sc.nextDouble());
                break;
            case 3:
                System.out.print("Enter side: ");
                poly = new EquilateralTriangle(sc.nextDouble());
                break;
            case 4:
                System.out.print("Enter length and width: ");
                poly = new Rectangle(sc.nextDouble(), sc.nextDouble());
                break;
            case 5:
                System.out.print("Enter side: ");
                poly = new Square(sc.nextDouble());
                break;
            case 6:
                System.out.print("Enter side: ");
                poly = new Pentagon(sc.nextDouble());
                break;
            case 7:
                System.out.print("Enter side: ");
                poly = new Hexagon(sc.nextDouble());
                break;
            case 8:
                System.out.print("Enter side: ");
                poly = new Octagon(sc.nextDouble());
                break;
            default:
                System.out.println("Invalid choice!");
                System.exit(0);
        }

        System.out.println("Perimeter = " + poly.perimeter());
        System.out.println("Area = " + poly.area());
        sc.close();
    }
}
