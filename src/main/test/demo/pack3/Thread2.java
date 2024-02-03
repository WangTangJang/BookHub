package demo.pack3;

public class Thread2 extends Thread{
    Resources resources;
    int i;
    public Thread2(Resources resources, int i){
        this.resources = resources;
        this.i = i;
    }
    public void run(){
        // 读取100次
        for(int i = 0; i < 100; i++){
            resources.read(this.i);
        }
    }

    public static void main(String[] args) {
        Resources resources = new Resources();
        Thread2 thread2 = new Thread2(resources, 2);
        thread2.start();
    }
}
