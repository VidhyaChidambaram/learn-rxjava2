package com.cvidhyac.reactive;

import com.mawashi.nio.jetty.ReactiveJ;

public class TodoMain {

  public static void main(String[] args) throws Exception {
    new ReactiveJ().port(8303)
        .endpoints(new TodoV1Endpoints())
        .endpoints(new TodoV2Endpoints())
        .start();
  }
}
