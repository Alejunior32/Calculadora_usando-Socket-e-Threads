package Server;

import Util.MsgReq;
import Util.MsgResp;
import Util.Status;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Worker extends Thread{

    Socket client;

    public Worker(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {

        try {
            ObjectInputStream input = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream());

            System.out.println("Aguardando Requisição");
            MsgReq request = (MsgReq) input.readObject();
            MsgResp response = null;

            switch (request.getOperation()){
                case '+':
                    response = new MsgResp(Status.SUCCESS,
                            request.getValue1()+ request.getValue2());
                    break;
                case '-':
                    response = new MsgResp(Status.SUCCESS,
                            request.getValue1()- request.getValue2());
                    break;
                case '*':
                    double result = request.getValue1()* request.getValue2();
                    response = new MsgResp(Status.SUCCESS, result);
                    break;
                case '/':
                    if(request.getValue2() == 0)
                        response = new MsgResp(Status.DIVIDE_BY_ZERO);
                    else
                        response = new MsgResp(Status.SUCCESS,
                            request.getValue1()/request.getValue2());
                    break;
                default:
                    response = new MsgResp(Status.INVALID_OPERATION);
                    break;
            }

            output.writeObject(response);

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
