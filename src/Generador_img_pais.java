import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class Generador_img_pais {
    
    public BufferedImage generarImagenPais(String nombrePais) {
        BufferedImage img = null;
        String url = "https://www.bandera.xyz/bandera/" + nombrePais.replaceAll("\\s", "-").toLowerCase() + "/285";
        try {
            URL imageURL = new URL(url);
            img = ImageIO.read(imageURL);
            img = resize(img, 285, 130);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
    
    private BufferedImage resize(BufferedImage img, int newWidth, int newHeight) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = new BufferedImage(newWidth, newHeight, img.getType());
        Graphics2D g = dimg.createGraphics();
        g.drawImage(img, 0, 0, newWidth, newHeight, 0, 0, w, h, null);
        g.dispose();
        return dimg;
    }
    
}
