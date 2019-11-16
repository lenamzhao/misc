import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

class MirrorImage {
    public static void main(String[] args) {
        BufferedImage originalImg = null;
        File f = null;

        //read source image file
        try{
            f = new File(MirrorImage.class.getResource("Coco.jpg").getFile());
            originalImg = ImageIO.read(f);
        }catch(IOException e){
            System.out.println("Error: " + e);
        }

        //flip the image vertically
        originalImg = mirror(originalImg);


        //save mirror image
        try{
            f = new File("./Coco_mirror.jpg");
            ImageIO.write(originalImg, "jpg", f);
        }catch(IOException e){
            System.out.println("Error: " + e);
        }
    }

    /*
     * Assumption - input is a BufferedImage object, output returns the same type object
     *
     * Since we want to mirror the image vertically, then we only flip the x-axis, y axis stays the same.
     * The idea is to loop through the first half of x-axis and switch the pixel between left end and right end.
     */
    public static BufferedImage mirror(BufferedImage image) {
        //get source image dimension
        int width = image.getWidth();
        int height = image.getHeight();

        //loop through y-axis
        for (int y = 0; y < height; y++){
            //loop through only the first half of the x-axis
            for (int x = 0; x < width/2; x++){
                //get the colour pixel from original image on the left end
                int pixel = image.getRGB(x, y);
                //get the colour pixel from original image on the right end
                int mirrorPixel = image.getRGB(width - x - 1, y);
                //set left end to the right end pixel and vice versa
                image.setRGB(width - x - 1, y, pixel);
                image.setRGB(x, y, mirrorPixel);
            }
        }
        //return the mirrored image
        return image;
    }
}