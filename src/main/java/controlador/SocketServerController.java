package controlador;

import modelo.Video;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.Channels;
import java.nio.channels.CompletionHandler;

public class SocketServerController {

    private ServerSocket serverSocket;

        public void iniciarHost(Video video)throws IOException{
            try{
                final AsynchronousServerSocketChannel listener =
                        AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(3400));

                listener.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {

                    @Override
                    public void completed(AsynchronousSocketChannel result, Void attachment) {
                        listener.accept(null, this);
                        File videoFile = new File(video.getArchivo());


                        try {
                            FileInputStream fileInputStream = new FileInputStream(videoFile.getAbsolutePath());
                            OutputStream outputStream = Channels.newOutputStream(result);
                            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

                            String fileName = videoFile.getName();
                            byte[] fileNameBytes = fileName.getBytes();


                            byte[] fileContentBytes = new byte[(int) videoFile.length()];
                            fileInputStream.read(fileContentBytes);

                            dataOutputStream.writeInt(fileNameBytes.length);
                            dataOutputStream.write(fileNameBytes);

                            dataOutputStream.writeInt(fileContentBytes.length);
                            dataOutputStream.write(fileContentBytes);

                            System.out.println("video envidado");

                            result.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }

                    @Override
                    public void failed(Throwable exc, Void attachment) {

                    }
                });

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void cerrarHost() throws IOException {
            serverSocket.close();
        }



}
