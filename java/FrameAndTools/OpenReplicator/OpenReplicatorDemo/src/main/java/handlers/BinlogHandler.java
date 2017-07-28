package handlers;

import com.google.code.or.binlog.BinlogEventV4;

/**
 * Created by mrlion on 2017/7/28.
 */
abstract public class BinlogHandler {
    abstract public void handle(BinlogEventV4 event);
}
