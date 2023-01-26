package com.example.isabe;

import app.IsaBeApplication;
import app.center.model.Term;
import app.center.service.ICenterService;
import app.center.service.ITermService;
import app.center.service.TermService;
import app.person.service.IPersonService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.concurrent.*;

import static org.springframework.test.util.AssertionErrors.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TermService.class)
@ContextConfiguration(classes = {IsaBeApplication.class})
public class IsaBeApplicationTests {

	@Autowired
	ITermService termService;
	@Autowired
	IPersonService personService;

	@Autowired
	ICenterService centerService;

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
	@Test()
	public void generateTerm() throws Throwable {

		ExecutorService executor = Executors.newFixedThreadPool(2);
		List<Term> terms = termService.getAll();

		Future<?> future1 = executor.submit(new Runnable() {
			public void run() {
				System.out.println("[Thread 1]Startovan");
				//try{Thread.sleep(3000);}catch(InterruptedException e){}
				System.out.println("[Thread 1]Kreiram termin");
				Term term = termService.create(new Term(LocalDateTime.of(2023,
						Month.JANUARY, 26, 14, 19, 10), 20, personService.findOne(1), centerService.findOne(1), 20));
				System.out.println("[Thread 1]Zavrsavam transakciju");
			}
		});
		executor.submit(new Runnable() {
			@Override
			public void run() {

				try{Thread.sleep(150);}catch(InterruptedException e){}
				System.out.println("[Thread 2]Kreiram termin");
				Term term = termService.create(new Term(LocalDateTime.of(2023,
						Month.JANUARY, 26, 14, 19, 10), 20, personService.findOne(4), centerService.findOne(1), 20));
				System.out.println("[Thread 2]Zavrsavam transakciju");
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
		List<Term> termList = termService.getAll();
		assertEquals("Not equal",terms.size() + 1,termList.size());

		executor.shutdown();
	};

	@Test()
	public void Same_medicalStaff_At_two_terms() throws Throwable {

		ExecutorService executor = Executors.newFixedThreadPool(2);
		List<Term> terms = termService.getAll();

		Future<?> future1 = executor.submit(new Runnable() {
			public void run() {
				System.out.println("[Thread 1]Prvi tred poceo");
				//try{Thread.sleep(3000);}catch(InterruptedException e){}
				System.out.println("[Thread 1]Kreiranje termin");
				Term term = termService.create(new Term(LocalDateTime.of(2023,
						Month.FEBRUARY, 15, 15, 35, 00), 20, personService.findOne(1), centerService.findOne(1), 30));
				System.out.println("[Thread 1]Zavrsavam transakciju");
			}
		});
		executor.submit(new Runnable() {
			@Override
			public void run() {

				try{Thread.sleep(150);}catch(InterruptedException e){}
				System.out.println("[Thread 2]Kreiram termin");
				Term term = termService.create(new Term(LocalDateTime.of(2023,
						Month.FEBRUARY, 15, 15, 35, 00), 20, personService.findOne(1), centerService.findOne(1), 50));
				System.out.println("[Thread 2]Zavrsavam transakciju");
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
		List<Term> termList = termService.getAll();
		assertEquals("Not equal",terms.size() + 1,termList.size());

		executor.shutdown();
	};


	//termService.create(createTermDTO.MapToModel(medicalStaffService.findOneByPersonId(createTermDTO.getManagerId()).getWorkingCenter(),personService.findOne(createTermDTO.getMedicalStaffId())));

}
