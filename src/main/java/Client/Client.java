package Client;

import Util.MsgReq;
import Util.MsgResp;
import Util.Status;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;


public class Client {



    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Conectado.");
        Socket socket;
        socket = new Socket("localhost",5500);

        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

        System.out.println("value 1:");
        double value1 = scanner.nextDouble();
        System.out.println("Operator:");
        String operator = scanner.next();
        System.out.println("value 2:");
        double value2 = scanner.nextDouble();

        MsgReq request = new MsgReq(value1,value2,operator.charAt(0));

        System.out.println("Enviando Requisição.");
        output.writeObject(request);

        System.out.println("Esperando Resposta.");
        MsgResp response = (MsgResp) input.readObject();


        if (response.getStatus() == Status.SUCCESS){
            System.out.println("Resultado: " + response.getValue());
        } else if (response.getStatus() == Status.DIVIDE_BY_ZERO) {
            System.out.println("Não é possivel dividir por zero");
        } else if (response.getStatus() == Status.INVALID_OPERATION) {
            System.out.println("Operador inválido");
        }


    }

}
