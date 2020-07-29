package edu.hm.hafner.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests the class {@link TreeStringBuilder}.
 *
 * @author Kohsuke Kawaguchi
 * @author Ullrich Hafner
 */
class TreeStringBuilderTest {
    @Test
    void shouldThrowAssertionErrorIfLabelIsEmpty() {
        assertThatThrownBy(() -> new TreeString(new TreeString(), "")).isInstanceOf(AssertionError.class);
    }

    /**
     * Pseudo random (but deterministic) test.
     */
    @Test
    void shouldCreateRandomTreeStrings() {
        String[] dictionary = {"aa", "b", "aba", "ba"};
        TreeStringBuilder builder = new TreeStringBuilder();

        Random random = new Random(0);

        List<String> a = new ArrayList<>();
        List<TreeString> o = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            StringBuilder b = new StringBuilder();
            for (int j = 0; j < random.nextInt(10) + 3; j++) {
                b.append(dictionary[random.nextInt(4)]);
            }
            String s = b.toString();

            a.add(s);

            TreeString p = builder.intern(s);
            assertThat(p).hasToString(s);
            o.add(p);
        }

        // make sure values are still all intact
        for (int i = 0; i < a.size(); i++) {
            assertThat(o.get(i)).hasToString(a.get(i));
        }

        builder.dedup();

        // verify one more time
        for (int i = 0; i < a.size(); i++) {
            assertThat(o.get(i)).hasToString(a.get(i));
        }
    }
}
