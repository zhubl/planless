package handlers;


import com.google.code.or.binlog.BinlogEventV4;
import com.google.code.or.binlog.BinlogEventV4Header;
import com.google.code.or.binlog.impl.event.QueryEvent;

public class Out2ConsoleHandler extends BinlogHandler{

    public void handle(BinlogEventV4 event){
        BinlogEventV4Header header = event.getHeader();
        Class<?> eventType = event.getClass();
        // 只监听QueryEvent信息
        if (eventType == QueryEvent.class) {
            // 具体操作本demo中不做处理
            QueryEvent actualEvent = (QueryEvent) event;
            System.out.println(actualEvent.toString());
        }
    }
}
