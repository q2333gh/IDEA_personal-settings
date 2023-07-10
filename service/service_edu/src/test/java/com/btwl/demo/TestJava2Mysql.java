package com.btwl.demo;


import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class TestJava2Mysql {



  @Value("${testdata}")
  public String td;

  @Test
  public void run() {

  }
}

