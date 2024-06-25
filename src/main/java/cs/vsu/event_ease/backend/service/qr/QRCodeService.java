package cs.vsu.event_ease.backend.service.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;

@Service
public class QRCodeService {
    private Integer width = 400;
    private Integer height = 400;

    @SneakyThrows
    public void generate(String text, String filename) {

        BitMatrix matrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height);

        MatrixToImageWriter.writeToFile(
                matrix, filename.substring(filename.lastIndexOf('.')+1), new File(filename)
        );
        //ByteArrayOutputStream os = new ByteArrayOutputStream();
        //MatrixToImageWriter.writeToStream(matrix, "PNG", os);
    }
}
