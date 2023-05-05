import java.net.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;

import java.awt.Font;
import java.io.*;


public class Client extends JFrame{
    
    Socket socket;

    BufferedReader br;
    PrintWriter out;

    //decleare component
    private JLabel heading=new JLabel("Client Area");
    private JTextArea messageArea=new JTextArea();
    private JTextField messageInput=new JTextField();
    private Font font=new Font("Roboto",Font.BOLD,20);
    
 //contsructor
    public Client(){



      try{

        /* System.out.println("Sending request to server..");
           socket = new Socket("127.0.0.1",7777);
         System.out.println("connection done....");

          

         br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
         out=new PrintWriter(socket.getOutputStream());


*/
           createGUI();
           //startReading();
          // startWriting();


       }
        catch(Exception e){
           // e.printStackTrace();
        }
    }

     private void createGUI(){

        this.setTitle("Client Message[END]");
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //coding for component
        heading.setFont(font);
        messageArea.setFont(font);
        messageInput.setFont(font);


        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        

        //frame for layput set karemge

        this.setLayout(new BorderLayout());
        //adding the componet to frame
        
        this.add(heading,BorderLayout.NORTH);
        this.add(messageArea,BorderLayout.CENTER);
        this.add(messageInput,BorderLayout.SOUTH);


        this.setVisible(true);
     }

     public void startReading(){

        //thread-read karke dete rahenge
        Runnable r1=()->{
            System.out.println("reader started...");
            
            try{
            while(true){
               
                String msg=br.readLine();
                if(msg.equals("exit")){
                    System.out.println("server terminated the chat");
                    socket.close();
                    break;
                }
                System.out.println("Server : " + msg);
              
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

                    if(content.equals("esixt")){

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

        System.out.println("Starting client...server");
        new Client();
    }
}