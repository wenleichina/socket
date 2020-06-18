import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class MainActivity {


    public static void main(String[] args) throws IOException {
        //1.创建客户端Socket，指定服务器地址和端口
        Socket socket = new Socket("127.0.0.1", 12345);
        //2.获取输出流，向服务器端发送信息
        OutputStream os = socket.getOutputStream();//字节输出流
        PrintWriter pw = new PrintWriter(os);//将输出流包装为打印流
        //获取客户端的IP地址
        InetAddress address = InetAddress.getLocalHost();
        String ip = address.getHostAddress();
        pw.write("客户端：~" + ip + "~ 接入服务器！！");
        pw.flush();
        socket.shutdownOutput();//关闭输出流

        InputStream is=null;
        InputStreamReader isr=null;
        BufferedReader br=null;
        is = socket.getInputStream();     //获取输入流
        isr = new InputStreamReader(is,"UTF-8");
        br = new BufferedReader(isr);
        String info = null;
        while((info=br.readLine())!=null){//循环读取客户端的信息
            System.out.println("服务端发送过来的信息" + info);
        }
        socket.shutdownInput();

        socket.close();
    }
}