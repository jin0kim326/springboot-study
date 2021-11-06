package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 함수 호출 / url :" +url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("connect = " + url);
    }

    public void call(String sendMsg) {
        System.out.println("call: "+url+" / sendMessage: " + sendMsg);
    }

    public void disconnect() {
        System.out.println("close:"+url);
    }

    @PostConstruct
    public void init() throws Exception {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() throws Exception {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}

/*
* 1. 인터페이스 (InitializingBean, DisposableBean)를 사용하는것은 좋지않음
* -> 코드가 스프링 전용 인터페이스에 의존
* -> 초기화,소멸 메서드 이름 변경 불가
* -> 내가 수정할 수 없는 라이브러리는 적용 할 수 없음
* -> 오래된 방법, 현재는 사용하지않음
* */