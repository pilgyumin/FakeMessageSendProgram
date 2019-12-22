package com.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.client.fakedata.FakeData;
import com.client.main.FakeMessageSendProgramClient;

@SpringBootApplication
public class FakeMessageSendProgramApplication {
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean isInputEnd = false;
		int totalCount = 0;
		
		FakeData fakeData = FakeData.getInstance();
		
		while(!isInputEnd) {
			try {
				while(totalCount <= 0) {
					System.out.println("원하는 조작 번호의 갯수를 정해주세요 (1 이상을 입력하세요!) : ");
					totalCount = Integer.parseInt(br.readLine());
					fakeData.setTotalCount(totalCount);
				}
				System.out.println("순서 별로 원하는 데이터의 갯수를 적어 주세요 ( 1 ~ 9 ) [구분자는 띄어쓰기]");
				String[] inputArray = br.readLine().split(" ");
				
				//check inputArray Length
				if(inputArray.length != 9) {
					continue;
				}
				
				//check inputArray is Integer
				for(int idx = 0; idx < 9; idx++) {
					int inputNum = Integer.parseInt(inputArray[idx]);
					if(inputNum < 0 || inputNum >  FakeData.getInstance().getTotalCount()) {
						continue;
					}
					fakeData.getArray()[idx] = inputNum;
				}
				
				isInputEnd = true;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		ConfigurableApplicationContext context = SpringApplication.run(FakeMessageSendProgramApplication.class, args);
		context.getBean(FakeMessageSendProgramClient.class).run();
		
		for(int idx = 0; idx < 9; idx++) {
			System.out.print(fakeData.getArray()[idx] + " ");
		}
		System.out.println();
	}
	

}
