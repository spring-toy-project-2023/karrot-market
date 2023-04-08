package com.karrot.domain.sample;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class SampleControllerTest {
    @Autowired
    SampleRepository sampleRepository;

    @Test
    public void 샘플하나_저장및조회() {
        //given
        SampleVO request = new SampleVO();
        request.setName("달력");
        request.setPrice("1000");
        sampleRepository.save(request);

        //when
        Optional<SampleVO> response = sampleRepository.findById(request.getId());

        //then
        Assert.assertEquals(request, response.get());

        System.out.println("============================================");
        System.out.println("id : " + response.get().getId());
        System.out.println("name : " + response.get().getName());
        System.out.println("price : " + response.get().getPrice());
        System.out.println("============================================");
    }
}