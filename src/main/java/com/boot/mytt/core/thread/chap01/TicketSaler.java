package com.boot.mytt.core.thread.chap01;

class MySalerThread implements Runnable{
    private int ticketNum = 10;
    @Override
    public void run() {
        for(int i=0;i<20;i++){
            if (ticketNum > 0) {
                System.out.println("ticket = "+ticketNum--);
            }
        }
    }
}

public class TicketSaler {
    public static void main(String args[]){
        MySalerThread saler = new MySalerThread();
        new Thread(saler).start();
        new Thread(saler).start();
        new Thread(saler).start();
    }
}