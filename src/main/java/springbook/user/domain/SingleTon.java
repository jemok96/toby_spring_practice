package springbook.user.domain;

import springbook.user.dao.ConnectionMaker;

public class SingleTon {
//    private int price; //여기가 문제
    private static final SingleTon instance = new SingleTon();

    public static SingleTon getInstance(){
        return instance;
    }
    private SingleTon(){}

    public int order(String user, int price){
        //        this.price = price;
        return price;
    }
    /*public int getPrice(){
        return price;
    }*/
    public void login(){
        System.out.println(" 로직 호출 ");
    }
}

/***
 * 생성자를 private으로 하여, 생성을 막음
 * 호출할 때마다 같은 객체 인스턴스를 반환
 *
 * 1.생성자가 private이기 떄문에 상속이 불가능함, 객체 지향의 장점을 활용할 수 없음
 * 2.객체 인스턴스 하나만 생성해서 멀티스레드 환경에서 공유하기 때문에, 상태를 유지하게 설계하면 안된다. 필드의 값이 변하지 않게 설정해줘야함
 * 3. 테스트시 어려움, 사용 단계에서 이미 초기화가 일어나기 때문에 다른 것들을 주입하기도 어려움
 * 읽기전용 값이라면 여러 곳에서 그냥 사용해도 무방하다.
 *
 * 스프링 컨테이너는 이러한 문제를 해결해줌
 * <=> 프로토 타입은 요청 할 때마다 매번 새로운 오브젝트 생성
 */