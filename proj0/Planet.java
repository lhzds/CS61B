public class Planet {
    private static final double G = 6.67e-11;

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx = p.xxPos - xxPos;
        double dy = p.yyPos - yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet p) {
        double distance = calcDistance(p);
        return G * p.mass * mass / distance / distance;
    }

    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - xxPos;
        double distance = calcDistance(p);
        return calcForceExertedBy(p) * dx / distance;
    }

    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - yyPos;
        double distance = calcDistance(p);
        return calcForceExertedBy(p) * dy / distance;
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double netForceX = 0;
        for (Planet planet : planets) {
            if (this.equals(planet)) {
                continue;
            }
            netForceX += calcForceExertedByX(planet);
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double netForceY = 0;
        for (Planet planet : planets) {
            if (this.equals(planet)) {
                continue;
            }
            netForceY += calcForceExertedByY(planet);
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY) {
        double xxAccel = fX / mass;
        double yyAccelY = fY / mass;

        xxVel += xxAccel * dt;
        yyVel += yyAccelY * dt;

        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
