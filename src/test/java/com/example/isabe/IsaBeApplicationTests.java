package com.example.isabe;

import app.IsaBeApplication;
import app.center.model.Term;
import app.center.service.ITermService;
import app.center.service.TermService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TermService.class)
@ContextConfiguration(classes = {IsaBeApplication.class})
public class IsaBeApplicationTests {

	@Autowired
	ITermService termService;

	@BeforeClass
	public static void setUp() {
		System.out.println("-----> SETUP <-----");
	}

	@Test(expected = ObjectOptimisticLockingFailureException.class)
	public void scheduling_existing_terms_at_the_same_time() throws Throwable {

		ExecutorService executor = Executors.newFixedThreadPool(2);
		Future<?> future1 = executor.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println("Startovan Thread 1");
				Term term = termService.findOne(12);
				try { Thread.sleep(3000); } catch (InterruptedException e) {}
				termService.schedule(term,6);
			}
		});
		executor.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println("Startovan Thread 2");
				Term term = termService.findOne(12);
				termService.schedule(term,6);
			}
		});
		try {
			future1.get();
		} catch (ExecutionException e) {
			System.out.println("Exception from thread " + e.getCause().getClass());
			throw e.getCause();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executor.shutdown();

	}

}
