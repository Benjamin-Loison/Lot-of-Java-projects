import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Main
{
    private static final String MY_MARKER = "<text>";
    
    private static final int BUFFER_SIZE = 4096;

    /**
     * Downloads a file from a URL
     * @param fileURL HTTP URL of the file to be downloaded
     * @param saveDir path of the directory to save the file
     * @throws IOException
     */
    public static void downloadFile(String fileURL, String saveDir)
            throws IOException {
        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();

        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            int contentLength = httpConn.getContentLength();

            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
            } else {
                // extracts file name from URL
                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
                        fileURL.length());
            }

            System.out.println("Content-Type = " + contentType);
            System.out.println("Content-Disposition = " + disposition);
            System.out.println("Content-Length = " + contentLength);
            System.out.println("fileName = " + fileName);

            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            String saveFilePath = saveDir + File.separator + fileName;

            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);

            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close();
            inputStream.close();

            System.out.println("File downloaded");
        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
    }
    
    public static void main(String[] args)
    {
        try
        {
            String url = "altiscraft.fr";
            //downloadFile("http://" + url, ".");
            /*URL websiteUrl = new URL("https://google.fr");
            BufferedReader in = new BufferedReader(new InputStreamReader(websiteUrl.openStream()));
            String wholeWebsiteContent = "";
            String websiteLine*//*, contentTag*/;
            //boolean firstTagDetected = false, secondTagDetected = false;
            /*while((websiteLine = in.readLine()) != null)
            {
                wholeWebsiteContent += websiteLine + "\n";
                System.out.println(websiteLine);
            }
            in.close();*/
            Document doc = Jsoup.connect("https://altiscraft.fr").get();
            /*String[] lines = doc.body().wholeText().replaceAll("\t", "").split("\n");
            for(String line : lines)
            {
                line = line.trim();
                if(!line.isEmpty())
                    System.out.println(line);
            }*/
            System.out.println(doc.body().text());
            //System.out.println(wholeWebsiteContent);
            /*wholeWebsiteContent = wholeWebsiteContent.replaceAll("\\<p\\>", MY_MARKER).replaceAll("\\</p\\>", MY_MARKER);
            wholeWebsiteContent = wholeWebsiteContent.replaceAll("\\<h\\d\\>", MY_MARKER).replaceAll("\\</h\\d\\>", MY_MARKER);
            wholeWebsiteContent = wholeWebsiteContent.replaceAll("\\<a.*ar.*\\</a\\>", MY_MARKER);
            String[] parts = wholeWebsiteContent.split(MY_MARKER);
            for(int partIndex = 1; partIndex < parts.length; partIndex += 2)
            {
                System.out.println(parts[partIndex]);
            }*/
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}