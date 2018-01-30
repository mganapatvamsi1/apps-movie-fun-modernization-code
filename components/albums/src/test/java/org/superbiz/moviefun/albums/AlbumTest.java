package org.superbiz.moviefun.albums;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

public class AlbumTest {

    @Test
    public void testIsEquivalent() {
        Album persisted = new Album("Radiohead", "OK Computer", 1997, 8);
        persisted.setId(10);

        Album sameFromCsv = new Album("Radiohead", "OK Computer", 1997, 9);
        Assert.assertThat(persisted.isEquivalent(sameFromCsv), Is.is(true));

        Album otherFromCsv = new Album("Radiohead", "Kid A", 2000, 9);
        Assert.assertThat(persisted.isEquivalent(otherFromCsv), Is.is(false));
    }
}
