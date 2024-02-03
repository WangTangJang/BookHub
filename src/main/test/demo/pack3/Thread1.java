package demo.pack3;

public class Thread1 extends Thread{
    Resources resources;
    int i;
    public Thread1(Resources resources, int i){
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
        Thread1 thread1 = new Thread1(resources, 1);
        Thread2 thread2 = new Thread2(resources, 2);
        thread2.start();
        thread1.start();
    }
}
