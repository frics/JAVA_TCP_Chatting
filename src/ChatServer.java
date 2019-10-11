import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public static void main(String[] args) {
        Socket client = null;
        ServerSocket server = null;
        int port = 5967; //5967번 포트
        try {

                server = new ServerSocket(port);  //서버 소켓 생성
                System.out.println("waiting for connecting...");
                client = server.accept(); //accept 메소드를 실행하여 Client의 접속 대기 -> 접속했을때 socket을 반환한다

            if (client != null) {
                InetAddress inetaddr = client.getInetAddress();
                System.out.println("IP: " + inetaddr.getHostAddress() + " connected");
                OutputStream outputStream = client.getOutputStream();
                InputStream inputStream = client.getInputStream();

                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream)); //출력
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream)); //입력
                String text = null;


                while((text = bufferedReader.readLine()) != null){  //Client가 보낸 메세지를 읽어옴
                    System.out.println("Client Message("+inetaddr.getHostAddress() + ") : " + text);


                    printWriter.println("20162518"); //학번을 Client로 전송
                    printWriter.flush();
                }
                printWriter.close();
                bufferedReader.close();
            }
            client.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}