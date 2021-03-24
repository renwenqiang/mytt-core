package com.boot.mytt.core.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author renwq
 * @since 2020/8/31 16:23
 */
public class Test01 {

    public static void main(String[] args) throws IOException {
        Calendar calendar = Calendar.getInstance();
        calendar.set( Calendar.HOUR_OF_DAY, 10 ); // 控制时
        calendar.set( Calendar.MINUTE, 0 );    // 控制分
        calendar.set( Calendar.SECOND, 0 );    // 控制秒
        Date time = calendar.getTime();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time));
    }
    public static void method6() throws IOException {
        try {
            FileInputStream fis = new FileInputStream("D:\\资料\\我的积分\\index.html");
            FileOutputStream fos = new FileOutputStream("D:\\资料\\我的积分\\index33.html");
            FileChannel readChannel = fis.getChannel();
            FileChannel writerChannel = fos.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(0124);
            while(true) {
                buffer.clear();
                int len = readChannel.read(buffer);
                if(len == -1)
                    break;
                buffer.flip();
                writerChannel.write(buffer);
            }
            readChannel.close();
            writerChannel.close();
            System.out.println("over");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
