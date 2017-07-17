import org.junit.jupiter.api.Test;
import pictureFilter.PictureFilter;
import pictureFilter.PictureFilterGUI;

import java.io.IOException;

public class PictureFilterTester {
    @Test
    void bwTester_IL(){
        String[] args = {"D:/Nikita/Pictures/josuke.png", "bw"};
        try {
            PictureFilter.main(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void bwTester() {
        try {
            PictureFilterGUI.main(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
