package pl.edu.agh.docker;

import org.xeustechnologies.jtar.TarEntry;
import org.xeustechnologies.jtar.TarOutputStream;

import java.io.*;
import java.util.Map;

public class CompressTarGz {

    public static void compress(String outputTarPath,Map<String,String> mapping) throws IOException{
        // Output file stream
        FileOutputStream dest = new FileOutputStream( outputTarPath );
        // Create a TarOutputStream
        TarOutputStream out = new TarOutputStream( new BufferedOutputStream( dest ) );

        for (String inPath : mapping.keySet()) {
            File inFile = new File(inPath);
            out.putNextEntry(new TarEntry(inFile, mapping.get(inPath)) );

            BufferedInputStream origin = new BufferedInputStream(new FileInputStream( inFile ));

            int count;
            byte data[] = new byte[2048];
            while((count = origin.read(data)) != -1) {
                out.write(data, 0, count);
            }

            out.flush();
            origin.close();
        }

        out.close();
    }
}
