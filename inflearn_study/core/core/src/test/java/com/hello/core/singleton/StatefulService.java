package com.hello.core.singleton;

public class StatefulService {

//    private int price; //상태를 유지하는 필드

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
//        this.price = price;// 싱글톤 방식에서 주의해야하는 부분 -> 클라이언트에서 특정 값을 변경할 수 있는 필드가 있으면 안된다.
        return price;
    }

//    public int getPrice(){
////        return price;
//    }
}
