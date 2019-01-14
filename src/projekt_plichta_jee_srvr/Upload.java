package projekt_plichta_jee_srvr;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class Upload {
	BufferedImage image;
	BufferedImage kopia;
    public byte[] upload(String fileName, byte[] imageBytes) {
         
        InputStream input = new ByteArrayInputStream(imageBytes);
        try {
			image = ImageIO.read(input);
			input = new ByteArrayInputStream(imageBytes);
			kopia = ImageIO.read(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Upload obrazka na serwer - zle");
		}
        int windowSize = 3;

		int w = image.getWidth();
		int h = image.getHeight();
		
		int middle = (windowSize * windowSize) / 2;
		for (int x = 0; x < w - windowSize; ++x) {
			for (int y = 0; y < h - windowSize; ++y) {
				int pixels[] = new int[windowSize * windowSize];
				for (int i = 0; i < windowSize; ++i) {
					for (int j = 0; j < windowSize; ++j) {
						pixels[i * windowSize + j] = image.getRGB(x + i, y + j);
					}
				}
				java.util.Arrays.sort(pixels);
				
				kopia.setRGB(x + windowSize / 2, y + windowSize / 2, pixels[middle]);

			}
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(kopia, "png", baos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return baos.toByteArray();
		//return ((DataBufferByte) kopia.getData().getDataBuffer()).getData();
    }
}
