package com.projeto.algasensors.device_management;

import com.projeto.algasensors.device_management.common.IdGenerator;
import io.hypersistence.tsid.TSID;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class TSIDTest {

    @Test
    void shouldGenerateTSID() {
        TSID tsid = IdGenerator.generateTSID();
        Assertions.assertThat(tsid.getInstant())
                .isCloseTo(Instant.now(), Assertions.within(1, ChronoUnit.MINUTES));
    }
}
