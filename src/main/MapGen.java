package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapGen {

    private static final Map<Color, Integer> COLOR_MAP = new LinkedHashMap<>();

    static {
        COLOR_MAP.put(new Color(34, 177, 76), 0);     // grass
        COLOR_MAP.put(new Color(40, 40, 40), 1);      // wall
        COLOR_MAP.put(new Color(0, 100, 0), 2);       // tree
        COLOR_MAP.put(new Color(120, 120, 120), 3);   // rubble1
        COLOR_MAP.put(new Color(180, 180, 180), 4);   // rubble2
        COLOR_MAP.put(new Color(139, 69, 19), 5);     // mud
        COLOR_MAP.put(new Color(200, 140, 20), 6);    // door
        COLOR_MAP.put(new Color(160, 82, 45), 7);     // chest
        COLOR_MAP.put(new Color(0, 120, 255), 8);     // water
        COLOR_MAP.put(new Color(240, 230, 140), 9);   // sand
    }

    private static int colorToValue(Color rgb) {
        if (COLOR_MAP.containsKey(rgb)) {
            return COLOR_MAP.get(rgb);
        }

        // nearest color fallback
        double bestDist = Double.MAX_VALUE;
        int bestVal = 0;

        for (Map.Entry<Color, Integer> e : COLOR_MAP.entrySet()) {
            Color c = e.getKey();
            double dist = Math.pow(c.getRed() - rgb.getRed(), 2)
                    + Math.pow(c.getGreen() - rgb.getGreen(), 2)
                    + Math.pow(c.getBlue() - rgb.getBlue(), 2);
            if (dist < bestDist) {
                bestDist = dist;
                bestVal = e.getValue();
            }
        }
        return bestVal;
    }

    public static int[][] imageToArray(String path) throws IOException {
        BufferedImage img = ImageIO.read(new File(path));
        int w = img.getWidth();
        int h = img.getHeight();

        int[][] arr = new int[h][w];

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int rgb = img.getRGB(x, y);
                Color c = new Color(rgb);
                arr[y][x] = colorToValue(c);
            }
        }
        return arr;
    }

    public static void saveMap(int[][] array, String outputPath) throws IOException {
        try (FileWriter fw = new FileWriter(outputPath)) {
            for (int[] row : array) {
                for (int i = 0; i < row.length; i++) {
                    fw.write(Integer.toString(row[i]));
                    if (i < row.length - 1) fw.write(" ");
                }
                fw.write("\n");
            }
        }
    }

    static void main(String[] args) {
        try {
            String imagePath = "C:\\dev\\TheTenthDoor\\src\\res\\maps\\mapImage\\level1.png";
            String outPath = "C:\\dev\\TheTenthDoor\\src\\res\\maps\\mapImage\\level1.png";
            int[][] arr = imageToArray(imagePath);

            String outputPath = outPath.replace(".png", ".txt");
            saveMap(arr, outputPath);

            System.out.println("Exported to " + outputPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
