package com.helospark.tomcatmanagerhoneypot.service.badips.helper;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BadIpsIsIpInWhitelistPredicateTest {
    private BadIpsIsIpInWhitelistPredicate underTest;

    @Test
    public void testSimpleMatchingIps() {
        // GIVEN
        underTest = new BadIpsIsIpInWhitelistPredicate(new String[] { "127.0.0.1", "192.168.0.1" });

        // WHEN
        boolean result = underTest.test("127.0.0.1");

        // THEN
        assertTrue(result);
    }

    @Test
    public void testSimpleMatchingIps2() {
        // GIVEN
        underTest = new BadIpsIsIpInWhitelistPredicate(new String[] { "127.0.0.1", "192.168.0.1" });

        // WHEN
        boolean result = underTest.test("192.168.0.1");

        // THEN
        assertTrue(result);
    }

    @Test
    public void testNonMatchingIp() {
        // GIVEN
        underTest = new BadIpsIsIpInWhitelistPredicate(new String[] { "127.0.0.1", "192.168.0.1" });

        // WHEN
        boolean result = underTest.test("8.8.8.8");

        // THEN
        assertFalse(result);
    }

    @Test
    public void testEmptyArray() {
        // GIVEN
        underTest = new BadIpsIsIpInWhitelistPredicate(new String[0]);

        // WHEN
        boolean result = underTest.test("8.8.8.8");

        // THEN
        assertFalse(result);
    }

    @Test
    public void testRange() {
        // GIVEN
        underTest = new BadIpsIsIpInWhitelistPredicate(new String[] { "192.168.0.0/16" });

        // WHEN
        boolean result = underTest.test("192.168.0.33");

        // THEN
        assertTrue(result);
    }

    @Test
    public void testRange2() {
        // GIVEN
        underTest = new BadIpsIsIpInWhitelistPredicate(new String[] { "192.168.0.0/16" });

        // WHEN
        boolean result = underTest.test("192.169.0.1");

        // THEN
        assertFalse(result);
    }

    @Test
    public void testFull() {
        // GIVEN
        underTest = new BadIpsIsIpInWhitelistPredicate(
                new String[] { "127.0.0.1", "10.0.0.0/8", "172.16.0.0/12", "192.168.0.0/16" });

        // WHEN
        boolean result = underTest.test("8.8.8.8");

        // THEN
        assertFalse(result);
    }
}
