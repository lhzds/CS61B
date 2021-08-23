public class NBody {
    public static double readRadius(String filename) {
        In in = new In(filename);
        in.readInt(); //discard useless data from In
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int numOfP = in.readInt();
        in.readDouble(); //discard useless data from In
        Planet[] planets = new Planet[numOfP];

        for (int i = 0; i < numOfP; ++i) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();

            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }

        return planets;
    }

    private static void drawBackground(double radius) {
        StdDraw.setScale(-radius, radius);

        StdDraw.clear();

        StdDraw.picture(0, 0, "images/starfield.jpg");
    }

    private static void drawPlanet(Planet[] planets) {
        for (Planet planet: planets) {
            planet.draw();
        }
    }

    private static void createAnimation(Planet[] planets, double radius, double dt, double T) {
        StdDraw.enableDoubleBuffering();

        int timeTicks = 0, numOfP = planets.length;
        while (timeTicks < T) {
            double[] xForce = new double[numOfP];
            double[] yForce = new double[numOfP];

            /* calculate the net x and y forces for each planet */
            for (int i = 0; i < numOfP; ++i) {
                xForce[i] = planets[i].calcNetForceExertedByX(planets);
                yForce[i] = planets[i].calcNetForceExertedByY(planets);
            }

            /* must update after finish calculating the net force for each planet */
            for (int i = 0; i < numOfP; ++i) {
                planets[i].update(dt, xForce[i], yForce[i]);
            }

            /* draw depend on the updated data */
            drawBackground(radius);
            drawPlanet(planets);
            StdDraw.show();
            StdDraw.pause(10);

            timeTicks += dt;
        }
    }

    private static void printUniverse(Planet[] planets, double radius) {
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        drawBackground(radius);
        drawPlanet(planets);
        createAnimation(planets, radius, dt, T);
        printUniverse(planets, radius);
    }
}
