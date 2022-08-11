package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Boot extends SuperObject{

    public OBJ_Boot() {

        name = "Boots";
        try {
            image = ImageIO.read(getClass().getResource("/objects/boots.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
