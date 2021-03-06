package com.luxoft.logeek;

import com.luxoft.logeek.entity.Parent;
import org.junit.Test;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@Sql("/DeleteChildTest.sql")
public class DeleteChildTest extends TestBase {

  private Long papaId = 1L;

  @Test
  public void deleteAllInBatch_expectParentIsNotDeleted() {
    childRepository.deleteAllInBatch();

    Parent papa = parentRepository.findById(papaId).orElse(null);

    assertNotNull(papa);
  }

  @Test
  public void deleteAll_expectParentIsDeleted() {
    childRepository.deleteAll();//проверить, почему в лог записан update

    Parent papa = parentRepository.findById(papaId).orElse(null);

    assertNull(papa);
  }

}
