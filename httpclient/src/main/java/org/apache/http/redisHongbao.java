package org.apache.http;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

import org.springframework.util.StopWatch;
 

import com.alibaba.fastjson.JSONObject;

import redis.clients.jedis.Jedis;

public class redisHongbao {
	static String host = "54.204.214.231";
	static int honBaoCount = 1000;

	static int threadCount = 10;

	static String hongBaoList = "hongBaoList";
	static String hongBaoConsumedList = "hongBaoConsumedList";
	static String hongBaoConsumedMap = "hongBaoConsumedMap";

	static Random random = new Random();

//	-- ���������Ի�ú��������ɹ����򷵻�json�ַ�����������ɹ����򷵻ؿ�
//	-- ����������������� �����ѵĶ�������ȥ�ص�Map�����û�ID
//	-- ����ֵ��nil ���� json�ַ����������û�ID��userId�����ID��id�������money
	static String tryGetHongBaoScript = 
//			"local bConsumed = redis.call('hexists', KEYS[3], KEYS[4]);\n"
//			+ "print('bConsumed:' ,bConsumed);\n"
			"if redis.call('hexists', KEYS[3], KEYS[4]) ~= 0 then\n"
			+ "return nil\n"
			+ "else\n"
			+ "local hongBao = redis.call('rpop', KEYS[1]);\n"
//			+ "print('hongBao:', hongBao);\n"
			+ "if hongBao then\n"
			+ "local x = cjson.decode(hongBao);\n"
			+ "x['userId'] = KEYS[4];\n"
			+ "local re = cjson.encode(x);\n"
			+ "redis.call('hset', KEYS[3], KEYS[4], KEYS[4]);\n"
			+ "redis.call('lpush', KEYS[2], re);\n"
			+ "return re;\n"
			+ "end\n"
			+ "end\n"
			+ "return nil";
	static StopWatch watch = new StopWatch();

	public static void main(String[] args) throws InterruptedException {
//		testEval();
		generateTestData();
		testTryGetHongBao();
	}

	static public void generateTestData() throws InterruptedException {
		Jedis jedis = new Jedis(host,12713,8223);
		jedis.auth("czyredis");
		jedis.flushAll();
		final CountDownLatch latch = new CountDownLatch(threadCount);
		for(int i = 0; i < threadCount; ++i) {
			final int temp = i;
			Thread thread = new Thread() {
				public void run() {
					Jedis jedis = new Jedis(host,12713,1223);
					jedis.auth("czyredis");
					int per = honBaoCount/threadCount;
					JSONObject object = new JSONObject();
					for(int j = temp * per; j < (temp+1) * per; j++) {
						object.put("id", j);
						object.put("money", j);
						jedis.lpush(hongBaoList, object.toJSONString());
					}
					latch.countDown();
				}
			};
			thread.start();
		}
		latch.await();
	}

	static public void testTryGetHongBao() throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(threadCount);
		System.err.println("start:" + System.currentTimeMillis()/1000);
		watch.start();
		for(int i = 0; i < threadCount; ++i) {
			final int temp = i;
			Thread thread = new Thread() {
				public void run() {
					Jedis jedis = new Jedis(host,12713,1223);
					jedis.auth("czyredis");
					String sha = jedis.scriptLoad(tryGetHongBaoScript);
					int j = honBaoCount/threadCount * temp;
					while(true) {
						Object object = jedis.eval(tryGetHongBaoScript, 4, hongBaoList, hongBaoConsumedList, hongBaoConsumedMap, "" + j);
						j++;
						if (object != null) {
//							System.out.println("get hongBao:" + object);
						}else {
							//�Ѿ�ȡ����
							if(jedis.llen(hongBaoList) == 0)
								break;
						}
					}
					latch.countDown();
				}
			};
			thread.start();
		}

		latch.await();
		watch.stop();

		System.err.println("time:" + watch.getTotalTimeSeconds());
		System.err.println("speed:" + honBaoCount/watch.getTotalTimeSeconds());
		System.err.println("end:" + System.currentTimeMillis()/1000);
	}
}
