import java.io.*;
        import java.net.InetAddress;
        import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) {
        Socket server = null;
        String serverIp = "127.0.0.1"; // Server IP(내부 서버로 접속)
        int port = 5967; // Port번호

        try {


            server = new Socket(serverIp, port); // ip값과 포트 번호를 넣어 socket 생성

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            if (server != null) {
                InetAddress inetaddr = server.getInetAddress();
                System.out.println("[Server]: connecting to " + inetaddr.getHostAddress());


                OutputStream outputStream = server.getOutputStream(); //Socket으로부터  Input고 Outputstream 객체 획득
                InputStream inputStream = server.getInputStream();


                PrintWriter output = new PrintWriter(new OutputStreamWriter(outputStream)); //outputstream ->printwriter(출력)

                BufferedReader input = new BufferedReader(new InputStreamReader(inputStream)); //inputstream -> BufferReader(입력) 키보드로부터 입력받음

                String text = null;


                System.out.println("input message : ");
                text = keyboard.readLine();


                output.println(text); // input message를 Server로 전송
                output.flush();


                String response= input.readLine(); //서버에서 반환되는 message를 읽어들임
                System.out.println("Server Message(" +inetaddr.getHostAddress() + ") : " + response);

                output.close();
                input.close();
            }
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}