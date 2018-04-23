package Display;

import Model.Color;
import Model.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Chess - Created 19/03/2018.
 */
public class ResourceMap
{
    private static Map<String, Image> imageMap;

    static
    {
        imageMap = new HashMap<>();
        loadResources(Constants.BLACK_RESOURCE_DIR);
        loadResources(Constants.WHITE_RESOURCE_DIR);
    }

    public static Image getImage(Model.Color color, String id)
    {
        String key = (color == Color.WHITE ? Constants.WHITE_RESOURCE_DIR : Constants.BLACK_RESOURCE_DIR) + id;
        return imageMap.get(key);
    }

    private static void loadResources(String dir)
    {
        try
        {
            for(File file : new File("resources/" + dir).listFiles())
            {
                Image image = ImageIO.read(file);
                image = image.getScaledInstance(SquareDisplay.SQUARE_SIZE, SquareDisplay.SQUARE_SIZE, 0);
                imageMap.put(dir + file.getName().substring(0, file.getName().lastIndexOf(".")), image);
            }
        }
        catch (NullPointerException | IOException e)
        {
            System.out.println("error loading resources");
        }
    }
}
