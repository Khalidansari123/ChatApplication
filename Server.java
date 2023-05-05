import java.net.*;
import java.io.*;
class Server{


    ServerSocket server;
    Socket socket;

    BufferedReader br;
    PrintWriter out;
    

    //constructor...
    public Server()
    {
        try{
            server=new ServerSocket(7778);

            System.out.println("Servere is ready accept connection");

            System.out.println("waiting server connection..");
            
            socket=server.accept();

            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out=new PrintWriter(socket.getOutputStream());

            startReading();
            startWriting();




        }
        catch(Exception e){
            e.printStackTrace();
        }


    }
    public void startReading(){

        //thread-read karke dete rahenge
        Runnable r1=()->{
            System.out.println("reader started...");
            try{
            while(true){
            
                String msg=br.readLine();
                if(msg.equals("exit")){
                    System.out.println("Client terminated the chat");
                    socket.close();
                    break;
                }
                System.out.println("client : " + msg);
                
            }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        };

          new Thread(r1).start();

    }
    public void startWriting(){

        //thread data user lega and the send karega client tak
        System.out.println("writer started");
        Runnable r2=() -> {
         
            try{
            while(!socket.isClosed()){
              
                    BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
                    String content=br1.readLine();

                    out.println(content);
                    out.flush();


                    if(content.equals("exit")){

                        break;
                    }
                
                }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        };

        new Thread(r2).start();

    }

    public static void main(String[] args){


        System.out.println("Starting server....chatapplication");
        new Server();
    }    
}