package demo.pack3;

public class Resources {
    public synchronized void read(int i) {
        if(i == 1){
            // 暂停三秒
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(i+"号正在读取---Reading....");
    }
}
