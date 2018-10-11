package Connection;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class that receives the input from server and creates a new file with it.
 * Created by cordu on 10/9/2018.
 */
public class ReceivedFileHandler implements Runnable {
    private InputStream inputStream;

    public ReceivedFileHandler(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * Method creates a new file and writes into it the bytes received from the server .
     */
    @Override
    public void run() {
        try {
            File result = new File("C:\\Users\\cordu\\Desktop\\nume.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(result);
            byte[] input = new byte[4096];
            int count;
            while((count=inputStream.read(input)) > 0){
                fileOutputStream.write(input,0,count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
