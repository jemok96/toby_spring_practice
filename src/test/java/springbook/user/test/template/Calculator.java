package springbook.user.test.template;

import springbook.user.test.template.BufferedReaderCallback;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
    /**
     * Calculator Context : 기본 뼈대
     * BufferedReaderCallback으로 strategy 받아서 변하는 부분만 doSomethingWithReader함수로 처리
     */
    public Integer fileReaderTemplate(String filepath,BufferedReaderCallback callback) throws IOException{
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(filepath));
            int result = callback.doSomethingWithReader(br);

            return result;
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        finally {
            if(br != null){
                try{br.close();}
                catch(IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * Sum
     * Strategy 부분 BufferedReaderCallback을 익명함수로 구현
     */
    public Integer calcSum(String filepath) throws IOException{

        LineCallback sumCallback = new LineCallback() {
            @Override
            public Integer doSomethingWithLine(String line, Integer value) {
                return value + Integer.valueOf(line);
            }};
        return lineReadTemplate(filepath,sumCallback,0);
    }
    public Integer calcMultiply(String filepath) throws IOException{
        LineCallback multiplyCallback = new LineCallback() {
            @Override
            public Integer doSomethingWithLine(String line, Integer value) {
                return value * Integer.valueOf(line);
            }};
        return lineReadTemplate(filepath,multiplyCallback,1);
    }
    public Integer lineReadTemplate(String filepath,LineCallback callback,int initVal) throws IOException{
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(filepath));
            int res = initVal;
            String line = null;
            while ((line = br.readLine()) != null){
                res = callback.doSomethingWithLine(line,res);
            }

            return res;
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        finally {
            if(br != null){
                try{br.close();}
                catch(IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

}
