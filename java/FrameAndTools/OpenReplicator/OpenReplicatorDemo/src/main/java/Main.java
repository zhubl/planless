import com.google.code.or.OpenReplicator;
import handlers.Out2ConsoleHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * Created by mrlion on 2017/7/28.
 */
public class Main {
    public static void main(String args[]) {

        String user = "root";
        String host = "localhost";
        int port = 3306;
        int ServerId = 10810;

        String passwd = args[0];

        final OpenReplicator or = new OpenReplicator();
        or.setUser(user);
        or.setHost(host);
        or.setPort(port);
        or.setPassword(passwd);
        or.setBinlogPosition(4);
        or.setServerId(ServerId);
        or.setBinlogFileName("master.000001");
        or.setBinlogEventListener(new DemoBinlogEventListener(new Out2ConsoleHandler()));
        try{
            or.start();
            System.out.println("press 'q' to stop");
            final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            for(String line = br.readLine(); line != null; line = br.readLine()) {
                if(line.equals("q")) {
                    or.stop(1, TimeUnit.SECONDS);
                    break;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
