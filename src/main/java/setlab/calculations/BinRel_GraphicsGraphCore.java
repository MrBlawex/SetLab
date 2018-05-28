package setlab.calculations;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import setlab.cores.BinRelCore;
import setlab.cores.BinRelCore.BinEl;
import setlab.cores.BinRelCore.BinRel;
import setlab.cores.SetCore.SetObj;

import java.util.ArrayList;
import java.util.HashMap;

public class BinRel_GraphicsGraphCore {

    private final static int X0 = 105;
    private final static int Y0 = 78;
    public static float angleImage = 0f;
    private static int r = 48;

    public static void setAngle(float angle) {
        angleImage = (float) (angle * Math.PI / 180);
    }

    public static void changeZoom(float zoom) {
        r += zoom;
        if (r <= 25) {
            r = 25;
        } else if (r >= 100) {
            r = 100;
        }
    }

    public static void resetZoom() {
        r = 48;
    }

    public static void Render(Canvas c, BinRel R) {
        GraphicsContext context = c.getGraphicsContext2D();
        context.clearRect(0, 0, 215, 160);
        context.setFill(Paint.valueOf("#6495ED"));
        context.setStroke(Paint.valueOf("#1E90FF"));

        SetObj set = BinRelCore.O(R);
        String[] elements = set.toArray(new String[set.size()]);
        int n = elements.length;
        final float angle = (float) (2 * Math.PI / n);

        ArrayList<Figure> listFigure = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            float x = (float) (Math.cos(angleImage + angle * i) * r);
            float y = (float) (Math.sin(angleImage + angle * i) * r);
            float x2 = -2 + X0 + x * 1.4f;
            float y2 = 6 + Y0 + y * 1.4f;
            x += X0;
            y += Y0;
            listFigure.add(new Figure(elements[i - 1], x, y, x2, y2));
        }

        HashMap<String, Figure> mapFigure = new HashMap<>();
        listFigure.stream().map((t) -> {
            context.fillText(t.name, t.x2, t.y2);
            return t;
        }).map((t) -> {
            context.strokeOval(t.x, t.y, 6, 6);
            return t;
        }).forEachOrdered((t) -> {
            mapFigure.put(t.name, t);
        });

        BinEl[] listBinEl = R.toArray(new BinEl[R.size()]);

        for (int i = 0; i < R.size(); i++) {
            BinEl t = listBinEl[i];
            Figure a = mapFigure.get(t.getX());
            Figure b = mapFigure.get(t.getY());
            if (a.equals(b)) {
                context.setFill(Paint.valueOf("#75A6FE"));
                context.strokeOval(a.x - 3, a.y - 3, 12, 12);
                context.setFill(Paint.valueOf("#1E90FF"));
            } else {
                double x1 = a.x + 3, x2 = b.x + 3;
                double y1 = a.y + 3, y2 = b.y + 3;
                makeLineWithArrow(context, x1, y1, x2, y2);
            }
        }

        context.fillText(Math.round(angleImage * 180 / Math.PI) + "°", 5, c.getHeight() - 5);
        context.fillText("n = " + n, 5, 14);
    }

    private static void makeLineWithArrow(GraphicsContext region, double startX, double startY, double endX, double endY) {
        double angle;

        final double R = 12 * r / 50;
        final double a = 60;
        final double b = 120;
        double mid = (startX + endX) / 2;
        if (endX - mid < 0.0) {
            angle = Math.atan((endY - startY) / (endX - startX)) + Math.PI;
        } else {
            angle = Math.atan((startY - endY) / (startX - endX));
        }
        if (endX - mid == 0.0) {
            angle += Math.PI;
        }
        angle = Math.round(Math.toDegrees(angle));
        angle = Math.toRadians(angle);

        double angle1 = b - a + angle;
        double angle2 = -b + a + angle;

        if (Math.toDegrees(angle) == 90) {
            angle += Math.PI;
        }

        region.strokeLine(startX, startY, endX, endY);
        region.strokeLine(endX, endY, R * Math.cos(angle1) + endX, R * Math.sin(angle1) + endY);
        region.strokeLine(endX, endY, R * Math.cos(angle2) + endX, R * Math.sin(angle2) + endY);
    }

    // --------------- class of Elements ---
    private static class Figure {

        float x, y, r, x2, y2;
        String name;

        Figure(String name, float x, float y, float x2, float y2) {
            this.name = name;
            this.x = x;
            this.y = y;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
}
