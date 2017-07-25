package com.luxoft.logeek;

import com.luxoft.logeek.entity.Child;
import com.luxoft.logeek.entity.Parent;
import com.luxoft.logeek.entity.Pupil;
import com.luxoft.logeek.repository.ChildRepository;
import com.luxoft.logeek.repository.ParentRepository;
import com.luxoft.logeek.repository.PupilRepository;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@TestExecutionListeners(value = {
		DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class
})
public abstract class TestBase {
	@PersistenceContext
	protected EntityManager em;
	@Autowired
	protected PupilRepository pupilRepository;
	@Autowired
	protected ChildRepository childRepository;
	@Autowired
	private ParentRepository parentRepository;

	protected Random random;

	public void setUp() throws Exception {
		initRandom();

		List<Pupil> pupils = Arrays.asList(
				new Pupil(1),
				new Pupil(2),
				new Pupil(3),
				new Pupil(4),
				new Pupil(5),
				new Pupil(6)
		);
		pupilRepository.save(pupils);
		pupilRepository.flush();

		Parent papa = new Parent("папа");
		Parent mama = new Parent("мама");

		Child child1 = new Child(papa);
		Child child2 = new Child(papa);
		Child child3 = new Child(papa);
		Child child4 = new Child(papa);

		Child child5 = new Child(mama);
		Child child6 = new Child(mama);

		childRepository.save(Arrays.asList(child1, child2, child3, child4, child5, child6));
		childRepository.flush();

		em.clear();
	}

	protected void initRandom() {
		random = new Random(System.nanoTime());
	}

	@After
	public void tearDown() throws Exception {
		pupilRepository.deleteAllInBatch();
		childRepository.deleteAllInBatch();
		parentRepository.deleteAllInBatch();
	}

	@BeforeTransaction
	public void beforeTransaction() {
		System.out.println("transaction begins");
	}

	@AfterTransaction
	public void afterTransaction() {
		System.out.println("transaction is over");
	}
}
