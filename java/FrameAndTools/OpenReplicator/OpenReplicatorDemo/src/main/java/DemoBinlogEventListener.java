import com.google.code.or.binlog.BinlogEventListener;
import com.google.code.or.binlog.BinlogEventV4;
import handlers.BinlogHandler;

class DemoBinlogEventListener implements BinlogEventListener {

    private BinlogHandler handler;

    public DemoBinlogEventListener(BinlogHandler handler) {
        this.handler = handler;
    }

    //binlog监听
    public void onEvents(BinlogEventV4 binlogEventV4) {
        try {
            handler.handle(binlogEventV4);
            binlogEventV4.getClass();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
