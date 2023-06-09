package com.example.jedisdemo2;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Calendar;

public class GetSetBenchmark {
  //log
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(GetSetBenchmark.class);

  private static HostAndPort hnp = HostAndPorts.getRedisServers().get(0);
  private static final String value = new String(new char[1000]).replace("\0", "c");

  public static void main(String[] args) throws UnknownHostException, IOException {
    run(20);
  }

  public static long run(int totalOperations) throws UnknownHostException, IOException {
    long finished = 0;
    try {
      Jedis jedis = new Jedis(hnp);
      jedis.connect();
//      jedis.auth("123456");
      jedis.flushAll();

      long begin = Calendar.getInstance().getTimeInMillis();

      for (int n = 0; n <= totalOperations; n++) {
        String key = "foo" + n;
        jedis.set(key, value);
        jedis.get(key);
        finished = n;
      }

      long elapsed = Calendar.getInstance().getTimeInMillis() - begin;

      jedis.disconnect();

      long ops = ((1000 * 2 * totalOperations) / elapsed);
      System.out.println(ops + " ops");
      return ops;
    } catch (Exception e) {
      e.printStackTrace();
      log.error("Error running benchmark", e);
    }
    log.info("Finished {} operations", finished);
    return finished;
  }
}
