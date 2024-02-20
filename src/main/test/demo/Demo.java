package demo;


import java.io.IOException;

public class Demo {
    public static void main(String[] args) throws IOException {
        System.out.println("请输入ip地址：");
        byte[] bytes = new byte[1024];
        int len = System.in.read(bytes);
        String str = new String(bytes, 0, len - 1);
        //    将接收的ip地址转为二进制
        String[] split = str.split("\\.");
        StringBuilder sb = new StringBuilder();
        for (String value : split) {
            StringBuilder s = new StringBuilder(Integer.toBinaryString(Integer.parseInt(value)));
            while (s.length() < 8) {
                s.insert(0, "0");
            }
            sb.append(s).append(".");
        }
        System.out.println(sb.toString());
    }
}
