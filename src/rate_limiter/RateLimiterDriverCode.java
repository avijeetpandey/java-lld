package rate_limiter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import rate_limiter.enums.UserTier;
import rate_limiter.model.User;
import rate_limiter.service.RateLimiterService;

public class RateLimiterDriverCode {

    static void checkConcurrency(RateLimiterService rateLimiterService) throws InterruptedException {
        User premiumUser = new User("Aman", UserTier.PREMIUM);

        int threads = 20;
        ExecutorService executorService = Executors.newFixedThreadPool(threads);

        CyclicBarrier barrier = new CyclicBarrier(threads);
        CountDownLatch latch = new CountDownLatch(threads);

        for (int i = 1; i <= threads; i++) {
            final int reqNum = i;
            executorService.submit(() -> {
                try {
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                boolean allowed = rateLimiterService.allowRequests(premiumUser);
                System.out.println(Thread.currentThread().getName() +
                        " | Request " + reqNum + " for FreeUser1: " + (allowed ? "ALLOWED" : "BLOCKED"));

                latch.countDown();
            });

            latch.await();
            executorService.shutdown();
        }

    }

    public static void main(String[] args) {
        RateLimiterService rateLimiterService = new RateLimiterService();
        try {
            checkConcurrency(rateLimiterService);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
