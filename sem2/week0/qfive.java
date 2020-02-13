package sem2.week0;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Author    : AlmasB
 * Source    : https://github.com/AlmasB/FXTutorials/blob/master/src/com/almasb/dl/DownloaderApp.java
 * Edited by : Paolo Espiritu
 */

public class qfive extends Application {

    /**
     * 5. Can you identify what it is doing? Find the code that creates a new thread. 
     * Consider why we need more than 1 thread for this type of application.
     */
    
    /**
     * What is it doing? - This application downloads a url file to the workspace 
     * directory. 
     * 
     * Why is more than one thread needed? - this is so that files can be downloaded
     * while others are being downloaded
     */

    private Parent createContent() {
        VBox root = new VBox();
        root.setPrefSize(400, 600);

        TextField fieldURL = new TextField();
        root.getChildren().addAll(fieldURL);

        fieldURL.setOnAction(event -> {
            Task<Void> task = new DownloadTask(fieldURL.getText());
            ProgressBar progressBar = new ProgressBar();
            progressBar.setPrefWidth(350);
            progressBar.progressProperty().bind(task.progressProperty());
            root.getChildren().add(progressBar);

            fieldURL.clear();

            // below creates a new thread
            Thread thread = new Thread(task);
            thread.setDaemon(true);
            thread.start();
        });

        return root;
    }

    private class DownloadTask extends Task<Void> {

        private String url;

        public DownloadTask(String url) {
            this.url = url;
        }

        @Override
        protected Void call() throws Exception {
            String ext = url.substring(url.lastIndexOf("."), url.length());
            URLConnection connection = new URL(url).openConnection();
            long fileLength = connection.getContentLengthLong();

            try (InputStream is = connection.getInputStream();
                    OutputStream os = Files.newOutputStream(Paths.get("downloadedfile" + ext))) {

                long nread = 0L;
                byte[] buf = new byte[8192];
                int n;
                while ((n = is.read(buf)) > 0) {
                    os.write(buf, 0, n);
                    nread += n;
                    updateProgress(nread, fileLength);
                }
            }

            return null;
        }

        @Override
        protected void failed() {
            System.out.println("failed");
        }

        @Override
        protected void succeeded() {
            System.out.println("downloaded");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}